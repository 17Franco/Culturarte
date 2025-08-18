/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.Propuesta;

import java.util.HashMap;
import java.util.Map;
import logica.Propuesta.Propuesta;


public class ManejadorPropuesta {
    private Map<String,Propuesta> propuestasp;
    private static ManejadorPropuesta instancia = null;

    private ManejadorPropuesta() {
        propuestasp = new HashMap<String, Propuesta>();
    }
    
    public static ManejadorPropuesta getinstance() {
        if (instancia == null)
            instancia = new ManejadorPropuesta();
        return instancia;
    }
    public void nuevaPropuesta(Propuesta p) {
        if (p != null) 
        {
            propuestasp.put(p.getTitulo(), p);
        }
    }
}
