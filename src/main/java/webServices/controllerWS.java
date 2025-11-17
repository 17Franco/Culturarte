/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package webServices;

import Configuracion.config;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.ws.Endpoint;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Set;
import logica.DTO.DTOCategoria;
import logica.DTO.DTOColaboracion;
import logica.DTO.DTOColaborador;
import logica.DTO.DTOPago;
import logica.DTO.DTOProponente;
import logica.DTO.DTOPropuesta;
import logica.DTO.DTORegistrosAccesoWeb;
import logica.DTO.DTOUsuario;
import logica.DTO.Estado;
import logica.DTO.TipoRetorno;
import logica.Fabrica;
import logica.IController;
import logica.Propuesta.Propuesta;
import logica.Usuario.Colaborador;
import logica.Usuario.Proponente;


@WebService(serviceName = "controllerWS")
public class controllerWS {
    private Endpoint endpoint;
    //Intancia controller
    private IController controller = Fabrica.getInstance().getController();
    
    
    public controllerWS(){}
    
    @WebMethod(exclude = true)
    public void publicar(){
        //obtengo intacion de clase config
        config conf = config.getInstance();
        
        
        //me traigo la ip desde el config.propertie
        String host = conf.getProps("WEB_SERVICES_HOST");
        //me traigo la port desde el config.propertie
        String port = conf.getProps("WEB_SERVICES_PORT");
        
        //genero url
        String endpointUrl = "http://" + host + ":" + port +"/controllerWS";
        
        //habro servicio
        this.endpoint = Endpoint.publish(endpointUrl, this);
        //this.endpoint = Endpoint.publish("../service", this);
        System.out.println("Servicio publicado dinámicamente en: " + endpointUrl);
        System.out.println("Endpoint URL: " + this.endpoint.toString());
        System.out.println("Servicio publicado en "+this.endpoint.getEndpointReference().toString());
    }
    
    @WebMethod(exclude = true)
    public Endpoint getEndpoint(){
        return this.endpoint;
    }
    
    
    //METODOS DE USUARIOS
    
    @WebMethod
    public boolean existeUsuario(String nick,String email) {

       return controller.existeUsuario(nick, email);
    }
    
    @WebMethod
    public boolean login(String nick,String Pass) {
 
       return controller.login(nick, Pass);
    }
    
    @WebMethod
    public boolean isProponente(String nick){
        return controller.isProponente(nick);
    }
    
    @WebMethod
    public byte[] getImg(String ruta) {
        return controller.getImg(ruta);
    }
    
    @WebMethod
    public boolean emailUsado(String email){
        return controller.emailUsado(email);
    }
    
    @WebMethod
    public boolean existe(String nick){
        return controller.existe(nick);
    }
    
    @WebMethod
    public List<DTOUsuario> ListaDTOUsuarios(){
        return controller.ListaDTOUsuarios();
    }
    
    @WebMethod
    public List<DTOUsuario> Seguidos(String nick){
    
        return controller.Seguidos(nick);
    }
    
    @WebMethod
    public boolean sigueAUsuario(String seguidor,String Seguido){
        return controller.sigueAUsuario(seguidor,Seguido);
    }
    
    @WebMethod
    public boolean seguir(String nick1,String nick2){
        return controller.seguir(nick1,nick2);
    }
    
    @WebMethod
    public boolean unFollowUser(String usuarioActual, String usuarioToUnfollow){
       return controller.unFollowUser(usuarioActual, usuarioToUnfollow);  
    }
    @WebMethod
    public void registroUsuario(String nickname, String pass, String nombre, String apellido, String email, String fecha, byte[] contenido,String nombreArchivo,boolean isProponente,String direccion,String web,String Biografia){
        LocalDate fechaC = LocalDate.parse(fecha);
        controller.registroUsuario(nickname, pass, nombre, apellido, email, fechaC, contenido, nombreArchivo, isProponente, direccion, web, Biografia);
    
    }
    @WebMethod
    public DTOProponente getDTOProponente(String nick) { 
        return controller.getDTOProponente(nick);
    }
    @WebMethod
    public DTOColaborador getDTOColaborador(String nick) { 
        return controller.getDTOColaborador(nick);
    }
    
    @WebMethod
    public void CancelarColaboracion(Long id){
         controller.CancelarColaboracion(id);
    }
    
    @WebMethod
    public List<DTOUsuario> getSeguidores(String nick){
        return controller.getSeguidores(nick);
    }
    
    @WebMethod
    public List<DTOUsuario> rankingUsuarios(){
        return controller.rankingUsuarios();
    }
    
    @WebMethod
    public List<DTOPropuesta> getFavoritas(String nick){
        return controller.getFavoritas(nick);
    }
    
    @WebMethod
    public List<DTOPropuesta> getPropuestasCreadasPorProponente(String nick){
        
        return new ArrayList<>(controller.getPropuestasCreadasPorProponente(nick));
    }
    
    @WebMethod
    public List<DTOColaboracion>  colaboraciones(String nick){
        return controller.colaboraciones(nick);
    }
    @WebMethod
    public boolean eliminarProponente(String nick){
        return controller.eliminarProponente(nick);
    }
    @WebMethod
    public void marcarComoFavorita(String nickname, String tituloPropuesta) {
        controller.marcarComoFavorita(nickname, tituloPropuesta);
    }
    @WebMethod
    public void quitarFavorita(String nickname, String tituloPropuesta) {
        controller.quitarFavorita(nickname, tituloPropuesta);
    }
    @WebMethod
    public boolean esFavorita(String nickname, String tituloPropuesta) {
        return controller.esFavorita(nickname, tituloPropuesta);
    }
    //FIN METODOS USUARIOS
    
    
    //METODOS PROPUESTAS
    @WebMethod
    public void altaPropuesta(String Titulo, String Descripcion, String Imagen, String Lugar, String Fecha, int Precio, int MontoTotal,String fechaPublicacio, List<TipoRetorno> Retorno, String cat, String usr,Estado est) {
        LocalDate fecha = LocalDate.parse(Fecha);
        LocalDate fechaP = LocalDate.parse(fechaPublicacio);
        controller.altaPropuesta(Titulo, Descripcion, Imagen, Lugar, fecha,Precio, MontoTotal,fechaP, Retorno,cat, usr, est);
    }
    public void altaPropuestaNew(String Titulo, String Descripcion, String FileName, byte[] contenido, String Lugar, String FechaW, int Precio, int MontoTotal, String fechaPublicacioW, List<TipoRetorno> Retorno, String cat, String usr, Estado est) {
        LocalDate fecha = LocalDate.parse(FechaW);
        LocalDate fechaP = LocalDate.parse(fechaPublicacioW);
        controller.altaPropuestaNew(Titulo, Descripcion, FileName, contenido, Lugar, fecha, Precio, MontoTotal, fechaP, Retorno, cat,  usr, est);
    }
    @WebMethod
    public DTOPropuesta getPropuestaDTO(String propuestaSel) {
        return controller.getPropuestaDTO(propuestaSel);
    }
    @WebMethod
    public int accionSobrePropuesta(String nickUsuario, String tituloProp) 
    {
        DTOPropuesta propuestaSel = controller.getPropuestaDTO(tituloProp);
        return controller.accionSobrePropuesta(nickUsuario, propuestaSel);
    }
    @WebMethod
    public int permisosSobrePropuesta(String userNick, String tipoUsuario, String tituloProp) 
    {
        DTOPropuesta propuestaActual = controller.getPropuestaDTO(tituloProp);
        return controller.permisosSobrePropuesta(userNick, tipoUsuario, propuestaActual);
    }
    @WebMethod
    public int accionesSobrePropuesta(String userNick, int permisos, String accionUsuario, String comentario, String tituloProp, String montoStr, String tipoRetorno) 
    {
        DTOPropuesta propuestaActual = controller.getPropuestaDTO(tituloProp);
        return controller.accionesSobrePropuesta(userNick, permisos, accionUsuario, comentario, propuestaActual, montoStr, tipoRetorno);
    }
    @WebMethod
    public List<DTOPropuesta> ObtenerPropuestaPorSubCategoria(String subcategoria) {
        return controller.ObtenerPropuestaPorSubCategoria(subcategoria);
    }
    @WebMethod
    public List<DTOPropuesta> BuscarPropuestas(String filtro) {
        return controller.BuscarPropuestas(filtro);
    }
    //FIN METODOS PROPUESTAS
    
    //METODOS CATEGORIA
    
    @WebMethod
    public List<DTOCategoria> getCategorias(){
        return controller.getCategorias();
    }
    //FIN METODOS CATEGORIA
    
    //METODOS COLABORACION
    
    @WebMethod
    public boolean acreditarColaboracion(Long id, DTOPago datosPago) 
    {
        return controller.acreditarColaboracion(id, datosPago);
    }
    
    

    public String GenerarPDF (long id ) throws IOException{
        //DTOColaboracion dto = mColaboraciones.getColaboracionPorId(id);
        File PDF = controller.GenerarPDF(id);
         byte[] pdfbyte = Files.readAllBytes(PDF.toPath());
        String base64 = Base64.getEncoder().encodeToString(pdfbyte);
        return base64;

       }

    //FIN METODOS COLABORACION
    
    //Registros de acceso web
    
    @WebMethod
    public boolean agregarRegistroAccesoWeb(DTORegistrosAccesoWeb input) 
    {
        return controller.agregarRegistroAccesoWeb(input);
    }
    
    //Fin Registros de acceso web
    
    //METODO EJEMPLO
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
}

