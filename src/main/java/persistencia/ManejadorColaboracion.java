
package persistencia;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import logica.Colaboracion.Colaboracion;
import logica.DTO.DTOColaboracion;
import logica.Propuesta.Propuesta;
import logica.Usuario.Colaborador;
import logica._enum.Estado;


public class ManejadorColaboracion {
    private Set<Colaboracion> AlmacenColaboraciones;
    private ManejadorPropuesta mPropuesta=ManejadorPropuesta.getinstance();
    private ManejadorUsuario mUsuario= ManejadorUsuario.getInstance();
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
        Set<DTOColaboracion> c=new HashSet<>();
        for(Colaboracion col: AlmacenColaboraciones ){
            DTOColaboracion dtoCol=new DTOColaboracion(col);
            c.add(dtoCol);
        }
          return c;
    }
    
    public void addColaboracion(DTOColaboracion colaboracion){
        
        //AlmacenColaboraciones.add();
        Propuesta p= mPropuesta.getPropuesta(colaboracion.getPropuesta());
        Colaborador c= (Colaborador) mUsuario.buscador(colaboracion.getColaborador());
       
        Colaboracion colab= new Colaboracion(colaboracion,c,p);
        c.setColaboraciones(colab);
        p.setColaboracion(colab);
        mPropuesta.actualizarEstado(p.getTitulo());
       /*if(p.getHistorialEstados().getFirst().getEstado().equals(Estado.PUBLICADA)){
            p.addEstHistorial(Estado.EN_FINANCIACION);
        }
        
        int recaudado=0;
        
        for(Colaboracion colabora: p.getAporte()){
            recaudado=recaudado+colabora.getMonto();
        }
        if(recaudado>=p.getMontoTotal()){
            p.addEstHistorial(Estado.FINANCIADA);
        }*/
        
        AlmacenColaboraciones.add(colab);
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
    
    public void deleteColaboracion(String nick, String propuesta){
        //Iterator<Colaboracion> it = AlmacenColaboraciones.iterator();
        Colaboracion aux=null;
        for (Colaboracion c:AlmacenColaboraciones ) {
            if (c.getColaborador().getNickname().equals(nick) && c.getPropuesta().getTitulo().equals(propuesta)){
                //Encontre el que estaba buscando, ahora tengo que borrarlo
                 aux=c;
                break;
            }
        }
        if(aux!=null){
            
            AlmacenColaboraciones.remove(aux);
            mPropuesta.eliminarColaboracion(nick,propuesta);
            mUsuario.eliminarColaboracion(nick,propuesta);
            mPropuesta.actualizarEstado(propuesta);//aca mando a actualizar estado al mPropuesta
            
        }
        
    }
    
}