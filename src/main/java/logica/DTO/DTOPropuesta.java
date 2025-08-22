package logica.DTO;
import java.util.ArrayList;
import java.util.List;
import logica.Categoria.Categoria;
import logica._enum.TipoRetorno;
import logica.DTO.DTOProponente;
import logica.DTO.DTOCategoria;
import logica.DTO.DTFecha;
import logica.Propuesta.Propuesta;
import logica.Propuesta.Registro_Estado;
import logica.Usuario.Proponente;
import logica._enum.Estado;

public class DTOPropuesta {
    
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
    
    private Estado EstadoAct;
    private List<DTORegistro_Estado> historialEstados = new ArrayList<>();
    private List<DTORegistro_Aporte> aporte =new ArrayList<>();
            
    public DTOPropuesta(){}
    public DTOPropuesta(String Titulo,String Descripcion,String Tipo,String Imagen ,String Lugar, DTFecha Fecha, String Precio, String MontoTotal,DTFecha FechaPublicacion,TipoRetorno Retorno,DTOCategoria cat,DTOProponente ust,Estado EstadoAct)
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
        this.usr=ust;
        this.EstadoAct=EstadoAct;
    }
    public Estado getEstado(){
        return EstadoAct;
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
    public List<DTORegistro_Estado> getHistorialEstados() {
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

    public void setImagen(String Imagen) {
        this.Imagen = Imagen;
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

    public void setFechaPublicacion(DTFecha fechaPublicacion) {
        FechaPublicacion = fechaPublicacion;
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
    public void extraerDatosPropuesta(Propuesta in)
    {
        Titulo = in.getTitulo();
        Descripcion = in.getDescripcion();
        Tipo = in.getTipo();
        Imagen = in.getImagen();
        Lugar = in.getLugar();
        Fecha = in.getFecha();           
        Precio = in.getPrecio();
        MontoTotal = in.getMontoTotal();
        FechaPublicacion = in.getFechaPublicacion();
        Retorno = in.getRetorno();
        cat = in.getCategoria().CrearDT();
       // historialEstados = in.getHistorialEstados();
        EstadoAct = in.getEstadoAct();
    }
    public void setHistorialEstados(DTORegistro_Estado historial) 
    {
         historialEstados.add(historial); 
    }
    public void setAportes(DTORegistro_Aporte a){
        aporte.add(a);
    }

    public DTOCategoria getCat() {
        return cat;
    }

    public DTOProponente getUsr() {
        return usr;
    }

    public List<DTORegistro_Aporte> getAporte() {
        return aporte;
    }
    
    public DTORegistro_Estado obtenerPrimero(){
        if(!historialEstados.isEmpty()){
             return  historialEstados.getFirst();
        }
        return null;
    }
    
    public DTORegistro_Estado getUltimoEstado() 
    {
        DTORegistro_Estado almacen = new DTORegistro_Estado();
        if (!historialEstados.isEmpty()) {
            almacen.DTOextraerDatos(historialEstados.get(0)); //El ultimo nodo se almacena en el DTO
            return almacen;
        }

        return almacen;
    }
    
    public DTOPropuesta(Propuesta p,DTOProponente proponente){
         this.Titulo=p.getTitulo();
        this.Descripcion=p.getDescripcion();
        this.Tipo=p.getTipo();
        this.Imagen=p.getImagen();
        this.Lugar=p.getLugar();
        this.Fecha=p.getFecha();
        this.Precio=p.getPrecio();
        this.MontoTotal=p.getMontoTotal();
        this.FechaPublicacion=p.getFechaPublicacion();
        this.Retorno=p.getRetorno();
        this.cat=p.getCategoria().CrearDT();
        this.usr=proponente;
        this.EstadoAct=p.getEstadoAct();
    }
    
}
