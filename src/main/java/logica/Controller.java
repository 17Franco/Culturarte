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
       
       
    @Override
    public void altaUsuario(DTOUsuario usu) {
        if(usu.isProponente()){
            mUsuario.addProponente((DTOProponente) usu);
        }else{
            mUsuario.addColaborador((DTOColaborador) usu);
        }
    }
    
     public boolean existe(String nick){
            return mUsuario.existe(nick);
    }
     
    @Override
    public boolean existeUsuario(String nick, String email) {
           return (mUsuario.existe(nick) || mUsuario.emailUsado(email));
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
     
    //devulve un dtoRegistroAPORTE
    public DTORegistro_Aporte getDTOAporte(registroAporte r,String titulo){
        return new DTORegistro_Aporte(r.getMoto(),r.getRetorno(),r.getFecha(),titulo,r.getColaborador().getNickname());
    }
    
    //Devuelve un dto de estado
    public DTORegistro_Estado getDTORegistroEstado(Registro_Estado r){
        return new DTORegistro_Estado(r.getFechaReg(),r.getEstado());
    }
    
    //devuelve un dtyoPropuesta con el historial de estado y aportes Recibido
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

       
}
