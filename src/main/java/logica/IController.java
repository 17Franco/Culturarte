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
import logica.DTO.DTOPropuesta;
import logica.DTO.DTOColaborador;
import logica.DTO.DTOProponente;
import logica.DTO.DTFecha;
import logica.DTO.DTOUsuario;
import logica._enum.Estado;
import logica._enum.TipoRetorno;


public interface IController {
    //Usuarios
    void altaUsuario(DTOUsuario usu);
    
    boolean existeUsuario(String nick, String email);
    
    boolean existe(String nick);
    
    List<String> ListaUsuarios();
    
    List<String> ListaProponentes();
    
    List<String> ListaColaborador();
    
  
   
    List<String>ListaSeguidosPorUsuario(String nick);
    
    DTOProponente getDTOProponente(String nick);
    
    DTOColaborador getDTOColaborador(String nick);
    
    boolean seguir(String nick1,String nick2);
    //Fin Usuario
    
    //Propuestas
    void altaPropuesta(String Titulo, String Descripcion, String Tipo, String Imagen, String Lugar, DTFecha Fecha, String Precio, String MontoTotal, DTFecha fechaPublicacio,TipoRetorno Retorno, String cat, String usr,Estado est);
    
    boolean existeProp(String Titulo);
    
    Set<DTOPropuesta> obtenerPropuestas(String estado);
    //Fin Propuesta
    
    //Categoria
    boolean altaDeCategoria(DTOCategoria categoriaIngresada);
    
    Map<String, Categoria> getCategorias();
  
    List<String> ListaCategoria();
    //Fin Categoria
}
