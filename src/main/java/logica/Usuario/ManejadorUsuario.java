
package logica.Usuario;

import java.util.HashMap;
import java.util.Map;
import logica.DTO.DTFecha;
import logica.DTO.DTOColaborador;
import logica.DTO.DTOProponente;
import logica.DTO.DTOUsuario;



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
}
