/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.DTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import logica.Usuario.Colaborador;


/**
 *
 * @author fran
 */
public class DTOColaborador extends DTOUsuario{
    private List<DTORegistro_Aporte> colaboraciones= new ArrayList<>();

    public DTOColaborador(String nickname, String nombre, String apellido, String email, DTFecha fecha, String rutaImg) {
        super(nickname, nombre, apellido, email, fecha, rutaImg);
    }
    public void setColaboracion(DTORegistro_Aporte r){
            colaboraciones.add(r);
    }
      public DTOColaborador(Colaborador c){
        super(c.getNickname(), c.getNombre(), c.getApellido(),c.getEmail(), c.getFecha(), c.getRutaImg());  
    }
      
     public boolean isColaborador(){
         return true;
     }

    public List<DTORegistro_Aporte> getColaboraciones() {
        return colaboraciones;
    }
     
}
