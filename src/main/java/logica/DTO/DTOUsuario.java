
package logica.DTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DTOUsuario {
    private String nickname;
    private String nombre;
    private String apellido;
    private String email;
    private DTFecha fecha;
    private String rutaImg;
    private List<String> usuarioSeguido=new ArrayList<>();
    private List<String> propFavorita=new ArrayList<>();
    private boolean isProponente;

    
    
    public DTOUsuario(String nickname, String nombre, String apellido, String email, DTFecha fecha, String rutaImg,boolean isProponente) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fecha = fecha;
        this.rutaImg = rutaImg;
        this.isProponente=isProponente;
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

    public String getEmail() {
        return email;
    }
    public boolean isProponente() {
        return isProponente;
    }


    public DTFecha getFecha() {
        return fecha;
    }
    

    public String getRutaImg() {
        return rutaImg;
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

    public void setFecha(DTFecha fecha) {
        this.fecha = fecha;
    }

    public void setRutaImg(String rutaImg) {
        this.rutaImg = rutaImg;
    }
    
    
}
