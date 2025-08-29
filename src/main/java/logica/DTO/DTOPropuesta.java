package logica.DTO;
import java.util.ArrayList;
import java.util.List;
import logica.Colaboracion.Colaboracion;
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
    private int Precio;
    private int MontoTotal;
    private DTFecha FechaPublicacion;
    private TipoRetorno Retorno;
    private DTOCategoria cat;
    private DTOProponente usr; 
    private Estado EstadoAct;
    private List<DTORegistro_Estado> historialEstados = new ArrayList<>();
    private List<DTOColaboracion> aporte =new ArrayList<>();
            
    public DTOPropuesta(){}
    
    public DTOPropuesta(String Titulo,String Descripcion,String Tipo,String Imagen ,String Lugar, DTFecha Fecha, int Precio, int MontoTotal,DTFecha FechaPublicacion,TipoRetorno Retorno,DTOCategoria cat,DTOProponente usr,Estado EstadoAct, List<Registro_Estado> _historialEstados, List<Colaboracion> _colaboradores)
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
       for (int b = 0; b < _colaboradores.size(); b++) 
        {
            aporte.add(new DTOColaboracion(_colaboradores.get(b).getTipoRetorno(), _colaboradores.get(b).getMonto(), _colaboradores.get(b).getColaborador().getNickname(), _colaboradores.get(b).getPropuesta().getTitulo(), _colaboradores.get(b).getCreado()));
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
    public int getPrecio() {
        return Precio;
    }
    public int getMontoTotal() {
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
            DTOProponente b = new DTOProponente("Error","Error","Error","Error","Error","Error","Error",a,"NO");
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

    public void setPrecio(int precio) {
        Precio = precio;
    }

    public void setMontoTotal(int montoTotal) {
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
                in.getProponente().getRutaImg());
        
        for (int i = 0; i < in.getHistorialEstados().size(); i++) 
        {
            historialEstados.add(new DTORegistro_Estado(in.getHistorialEstados().get(i).getFechaReg(), in.getHistorialEstados().get(i).getEstado()));
        }
        for (int b = 0; b < in.getAporte().size(); b++) 
        {
            aporte.add(new DTOColaboracion(in.getAporte().get(b).getTipoRetorno(), in.getAporte().get(b).getMonto(), in.getAporte().get(b).getColaborador().getNickname(), in.getAporte().get(b).getPropuesta().getTitulo(), in.getAporte().get(b).getCreado()));
        }
    }
        
    public void setHistorialEstados(DTORegistro_Estado historial) 
    {
         historialEstados.add(historial); 
    }
   public void setColaboracion(DTOColaboracion c){
        aporte.add(c);
    }
    public List<DTOColaboracion> getAporte() {
        return aporte;
    }
    public DTOCategoria getCat() {
        return cat;
    }

    public DTOProponente getUsr() {
        return usr;
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
    }

   
    
}
