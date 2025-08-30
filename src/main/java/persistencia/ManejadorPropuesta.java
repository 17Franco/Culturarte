/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import logica.DTO.DTOPropuesta;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import logica.Categoria.Categoria;
import logica.Colaboracion.Colaboracion;
import logica.Propuesta.Propuesta;
import logica.Usuario.Proponente;
import logica._enum.Estado;
import logica._enum.TipoRetorno;



public class ManejadorPropuesta {
    private Map<String,Propuesta> propuestasp;
    
    private static ManejadorPropuesta instancia = null;

    
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
        public boolean existeProp(String Titulo){
        Propuesta prop = propuestasp.get(Titulo); // devuelve null si no existe
        if (prop!= null) {
            return true;
        } else {
            return false;
        }
        
    }
        
        public int getMontoRecaudado(String titulo){
            Propuesta p=buscarPropuestaPorTitulo(titulo);
            int recaudado=0;
            for(Colaboracion c: p.getAporte()){
                recaudado=recaudado+c.getMonto();
            }
            return recaudado;
        }
    public Propuesta buscarPropuestaPorTitulo(String titulo) {
        if (propuestasp.containsKey(titulo)) {
            return propuestasp.get(titulo);
        }
        return null;
    }  
    
    public List<String> listColaboradores(String titulo){
       Propuesta p=buscarPropuestaPorTitulo(titulo);
       List<String> colab=new ArrayList<>();
       for(Colaboracion c: p.getAporte()){
           colab.add(c.getColaborador().getNickname());
       }
       return colab;
    }
    public Set<DTOPropuesta> obtenerPropuestas(String estadoInput) 
    {
        //La variable Estado permite elegir entre obtener un set por estado o todos los que haya. 
        //Si se le ingresa "" te manda todas las propuestas.

        Set<DTOPropuesta> temp = new HashSet<>();
        Iterator<Map.Entry<String, Propuesta>> ct;  //Se crea iterador
        ct = propuestasp.entrySet().iterator();         //Se configura tipo de iterator

        while (ct.hasNext()) 
        {
            Map.Entry<String, Propuesta> entry = ct.next();    //Pasa al siguiente
            Propuesta punteroPropuesta = entry.getValue();

            DTOPropuesta almacenTemp = new DTOPropuesta();

            if (estadoInput.isEmpty()) //Si no se especifica, se agregan todos.
            {
                almacenTemp.extraerDatosPropuesta(punteroPropuesta);
                temp.add(almacenTemp);
            } 
            
            if (!(estadoInput.isEmpty()))   //Si se especifica estado...
            { 
                if((punteroPropuesta.getUltimoEstado().getEstado()) == Estado.valueOf(estadoInput)) //Se compara el enum en la posicion actual con el string que ingresa.
                {   
                    almacenTemp.extraerDatosPropuesta(punteroPropuesta);
                    temp.add(almacenTemp);
                }
            }
        }

        return temp;
    }
    
    public Propuesta getPropuesta(String titulo){
        return propuestasp.get(titulo); 
    }
        

    private void addDummyEntry(int i) {
        Propuesta p1 = new Propuesta();
        p1.setTitulo("titulo" + i);
        p1.setCategoria(new Categoria("cat" + i));
        p1.setDescripcion("desc"+i);
        p1.setFecha(LocalDate.of(3,2,1)); //
        p1.setFechaPublicacion(LocalDate.of(3,2,1));
        p1.setImagne("img"+i);
        p1.setLugar("lugar"+i);
        p1.setRetorno(TipoRetorno.EntradaGratis);
        p1.setTipo("tipo"+i);
        p1.setPrecio(i);
        p1.setMontoTotal(i);
        Proponente prop1 = new Proponente();
        prop1.setNickname("nick" + i);
        p1.setProponente(prop1);
        propuestasp.put(p1.getTitulo(), p1);
    }
    // esto esta hecho asi para tener datos para cargar en el formulario , para ahcer pruebas rapidas sin tener que setear todo de cero en cada ocasion  
    private ManejadorPropuesta() {
        propuestasp = new HashMap<String, Propuesta>();
        for (int i = 0; i < 10; i++){
            this.addDummyEntry(i);
        }
    }
    
    
}