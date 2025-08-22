
package logica.Usuario;

import java.util.HashMap;
import java.util.Map;
import logica.DTO.DTOColaborador;
import logica.DTO.DTOProponente;
import logica.DTO.DTOUsuario;


public class ManejadorUsuario {
    private Map<String,Usuario> usuarios;
    
    private static ManejadorUsuario instancia = null;

    private ManejadorUsuario() {
        usuarios = new HashMap<String, Usuario>();
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
}
