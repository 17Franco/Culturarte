/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import logica.Categoria.Categoria;
import logica.DTO.DTOCategoria;
import logica.DTO.DTOPropuesta;
import logica.DTO.DTOColaborador;
import logica.DTO.DTOProponente;
import logica.Colaboracion.Colaboracion;
import logica.DTO.DTOColaboracion;
import logica.DTO.DTOPago;
import logica.DTO.DTORegistrosAccesoWeb;
import logica.DTO.DTOUsuario;
import logica.DTO.Estado;
import logica.DTO.TipoRetorno;


public interface IController {
    //Usuarios
    void altaUsuario(DTOUsuario usu);
    
    void registroUsuario(String nickname, String pass, String nombre, String apellido, String email, LocalDate fecha, byte[] contenido,String nombreArchivo,boolean isProponente,String direccion,String web,String Biografia);
    
    boolean existeUsuario(String nick, String email);
    
    boolean emailUsado(String email);
    
    boolean existe(String nick);
    
    List<String> ListaUsuarios();
    
    List<String> ProponenteEliminados();
    
    List<String> ListaProponentes();
    
    List<String> ListaColaborador();
    
    List<DTOColaboracion>  colaboraciones(String nick);
    
    List<String>ListaSeguidosPorUsuario(String nick);
    
    DTOProponente getDTOProponente(String nick);
    
    DTOColaborador getDTOColaborador(String nick);
    
    List<String> colaboradoresAPropuesta(String titulo);
    
    boolean seguir(String nick1,String nick2);
    
    boolean unFollowUser(String usuarioActual, String usuarioToUnfollow);
    
    Set<DTOPropuesta> getPropuestasCreadasPorProponente(String nick);
    
    //SE USAN EN WEB
    
    boolean login(String nick,String Pass);
    
    boolean isProponente(String nick);
    
    List<DTOUsuario> rankingUsuarios();
    
    byte[] getImg(String ruta) ;
    
    List<DTOUsuario> ListaDTOUsuarios();
    
    void marcarComoFavorita(String nickname, String tituloPropuesta);
    
    void quitarFavorita(String nickname, String tituloPropuesta);
    
    boolean esFavorita(String nickname, String tituloPropuesta);
    
    boolean sigueAUsuario(String seguidor,String Seguido);
    
    List<DTOUsuario>Seguidos(String nick);
    
    List<DTOUsuario> getSeguidores(String nick);
    
    List<DTOPropuesta> getFavoritas(String nick);
    
    boolean eliminarProponente(String nick);
    //FIN WEB
    
    //Fin Usuario
    
    //Propuestas
    void altaPropuesta(String Titulo, String Descripcion, String Imagen, String Lugar, LocalDate Fecha, int Precio, int MontoTotal, LocalDate fechaPublicacio,List<TipoRetorno> Retorno, String cat, String usr,Estado est);
    
    void modificarPropuesta(String titulo, String descripcion, String rutaImagen, String lugar, LocalDate fechaEvento,int precio, int montoTotal, List<TipoRetorno> retorno, String categoria, String usuarios, Estado estado);
    
    boolean existeProp(String Titulo);
    
    Set<DTOPropuesta> obtenerPropuestas(String estado);
    
    DTOPropuesta getPropuestaDTO(String propuestaSel);

    String creadorPropuesta(String titulo);
    
    String estadoPropuestas(String titulo);
    
    Set<DTOPropuesta> ListarPropuestas(String estado1, String estado2);
    
    //SE USAN EN WEB
    int accionSobrePropuesta(String nickUsuario, DTOPropuesta propuestaSel);
    
    int extenderOCancelarPropuesta(String accionUsuario,String tituloPropuesta);
    
    boolean nuevoComentario(String comentario,String userNick,String tituloPropuesta);

    int accionesSobrePropuesta(String userNick, int permisos, String accionUsuario,String comentario, DTOPropuesta propuestaActual, String montoStr, String tipoRetorno);

    int permisosSobrePropuesta(String userNick, String tipoUsuario, DTOPropuesta propuestaActual);
    
    //FIN WEB (PROPUESTAS)
    
    //Fin Propuesta
    
    //Categoria
    boolean altaDeCategoria(DTOCategoria categoriaIngresada);
    
    List<DTOCategoria> getCategorias();
    
    List<String> ListaCategoria();
    
    //Fin Categoria
    
    //COLABORACIONES
    void altaColaboracion(DTOColaboracion colaboracion); 
    
    boolean colaboracionExiste(String colaborador, String titulo);
    
    int  getMontoRecaudado(String titulo);
    
    void CancelarColaboracion(Long id); 
    
    Set<DTOColaboracion> getDTOColaboraciones();
    
    boolean acreditarColaboracion(Long id, DTOPago datosPago);
    List<DTOColaboracion> apoertesPorpuesta(String title);
    
    File GenerarPDF (long id );

    //Registros
    
    List<DTORegistrosAccesoWeb> obtenerRegistrosAccesoWeb();
    
    public boolean agregarRegistroAccesoWeb(DTORegistrosAccesoWeb input);
    
    //Fin registros
    
    //CARGA DE DATOS
    void cargarDatosPruebaProponente();
    
    void cargarDatosPruebaColaborador();
    
    void cargarSeguidos();

    void cargarPropuesta();
    
    void cargarCategorias();
    
    void cargarColaboraciones();
    
    //FIN CARGA DE DATOS
   
    Set<DTOPropuesta> ObtenerPropuestaPorSubCategoria(String subcategoria);
    // private Set <DTOCategoria> subcategorias;
    // al hacer click en la subcat traer las propu que en la BD en la columna ca
    // crear manejadorSubCat?
    // Funcion usada por el buscador web para filtrar propuestas
    List<DTOPropuesta> BuscarPropuestas(String filtro);
}
