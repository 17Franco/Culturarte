/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.List;
import java.util.Map;
import java.util.Set;
import logica.Categoria.Categoria;
import logica.Propuesta.ManejadorPropuesta;
import logica.Categoria.ManejadorCategoria;
import logica.DTO.DTOCategoria;
import logica.DTO.DTOColaborador;
import logica.DTO.DTOProponente;
import logica.DTO.DTOUsuario;
import logica.DTO.DTFecha;
import logica.Propuesta.Propuesta;
import logica._enum.TipoRetorno;
import logica.DTO.DTOPropuesta;

/**
 *
 * @author fran
 */
public class Controller  implements IController {

    @Override
    public void altaUsuario(DTOUsuario usu) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<String> listarProponente() {
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
    public boolean datosUsadosUsuario(String nick, String email) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public Set<DTOPropuesta> consultaPropuestas_porEstado(String estadoSeleccionado)
    {
        //En proceso
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public boolean altaDeCategoria(DTOCategoria categoriaIngresada)
    {
       if(ManejadorCategoria.getInstance().existe(categoriaIngresada) == 1) //por ahora la opcion "2" no se usa
       {    //Si no existen previamente.
            ManejadorCategoria.getInstance().addCategoria(categoriaIngresada);
            return true;    //Le dice a UI que todo fue correcto.
       }
       
       return false;    //Le dice a ui que no se agregó nada.
    }
    
    public Map<String, Categoria> getCategorias()
    {
        return ManejadorCategoria.getInstance().getCategorias();
    }
    
}
