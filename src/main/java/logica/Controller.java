/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.ArrayList;
import java.util.HashMap;
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
import logica.Usuario.Usuario;
import logica.Usuario.ManejadorUsuario;
import logica._enum.TipoRetorno;
import logica.DTO.DTOPropuesta;
import logica.Usuario.Proponente;
import logica.Usuario.Usuario;

/**
 *
 * @author fran
 */
public class Controller  implements IController {
       private ManejadorUsuario mUsuario=ManejadorUsuario.getinstance();
       private ManejadorCategoria mCategoria=ManejadorCategoria.getInstance();
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
    public List<String> listarUsuario(String tipo) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public DTOProponente verPerfilProponente(String nick) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<DTOColaborador> usuarioColPropuesta(String nombProp) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    public void altaPropuesta(String Titulo, String Descripcion, String Tipo, String Imagen, String Lugar, DTFecha Fecha, String Precio, String MontoTotal,DTFecha fechaPublicacio, TipoRetorno Retorno, String cat, String usr) {
        
        Propuesta propuesta = new Propuesta (Titulo, Descripcion, Tipo, Imagen, Lugar, Fecha, Precio, MontoTotal, fechaPublicacio ,Retorno, mCategoria.buscadorC(cat), (Proponente) mUsuario.buscador(usr));
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
    public Map<String, DTOUsuario> listarDtoUsuario(char tipo) {
        Map<String, DTOUsuario> usr = new HashMap<>();
        if(mUsuario.getUsuarios().isEmpty()){
              return null;
         }else{
            
            for(Usuario u: mUsuario.getUsuarios().values()){
                if(u instanceof Proponente){
                    DTOProponente aux =new DTOProponente(((Proponente) u).getDireccion(),((Proponente) u).getBiografia(),((Proponente) u).getWebSite(),u.getNickname(),u.getNombre(),u.getApellido(),u.getEmail(),u.getFecha(),u.getRutaImg(),true);
                }else{
                    //DTOColaborador aux= new DTOColaborador();
                }
                
            }
            return usr;
        }
    }
        
       @Override
        public Set<DTOPropuesta> obtenerPropuestas(String estado){
           return  propu.obtenerPropuestas(estado);
        }

        @Override
        public List<String> ListaProponentes(){
            List<String> aux = new ArrayList<>();
            for (Usuario c : mUsuario.getUsuarios().values()){
               if (c instanceof Proponente){
                    aux.add(c.getNickname());
               }
            }
                return aux;    
        }

        public List<String> ListaCategoria(){
            List<String> aux2 = new ArrayList<>();
            for (Categoria c : mCategoria.getCategoria().values()){
                aux2.add(c.getNombreCategoria());
            }
                return aux2; 
        }
    }
