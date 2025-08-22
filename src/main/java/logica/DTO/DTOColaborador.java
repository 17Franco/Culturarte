/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.DTO;

import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author fran
 */
public class DTOColaborador extends DTOUsuario{
    private Map<String,DTORegistro_Aporte> colaboraciones= new HashMap<>();

    public DTOColaborador(String nickname, String nombre, String apellido, String email, DTFecha fecha, String rutaImg,boolean isProponente) {
        super(nickname, nombre, apellido, email, fecha, rutaImg,isProponente);
    }
    
    
}
