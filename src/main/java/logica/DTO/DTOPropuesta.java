package logica.DTO;
import java.util.ArrayList;
import java.util.List;
import logica._enum.TipoRetorno;
import logica.DTO.DTOProponente;
import logica.DTO.DTOCategoria;
import logica.DTO.DTFecha;
import logica.Propuesta.Propuesta;
import logica._enum.Estado;
import logica.Propuesta.Registro_Estado;



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
    
    public DTOPropuesta(String Titulo,String Descripcion,String Tipo,String Imagen ,String Lugar, DTFecha Fecha, String Precio, String MontoTotal,DTFecha FechaPublicacion,TipoRetorno Retorno,DTOCategoria cat,DTOProponente usr,Estado EstadoAct, List<Registro_Estado> _historialEstados)
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
        this.usr = usr;
        this.EstadoAct=EstadoAct;
        
        for (int i = 0; i < _historialEstados.size(); i++)   //Pasa de Lista Class normal a lista de DTO
        {
            historialEstados.add(new DTORegistro_Estado(_historialEstados.get(i).getFechaReg(), _historialEstados.get(i).getEstado()));
        }
       
        
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
    public DTOProponente getProponente()
    {
        if(usr == null) //Control de error, no es reelevante al uso.
        {   DTFecha a = new DTFecha(30,12,9999);
            DTOProponente b = new DTOProponente("Error","Error","Error","Error","Error","Error","Error",a,"NO",true);
           return b;
        }
        
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

        usr = new DTOProponente(in.getProponente().getDireccion(),in.getProponente().getBiografia(),
                in.getProponente().getWebSite(),
                in.getProponente().getNickname(),
                in.getProponente().getNombre(),
                in.getProponente().getApellido(),
                in.getProponente().getEmail(),
                in.getProponente().getFecha(),
                in.getProponente().getRutaImg(),
                true);
        
        for (int i = 0; i < in.getHistorialEstados().size(); i++) 
        {
            historialEstados.add(new DTORegistro_Estado(in.getHistorialEstados().get(i).getFechaReg(), in.getHistorialEstados().get(i).getEstado()));
        }

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
    public String nickProponenteToString ()
    {
        if(usr == null)
        {
            return "NO_USER_(NO_EXISTE)";
        }
        
        return usr.getNickname();
    }
    
}
