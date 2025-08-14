
package logica;

//Singleton//lugar cenrtralizado para intanciar objetos
public class Fabrica {
    
    private static IController controller;

    private Fabrica() {
        // Constructor privado para que no se pueda instanciar
    }

    public static IController getInstance() {
        if (controller == null) {
            controller = new Controller(); // instancia única
        }
        return controller;
    }
}
