package logica;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import logica.Categoria.Categoria;
import persistencia.ManejadorPropuesta;
import persistencia.ManejadorCategoria;
import persistencia.ManejadorColaboracion;
import logica.DTO.DTOCategoria;
import logica.DTO.DTOColaborador;
import logica.DTO.DTOProponente;
import logica.DTO.DTOUsuario;
import logica.Colaboracion.Colaboracion;
import logica.DTO.DTOColaboracion;
import logica.Propuesta.Propuesta;
import logica.Usuario.Usuario;
import persistencia.ManejadorUsuario;
import logica._enum.TipoRetorno;
import logica.DTO.DTOPropuesta;
import logica.DTO.DTORegistro_Estado;
import logica.Propuesta.Registro_Estado;
import logica.Usuario.Colaborador;
import logica.Usuario.Proponente;
import logica._enum.Estado;

public class Controller  implements IController {
    private ManejadorUsuario mUsuario=ManejadorUsuario.getInstance();
    private ManejadorCategoria mCategoria=ManejadorCategoria.getInstance();
    private ManejadorPropuesta mPropuesta=ManejadorPropuesta.getinstance();
    private ManejadorColaboracion mColaboraciones = ManejadorColaboracion.getInstance();
    

       
    //Usuarios
    @Override
    public void altaUsuario(DTOUsuario usu) {
        if(usu instanceof DTOProponente){
            mUsuario.addProponente((DTOProponente) usu);
        }else{
            mUsuario.addColaborador((DTOColaborador) usu);
        }
    }
    
    @Override
    public boolean existeUsuario(String nick, String email) {
           return (mUsuario.existe(nick) || mUsuario.emailUsado(email));
    }
 
    
     public boolean existe(String nick){
            return mUsuario.existe(nick);
    }
    
     @Override
     public List<String> ListaUsuarios(){
         List<String> aux = new ArrayList<>();
         for (DTOUsuario c : mUsuario.getUsuarios().values()){
                 aux.add(c.getNickname());
         }
        return aux;    
     }
    @Override
     public List<String> ListaProponentes(){
         List<String> aux = new ArrayList<>();
         for (DTOUsuario c : mUsuario.getUsuarios().values()){
            if (c instanceof DTOProponente){
                 aux.add(c.getNickname());
            }
         }
        return aux;    
     }
     @Override
     public List<String> ListaColaborador(){
         List<String> aux = new ArrayList<>();
         for (DTOUsuario c : mUsuario.getUsuarios().values()){
            if (c instanceof DTOColaborador){
                 aux.add(c.getNickname());
            }
         }
        return aux;    
     }
     
     @Override
     public List<String> ListaSeguidosPorUsuario(String nick){
        /* List<String> aux = new ArrayList<>();
         Usuario usuario = mUsuario.buscador(nick);
         if (usuario != null && usuario.getUsuarioSeguido() != null) {
            for(Usuario u:usuario.getUsuarioSeguido().values()){
                aux.add(u.getNickname());
            }
         }*/
         return mUsuario.listaSeguidos(nick);
     }
     
    public List<DTOColaboracion>  colaboraciones(String nick){
           return mUsuario.getDTOColaboraciones(nick);
    }
     
     
     @Override
      public List<String> colaboradoresAPropuesta(String titulo){
         return  mPropuesta.listColaboradores(titulo);
      }
     
     @Override
     public boolean seguir(String nick1,String nick2){
         
         return mUsuario.seguirUsr(nick1,nick2);
     
     }
     @Override
     public boolean unFollowUser(String usuarioActual, String usuarioToUnfollow)
     {
        return (mUsuario.getUsuario(usuarioActual).unfollow(mUsuario.getUsuario(usuarioToUnfollow)));  
     }
     
    // Funciones que devuelven Distintos DTO 
    public DTOColaboracion getDTOAporte(Colaboracion r,String titulo){
        return new DTOColaboracion(r.getTipoRetorno(),r.getMonto(),r.getColaborador().getNickname(),titulo,r.getCreado());
    }
    
  
    public DTORegistro_Estado getDTORegistroEstado(Registro_Estado r){
        return new DTORegistro_Estado(r.getFechaReg(),r.getEstado());
    }
    
    //devuelve un dtoPropuesta con el historial de estado y aportes Recibido
    public DTOPropuesta getDTOPropuesta(Propuesta p,DTOProponente prop){
            DTOPropuesta propuesta= new DTOPropuesta(p,prop);
            List<Registro_Estado> r=p.getHistorialEstados();
            List<Colaboracion> rA = p.getAporte();
            
            for(Registro_Estado re:r){
                propuesta.setHistorialEstados(getDTORegistroEstado(re));
            }

            return propuesta;
    }
    
    @Override
    //me crea un dtoProponente datos basicos
    public DTOProponente getDTOProponente(String nick) { 
           Proponente usr= (Proponente) mUsuario.getUsuario(nick);
          DTOProponente resu=new DTOProponente(usr);
          
          //Map<String,DTOPropuesta> p=mUsuario.getPropuestasCreadas(resu);
           
          //resu.setPropCreadas(p);
           
           return resu;
    }
    
    public Set<DTOPropuesta> getPropuestasCreadasPorProponente(String nick){
        
        return mUsuario.getPropuestasCreadasPorProponente(nick);
    }
    
 
    @Override
     public DTOColaborador getDTOColaborador(String nick) { 
           Colaborador usr= (Colaborador) mUsuario.getUsuario(nick);
           DTOColaborador resu=new DTOColaborador(usr);
           
           return resu;
    }
     //Fin de Devolucion de DTO
    
   
    @Override
    public void altaPropuesta(String Titulo, String Descripcion, String Tipo, String Imagen, String Lugar, LocalDate Fecha, int Precio, int MontoTotal,LocalDate fechaPublicacio, List<TipoRetorno> Retorno, String cat, String usr,Estado est) {
        

        Propuesta propuesta = new Propuesta (Titulo, Descripcion, Tipo, Imagen, Lugar, Fecha, Precio, MontoTotal, fechaPublicacio ,Retorno, mCategoria.buscadorC(cat), (Proponente) mUsuario.getUsuario(usr),est);

        mPropuesta.nuevaPropuesta(propuesta);
    }
    @Override
     public Set<DTOPropuesta> obtenerPropuestas(String estado){
        return  mPropuesta.obtenerPropuestas(estado);
     }
     
    @Override
      public boolean existeProp(String Titulo){
         return (mPropuesta.existeProp(Titulo));
    }
      
      @Override
      public String creadorPropuesta(String titulo){
          return mPropuesta.obtenerNombreCreadorPropuesta(titulo);
      }
      @Override
      public String estadoPropuestas(String titulo){
          return mPropuesta.obtenerEstado(titulo);
      }
      //Fin Propuesta
    
      //Categoria
    @Override
    public boolean altaDeCategoria(DTOCategoria categoriaIngresada) 
    {
        return mCategoria.addCategoria(categoriaIngresada);

    }
    @Override
    public List<DTOCategoria> getCategorias() 
    {
        
        return mCategoria.getCategorias();
    }
    
    @Override
    public List<String> ListaCategoria()
    {
         List<String> aux2 = new ArrayList<>();
         for(DTOCategoria c : mCategoria.getCategorias())
         {
             aux2.add(c.getNombreCategoria());
         }
             return aux2; 
    }
    
    //Propuesta
    
    @Override
    public void modificarPropuesta(String titulo, String descripcion, String tipo,String rutaImagen, String lugar, LocalDate fechaEvento,int precio, int montoTotal, List<TipoRetorno> retorno,String categoria, String usuarios, Estado estado) {
        Propuesta propuestaSeleccionada = null;
        propuestaSeleccionada = mPropuesta.buscarPropuestaPorTitulo(titulo);
        if (propuestaSeleccionada != null){  
            mPropuesta.UpdatePropuesta(titulo, descripcion, tipo, rutaImagen, lugar, fechaEvento,precio, montoTotal, retorno, categoria, usuarios, estado);
        }
     }
     @Override
    public Set<DTOPropuesta> ListarPropuestas(String estado1, String estado2) {
        Set<DTOPropuesta>propuestaEstado1=mPropuesta.obtenerPropuestas(estado1); //estado publicado
        Set<DTOPropuesta> propuestaEstado2=mPropuesta.obtenerPropuestas(estado2);//estado en financiacion
        propuestaEstado1.addAll(propuestaEstado2);
        return propuestaEstado1;
    }
    
    @Override
    public void altaColaboracion(DTOColaboracion colaboracion){
  
        mColaboraciones.addColaboracion(colaboracion);
        
    }

    public Set<DTOColaborador> ListarColaboradores() {
        return mUsuario.listaColaboradores();
    }

    @Override
    public Set<Colaboracion> ListarColaboracionesDeColaborador(String nickname) {
        return mColaboraciones.getColaboracionesDeColaborador(nickname);
    }
    
    @Override
    public void CancelarColaboracion(String nick, String proponente){
         mColaboraciones.deleteColaboracion(nick,proponente);
    }

    @Override
    public Set<DTOColaborador> ListarColaboradres() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    @Override
    public int getMontoRecaudado(String titulo){
        return mPropuesta.getMontoRecaudado(titulo);
    }
    @Override
    public boolean colaboracionExiste(String colaborador, String titulo){
        
        return mUsuario.existeColaboracion(colaborador, titulo); 
    }
    @Override
    public Set<DTOColaboracion> getDTOColaboraciones(){
        return mColaboraciones.getColaboraciones();
        
    }
}

  
