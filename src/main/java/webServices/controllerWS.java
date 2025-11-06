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
    
    public controllerWS(){}
    
    @WebMethod(exclude = true)
    public void publicar(){
        config conf = config.getInstance();
        
        String host = conf.getProps("WEB_SERVICES_HOST");
        String port = conf.getProps("WEB_SERVICES_PORT");
        
        String endpointUrl = "http://" + host + ":" + port +"/controllerWS";
        
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
    //public boolean existeUsuario(String nick, String email)
    @WebMethod(operationName = "existe")
    public boolean existeUsuario(String nick,String email) {
       IController controller = Fabrica.getInstance().getController();
        
        return controller.existeUsuario(nick, email);
    }
    
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
}

