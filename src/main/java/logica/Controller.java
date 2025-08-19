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
import logica.Usuario.ManejadorUsuario;
import logica._enum.TipoRetorno;
import logica.DTO.DTOPropuesta;

/**
 *
 * @author fran
 */
public class Controller  implements IController {
       private ManejadorUsuario mUsuario=ManejadorUsuario.getinstance();
       private ManejadorPropuesta propu=ManejadorPropuesta.getinstance();
    @Override
    public void altaUsuario(DTOUsuario usu) {
        if(usu.isProponente()){
            mUsuario.addProponente((DTOProponente) usu);
        }else{
            mUsuario.addColaborador((DTOColaborador) usu);
        }
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
    public void altaPropuesta(String Titulo, String Descripcion, String Tipo, String Imagen, String Lugar, DTFecha Fecha, String Precio, String MontoTotal,DTFecha fechaPublicacio, TipoRetorno Retorno, DTOCategoria cat, DTOProponente usr) {
        Propuesta propuesta = new Propuesta (Titulo, Descripcion, Tipo, Imagen, Lugar, Fecha, Precio, MontoTotal, fechaPublicacio ,Retorno, cat, usr);
        ManejadorPropuesta.getinstance().nuevaPropuesta(propuesta);
    }

    @Override
    public boolean existeUsuario(String nick, String email) {
           return (mUsuario.existe(nick) || mUsuario.emailUsado(email));
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
    @Override
    public Map<String, Categoria> getCategorias()
    {
        return ManejadorCategoria.getInstance().getCategorias();
    }
    
    public boolean existeProp(String Titulo){
         return (propu.existeProp(Titulo));
    }
    @Override
    public Set<DTOPropuesta> obtenerPropuestas()
    {
        return ManejadorPropuesta.getinstance().obtenerPropuestas();
    }
}
