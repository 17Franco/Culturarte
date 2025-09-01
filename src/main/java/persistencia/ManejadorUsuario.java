
package persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import logica.Colaboracion.Colaboracion;
import logica.DTO.DTOColaboracion;
import logica.DTO.DTOColaborador;
import logica.DTO.DTOProponente;
import logica.DTO.DTOPropuesta;
import logica.DTO.DTOUsuario;
import logica.Propuesta.Propuesta;
import logica.Usuario.Colaborador;
import logica.Usuario.Proponente;
import logica.Usuario.Usuario;



public class ManejadorUsuario {
    private Map<String,Usuario> usuarios;
    
    private static ManejadorUsuario instancia = null;
    private EntityManager em;

        
    private ManejadorUsuario() {

        usuarios = new HashMap<String, Usuario>();
        
        LocalDate a = LocalDate.of(2024,01,30);
        LocalDate b = LocalDate.of(2023,02,2);
        //Borrar luego
        Proponente p1 = new Proponente("Calle Falsa 123", "Bio", "www.web.com",
                "nick1", "Juan", "Pérez", "juan@mail.com",
                a, "img1.png");
        Colaborador c1 = new Colaborador("nick2", "Ana", "López", "ana@mail.com",
                b, "img2.png");
        usuarios.put(p1.getNickname(), p1);
        usuarios.put(c1.getNickname(), c1);
    }
    
    public static ManejadorUsuario getInstance() {
        if (instancia == null)
            instancia = new ManejadorUsuario();
        return instancia;
    }
    
    public void addProponente(DTOProponente u){
        Proponente p=new Proponente(u);
        usuarios.put(u.getNickname(),p);
        
     /*   em= PersistenciaManager.getEntityManager();
        EntityTransaction t = em.getTransaction();
        try{
            t.begin();
            em.persist(p);
            t.commit();
            
        }
        catch(Exception e){
            t.rollback();    
        }
        em.close();
*/
    }
   
     public void addColaborador(DTOColaborador u){
        Colaborador p=new Colaborador(u);
        usuarios.put(u.getNickname(),p);
        
      /*  em= PersistenciaManager.getEntityManager();
        EntityTransaction t = em.getTransaction();
        try{
            t.begin();
            em.persist(p);
            t.commit();
            
        }
        catch(Exception e){
            t.rollback();    
        }
        em.close();
    */
    }
     
    public boolean existe(String nick){

     /*   em= PersistenciaManager.getEntityManager();
      try{
            Usuario u = em.find(Usuario.class, nick);
            return  (u != null);
      }finally{
            em.close(); 
      }*/
      return usuarios.containsKey(nick);
    }
    
    public Usuario buscador(String nick) {
       /*  em = PersistenciaManager.getEntityManager();
         try {
             return em.find(Usuario.class, nick);//esto devuelve la info basica los listas como seguidor favorito propcreadas no se cargan 
         } finally {
             em.close(); // se ejecuta SIEMPRE, haya error o no
         }*/
       return usuarios.get(nick);
     }
    public Map<String,DTOPropuesta> getPropCreadas(DTOProponente proponente){
        Map<String,DTOPropuesta> resu=new HashMap<>();
        Proponente p=(Proponente) usuarios.get(proponente.getNickname());
        for(Propuesta prop: p.getPropCreadas().values()){
                DTOPropuesta propuesta=new DTOPropuesta(prop,proponente);
                resu.put(prop.getTitulo(), propuesta);
        }
        return resu;
      /*  em = PersistenciaManager.getEntityManager();
        String nick=proponente.getNickname();
        Map<String,DTOPropuesta> resu=new HashMap<>();
         try {
             List<Propuesta> listaPropuesta = em.createQuery("SELECT p FROM Propuesta p WHERE p.usr.nickname=:nick", Propuesta.class).setParameter("nick", nick).getResultList();
            for(Propuesta p: listaPropuesta){
                DTOPropuesta prop=new DTOPropuesta(p,proponente);
                resu.put(prop.getTitulo(), prop);
            }
             return resu;
         } finally {
             em.close(); // se ejecuta SIEMPRE, haya error o no
         }*/
      
    }
    public boolean emailUsado(String email){
      /*   em = PersistenciaManager.getEntityManager();
         
         try{
             String norm = email == null ? null : email.trim().toLowerCase();
             Long count = em.createQuery(
            "SELECT COUNT(u) FROM Usuario u WHERE u.email = :email", Long.class).setParameter("email", norm).getSingleResult();
            return count > 0;
         }finally{
            em.close();
         }*/
       for (Usuario u : usuarios.values()) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return true; // email ya está registrado
            }
        }
        return false;
    }
    
    //DEVUELVO TODOS LOS USUARIOS COMO DTO A CONTROLLER
    public Map<String, DTOUsuario> getUsuarios() {
        
         Map<String, DTOUsuario> resu = new HashMap<>();

        // Recorremos todos los valores del Map de usuarios
        for (Usuario u: usuarios.values()) {
            if(u instanceof Colaborador){
                    DTOColaborador c= new DTOColaborador( (Colaborador) u);
                     resu.put(c.getNickname(),c);
                }else{
                    DTOProponente p=new DTOProponente((Proponente) u);
                     resu.put(p.getNickname(), p);
                }
           
        }
        return resu;
       /* em = PersistenciaManager.getEntityManager();
         
         try{
           // Traigo todos los usuarios en una lista
            List<Usuario> lista = em.createQuery("FROM Usuario", Usuario.class).getResultList();
            Map<String,DTOUsuario> mapDtoUsuario=new HashMap<>();
            for(Usuario u: lista){
                if(u instanceof Colaborador){
                    DTOColaborador c= new DTOColaborador( (Colaborador) u);
                    mapDtoUsuario.put(c.getNickname(), c);
                }else{
                    DTOProponente p=new DTOProponente((Proponente) u);
                    mapDtoUsuario.put(p.getNickname(), p);
                }
            }
            return mapDtoUsuario;
         }finally{
            em.close();
         }*/
    }
    
    // ACA DEVUELVO  set de DTOColaborador
    public Set<DTOColaborador> listColaboradores(){
        Set<DTOColaborador> results = new HashSet<DTOColaborador>();
        
       /* em = PersistenciaManager.getEntityManager();
        try{
             List<Colaborador> listaColaborador = em.createQuery("SELECT u FROM Usuario u WHERE u INSTANCE OF Colaborador", Colaborador.class).getResultList();
             for(Colaborador c: listaColaborador){
                     DTOColaborador col=new DTOColaborador(c);
                     results.add(col);
                 
             }
              return results;
        }finally{
            em.close();
        }*/
        for (Usuario u : usuarios.values()) {
            if (u instanceof Colaborador) {
                DTOColaborador dtoColaborador = new DTOColaborador((Colaborador)u);// si borro , !u.isColaborador() queda bien
                results.add(dtoColaborador);
            }
        }
        return results;
    }
    
    
    public boolean existeColaboracion(String colaborador, String titulo){
       /*  em = PersistenciaManager.getEntityManager();
         try{
              //String norm = email == null ? null : email.trim().toLowerCase();
             Long count = em.createQuery(
            "SELECT COUNT(c) FROM Colaboracion c WHERE c.colaborador.nickname = :colaborador and c.propuesta.Titulo :titulo", Long.class).setParameter("colaborador", colaborador).setParameter("propuesta", titulo).getSingleResult();
            return count > 0;
         }finally{
            em.close();
         }*/
       Colaborador c=(Colaborador) buscador(colaborador);
        for (Colaboracion colab : c.getColaboraciones()) {
            if (colab.getPropuesta().getTitulo().equals(titulo)) {
                return true; 
            }
        }
        return false; 
    }
    
    public List<DTOColaboracion> getDTOColaboraciones(String nick){
         List<DTOColaboracion> resu=new ArrayList<>();
     /*   em = PersistenciaManager.getEntityManager();
        List<DTOColaboracion> resu=new ArrayList<>();
        try{
            Colaborador c=em.find(Colaborador.class, nick);
             for(Colaboracion colab: c.getColaboraciones()){
                DTOColaboracion DTOColab = new DTOColaboracion(colab);
                resu.add(DTOColab);
            }
             return resu;
        }finally{
            em.close();
        }*/
       
        Colaborador c=(Colaborador) buscador(nick);
        
                
        for(Colaboracion colab: c.getColaboraciones()){
            DTOColaboracion DTOColab = new DTOColaboracion(colab);
            resu.add(DTOColab);
        }
            return resu;
        }
        
        public void eliminarColaboracion(String nick,String propuesta){
            Colaborador c=(Colaborador) usuarios.get(nick);
            Colaboracion aux=null;
             for(Colaboracion col: c.getColaboraciones()){
                if(col.getPropuesta().getTitulo().equals(propuesta)){
                aux=col;
                break;
            }
            }
            if(aux!=null){
                c.getColaboraciones().remove(aux);
            }

        }
}
