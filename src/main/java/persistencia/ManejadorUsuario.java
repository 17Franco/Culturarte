
package persistencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import logica.Colaboracion.Colaboracion;
import logica.DTO.DTFecha;
import logica.DTO.DTOColaboracion;
import logica.DTO.DTOColaborador;
import logica.DTO.DTOProponente;
import logica.Usuario.Colaborador;
import logica.Usuario.Proponente;
import logica.Usuario.Usuario;



public class ManejadorUsuario {
    private Map<String,Usuario> usuarios;
    
    private static ManejadorUsuario instancia = null;

    private ManejadorUsuario() {
        usuarios = new HashMap<String, Usuario>();
        
        DTFecha a = new DTFecha(30,01,2024);
        DTFecha b = new DTFecha(12,02,2023);
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
    }
   
     public void addColaborador(DTOColaborador u){
        Colaborador p=new Colaborador(u);
        usuarios.put(u.getNickname(),p);
    }
     
    public boolean existe(String nick){
      return usuarios.containsKey(nick);
    }
    
     public Usuario buscador(String nick){
        return usuarios.get(nick);
    }
    public boolean emailUsado(String email){
        for (Usuario u : usuarios.values()) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return true; // email ya está registrado
            }
        }
        return false;
    }
    
    public Map<String, Usuario> getUsuarios() {
        return usuarios;
    }
    
    public Set<DTOColaborador> listColaboradores(){
        Set<DTOColaborador> results = new HashSet<DTOColaborador>();
        for (Usuario u : usuarios.values()) {
            if (u.isColaborador()) {
                DTOColaborador dtoColaborador = new DTOColaborador(
                        u.getNickname(), u.getNombre(), u.getApellido(), u.getEmail(), 
                        u.getFecha(), u.getRutaImg());// si borro , !u.isColaborador() queda bien
                results.add(dtoColaborador);
            }
        }
        return results;
    }
    
    
    public boolean existeColaboracion(String colaborador, String titulo){
        Colaborador c=(Colaborador) buscador(colaborador);
        for (Colaboracion colab : c.getColaboraciones()) {
            if (colab.getPropuesta().getTitulo().equals(titulo)) {
                return true; 
            }
        }
        return false; 
    }
    
    public List<DTOColaboracion> getDTOColaboraciones(String nick){
        Colaborador c=(Colaborador) buscador(nick);
        List<DTOColaboracion> resu=new ArrayList<>();
                
        for(Colaboracion colab: c.getColaboraciones()){
            DTOColaboracion DTOColab = new DTOColaboracion(colab);
            resu.add(DTOColab);
        }
        return resu;
    }
}
