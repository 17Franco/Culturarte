/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.List;
import logica.Propuesta.ManejadorPropuesta;
import logica.DTO.DTOCategoria;
import logica.DTO.DTOColaborador;
import logica.DTO.DTOProponente;
import logica.DTO.DTOUsuario;
import logica.DTO.DTFecha;
import logica.Propuesta.Propuesta;
import logica.Usuario.ManejadorUsuario;
import logica._enum.TipoRetorno;

/**
 *
 * @author fran
 */
public class Controller  implements IController {
       private ManejadorUsuario mUsuario=ManejadorUsuario.getinstance();
       
    @Override
    public void altaUsuario(DTOUsuario usu) {
        if(usu.isProponente()){
            mUsuario.addProponente((DTOProponente) usu);
        }else{
            mUsuario.addColaborador((DTOColaborador) usu);
        }
    }

    @Override
    public List<String> listarUsuario(String tipo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public DTOProponente verPerfilProponente(String nick) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DTOColaborador> usuarioColPropuesta(String nombProp) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public void altaPropuesta(String Titulo, String Descripcion, String Tipo, String Imagen, String Lugar, DTFecha Fecha, String Precio, String MontoTotal, TipoRetorno Retorno, DTOCategoria cat, DTOProponente usr) {
        Propuesta propuesta = new Propuesta (Titulo, Descripcion, Tipo, Imagen, Lugar, Fecha, Precio, MontoTotal, Retorno, cat, usr);
        ManejadorPropuesta.getinstance().nuevaPropuesta(propuesta);
    }

    @Override
    public boolean existeUsuario(String nick, String email) {
           return (mUsuario.existe(nick) || mUsuario.emailUsado(email));
    }
    
}
