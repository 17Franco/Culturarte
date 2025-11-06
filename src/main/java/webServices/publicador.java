
package webServices;

//import jakarta.servlet.ServletContextEvent;
//import jakarta.servlet.ServletContextListener;
//import jakarta.servlet.annotation.WebListener;
//import javax.swing.SwingUtilities;
//import ui.Main;

public class publicador {

  
    public static void main(String[] args) {
        

        controllerWS ws = new controllerWS();
        ws.publicar();
    }
    
}
//@WebListener
//public class publicador implements ServletContextListener  {
//    private controllerWS ws;
//    
//    @Override
//    public void contextInitialized(ServletContextEvent sce) {
//        try {
//            ws = new controllerWS();
//            ws.publicar(); // llama al Endpoint.publish(...)
//            
//            SwingUtilities.invokeLater(new Runnable() {
//             public void run() {
//                 new Main().setVisible(true); 
//             }
//         });
//            System.out.println("Servicio SOAP publicado automáticamente.");
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent sce) {
//        if (this.ws != null && this.ws.getEndpoint() != null) {
//            this.ws.getEndpoint().stop(); // <--- Detiene el servidor HTTP ligero
//            System.out.println("Servicio SOAP detenido y puerto liberado.");
//        }
//        
//    }
//    
//}
