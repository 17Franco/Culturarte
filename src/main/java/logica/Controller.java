package logica;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import logica.Categoria.Categoria;
import logica.Propuesta.ManejadorPropuesta;
import logica.Categoria.ManejadorCategoria;
import logica.DTO.DTOCategoria;
import logica.DTO.DTOColaborador;
import logica.DTO.DTOProponente;
import logica.DTO.DTOUsuario;
import logica.DTO.DTFecha;
import logica.Propuesta.Propuesta;
import logica.Usuario.Usuario;
import logica.Usuario.ManejadorUsuario;
import logica._enum.TipoRetorno;
import logica.DTO.DTOPropuesta;
import logica.DTO.DTORegistro_Aporte;
import logica.DTO.DTORegistro_Estado;
import logica.Propuesta.Registro_Estado;
import logica.Usuario.Colaborador;
import logica.Usuario.Proponente;
import logica.Usuario.registroAporte;
import logica._enum.Estado;
/**
 *
 * @author fran
 */
public class Controller  implements IController {
    private ManejadorUsuario mUsuario=ManejadorUsuario.getInstance();
    private ManejadorCategoria mCategoria=ManejadorCategoria.getInstance();
    private ManejadorPropuesta mPropuesta=ManejadorPropuesta.getinstance();
       
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
         for (Usuario c : mUsuario.getUsuarios().values()){
                 aux.add(c.getNickname());
         }
        return aux;    
     }
    @Override
     public List<String> ListaProponentes(){
         List<String> aux = new ArrayList<>();
         for (Usuario c : mUsuario.getUsuarios().values()){
            if (c instanceof Proponente){
                 aux.add(c.getNickname());
            }
         }
        return aux;    
     }
     @Override
     public List<String> ListaColaborador(){
         List<String> aux = new ArrayList<>();
         for (Usuario c : mUsuario.getUsuarios().values()){
            if (c instanceof Colaborador){
                 aux.add(c.getNickname());
            }
         }
        return aux;    
     }
     @Override
     public List<String> ListaSeguidosPorUsuario(String nick){
         List<String> aux = new ArrayList<>();
         Usuario usuario = mUsuario.buscador(nick);
         if (usuario != null && usuario.getUsuarioSeguido() != null) {
            for(Usuario u:usuario.getUsuarioSeguido().values()){
                aux.add(u.getNickname());
            }
         }
         return aux;
     }
     
     @Override
     public boolean seguir(String nick1,String nick2){
        Usuario usuario1 = mUsuario.buscador(nick1);
        Usuario usuario2 = mUsuario.buscador(nick2);
        
        if (usuario1 == null || usuario2 == null) return false;
        if (nick1.equals(nick2)) return false;
        if (usuario1.getUsuarioSeguido().containsKey(nick2)) return false;

        usuario1.seguir(usuario2);
        return true;
         
     }
     
    @Override
    public boolean unFollowUser(String usuarioActual, String usuarioToUnfollow) 
    {
       return (mUsuario.buscador(usuarioActual).unfollow(mUsuario.buscador(usuarioToUnfollow)));   
    }
     
    // Funciones que devuelven Distintos DTO 
    public DTORegistro_Aporte getDTOAporte(registroAporte r,String titulo){
        return new DTORegistro_Aporte(r.getMoto(),r.getRetorno(),r.getFecha(),titulo,r.getColaborador().getNickname());
    }
    
  
    public DTORegistro_Estado getDTORegistroEstado(Registro_Estado r){
        return new DTORegistro_Estado(r.getFechaReg(),r.getEstado());
    }
    
    //devuelve un dtoPropuesta con el historial de estado y aportes Recibido
    public DTOPropuesta getDTOPropuesta(Propuesta p,DTOProponente prop){
            DTOPropuesta propuesta= new DTOPropuesta(p,prop);
            List<Registro_Estado> r=p.getHistorialEstados();
            List<registroAporte> rA = p.getAporte();
            
            for(Registro_Estado re:r){
                propuesta.setHistorialEstados(getDTORegistroEstado(re));
            }
            
            for(registroAporte registro:rA){
                propuesta.setAportes(getDTOAporte(registro,propuesta.getTitulo()));
            }

            return propuesta;
    }
    
    @Override
    //me crea un dtoProponente completo incluido las propuestas que el usuario creo
    public DTOProponente getDTOProponente(String nick) { 
           Proponente usr= (Proponente) mUsuario.buscador(nick);
           DTOProponente resu=new DTOProponente(usr);
          
           Map<String,Propuesta> p=usr.getPropCreadas();
           
           for(Propuesta prop:p.values()){
               resu.addDTOPropuesta(getDTOPropuesta(prop,resu));
           }
           
           return resu;
    }
     public DTOColaborador getDTOColaborador(String nick) { 
           Colaborador usr= (Colaborador) mUsuario.buscador(nick);
           DTOColaborador resu=new DTOColaborador(usr);
          
          List<registroAporte> registro=usr.getColaboraciones();
           
          for(registroAporte r:registro){
              resu.setColaboracion(getDTOAporte(r,r.getColabPropuesta().getTitulo()));
          }
           
           return resu;
    }
     //Fin de Devolucion de DTO
    
     //Propuesta
    public void altaPropuesta(String Titulo, String Descripcion, String Tipo, String Imagen, String Lugar, DTFecha Fecha, String Precio, String MontoTotal,DTFecha fechaPublicacio, TipoRetorno Retorno, String cat, String usr,Estado est) {
        
        Propuesta propuesta = new Propuesta (Titulo, Descripcion, Tipo, Imagen, Lugar, Fecha, Precio, MontoTotal, fechaPublicacio ,Retorno, mCategoria.buscadorC(cat), (Proponente) mUsuario.buscador(usr),est);
        ((Proponente) mUsuario.buscador(usr)).setPropuestaCreada(propuesta);
        mPropuesta.nuevaPropuesta(propuesta);
    }
    @Override
     public Set<DTOPropuesta> obtenerPropuestas(String estado){
        return  mPropuesta.obtenerPropuestas(estado);
     }
     
      public boolean existeProp(String Titulo){
         return (mPropuesta.existeProp(Titulo));
    }
      //Fin Propuesta
      
      //Categoria
    @Override
    public boolean altaDeCategoria(DTOCategoria categoriaIngresada)
    {
       if(mCategoria.existe(categoriaIngresada) == 1) //por ahora la opcion "2" no se usa
       {    //Si no existen previamente.
            mCategoria.addCategoria(categoriaIngresada);
            return true;    //Le dice a UI que todo fue correcto.
       }
       
       return false;    //Le dice a ui que no se agregó nada.
    }
    
    @Override
    public Map<String, Categoria> getCategorias()
    {
        return mCategoria.getCategorias();
    }
    
   
     public List<String> ListaCategoria(){
         List<String> aux2 = new ArrayList<>();
         for (Categoria c : mCategoria.getCategorias().values()){
             aux2.add(c.getNombreCategoria());
         }
             return aux2; 
     }
     public void modificarPropuesta(String titulo, String descripcion, String tipo,String rutaImagen, String lugar, DTFecha fechaEvento,String precio, String montoTotal, TipoRetorno retorno,String categoria, String usuarios, Estado estado) {  
        Propuesta propuestaSeleccionada = null;
        propuestaSeleccionada = mPropuesta.buscarPropuestaPorTitulo(titulo);
        if (propuestaSeleccionada != null){
            propuestaSeleccionada.setDescripcion(descripcion);
            propuestaSeleccionada.setTipo(tipo);
            propuestaSeleccionada.setImagne(rutaImagen);
            propuestaSeleccionada.setLugar(lugar);
            propuestaSeleccionada.setFecha(fechaEvento);
            propuestaSeleccionada.setPrecio(precio);
            propuestaSeleccionada.setMontoTotal(montoTotal);
            propuestaSeleccionada.setRetorno(retorno);
            propuestaSeleccionada.setCategoria(mCategoria.buscadorC(categoria));
            propuestaSeleccionada.setEstadoAct(estado); //este no lo actualiza reviso despues ,pero esta llegando el dato que quiero para cambiar
        }
     }

}
