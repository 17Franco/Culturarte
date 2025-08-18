/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.List;
import java.util.Map;
import java.util.Set;
import logica.Categoria.Categoria;
import logica.DTO.DTOCategoria;
import logica.DTO.DTOColaborador;
import logica.DTO.DTOProponente;
import logica.DTO.DTOPropuesta;
import logica.DTO.DTFecha;
import logica.DTO.DTOUsuario;
import logica._enum.TipoRetorno;

/**
 *
 * @author fran
 */
public interface IController {
    //cu alta perfil
    void altaUsuario(DTOUsuario usu);
    // cu ver perfil proponente 
    
    List<String> listarProponente();
    
    DTOProponente verPerfilProponente(String nick);
    
    List<DTOColaborador> usuarioColPropuesta(String nombProp);
    //cu alta propuesta
    void altaPropuesta(String Titulo, String Descripcion, String Tipo, String Imagen, String Lugar, DTFecha Fecha, String Precio, String MontoTotal, TipoRetorno Retorno, DTOCategoria cat, DTOProponente usr);
    
    boolean datosUsadosUsuario(String nick, String email);
    
    Set<DTOPropuesta> consultaPropuestas_porEstado(String estadoSeleccionado);
    
    boolean altaDeCategoria(DTOCategoria categoriaIngresada);
    Map<String, Categoria> getCategorias();
    
    
}
