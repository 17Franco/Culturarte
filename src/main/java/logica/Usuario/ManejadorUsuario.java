
package logica.Usuario;

import java.util.HashMap;
import java.util.Map;
import logica.DTO.DTOColaborador;
import logica.DTO.DTOProponente;
import logica.DTO.DTOUsuario;
import java.util.Set;
import java.util.HashSet;


public class ManejadorUsuario {
    private Map<String,Usuario> usuarios;
    private static ManejadorUsuario instancia = null;

    private ManejadorUsuario() {
        usuarios = new HashMap<String, Usuario>();
    }
    
    public static ManejadorUsuario getinstance() {
        if (instancia == null)
            instancia = new ManejadorUsuario();
        return instancia;
    }
    
    public void addProponente(DTOProponente u){

        Proponente p=new Proponente(u.getDireccion(),u.getBiografia(),u.getWebSite(),u.getNickname(),u.getNombre(),u.getApellido(),u.getEmail(),u.getFecha(),u.getRutaImg());
        usuarios.put(u.getNickname(),p);
       
    }
    
     public void addColaborador(DTOColaborador u){

        Colaborador p=new Colaborador(u.getNickname(),u.getNombre(),u.getApellido(),u.getEmail(),u.getFecha(),u.getRutaImg());
        usuarios.put(u.getNickname(),p);
       
    }
    public boolean existe(String nick){
        Usuario u = usuarios.get(nick); // devuelve null si no existe
        if (u != null) {
            return true;
        } else {
            return false;
        }
    }
     public Usuario buscador(String nick){
        Usuario u = usuarios.get(nick); 
        
        return u;
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
                        u.getFecha(), u.getRutaImg(), !u.isColaborador());
                results.add(dtoColaborador);
            }
        }
        return results;
    }
}
