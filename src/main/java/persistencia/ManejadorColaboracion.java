package persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import logica.Colaboracion.Colaboracion;
import logica.DTO.DTOColaboracion;
import logica.Propuesta.Propuesta;
import logica.Usuario.Colaborador;


public class ManejadorColaboracion {
    private ManejadorPropuesta mPropuesta=ManejadorPropuesta.getinstance();
    private ManejadorUsuario mUsuario= ManejadorUsuario.getInstance();
    private static ManejadorColaboracion instancia = null;
    
    private EntityManager em;
    
    public static ManejadorColaboracion getInstance() 
    {
        if (instancia == null){
            instancia = new ManejadorColaboracion();
        }   
        return instancia;
    }
    
    public Set< DTOColaboracion> getColaboraciones(){
        // iniializo la variable para retornar
        Set<DTOColaboracion> results=new HashSet<>();
       
        // obtengo la instancia del EM
        em= PersistenciaManager.getEntityManager();//instancia del manegador de la persistencia 
        try{
            // Traigo de la base de datos todas las colaboraciones
            List<Colaboracion> listaColaboraciones = em
                    .createQuery("SELECT c FROM Colaboracion c", Colaboracion.class)
                    .getResultList();
            for(Colaboracion colab: listaColaboraciones){
                // Convierto cada colaboracion en un dto (abreviado)
                DTOColaboracion dtoColab=new DTOColaboracion(
                        colab.getTipoRetorno(),
                        colab.getMonto(),
                        colab.getColaborador().getNickname(),
                        colab.getPropuesta().getTitulo(),
                        colab.getCreado()
                );
                dtoColab.setId(colab.getId());
                // Agrego cada dto a la lista de resultados
                results.add( dtoColab);
            }
        }
        finally{
            // Cierro el EM en cualquier caso
            em.close();// cierro el manejador 
        }
        
        return results;
    }
    
    public void addColaboracion(DTOColaboracion colaboracion){
        //AlmacenColaboraciones.add();
        Propuesta p= mPropuesta.getPropuesta(colaboracion.getPropuesta());
        Colaborador c= (Colaborador) mUsuario.getUsuario(colaboracion.getColaborador());
        Colaboracion colab= new Colaboracion(colaboracion,c,p);
        
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
        em= PersistenciaManager.getEntityManager();//instancia del manegador de la persistencia 
        EntityTransaction t = em.getTransaction(); // intancia de una transaccion nesesario si se hace alta baja y modificado 
        try{
            t.begin(); // aca inicio transaccion
            em.persist(colab);// persisto los datos 
            t.commit();// los aseguro  
        }
        catch(Exception e){
            t.rollback();    // en caso de error hace rollback
        }
        em.close();// cierro el manejador 
    }
    
    public void deleteColaboracion(Long id){
        em= PersistenciaManager.getEntityManager();//instancia del manegador de la persistencia 
        EntityTransaction t = em.getTransaction(); // intancia de una transaccion nesesario si se hace alta baja y modificado 
        try{
            t.begin(); // aca inicio transaccion
            em.createQuery("DELETE FROM Colaboracion c WHERE c.id = :id")
                     .setParameter("id", id)
                     .executeUpdate();
            t.commit();
        }
        catch(Exception e){
            t.rollback();    // en caso de error hace rollback
        }
        em.close();// cierro el manejador 
    }
    
}