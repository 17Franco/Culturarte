
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
import logica.Propuesta.Registro_Estado;
import logica.Usuario.Colaborador;
import logica.Usuario.Proponente;
import logica.Usuario.Usuario;
import logica._enum.Estado;



public class ManejadorUsuario {
    private Map<String,Usuario> usuarios;
    
    private static ManejadorUsuario instancia = null;
    private EntityManager em;

        
    private ManejadorUsuario() {

        usuarios = new HashMap<String, Usuario>();
        
       /* LocalDate a = LocalDate.of(2024,01,30);
        LocalDate b = LocalDate.of(2023,02,2);
        //Borrar luego
        Proponente p1 = new Proponente("Calle Falsa 123", "Bio", "www.web.com",
                "nick1", "Juan", "Pérez", "juan@mail.com",
                a, "img1.png");
        Colaborador c1 = new Colaborador("nick2", "Ana", "López", "ana@mail.com",
                b, "img2.png");
        usuarios.put(p1.getNickname(), p1);
        usuarios.put(c1.getNickname(), c1);*/
    }
    
    public static ManejadorUsuario getInstance() {
        if (instancia == null)
            instancia = new ManejadorUsuario();
        return instancia;
    }
    
    public void addProponente(DTOProponente u){
        Proponente p=new Proponente(u); //creo proponente
        //usuarios.put(u.getNickname(),p);
        
        em= PersistenciaManager.getEntityManager();//instancia del manegador de la persistencia 
        EntityTransaction t = em.getTransaction(); // intancia de una transaccion nesesario si se hace alta baja y modificado 
        try{
            t.begin(); // aca inicio transaccion
            em.persist(p);// persisto los datos 
            t.commit();// los aseguro  
        }
        catch(Exception e){
            t.rollback();    // en caso de error hace rollback
        }
        em.close();// cierro el manejador 

    }
   
     public void addColaborador(DTOColaborador u){
        Colaborador p=new Colaborador(u);
        em= PersistenciaManager.getEntityManager();
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
    
    }
     
    public boolean existe(String nick){

        em= PersistenciaManager.getEntityManager();
      try{
            Usuario u = em.find(Usuario.class, nick);
            return  (u != null);
      }finally{
            em.close(); 
      }
    }
    
    public Usuario getUsuario(String nick) {
         em = PersistenciaManager.getEntityManager();
         try {
             return em.find(Usuario.class, nick);
         } finally {
             em.close(); // se ejecuta SIEMPRE, haya error o no
         }
     }
    public Map<String,DTOPropuesta> getPropuestasCreadas(DTOProponente proponente){
      
        em = PersistenciaManager.getEntityManager();
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
         }
      
    }
    public boolean emailUsado(String email){
        em = PersistenciaManager.getEntityManager();
         
         try{
             String norm = email == null ? null : email.trim().toLowerCase();
             Long count = em.createQuery(
            "SELECT COUNT(u) FROM Usuario u WHERE u.email = :email", Long.class).setParameter("email", norm).getSingleResult();
            return count > 0;
         }finally{
            em.close();
         }
    }
    
    //DEVUELVO TODOS LOS USUARIOS COMO DTO A CONTROLLER
    public Map<String, DTOUsuario> getUsuarios() {
        
         Map<String, DTOUsuario> resu = new HashMap<>();
       em = PersistenciaManager.getEntityManager();
         
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
         }
    }
    
    // ACA DEVUELVO  set de DTOColaborador
    public Set<DTOColaborador> listaColaboradores(){
        Set<DTOColaborador> results = new HashSet<DTOColaborador>();
        
        em = PersistenciaManager.getEntityManager();
        try{
             List<Colaborador> listaColaborador = em.createQuery("SELECT u FROM Usuario u WHERE u INSTANCE OF Colaborador", Colaborador.class).getResultList();
             for(Colaborador c: listaColaborador){
                     DTOColaborador col=new DTOColaborador(c);
                     results.add(col);
                 
             }
              return results;
        }finally{
            em.close();
        }
    }
    
    
    public boolean existeColaboracion(String colaborador, String titulo){
        em = PersistenciaManager.getEntityManager();
         try{
              //String norm = email == null ? null : email.trim().toLowerCase();
             Long count = em.createQuery(
            "SELECT COUNT(c) FROM Colaboracion c WHERE c.colaborador.nickname = :colaborador and c.propuesta.Titulo :titulo", Long.class).setParameter("colaborador", colaborador).setParameter("propuesta", titulo).getSingleResult();
            return count > 0;
         }finally{
            em.close();
         }
    }
    
    public List<DTOColaboracion> getDTOColaboraciones(String nick){
         //List<DTOColaboracion> resu=new ArrayList<>();
        em = PersistenciaManager.getEntityManager();
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
        }
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
        
        public boolean seguirUsr(String nick1, String nick2){
            em = PersistenciaManager.getEntityManager();
            try{
                  
                  Usuario usuario1 = em.find(Usuario.class,nick1);
                  Usuario usuario2 = em.find(Usuario.class,nick2);

                    if (usuario1 == null || usuario2 == null) return false;
                    if (nick1.equals(nick2)) return false;
                    if (usuario1.getUsuarioSeguido().containsKey(nick2)) return false;
                    
                    EntityTransaction t = em.getTransaction();
                    t.begin();
                    usuario1.seguir(usuario2);
                    //em.merge(usuario2); // sincriniza los cambios de un objeto  se usa cuando el objeto no es manage por el entitymanager 
                    t.commit();
                    return true;
            
            }catch(Exception e){
                return false;
            }finally{
                em.close();
            }
          
        }
        
        public List<String> listaSeguidos(String nick){
            em = PersistenciaManager.getEntityManager();
            try{
                List<String> aux = new ArrayList<>();
                Usuario usuario = em.find(Usuario.class,nick);
                if (usuario != null && usuario.getUsuarioSeguido() != null) {
                   for(Usuario u:usuario.getUsuarioSeguido().values()){
                       aux.add(u.getNickname());
                   }
                }
                return aux;
            }finally{
                em.close();
            }
            
        }
        
        public Set<DTOPropuesta> getPropuestasCreadasPorProponente(String nick){
                em = PersistenciaManager.getEntityManager();
                   Proponente  p=em.find(Proponente.class,nick);
                   Set<DTOPropuesta> resu=new HashSet<>();
                try{
                    DTOProponente dtoProp=new DTOProponente(p);
                    for(Propuesta prop: p.getPropCreadas().values()){
                        Estado aux=prop.getHistorialEstados().getFirst().getEstado();
                        DTOPropuesta dtoP=new DTOPropuesta(prop,dtoProp);
                        dtoP.setEstadoAct(aux);
                        
                        resu.add(dtoP);
                    }
                    return resu;
                }finally{
                    em.close();
                }
        
        }
}
