package logica.DTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import logica.Colaboracion.Colaboracion;
import logica.Propuesta.Propuesta;
import logica.Propuesta.Registro_Estado;


@XmlRootElement(name = "DTOPropuesta") 
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(Include.NON_NULL)
public class DTOPropuesta {
    
    private String Titulo;
    private String Descripcion;
    private String Imagen;
    private String Lugar;
    @JsonIgnore
    private Date Fecha;
    @JsonIgnore
    private Date FechaPublicacion;
    @JsonIgnore
    private Date fechaExpiracion;
    
    private String FechaString;
    private String FechaPublicacionString;
    private String fechaExpiracionString;
    private int Precio;
    private int MontoTotal;
    
    @XmlTransient
    @JsonIgnore
    private List<TipoRetorno> Retorno = new ArrayList<>();
    @JsonIgnore
    private DTOCategoria cat;
    private String categoria;
    @JsonIgnore //ignora en json
    private DTOProponente usr; 
    
    private Estado EstadoAct;
    
    @JsonInclude(Include.NON_EMPTY)
    private List<DTORegistro_Estado> historialEstados = new ArrayList<>();
    
    @JsonInclude(Include.NON_EMPTY)//ignora en caso vacio en json
    private List<DTOColaboracion> aporte =new ArrayList<>();
    
   
    private List<String> aporteNick =new ArrayList<>();
    
    @JsonInclude(Include.NON_EMPTY)
    private Map<String,String> comentarios = new HashMap<>();
            
            
    public DTOPropuesta(){}
    
    public DTOPropuesta(String titulo, LocalDate fechaExp)  //Es para el caso donde solo debo transportar el titulo y Fecha expriracion para algunas funciones que piden DTO
    {
        this.Titulo=titulo;
        this.fechaExpiracion=Date.from(fechaExp.atStartOfDay(ZoneId.systemDefault()).toInstant());;
    
    }
    
    public DTOPropuesta(String Titulo, String Descripcion, String Imagen, String Lugar, LocalDate Fecha, int Precio, int MontoTotal, LocalDate FechaPublicacion, List<TipoRetorno> Retorno, DTOCategoria cat, DTOProponente usr, Estado EstadoAct, List<Registro_Estado> _historialEstados, List<Colaboracion> _colaboradores, Map<String, String> comentarios) 
    {
        this.Titulo = Titulo;
        this.Descripcion = Descripcion;
        this.Imagen = Imagen;
        this.Lugar = Lugar;
        this.Fecha = Date.from(Fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());;
        this.Precio = Precio;
        this.MontoTotal = MontoTotal;
        this.FechaPublicacion = Date.from(FechaPublicacion.atStartOfDay(ZoneId.systemDefault()).toInstant());;
        this.cat = cat;
        this.usr = usr;
        this.EstadoAct = EstadoAct;
        this.comentarios = comentarios;

        for (int i = 0; i < _historialEstados.size(); i++) //Pasa de Lista Class normal a lista de DTO
        {
            historialEstados.add(new DTORegistro_Estado(_historialEstados.get(i).getFechaReg(), _historialEstados.get(i).getEstado()));
        }
        for (int b = 0; b < _colaboradores.size(); b++) {
            aporte.add(new DTOColaboracion(_colaboradores.get(b).getTipoRetorno(), _colaboradores.get(b).getMonto(), _colaboradores.get(b).getColaborador().getNickname(), _colaboradores.get(b).getPropuesta().getTitulo(), _colaboradores.get(b).getCreado()));
        }

    }
    
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setAporte(List<DTOColaboracion> aporte) {
        this.aporte = aporte;
    }
    
    
    public DTOPropuesta(String Titulo,String Descripcion,String Imagen ,String Lugar, LocalDate Fecha, int Precio, int MontoTotal,LocalDate FechaPublicacion,List<TipoRetorno> Retorno,DTOCategoria cat,DTOProponente usr,Estado EstadoAct, List<Registro_Estado> _historialEstados, List<Colaboracion> _colaboradores)
    {
        this.Titulo=Titulo;
        this.Descripcion=Descripcion;
        this.Imagen=Imagen;
        this.Lugar=Lugar;
        this.Fecha=Date.from(Fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());;
        this.Precio=Precio;
        this.MontoTotal=MontoTotal;
        this.FechaPublicacion=Date.from(FechaPublicacion.atStartOfDay(ZoneId.systemDefault()).toInstant());;
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

    public List<String> getAporteNick() {
        return aporteNick;
    }

    public void setAporteNick(List<String> aporteNick) {
        this.aporteNick = aporteNick;
    }
    
    
    public DTOPropuesta(Propuesta p, DTOProponente proponente) 
    {
        this.Titulo = p.getTitulo();
        this.Descripcion = p.getDescripcion();
        this.Imagen = p.getImagen();
        this.Lugar = p.getLugar();
        this.Fecha = Date.from(p.getFecha().atStartOfDay(ZoneId.systemDefault()).toInstant());;
        this.FechaString = p.getFecha().toString();
        this.Precio = p.getPrecio();
        this.MontoTotal = p.getMontoTotal();
        this.FechaPublicacion = Date.from(p.getFechaPublicacion().atStartOfDay(ZoneId.systemDefault()).toInstant());;
        this.FechaPublicacionString = p.getFechaPublicacion().toString();
        this.fechaExpiracionString=p.getFechaExpiracion().toString();
        this.Retorno = p.getRetorno();
        this.cat = p.getCategoria().Cat_a_DTO();
        this.usr = proponente;
        this.comentarios = p.getComentarios();
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
    public  String getImagen() {
        return Imagen;
    }
    public  String getLugar() {
        return Lugar;
    }
    public  String getCategorioToString() {
        return categoria;
    }
    public  Date getFecha() {
        return Fecha;
    }
    public int getPrecio() {
        return Precio;
    }
    public int getMontoTotal() {
        return MontoTotal;
    }
    public Date getFechaPublicacion() {
        return FechaPublicacion;
    }
    public List<TipoRetorno> getRetorno() {
        return Retorno;
    }
    public DTOCategoria getCategoria(){
        return cat;
    }
    public DTOProponente getProponente()
    {
        if(usr == null) //Control de error, no es reelevante al uso.
        {   //DTFecha a = new DTFecha(30,12,9999);
            LocalDate a =  LocalDate.of(9999,30,12);
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

    public void setImagen(String Imagen) {
        this.Imagen = Imagen;
    }
   
    public void setLugar(String lugar) {
        Lugar = lugar;
    }

    public void setFecha(LocalDate fecha) {
        Fecha = Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());;
    }

    public void setPrecio(int precio) {
        Precio = precio;
    }

    public void setMontoTotal(int montoTotal) {
        MontoTotal = montoTotal;
    }

    public Estado getEstadoAct() {
        return EstadoAct;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        FechaPublicacion = Date.from(fechaPublicacion.atStartOfDay(ZoneId.systemDefault()).toInstant());;
    }
    public void setRetornos(List<TipoRetorno> retorno) { 
        this.Retorno = retorno;     
    }
    public void setCategoria(DTOCategoria Cat){
        cat = Cat;
    }
    public void setProponente(DTOProponente Propo){
        usr = Propo;
    }

    public void setEstadoAct(Estado EstadoAct) {
        this.EstadoAct = EstadoAct;
    }
    
    public void setComentarios(Map<String,String> input)
    {
        this.comentarios = input;
    }
    
    public void addNewComentario(String usuario, String comentario)
    {
        if(!usuario.isEmpty() && !comentario.isEmpty())
        {
            this.comentarios.put(usuario,comentario);
        }
       
    }
    
    public void extraerDatosPropuesta(Propuesta in)
    {
        Titulo = in.getTitulo();
        Descripcion = in.getDescripcion();
        Imagen = in.getImagen();
        Lugar = in.getLugar();
        Fecha = Date.from(in.getFecha().atStartOfDay(ZoneId.systemDefault()).toInstant());;           
        Precio = in.getPrecio();
        MontoTotal = in.getMontoTotal();
        FechaPublicacion = Date.from(in.getFechaPublicacion().atStartOfDay(ZoneId.systemDefault()).toInstant());;
        fechaExpiracion = Date.from(in.getFechaExpiracion().atStartOfDay(ZoneId.systemDefault()).toInstant());;
        Retorno = in.getRetorno();
        comentarios = in.getComentarios();
        if (in.getCategoria() != null) {
            this.cat = in.getCategoria().Cat_a_DTO();
        } 
        else {
            this.cat = null; // o crear un DTO de categoría vacío si quieres
        }
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
            aporte.add(new DTOColaboracion(in.getAporte().get(b).getTipoRetorno(), in.getAporte().get(b).getMonto(), in.getAporte().get(b).getColaborador().getNickname(), in.getAporte().get(b).getPropuesta().getTitulo(), in.getAporte().get(b).getCreado(), in.getAporte().get(b).getDatosPago()));
        }
    }
        
    public void setHistorialEstados(DTORegistro_Estado historial) 
    {
        historialEstados.add(historial); 
    }
    public void setHistorialEstadosB(List<Registro_Estado> input) 
    {
        for (Registro_Estado ct : input)
        {
           historialEstados.add(new DTORegistro_Estado(ct.getFechaReg(),ct.getEstado()));  
        }
        
    }
   public void setColaboracion(DTOColaboracion c){
        aporte.add(c);
    }
    public void setColaboracionB(List<DTOColaboracion> c)
    {
        aporte = c;
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
             //return  historialEstados.getFirst();
            return historialEstados.get(0); 
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
    
    public Date getFechaExpiracion()
    {
        return fechaExpiracion;
    }
    
    public Map<String,String> getComentarios()
    {
        return comentarios;
    }
    
    public boolean usuarioHaComentadoSN(String nick)    
    {
        if(comentarios.containsKey(nick))
        {
            return true;    //El usuario ya comentó
        }
        
        return false;
    }
   
    public int chequearRecaudado(List<DTOColaboracion> aporteInput)
    {
        int recaudo = 0;
        
        if(aporteInput != null && !aporteInput.isEmpty())
        {
            for(DTOColaboracion ct : aporteInput)
            {
                recaudo += ct.getMonto();
            }
        }
        
        return recaudo;
    }

    public void setFechaString(String FechaString) {
        this.FechaString = FechaString;
    }

    public void setFechaPublicacionString(String FechaPublicacionString) {
        this.FechaPublicacionString = FechaPublicacionString;
    }

    public void setFechaExpiracionString(String fechaExpiracionString) {
        this.fechaExpiracionString = fechaExpiracionString;
    }

    public String getFechaString() {
        return FechaString;
    }

    public String getFechaPublicacionString() {
        return FechaPublicacionString;
    }

    public String getFechaExpiracionString() {
        return fechaExpiracionString;
    }
    
    
}

