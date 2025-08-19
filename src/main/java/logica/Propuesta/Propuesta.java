package logica.Propuesta;

import logica._enum.TipoRetorno;
import logica.DTO.DTOProponente;
import logica.DTO.DTOCategoria;
import logica.DTO.DTFecha;
import java.util.List;
import java.util.ArrayList;
import logica.DTO.DTORegistro_Estado;
import logica._enum.Estado;


public class Propuesta {
    
    private String Titulo;
    private String Descripcion;
    private String Tipo;
    private String Imagen;
    private String Lugar;
    private DTFecha Fecha;
    private String Precio;
    private String MontoTotal;
    private DTFecha FechaPublicacion;
    private TipoRetorno Retorno;
    private DTOCategoria cat;
    private DTOProponente usr;
    private List<Registro_Estado> historialEstados = new ArrayList<>(); //El primero es el ultimo! añadan al inicio
    //private Map<String, Registro> Aporte;
            
    public Propuesta(){}
    public Propuesta(String Titulo,String Descripcion,String Tipo,String Imagen ,String Lugar, DTFecha Fecha, String Precio, String MontoTotal,DTFecha FechaPublicacion,TipoRetorno Retorno,DTOCategoria cat,DTOProponente ust)
    {
        this.Titulo=Titulo;
        this.Descripcion=Descripcion;
        this.Tipo=Tipo;
        this.Imagen=Imagen;
        this.Lugar=Lugar;
        this.Fecha=Fecha;
        this.Precio=Precio;
        this.MontoTotal=MontoTotal;
        this.FechaPublicacion=FechaPublicacion;
        this.Retorno=Retorno;
        this.cat=cat;
        this.usr=usr;
        
        
        
        
    }
    public  String getTitulo() {
        return Titulo;
    }
    public  String getDescripcion() {
        return Descripcion;
    }
    public  String getTipo() {
        return Tipo;
    }
    public  String getImagen() {
        return Imagen;
    }
    public  String getLugar() {
        return Lugar;
    }
    public  DTFecha getFecha() {
        return Fecha;
    }
    public String getPrecio() {
        return Precio;
    }
    public String getMontoTotal() {
        return MontoTotal;
    }
    public DTFecha getFechaPublicacion() {
        return FechaPublicacion;
    }
    public TipoRetorno getRetorno() {
        return Retorno;
    }
    public DTOCategoria getCategoria(){
        return cat;
    }
    public DTOProponente getProponente(){
        return usr;
    }
    public List<Registro_Estado> getHistorialEstados() 
    {
        return historialEstados;
    }
    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }
    public void setImagne(String Imagen) {
        Imagen = Imagen;
    }
    public void setLugar(String lugar) {
        Lugar = lugar;
    }

    public void setFecha(DTFecha fecha) {
        Fecha = fecha;
    }

    public void setPrecio(String precio) {
        Precio = precio;
    }

    public void setMontoTotal(String montoTotal) {
        MontoTotal = montoTotal;
    }

    public void setFechaPublicacion(DTFecha FechaPublicacion) {
        FechaPublicacion = FechaPublicacion;
    }
    public void setRetorno(TipoRetorno retorno) {
        Retorno = retorno;
    } 
    public void setCategoria(DTOCategoria Cat){
        cat = Cat;
    }
    public void setProponente(DTOProponente Propo){
        usr = Propo;
    }

    public void setHistorialEstados(List<Registro_Estado> _historial) 
    {
        historialEstados = _historial;
    }
    
    public void agregarNuevoEstado(DTORegistro_Estado input)
    {   
        //Registro_Estados almacen = new Registro_Estados(input.getFecha(),input.getEstados());
        //historialEstados.add(0,almacen);
    }
    public Estado estadoActual(Registro_Estado input)   //Devuelve el ultimo estado
    {
        Estado almacen = input.getEstado();
        
        return almacen;
    }
}
