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
import logica.Fabrica;
import logica.IController;


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
    
    //FIN METODOS USUARIOS
    
    //METODOS PROPUESTAS
    //FIN METODOS PROPUESTAS
    
    //METODOS CATEGORIA
    //FIN METODOS CATEGORIA
    
    //METODOS COLABORACION
    //FIN METODOS COLABORACION
    
    //METODO EJEMPLO
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
}

