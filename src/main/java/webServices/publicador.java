
package webServices;

//import jakarta.servlet.ServletContextEvent;
//import jakarta.servlet.ServletContextListener;
//import jakarta.servlet.annotation.WebListener;
//import javax.swing.SwingUtilities;
//import ui.Main;

public class publicador {

  //no se usa se publican cuado junto con el main de swing 
    public static void main(String[] args) {

        controllerWS ws = new controllerWS();
        ws.publicar();
    }
    
}

