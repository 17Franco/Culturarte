
package logica.Usuario;

//import  jakarta.persistence.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import logica.DTO.DTFecha;
import logica.Propuesta.Propuesta;
import java.util.Iterator;


public class Usuario {
    
    private String nickname;
    private String nombre;
    private String apellido;
    private String email;
    private DTFecha fecha;
    private String rutaImg;
    private Map<String,Usuario> usuarioSeguido=new HashMap<>();
    private Map<String,Propuesta> propFavorita=new HashMap<>();
    private List<registroAporte> colaboraciones;
    
    public Usuario(){}
   
    public Usuario(String nickname, String nombre, String apellido, String email, DTFecha fecha, String rutaImg) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fecha = fecha;
        this.rutaImg = rutaImg;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setRutaImg(String rutaImg) {
        this.rutaImg = rutaImg;
    }

    public String getNickname() {
        return nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public DTFecha getFecha() {
        return fecha;
    }

    public String getEmail() {
        return email;
    }
    
     public String getRutaImg() {
        return rutaImg;
    }
     
    /*public String toString() {///?????
        return getNickname();
    }*/
    
    public void seguir(Usuario usu){
        String n = usu.getNickname();
        this.usuarioSeguido.put(n, usu);
    }
    /*public void Favorita(Propuesta prop){
        String n = prop.
        this.usuarioSeguido.put(n, usu);
    }*/

    public Map<String, Usuario> getUsuarioSeguido() {
        return usuarioSeguido;
    }
    
    public boolean unfollow(Usuario usr)
    {
        if(usr != null) //Raro que suceda pero por las dudas...
        {
            if(!usuarioSeguido.isEmpty())
            {
                Iterator<Map.Entry<String, Usuario>> ct = usuarioSeguido.entrySet().iterator();

                while(ct.hasNext()) 
                {
                    Map.Entry<String,Usuario> nodoActual = ct.next();

                    if(nodoActual.getValue() == usr)
                    {
                        ct.remove();    //Se borra el nodo del usuario a dejar de seguir desde el iterator.
                        return true;
                    }

                }
            }
        }
        
        return false;   //Posible error
        
    }
    
}

