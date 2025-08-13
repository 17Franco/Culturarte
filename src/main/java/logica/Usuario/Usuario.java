
package logica.Usuario;

//import  jakarta.persistence.*;

public class Usuario {
    
    private String nickname;
    private String nombre;
    private String apellido;
    private String email;
    
    public Usuario(){}
    public Usuario(String nickname,String nombre,String apellido,String email){this.nickname=nickname; this.nombre=nombre;this.apellido=apellido;this.email=email;}

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
    
    
}
