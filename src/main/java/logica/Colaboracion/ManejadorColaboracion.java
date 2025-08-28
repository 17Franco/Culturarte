
package logica.Colaboracion;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import logica.DTO.DTOColaboracion;


public class ManejadorColaboracion {
    private Set<DTOColaboracion> AlmacenColaboraciones;
    
    private static ManejadorColaboracion instancia = null;
    
    public static ManejadorColaboracion getInstance() 
    {
        if (instancia == null){
            instancia = new ManejadorColaboracion();
            instancia.AlmacenColaboraciones = new HashSet<>();
        }   
        return instancia;
    }
    
    public Set< DTOColaboracion> getColaboraciones(){
        return AlmacenColaboraciones;
    }
    
    public void addColaboracion(DTOColaboracion colaboracion){
        AlmacenColaboraciones.add(colaboracion);
        System.out.println("AlmacenColaboracion Tiene " + AlmacenColaboraciones.size());
    }
    
    public Set<DTOColaboracion> getColaboracionesDeColaborador(String nickname){
        Set<DTOColaboracion> results = new HashSet<>();
        Iterator<DTOColaboracion> it = AlmacenColaboraciones.iterator();
        for (DTOColaboracion colaboracion = it.next(); it.hasNext(); ) {
            if (colaboracion.getColaborador().getNickname().equals(nickname)){
                results.add(colaboracion);
            }
        }
        return results;
    }
    
    public void deleteColaboracion(DTOColaboracion seleccionado){
        Iterator<DTOColaboracion> it = AlmacenColaboraciones.iterator();
        for (DTOColaboracion guardado = it.next(); it.hasNext(); ) {
            if (seleccionado.equals(guardado)){
                //Encontre el que estaba buscando, ahora tengo que borrarlo
                AlmacenColaboraciones.remove(guardado);
            }
        }
    }
    
}