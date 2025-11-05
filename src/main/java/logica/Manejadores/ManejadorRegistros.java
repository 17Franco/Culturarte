/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.Manejadores;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import logica.DTO.DTORegistrosAccesoWeb;
import logica.Registros.RegistrosAccesoWeb;
import persistencia.PersistenciaManager;

/**
 *
 * @author klaas
 */
public class ManejadorRegistros 
{
    private static ManejadorRegistros instancia = null; 
    private EntityManager dbManager;
    
    private List<DTORegistrosAccesoWeb> buffer; //Evito sobrecargar las llamadas a bases de datos cada vez que se produce una llegada de registros.
    private long tiempoAnterior;  //Almacen de tiempos
    int frecuencia = 2000;  //Esto es para la velocidad de actualización en la base de datos en milisegundos
    boolean verificarDatos = true;
    
    
    private ManejadorRegistros() 
    {
        buffer = new ArrayList<>();
        tiempoAnterior = System.currentTimeMillis();    //Almaceno milisegundos (reduce carga cpu)
    }
    
    public static ManejadorRegistros getInstance() 
    {
        if (instancia == null) 
        {
            instancia = new ManejadorRegistros();
        }
        return instancia;
    }
    
    public List<DTORegistrosAccesoWeb> obtenerRegistrosAccesoWeb()
    {
        List<DTORegistrosAccesoWeb> almacen = new ArrayList<>();
        
        if(verificarDatos == true) //Elimino entradas de más de un mes, reviso una sola vez por conexión
        {
            regUpdaterAsync();   //Más de 30 dias, se borran
            verificarDatos = false;     //Aseguro que solo sea una vez por conexión
        }
        
        dbManager = PersistenciaManager.getEntityManager(); //Se asigna base de datos
        
        try 
        {                     
            List<RegistrosAccesoWeb> datosImportadosDb = dbManager.createQuery("select regs from RegistrosAccesoWeb regs", RegistrosAccesoWeb.class).getResultList();

            for (RegistrosAccesoWeb ct : datosImportadosDb) 
            {
                DTORegistrosAccesoWeb temp = new DTORegistrosAccesoWeb(ct); //Se pasa a DTO por el constructor.
                almacen.add(temp);                  //Se almacena en la lista.
            }    
        } 
        finally 
        {
            dbManager.close();
        }
        
        return almacen;
    }
    
    public synchronized boolean agregarRegistroAccesoWeb(DTORegistrosAccesoWeb input)
    {
        boolean pass = false;
        
        if(verificarDatos == true) //Elimino entradas de más de un mes, reviso una sola vez por conexión
        {
            regUpdaterAsync();   //Más de 30 dias, se borran
            verificarDatos = false;     //Aseguro que solo sea una vez por conexión
        }
        
        if(input != null)   //Ingreso en el buffer
        {
           pass = agregarAlBufferRegistrosWeb(input);
        }
 
        if(pass == true && !(buffer.isEmpty()))
        {
            agregarRegistroAccesoWeb();
            return true;
        }

        return false;
    }
    
    public synchronized boolean agregarAlBufferRegistrosWeb(DTORegistrosAccesoWeb input)   //Synchro permite concurrencia
    {
        buffer.add(input);

        long ahora = System.currentTimeMillis();
        long tiempoTranscurrido = ahora - tiempoAnterior;

        if (tiempoTranscurrido >= frecuencia || buffer.size() >= 100)     //Almaceno en db cada 2 segundos (setear arriba)
        {
            tiempoAnterior = System.currentTimeMillis();
            return true;
        }
        
        return false;

    }
    
    private synchronized void agregarRegistroAccesoWeb() 
    {
        dbManager = PersistenciaManager.getEntityManager();
        EntityTransaction transaccion = dbManager.getTransaction();
        
        try 
        {
            transaccion.begin();
            
            regSpaceManager(dbManager);  //Abro espacio si hay 10000 registros.

            for (DTORegistrosAccesoWeb ct : buffer) 
            {
                String navegador = formateadorNavegador_SO(ct.getNavegadorWeb(), 1);
                String so = formateadorNavegador_SO(ct.getSO(), 2);

                RegistrosAccesoWeb reg = new RegistrosAccesoWeb(ct.getIp(), navegador, so, ct.getUrl(), LocalDate.now());

                dbManager.persist(reg);
            }

            transaccion.commit();
            buffer.clear(); //Limpio buffer
            

        } 
        catch (Exception e) 
        {
            if (transaccion.isActive()) 
            {
                transaccion.rollback();
            }
            
        } 
        finally 
        {
            dbManager.close();
        }
    }
    
    public String formateadorNavegador_SO(String input, int sel)
    {  
        //Sel = 1 navegadores
        //Sel = 2 SO.
        
        if(sel == 1)
        {
            if (input == null || input.isEmpty()) {return "Desconocido";}

            if (input.contains("Edg/")) return "Edge";
            if (input.contains("OPR/") || input.contains("Opera/")) return "Opera";
            if (input.contains("Chrome/")) return "Chrome";
            if (input.contains("Firefox/")) return "Firefox";
            if (input.contains("Safari/")) return "Safari";
            if (input.contains("MSIE") || input.contains("Trident/")) return "Internet Explorer";

            return "Ofuscado";
        }
        
        if (sel == 2)
        {
            if (input == null || input.isEmpty()) {return "Desconocido";}
            if (input.contains("Android")) {return "Android";}   
            if (input.contains("iPhone") || input.contains("iPad")) {return "iOS";}         
            if (input.contains("Windows NT")) {return "Windows";}        
            if (input.contains("Mac OS X")) {return "macOS";}         
            if (input.contains("Linux")) {return "Linux";}

            return "Ofuscado";
        }
        
        return "error";
    }
    
    public void regUpdaterAsync() 
    {
        new Thread(() ->   
        {
            LocalDate limite = LocalDate.now().minusDays(30);
            int batchSize = 500;

            while (true) 
            {
                EntityManager em = PersistenciaManager.getEntityManager();
                EntityTransaction transaccionActual = em.getTransaction();
                
                int deleted = 0;

                try 
                {
                    
                    List<Long> ids = em.createQuery("SELECT r.id FROM RegistrosAccesoWeb r WHERE r.fechaRegistro < :limite", Long.class).setParameter("limite", limite).setMaxResults(batchSize).getResultList();

                    if (ids.isEmpty()) 
                    {
                        break;
                    }

                    transaccionActual.begin();
                    
                    em.createQuery("DELETE FROM RegistrosAccesoWeb r WHERE r.id IN :ids").setParameter("ids", ids).executeUpdate();
                    
                    transaccionActual.commit();

                    deleted = ids.size();
                    
                } 
                catch (Exception e) 
                {
                    if (transaccionActual.isActive()) 
                    {
                        transaccionActual.rollback();
                    }
                    break;
                } 
                finally 
                {
                    em.close();
                }

                if (deleted < batchSize) 
                {
                    break;
                }
            }
            
        }).start();
    }
    
    public void regSpaceManager(EntityManager dbManager) 
    {
        int cantElementosEnBuffer = buffer.size();
            
        //Averiguo cantidad actual de datos
        Long totalRegistros = (Long) dbManager.createQuery("SELECT COUNT(r) FROM RegistrosAccesoWeb r").getSingleResult();
        
        if (totalRegistros >= 10000)    //Si hay más de 10k, borra los más viejos para que entren. 
        {
            List<Long> olds = (List<Long>) dbManager.createQuery("SELECT r.id FROM RegistrosAccesoWeb r ORDER BY r.fechaRegistro ASC").setMaxResults(cantElementosEnBuffer).getResultList();
            
            if (!olds.isEmpty()) 
            {
                dbManager.createQuery("DELETE FROM RegistrosAccesoWeb r WHERE r.id IN :ids").setParameter("ids", olds).executeUpdate();
            }
        }
    }
}

