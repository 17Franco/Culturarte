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
import logica.Colaboracion.Colaboracion;
import logica.DTO.DTOColaboracion;
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
    
    List<DTOColaboracion>  colaboraciones(String nick);
  
   
    List<String>ListaSeguidosPorUsuario(String nick);
    
    DTOProponente getDTOProponente(String nick);
    
    DTOColaborador getDTOColaborador(String nick);
    
    List<String> colaboradoresAPropuesta(String titulo);
    boolean seguir(String nick1,String nick2);
    
    boolean unFollowUser(String usuarioActual, String usuarioToUnfollow);
            
    //Fin Usuario
    
    //Propuestas
    void altaPropuesta(String Titulo, String Descripcion, String Tipo, String Imagen, String Lugar, DTFecha Fecha, int Precio, int MontoTotal, DTFecha fechaPublicacio,TipoRetorno Retorno, String cat, String usr,Estado est);
    void modificarPropuesta(String titulo, String descripcion, String tipo,String rutaImagen, String lugar, DTFecha fechaEvento,int precio, int montoTotal, TipoRetorno retorno, String categoria, String usuarios, Estado estado);
    boolean existeProp(String Titulo);
    
    Set<DTOPropuesta> obtenerPropuestas(String estado);
    
    String creadorPropuesta(String titulo);
    
    String estadoPropuestas(String titulo);
    //Fin Propuesta
    
    //Categoria
    boolean altaDeCategoria(DTOCategoria categoriaIngresada);
    
    Map<String, Categoria> getCategorias();
  
    List<String> ListaCategoria();
    //Fin Categoria
    
    //cu Registrar colaboracion a Propuesta
    Set<DTOPropuesta> ListarPropuestas();
    
    void altaColaboracion(DTOColaboracion colaboracion); 
    boolean colaboracionExiste(String colaborador, String titulo);
    int  getMontoRecaudado(String titulo);
    // cu Consulta de colaboracion a Propuesta
    Set<DTOColaborador> ListarColaboradres();
    Set<Colaboracion> ListarColaboracionesDeColaborador(String nickname);
    
    // cu cancelar Colaboracion a Propuesta
    void CancelarColaboracion(Colaboracion colaboracion);
}
