
package logica.Usuario;

import java.util.HashMap;
import java.util.Map;
import logica.DTO.DTFecha;

public class Colaborador extends Usuario{
    private Map<String,registroAporte> colaboraciones= new HashMap<>();

    public Colaborador() {
    }

    public Colaborador(String nickname, String nombre, String apellido, String email, DTFecha fecha, String rutaImg) {
        super(nickname, nombre, apellido, email, fecha, rutaImg);
    }

    
    
}
