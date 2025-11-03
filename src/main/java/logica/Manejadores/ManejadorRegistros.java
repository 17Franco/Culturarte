/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.Manejadores;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
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
        
        if(input == null)
        {
            return pass;
        }

        dbManager = PersistenciaManager.getEntityManager();
        EntityTransaction transaccionActual = dbManager.getTransaction();

        transaccionActual.begin();

        RegistrosAccesoWeb reg = new RegistrosAccesoWeb(input.getIp(), input.getNavegadorWebSO(), input.getUrl());
        
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
}
