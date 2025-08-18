/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.List;
import logica.DTO.DTOColaborador;
import logica.DTO.DTOProponente;
import logica.DTO.DTOPropuesta;
import logica.DTO.DTOUsuario;

/**
 *
 * @author fran
 */
public interface IController {
    //cu alta perfil
    void altaUsuario(DTOUsuario usu);
    
    boolean existeUsuario(String nick, String email);
    
    // cu ver perfil proponente 
    
    List<String> listarProponente();
    
    DTOProponente verPerfilProponente(String nick);
    
    List<DTOColaborador> usuarioColPropuesta(String nombProp);
    //cu alta propuesta
    void altaPropuesta(DTOPropuesta prop);
    
    
    
    
    
}
