package logica;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import logica.Manejadores.ManejadorPropuesta;
import logica.Manejadores.ManejadorCategoria;
import logica.Manejadores.ManejadorColaboracion;
import logica.Manejadores.ManejadorRegistros;
import logica.DTO.DTOCategoria;
import logica.DTO.DTOColaborador;
import logica.DTO.DTOProponente;
import logica.DTO.DTOUsuario;
import logica.Colaboracion.Colaboracion;
import logica.DTO.DTOColaboracion;
import logica.Propuesta.Propuesta;
import logica.Manejadores.ManejadorUsuario;
import logica.DTO.TipoRetorno;
import logica.DTO.DTOPropuesta;
import logica.DTO.DTORegistro_Estado;
import logica.Propuesta.Registro_Estado;
import logica.Usuario.Colaborador;
import logica.Usuario.Proponente;
import logica.DTO.Estado;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import logica.DTO.DTORegistrosAccesoWeb;

public class Controller  implements IController {
    private ManejadorUsuario mUsuario=ManejadorUsuario.getInstance();
    private ManejadorCategoria mCategoria=ManejadorCategoria.getInstance();
    private ManejadorPropuesta mPropuesta=ManejadorPropuesta.getinstance();
    private ManejadorColaboracion mColaboraciones = ManejadorColaboracion.getInstance();
    private ManejadorRegistros mRegistros = ManejadorRegistros.getInstance();
    
  
    //USUARIOS
    @Override
    public void altaUsuario(DTOUsuario usu) {
        if(usu instanceof DTOProponente){
            mUsuario.addProponente((DTOProponente) usu);
        }else{
            mUsuario.addColaborador((DTOColaborador) usu);
        }
    }
    
     @Override
    public void registroUsuario(String nickname, String pass, String nombre, String apellido, String email, LocalDate fecha, byte[] contenido,String nombreArchivo,boolean isProponente,String direccion,String web,String Biografia){
        String ruta = obtenerPathImg(nickname,contenido,nombreArchivo);
        if(isProponente){
            
            DTOProponente p=new DTOProponente(direccion,Biografia,web,nickname,pass,nombre,apellido,email,fecha,ruta,"Proponente");
            mUsuario.addProponente((DTOProponente) p);
        }else{
            DTOColaborador c= new DTOColaborador(nickname,pass,nombre,apellido,email,fecha,ruta,"Colaborador");
            mUsuario.addColaborador((DTOColaborador) c);
        }
    
    }
    
    @Override
    public boolean existeUsuario(String nick, String email) {
           return (mUsuario.existe(nick) || mUsuario.emailUsado(email));
    }
    
    public boolean emailUsado(String email){
        return mUsuario.emailUsado(email);
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
    public List<String> ProponenteEliminados(){
        List<String> aux = new ArrayList<>();
        for (DTOUsuario c : mUsuario.getProponenteEliminados().values()){
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
    public List<DTOColaboracion>  colaboraciones(String nick){
           return mUsuario.getDTOColaboraciones(nick);
    }
    
    @Override
    public List<String> ListaSeguidosPorUsuario(String nick){

        return mUsuario.listaSeguidos(nick);
    }
    
    @Override
    //me crea un dtoProponente datos basicos
    public DTOProponente getDTOProponente(String nick) { 
        Proponente usr= (Proponente) mUsuario.getUsuario(nick);
        DTOProponente resu=new DTOProponente(usr);

        return resu;
    }
    
    @Override
    public DTOColaborador getDTOColaborador(String nick) { 
        Colaborador usr= (Colaborador) mUsuario.getUsuario(nick);
        DTOColaborador resu=new DTOColaborador(usr);

        return resu;
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
    public boolean unFollowUser(String usuarioActual, String usuarioToUnfollow){
       return mUsuario.dejarDeSeguirUsuario(usuarioActual, usuarioToUnfollow);  
    }
    
    public Set<DTOPropuesta> getPropuestasCreadasPorProponente(String nick){

        return mUsuario.getPropuestasCreadasPorProponente(nick);
    }
    
    //METODOS QUE SE USAN EN WEB
    
    @Override
    public boolean login(String nick,String Pass){
        return mUsuario.verificarCredenciales(nick,Pass);
    }
    
    @Override
    public boolean isProponente(String nick){
        return mUsuario.isProponente(nick);
    }
    
    @Override
    public List<DTOUsuario> rankingUsuarios(){
        List<DTOUsuario> u=new ArrayList<>();
        u=mUsuario.rankUsuario();
        return u;
    }
    public String obtenerPathImg(String nick,byte[] contenido,String nombreArchivo){
        if(!nombreArchivo.equals("")){
        //String RUTA_IMAGENES = "/IMG"; //RUTA PARA DOCKER
        String RUTA_IMAGENES = "/home/fran/Escritorio/Lab2PA/IMG"; //configurar en cada maquina o buscar solucion
        String resdir="IMG" + File.separator+ nick +File.separator+ nombreArchivo;//la direccion que guardare en la bd
        String carpetaDestino = RUTA_IMAGENES + File.separator + nick;
        File dir = new File(carpetaDestino);
        if (!dir.exists()) dir.mkdirs();
        
        Path destino = Paths.get(carpetaDestino, nombreArchivo);

        try {
            Files.write(destino, contenido); 
            return resdir;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        }
        
        return "";    
    }
    
    @Override
    public byte[] getImg(String ruta) {
        String RUTA_IMAGENES = "/home/fran/Escritorio/Lab2PA";
        try{
        File img=new File(RUTA_IMAGENES + File.separator +ruta);
        //File img=new File(File.separator +ruta); //ESTO ES PARA DOCKER
        if(!img.exists()) return null;
        //byte[] img= new ;
        
          return Files.readAllBytes(img.toPath());//devuelve array de bytes de la img 
        }catch(IOException i){
            i.printStackTrace();
            return null;
        }
    }
    
    @Override
    public List<DTOUsuario> ListaDTOUsuarios(){
        return mUsuario.getListDTOUsuario();
    }
    
    @Override
    public void marcarComoFavorita(String nickname, String tituloPropuesta) {     
        mUsuario.marcarComoFavorita(nickname, tituloPropuesta);
    }
    @Override
    public void quitarFavorita(String nickname, String tituloPropuesta) {   
        mUsuario.quitarFavorita(nickname, tituloPropuesta);
    }
    @Override
    public boolean esFavorita(String nickname, String tituloPropuesta) {
        return mUsuario.esFavorita(nickname, tituloPropuesta);
    }
    
    @Override
    public boolean sigueAUsuario(String seguidor,String Seguido){
        return mUsuario.sigue(seguidor,Seguido);
    }
    
    @Override
    public List<DTOUsuario> Seguidos(String nick){
    
        return mUsuario.getSeguidos(nick);
    }
    
    @Override
    public List<DTOUsuario> getSeguidores(String nick){
        return mUsuario.obtenerSeguidores(nick);
    }

    @Override
    public List<DTOPropuesta> getFavoritas(String nick){
        return mUsuario.getFavoritas(nick);
    }
    
    @Override
    public boolean eliminarProponente(String nick){
        return mUsuario.eliminarProponente(nick);
    }
    //FIN METODOS QUE SE USAN EN WEB (USUARIOS)
    
    //FIN USUARIOS
    
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
    
    //PROPUESTAS
    
    @Override
    public void altaPropuesta(String Titulo, String Descripcion, String Imagen, String Lugar, LocalDate Fecha, int Precio, int MontoTotal,LocalDate fechaPublicacio, List<TipoRetorno> Retorno, String cat, String usr,Estado est) {
        
        Propuesta propuesta = new Propuesta (Titulo, Descripcion,Imagen, Lugar, Fecha, Precio, MontoTotal, fechaPublicacio ,Retorno, mCategoria.buscadorC(cat), (Proponente) mUsuario.getUsuario(usr),est);
        mPropuesta.nuevaPropuesta(propuesta);
    }
    
    @Override
    public void modificarPropuesta(String titulo, String descripcion,String rutaImagen, String lugar, LocalDate fechaEvento,int precio, int montoTotal, List<TipoRetorno> retorno,String categoria, String usuarios, Estado estado) {
        Propuesta propuestaSeleccionada = null;
        propuestaSeleccionada = mPropuesta.buscarPropuestaPorTitulo(titulo);
        if (propuestaSeleccionada != null){  
            mPropuesta.UpdatePropuesta(titulo, descripcion,rutaImagen, lugar, fechaEvento,precio, montoTotal, retorno, categoria, usuarios, estado);
        }
    }
    
    @Override
      public boolean existeProp(String Titulo){
         return (mPropuesta.existeProp(Titulo));
    }
    
    @Override
    public Set<DTOPropuesta> obtenerPropuestas(String estado){
        return  mPropuesta.obtenerPropuestas(estado);
    }
    
    @Override
    public DTOPropuesta getPropuestaDTO(String propuestaSel)
    {
        return mPropuesta.getPropuestaDTO(propuestaSel);

    }
    
    @Override
    public String creadorPropuesta(String titulo){
        return mPropuesta.obtenerNombreCreadorPropuesta(titulo);
    }
      
    @Override
    public String estadoPropuestas(String titulo){
        return mPropuesta.obtenerEstado(titulo);
    }
    
    @Override
    public Set<DTOPropuesta> ListarPropuestas(String estado1, String estado2) {
        Set<DTOPropuesta>propuestaEstado1=mPropuesta.obtenerPropuestas(estado1); //estado publicado
        Set<DTOPropuesta> propuestaEstado2=mPropuesta.obtenerPropuestas(estado2);//estado en financiacion
        propuestaEstado1.addAll(propuestaEstado2);
        return propuestaEstado1;
    }
    
    //SE USAN EN WEB (PROPUESTAS)
    
    @Override
    public int accionSobrePropuesta(String nickUsuario, DTOPropuesta propuestaSel)
    {
        //Permite habilitar botones en cliente web (CU Obtener propuestas):
        //Retorna:  
        //          1: El usuario es proponente.
        //          2: El usuario es colaborador.
        //          3: El usuario no ha participado aún en la propuesta.

        if (propuestaSel.nickProponenteToString().equals(nickUsuario)) //Si es proponente
        {
            return 1;            
        } 
        else 
        {
            List<DTOColaboracion> t1 = propuestaSel.getAporte();

            for (DTOColaboracion ct : t1) 
            {
                if (ct.getColaborador().equals(nickUsuario)) //Si es colaborador
                {
                    return 2;
                }
            }
        }

        return 3;   //Si no es ninguno de los dos. 
    }
    
    @Override
    public int extenderOCancelarPropuesta(String accionUsuario,String tituloPropuesta)
    {
        if(accionUsuario != null && tituloPropuesta != null)
        {
            //CASO CANCELAR PROPUESTA
            if (accionUsuario.equals("CANCELAR")) 
            {
                mPropuesta.cancelarPropuestaSeleccionada(tituloPropuesta);
                return 2; //Proponente logra cancelar.
            }

            //CASO EXTENDER FINANCIACION
            if(accionUsuario.equals("EXTENDER")) 
            {
                mPropuesta.extenderFinanciacion(tituloPropuesta);
                return 3; //Proponente logra extender.
            }
        }
        
        return 0;
    }
    
    @Override
    public boolean nuevoComentario(String comentario,String userNick,String tituloPropuesta)
    {
        if(userNick != null && !userNick.isEmpty() && mPropuesta.nuevoComentario(comentario, userNick, tituloPropuesta))
        {
            return true;
        }
        
        return false;
    }
    
    @Override
    public int accionesSobrePropuesta(String userNick, int permisos, String accionUsuario,String comentario, DTOPropuesta propuestaActual, String montoStr, String tipoRetorno)
    {
        //Seteo tipos de retorno.
        TipoRetorno retorno = null;
        
        if(tipoRetorno != null)
        {
            if(tipoRetorno.equals("EntradaGratis"))     { retorno = TipoRetorno.EntradaGratis; }
            if(tipoRetorno.equals("PorcentajeGanancia")){ retorno = TipoRetorno.PorcentajeGanancia; }
        }
        
        int resultadoOperacion = 0;
        
        //Si es proponente...
        if(permisos == 1)   
        {        
            //Se verifica que sea una propuesta de este proponente (esto puede ser pasado a una funcion en controller).
            Set<DTOPropuesta> temp = getPropuestasCreadasPorProponente(userNick);
            
            for(DTOPropuesta ct : temp)
            {
                if(ct.nickProponenteToString().equals(userNick))
                {
                    resultadoOperacion = extenderOCancelarPropuesta(accionUsuario,ct.getTitulo());
                    //resultado 3, logra extender, resultado 2, logra cancelar, 0, no sucedió nada.
                }
            }
        }  
    
        //Si es colaborador que ya colaboró...
        if(permisos == 2)
        {
            if(accionUsuario.equals("COMENTAR"))
            {
                if(nuevoComentario(comentario,userNick,propuestaActual.getTitulo()))
                {
                    resultadoOperacion = 1; //Usuario logra comentar.
                }
                
            }   
        }
        
        //Si es colaborador que no colaboró aún y decide colaborar con la propuesta...
        if(permisos == 3) 
        {   
            int monto = -1;

            if(accionUsuario.equals("COLABORAR"))
            {

                if (montoStr != null && !montoStr.isEmpty()) 
                {
                    try //Controlar que no haya algun error extraño y asi evitar corromper la base de datos
                    {
                        monto = Integer.parseInt(montoStr);
                    } 
                    catch (NumberFormatException e){}
                }
            }
                
            if(monto != -1) //Si no hubo error con el monto...
            {

                DTOColaborador usuarioActual = (DTOColaborador) getDTOColaborador(userNick);
                DTOColaboracion nuevaColaboracion = new DTOColaboracion(retorno,monto,usuarioActual.getNickname(),propuestaActual.getTitulo(),LocalDate.now(),usuarioActual,propuestaActual);
                altaColaboracion(nuevaColaboracion);    
                resultadoOperacion = 4;
            }
        }
        
        return resultadoOperacion;
    }
    
    @Override
    public int permisosSobrePropuesta(String userNick, String tipoUsuario, DTOPropuesta propuestaActual)
    {
        int permisos = 0;
        
        if (tipoUsuario != null && !userNick.equals("VISITANTE") && propuestaActual.getTitulo() != null) 
        {
            
            if(!propuestaActual.usuarioHaComentadoSN(userNick))
            {
                permisos = accionSobrePropuesta(userNick, propuestaActual);  //Se obtienen permisos de usuario en propuesta.
            }
            
            if(permisos == 3 && tipoUsuario.equals("Proponente"))   //Esto es por si un proponente visita otras props...
            {
                permisos = 0;   //Le quito el permiso de colaborar, lo dejo por si más adelante se agrega que puede o algo así.
            }
            
        }
        
        return permisos;
    }
    //FIN WEB (PROPUESTAS)
    
    //Fin Propuesta
    
    //Categoria
    @Override
    public boolean altaDeCategoria(DTOCategoria categoriaIngresada) {
        return mCategoria.addCategoria(categoriaIngresada);
    }
    
    @Override
    public List<DTOCategoria> getCategorias(){
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
    //FIN CATEGORIA
    
    
    //COLABORACIONES
    @Override
    public void altaColaboracion(DTOColaboracion colaboracion){
        mColaboraciones.addColaboracion(colaboracion);
        //actualizo despues de agregar la colaboracion 
        mPropuesta.actualizarEstado(colaboracion.getPropuesta());
        
    }
    
    @Override
    public boolean colaboracionExiste(String colaborador, String titulo){
        
        return mUsuario.existeColaboracion(colaborador, titulo); 
    }
    
    @Override
    public int getMontoRecaudado(String titulo){
        return mPropuesta.getMontoRecaudado(titulo);
    }
    
    @Override
    public void CancelarColaboracion(Long id){
         mColaboraciones.deleteColaboracion(id);
    }
    
    @Override
    public Set<DTOColaboracion> getDTOColaboraciones(){
        return mColaboraciones.getColaboraciones();
        
    }
    
    public Set<DTOColaborador> ListarColaboradores() {
        return mUsuario.listaColaboradores();
    }
    
    public boolean acreditarColaboracion(Long id)
    {
        return mColaboraciones.acreditarColaboracion(id);
    }
    
    //FIN COLABORACIONES
    
    //Inicio Registros
    
    @Override
    public List<DTORegistrosAccesoWeb> obtenerRegistrosAccesoWeb()
    {
        return mRegistros.obtenerRegistrosAccesoWeb();
    }
    
    @Override
    public boolean agregarRegistroAccesoWeb(DTORegistrosAccesoWeb input)
    {
        return mRegistros.agregarRegistroAccesoWeb(input);
    }
    
    //Fin Registros
    
    //CARGA DE DATOS
    @Override
    public void cargarDatosPruebaProponente(){
        mUsuario.cargarpProponente();
        
    
    }
    @Override
    public void cargarDatosPruebaColaborador(){
        mUsuario.cargarpColaboradores();
    
    }
    @Override
    public void cargarSeguidos(){
        mUsuario.CargarSeguidos();
    }
    @Override
    public void cargarPropuesta(){
        mPropuesta.cargarPropuesta();
    }
    @Override
    public void cargarCategorias(){
        mCategoria.cargarCategorias();
    }
    
    @Override
    public void cargarColaboraciones(){
        mColaboraciones.cargarDatosColaboracion();
    }
    
    //FIN CARGA DE DATOS
   
    //private ManejadorPropuesta mPropuesta=ManejadorPropuesta.getinstance();
    //cabecera icontroles:    Set<DTOPropuesta> ObtenerPropuestaPorSubCategoria(String subcategorias);
    @Override
    public Set<DTOPropuesta> ObtenerPropuestaPorSubCategoria(String subcategoria) {
       return  mPropuesta.ObtenerPropuestasPorSubCategoria(subcategoria);
    }

    // Funcion usada por el buscador web para filtrar propuestas
    @Override
    public List<DTOPropuesta> BuscarPropuestas(String filtro){
       return mPropuesta.BuscarPropuestas(filtro);
    }
}

  
