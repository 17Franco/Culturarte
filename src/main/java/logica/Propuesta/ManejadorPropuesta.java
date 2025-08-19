/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.Propuesta;

import java.util.HashMap;
import java.util.Map;
import logica.Propuesta.Propuesta;
import logica.DTO.DTOPropuesta;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Calendar;
import logica.DTO.DTFecha;
import java.util.Date;


public class ManejadorPropuesta {
    private Map<String,Propuesta> propuestasp;
    
    private static ManejadorPropuesta instancia = null;

    private ManejadorPropuesta() {
        propuestasp = new HashMap<String, Propuesta>();
    }
    
    public static ManejadorPropuesta getinstance() {
        if (instancia == null)
            instancia = new ManejadorPropuesta();
        return instancia;
    }
    public void nuevaPropuesta(Propuesta p) {
        if (p != null) 
        {
            propuestasp.put(p.getTitulo(), p);
        }
    }
        public boolean existeProp(String Titulo){
        Propuesta prop = propuestasp.get(Titulo); // devuelve null si no existe
        if (prop!= null) {
            return true;
        } else {
            return false;
        }
        
        

    }
        
        public Set<DTOPropuesta> obtenerPropuestas(String Estado)
        {
            //La variable Estado permite elegir entre obtener un set por estado o todos los que haya. 
            //Si pones "" te manda todas las propuestas.
            
            Set<DTOPropuesta> temp = new HashSet<>();
            
            Iterator <Map.Entry  <String, Propuesta> > ct;  //Se crea iterador
            ct = propuestasp.entrySet().iterator();         //Se configura tipo de iterator

            while (ct.hasNext()) 
            {
                Map.Entry <String, Propuesta> entry = ct.next();    //Pasa al siguiente
                Propuesta punteroV = entry.getValue();

                DTOPropuesta almacenTemp = new DTOPropuesta();
                
                if(Estado.isEmpty())    //Si no se especifica, se agregan todos.
                {
                    almacenTemp.extraerDatosPropuesta(punteroV);
                    temp.add(almacenTemp);
                }
                else    //Si se especifica...
                {
                    if(punteroV.get)
                    {
                        almacenTemp.extraerDatosPropuesta(punteroV);
                        temp.add(almacenTemp);
                    }
                }
            }
            
            
            
            return temp;
        }
        
        
}
