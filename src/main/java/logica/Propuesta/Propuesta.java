package logica.Propuesta;

import java.time.LocalDate;
import logica._enum.TipoRetorno;
import logica.DTO.DTFecha;
import logica.Categoria.Categoria;
import logica.Usuario.Proponente;
import java.util.List;
import java.util.ArrayList;
import logica.Colaboracion.Colaboracion;
import logica.DTO.DTORegistro_Estado;
import logica._enum.Estado;


public class Propuesta {
    
    private String Titulo;
    private String Descripcion;
    private String Tipo;
    private String Imagen;
    private String Lugar;
    private DTFecha Fecha;
    private String Precio; //tambien debe ser int 
    private String MontoTotal;  //deberia ser int o por lo menos controlar si es texto que se pueda transformar a numero
    private DTFecha FechaPublicacion;
    private TipoRetorno Retorno;
    private Categoria cat;
    private Proponente usr;
    private Estado estadoAct; 
    private List<Registro_Estado> historialEstados = new ArrayList<>(); //El primero es el ultimo! añadan al inicio
    private List<Colaboracion> Aporte= new ArrayList<>();// se guarda los aportes que a recibido la propuesta 
            
    public Propuesta(){}
    public Propuesta(String Titulo,String Descripcion,String Tipo,String Imagen ,String Lugar, DTFecha Fecha, String Precio, String MontoTotal,DTFecha FechaPublicacion,TipoRetorno Retorno,Categoria cat,Proponente usr,Estado estadoAct)
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
        this.estadoAct=estadoAct;   
        this.historialEstados.add(0,(new Registro_Estado(new DTFecha(LocalDate.now()), estadoAct)));    //Añade al inicio!
    }
    public Estado getEstadoAct(){
        return  this.estadoAct;
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
    public Categoria getCategoria(){
        return cat;
    }
    public Proponente getProponente(){
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
    public void setImagne(String imagen) {
        Imagen = imagen;
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

    public void setFechaPublicacion(DTFecha _FechaPublicacion) {
        FechaPublicacion = _FechaPublicacion;
    }
    public void setRetorno(TipoRetorno retorno) {
        Retorno = retorno;
    } 
    public void setCategoria(Categoria Cat){
        cat = Cat;
    }
    public void setProponente(Proponente Propo){
        usr = Propo;
    }
    public void setEstadoAct(Estado Estad){
        estadoAct = Estad;
    }
    public void setHistorialEstados(List<Registro_Estado> _historial) 
    {
        historialEstados = _historial;
    }

    public List<Colaboracion> getAporte() {
        return Aporte;
    }
    public void setColaboracion(Colaboracion c){
        Aporte.add(c);
    }
    public DTORegistro_Estado getUltimoEstado()
    {
        DTORegistro_Estado almacen = new DTORegistro_Estado();
        if(!historialEstados.isEmpty())
        {
            almacen.extraerDatos(historialEstados.get(0));  //El ultimo nodo se almacena en el DTO
            return almacen;
        }
        
        return almacen;
    }
    public void addEstHistorial(Estado aux1){
        
        Registro_Estado nuevoReg = new Registro_Estado(new DTFecha(LocalDate.now()),aux1);
        this.historialEstados.add(0,nuevoReg);
    } 
}
