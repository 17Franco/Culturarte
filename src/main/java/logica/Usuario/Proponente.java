
package logica.Usuario;

import java.util.HashMap;
import java.util.Map;
import logica.Propuesta.Propuesta;


public class Proponente extends Usuario{
    private String direccion;
    private String biografia;
    private String webSite;
    private Map<String,Propuesta> propCreadas=new HashMap<>();

    public Proponente() {
    }

    public Proponente(String nickname, String nombre, String apellido, String email,String direccion,String biografia,String webSite) {
        super(nickname, nombre, apellido, email);
        this.direccion=direccion;
        this.biografia=biografia;
        this.webSite=webSite;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getBiografia() {
        return biografia;
    }

    public String getWebSite() {
        return webSite;
    }

    public String getDireccion() {
        return direccion;
    }

    
    
}
