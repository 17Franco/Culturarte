
package persistencia;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import logica.Colaboracion.Colaboracion;


public class ManejadorColaboracion {
    private Set<Colaboracion> AlmacenColaboraciones;
    
    private static ManejadorColaboracion instancia = null;
    
    public static ManejadorColaboracion getInstance() 
    {
        if (instancia == null){
            instancia = new ManejadorColaboracion();
            instancia.AlmacenColaboraciones = new HashSet<>();
        }   
        return instancia;
    }
    
    public Set< Colaboracion> getColaboraciones(){
        return AlmacenColaboraciones;
    }
    
    public void addColaboracion(Colaboracion colaboracion){
        
        AlmacenColaboraciones.add(colaboracion);
       //System.out.println("AlmacenColaboracion Tiene " + AlmacenColaboraciones.size());
    }
    
    public Set<Colaboracion> getColaboracionesDeColaborador(String nickname){
        Set<Colaboracion> results = new HashSet<>();
        Iterator<Colaboracion> it = AlmacenColaboraciones.iterator();
        for (Colaboracion colaboracion = it.next(); it.hasNext(); ) {
            if (colaboracion.getColaborador().getNickname().equals(nickname)){
                results.add(colaboracion);
            }
        }
        return results;
    }
    
    public void deleteColaboracion(Colaboracion seleccionado){
        Iterator<Colaboracion> it = AlmacenColaboraciones.iterator();
        for (Colaboracion guardado = it.next(); it.hasNext(); ) {
            if (seleccionado.equals(guardado)){
                //Encontre el que estaba buscando, ahora tengo que borrarlo
                AlmacenColaboraciones.remove(guardado);
            }
        }
    }
    
}