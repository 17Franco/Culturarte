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
    
    private ManejadorRegistros() 
    {
        
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
        
        regUpdater();   //Actualizo borrando registros viejos
        
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
    
    public boolean agregarRegistroAccesoWeb(DTORegistrosAccesoWeb input)
    {
        
        boolean pass = false;
        
        regSpaceManager(); //Si no hay espacio, le hace uno nuevo borrando el registro más viejo
        
        if(input == null)
        {
            return pass;
        }
        
        //Formateo navegador y SO:
        
        String navegadorWeb = formateadorNavegador_SO(input.getNavegadorWeb(),1);
        String SO = formateadorNavegador_SO(input.getSO(),2);

        dbManager = PersistenciaManager.getEntityManager();
        EntityTransaction transaccionActual = dbManager.getTransaction();

        transaccionActual.begin();

        RegistrosAccesoWeb reg = new RegistrosAccesoWeb(input.getIp(), navegadorWeb, SO, input.getUrl(), LocalDate.now());
        
            try
            {
                dbManager.persist(reg);
                transaccionActual.commit();

                pass = true;    //Aviso en bool que fué todo asignado correctamente o a la espera de posible exception.
            }
            catch(Exception wtf)
            {
                if(transaccionActual.isActive())    
                {
                    transaccionActual.rollback();
                }
                
                pass = false;   //Almaceno en bool que hubo un error inesperado al ingresar los datos...  
            }
            finally
            {
                dbManager.close(); 
            }  
            
            return pass;
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
    
    public void regUpdater() 
    {
        
        dbManager = PersistenciaManager.getEntityManager();
        EntityTransaction transaccionActual = dbManager.getTransaction();
        
        try 
        {
            transaccionActual.begin();

            LocalDate hace30Dias = LocalDate.now().minusDays(30);

            dbManager.createQuery("delete from RegistrosAccesoWeb r where r.fechaRegistro < :fechaLimite").setParameter("fechaLimite", hace30Dias).executeUpdate();

            dbManager.getTransaction().commit();

        } 
        catch (Exception e) 
        {
            if (dbManager.getTransaction().isActive()) 
            {
                dbManager.getTransaction().rollback();
            }
               
        } 
        finally 
        {
            dbManager.close();
        }
    }
    
    public void regSpaceManager() 
    {
        dbManager = PersistenciaManager.getEntityManager();
        EntityTransaction transaccionActual = dbManager.getTransaction();

        try 
        {
            transaccionActual.begin();

            int totalRegistros = (int) dbManager.createQuery("SELECT COUNT(r) FROM RegistrosAccesoWeb r").getSingleResult();

            if (totalRegistros >= 10000)    //Si hay más de 10k, borra el más viejo para que entre uno nuevo. 
            {
                Integer old = (Integer) dbManager.createQuery("SELECT r.id FROM RegistrosAccesoWeb r ORDER BY r.fechaRegistro ASC").setMaxResults(1).getSingleResult();

                dbManager.createQuery("DELETE FROM RegistrosAccesoWeb r WHERE r.id = :id").setParameter("id", old).executeUpdate();
            }

            dbManager.getTransaction().commit();
        } 
        catch (Exception e) 
        {
            if (dbManager.getTransaction().isActive()) 
            {
                dbManager.getTransaction().rollback();
            }
        } 
        finally 
        {
            dbManager.close();
        }
    }
}

