
package logica.Usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import logica.DTO.DTFecha;
import logica.DTO.DTOColaborador;

public class Colaborador extends Usuario{
    private List<registroAporte> colaboraciones= new ArrayList<>();

    public Colaborador() {
    }

    public Colaborador(String nickname, String nombre, String apellido, String email, DTFecha fecha, String rutaImg) {
        super(nickname, nombre, apellido, email, fecha, rutaImg);
    }

    public List<registroAporte> getColaboraciones() {
        return colaboraciones;
    }

    public void setColaboraciones(registroAporte r){
        colaboraciones.add(r);
    }
    
    public Colaborador(DTOColaborador dto){
        super(dto.getNickname(), dto.getNombre(), dto.getApellido(),dto.getEmail(), dto.getFecha(), dto.getRutaImg());
        
    }
        
     public boolean isColaborador(){
         return true;
     }
}
