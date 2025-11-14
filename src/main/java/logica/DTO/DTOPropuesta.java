package logica.DTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import logica.Colaboracion.Colaboracion;
import logica.Propuesta.Comentario;
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
    private LocalDate Fecha;
    @JsonIgnore
    private LocalDate FechaPublicacion;
    @JsonIgnore
    private LocalDate fechaExpiracion;
    
    private String FechaString;
    private String FechaPublicacionString;
    private String fechaExpiracionString;
    private int Precio;
    private int MontoTotal;
    private byte[] img;
    
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
   
    @JsonInclude(Include.NON_EMPTY)
    private List<Comentario> comentarios = new ArrayList<>();       
            
    public DTOPropuesta(){}
    
    public DTOPropuesta(String titulo, LocalDate fechaExp)  //Es para el caso donde solo debo transportar el titulo y Fecha expriracion para algunas funciones que piden DTO
    {
        this.Titulo=titulo;
        this.fechaExpiracion=fechaExp;
        this.fechaExpiracionString = (fechaExp != null) ? fechaExp.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "";

    }
    
    public DTOPropuesta(String Titulo, String Descripcion, String Imagen, String Lugar, LocalDate Fecha, int Precio, int MontoTotal, LocalDate FechaPublicacion, List<TipoRetorno> Retorno, DTOCategoria cat, DTOProponente usr, Estado EstadoAct, List<Registro_Estado> _historialEstados, List<Colaboracion> _colaboradores, List<Comentario> _comentarios) 
    {
        this.Titulo = Titulo;
        this.Descripcion = Descripcion;
        this.Imagen = Imagen;
        this.Lugar = Lugar;
        this.Fecha = Fecha;
        this.Precio = Precio;
        this.MontoTotal = MontoTotal;
        this.FechaPublicacion = FechaPublicacion;
        this.FechaString = (Fecha != null) ? Fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "";
        this.FechaPublicacionString=(FechaPublicacion != null) ? FechaPublicacion.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "";
        this.cat = cat;
        this.usr = usr;
        this.EstadoAct = EstadoAct;
        this.comentarios = _comentarios; 

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
        this.Fecha=Fecha;
        this.FechaString = (Fecha != null) ? Fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "";
        this.Precio=Precio;
        this.MontoTotal=MontoTotal;
        this.FechaPublicacion=FechaPublicacion;
        this.FechaPublicacionString = (FechaPublicacion != null) ? FechaPublicacion.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "";  
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

    public void setImg(byte[] img) {
        this.img = img;
    }

    public byte[] getImg() {
        return img;
    }

    
    public DTOPropuesta(Propuesta p, DTOProponente proponente) 
    {
        this.Titulo = p.getTitulo();
        this.Descripcion = p.getDescripcion();
        this.Imagen = p.getImagen();
        this.Lugar = p.getLugar();
        this.Fecha = p.getFecha();
        this.FechaString = (p.getFecha() != null) ? p.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "";       
        this.Precio = p.getPrecio();
        this.MontoTotal = p.getMontoTotal();
        this.FechaPublicacion = p.getFechaPublicacion();
        this.FechaPublicacionString = (p.getFechaPublicacion() != null) ? p.getFechaPublicacion().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "";
        this.fechaExpiracionString = (p.getFechaExpiracion() != null) ? p.getFechaExpiracion().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "";
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
    public  LocalDate getFecha() {
        return Fecha;
    }
    public int getPrecio() {
        return Precio;
    }
    public int getMontoTotal() {
        return MontoTotal;
    }
    public LocalDate getFechaPublicacion() {
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
        Fecha = fecha;
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
        FechaPublicacion = fechaPublicacion;
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
    
    public void setComentarios(List<Comentario> input)
    {
        this.comentarios = input;
    }
    
    public void addNewComentario(String usuario, String comentario)
    {
        if(!usuario.isEmpty() && !comentario.isEmpty())
        {
            
            Comentario temp = new Comentario (usuario,comentario);
            this.comentarios.add(temp);
        }
       
    }
    
    public void extraerDatosPropuesta(Propuesta in)
    {
        Titulo = in.getTitulo();
        Descripcion = in.getDescripcion();
        Imagen = in.getImagen();
        Lugar = in.getLugar();
        Fecha = in.getFecha();
        FechaString = (this.Fecha != null) ? this.Fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "";
        Precio = in.getPrecio();
        MontoTotal = in.getMontoTotal();
        FechaPublicacion = in.getFechaPublicacion();
        fechaExpiracion = in.getFechaExpiracion();
        FechaPublicacionString = (this.FechaPublicacion != null) ? this.FechaPublicacion.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "";
        fechaExpiracionString = (this.fechaExpiracion != null) ? this.fechaExpiracion.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "";
        EstadoAct = in.getHistorialEstados().get(0).getEstado();
        Retorno = in.getRetorno();
        comentarios = in.getComentarios();
        categoria = in.getCategoria().getNombreCategoria();
        
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
    
    public LocalDate getFechaExpiracion()
    {
        return fechaExpiracion;
    }
    
    public List<Comentario> getComentarios()
    {
        return comentarios;
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
    
    public boolean usuarioHaComentadoSN(String userNick)
    {
        for(Comentario ct : comentarios)
        {
            if(ct.getNickUsuario().equals(userNick))
            {
                return true;
            }
        }
        
        return false;
    }
}

