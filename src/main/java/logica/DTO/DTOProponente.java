
package logica.DTO;

import java.util.HashMap;
import java.util.Map;



public class DTOProponente extends DTOUsuario{
      private String direccion;
    private String biografia;
    private String webSite;
    private Map<String,DTOPropuesta> propCreadas=new HashMap<>();

    public DTOProponente(String direccion, String biografia, String webSite, String nickname, String nombre, String apellido, String email, DTFecha fecha, String rutaImg) {
        super(nickname, nombre, apellido, email, fecha, rutaImg);
        this.direccion = direccion;
        this.biografia = biografia;
        this.webSite = webSite;
    }
    
    
}
