
package webservices;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the webservices package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CancelarColaboracion_QNAME = new QName("http://webServices/", "CancelarColaboracion");
    private final static QName _CancelarColaboracionResponse_QNAME = new QName("http://webServices/", "CancelarColaboracionResponse");
    private final static QName _DTOCategoria_QNAME = new QName("http://webServices/", "DTOCategoria");
    private final static QName _DTOColaboracion_QNAME = new QName("http://webServices/", "DTOColaboracion");
    private final static QName _DTOColaborador_QNAME = new QName("http://webServices/", "DTOColaborador");
    private final static QName _DTOProponente_QNAME = new QName("http://webServices/", "DTOProponente");
    private final static QName _DTOPropuesta_QNAME = new QName("http://webServices/", "DTOPropuesta");
    private final static QName _DTORegistroEstado_QNAME = new QName("http://webServices/", "DTORegistro_Estado");
    private final static QName _ListaDTOUsuarios_QNAME = new QName("http://webServices/", "ListaDTOUsuarios");
    private final static QName _ListaDTOUsuariosResponse_QNAME = new QName("http://webServices/", "ListaDTOUsuariosResponse");
    private final static QName _Seguidos_QNAME = new QName("http://webServices/", "Seguidos");
    private final static QName _SeguidosResponse_QNAME = new QName("http://webServices/", "SeguidosResponse");
    private final static QName _Usuario_QNAME = new QName("http://webServices/", "Usuario");
    private final static QName _AltaPropuesta_QNAME = new QName("http://webServices/", "altaPropuesta");
    private final static QName _AltaPropuestaResponse_QNAME = new QName("http://webServices/", "altaPropuestaResponse");
    private final static QName _Colaboraciones_QNAME = new QName("http://webServices/", "colaboraciones");
    private final static QName _ColaboracionesResponse_QNAME = new QName("http://webServices/", "colaboracionesResponse");
    private final static QName _EliminarProponente_QNAME = new QName("http://webServices/", "eliminarProponente");
    private final static QName _EliminarProponenteResponse_QNAME = new QName("http://webServices/", "eliminarProponenteResponse");
    private final static QName _EmailUsado_QNAME = new QName("http://webServices/", "emailUsado");
    private final static QName _EmailUsadoResponse_QNAME = new QName("http://webServices/", "emailUsadoResponse");
    private final static QName _Existe_QNAME = new QName("http://webServices/", "existe");
    private final static QName _ExisteResponse_QNAME = new QName("http://webServices/", "existeResponse");
    private final static QName _ExisteUsuario_QNAME = new QName("http://webServices/", "existeUsuario");
    private final static QName _ExisteUsuarioResponse_QNAME = new QName("http://webServices/", "existeUsuarioResponse");
    private final static QName _GetCategorias_QNAME = new QName("http://webServices/", "getCategorias");
    private final static QName _GetCategoriasResponse_QNAME = new QName("http://webServices/", "getCategoriasResponse");
    private final static QName _GetDTOColaborador_QNAME = new QName("http://webServices/", "getDTOColaborador");
    private final static QName _GetDTOColaboradorResponse_QNAME = new QName("http://webServices/", "getDTOColaboradorResponse");
    private final static QName _GetDTOProponente_QNAME = new QName("http://webServices/", "getDTOProponente");
    private final static QName _GetDTOProponenteResponse_QNAME = new QName("http://webServices/", "getDTOProponenteResponse");
    private final static QName _GetFavoritas_QNAME = new QName("http://webServices/", "getFavoritas");
    private final static QName _GetFavoritasResponse_QNAME = new QName("http://webServices/", "getFavoritasResponse");
    private final static QName _GetImg_QNAME = new QName("http://webServices/", "getImg");
    private final static QName _GetImgResponse_QNAME = new QName("http://webServices/", "getImgResponse");
    private final static QName _GetPropuestasCreadasPorProponente_QNAME = new QName("http://webServices/", "getPropuestasCreadasPorProponente");
    private final static QName _GetPropuestasCreadasPorProponenteResponse_QNAME = new QName("http://webServices/", "getPropuestasCreadasPorProponenteResponse");
    private final static QName _GetSeguidores_QNAME = new QName("http://webServices/", "getSeguidores");
    private final static QName _GetSeguidoresResponse_QNAME = new QName("http://webServices/", "getSeguidoresResponse");
    private final static QName _Hello_QNAME = new QName("http://webServices/", "hello");
    private final static QName _HelloResponse_QNAME = new QName("http://webServices/", "helloResponse");
    private final static QName _IsProponente_QNAME = new QName("http://webServices/", "isProponente");
    private final static QName _IsProponenteResponse_QNAME = new QName("http://webServices/", "isProponenteResponse");
    private final static QName _Login_QNAME = new QName("http://webServices/", "login");
    private final static QName _LoginResponse_QNAME = new QName("http://webServices/", "loginResponse");
    private final static QName _RankingUsuarios_QNAME = new QName("http://webServices/", "rankingUsuarios");
    private final static QName _RankingUsuariosResponse_QNAME = new QName("http://webServices/", "rankingUsuariosResponse");
    private final static QName _RegistroUsuario_QNAME = new QName("http://webServices/", "registroUsuario");
    private final static QName _RegistroUsuarioResponse_QNAME = new QName("http://webServices/", "registroUsuarioResponse");
    private final static QName _Seguir_QNAME = new QName("http://webServices/", "seguir");
    private final static QName _SeguirResponse_QNAME = new QName("http://webServices/", "seguirResponse");
    private final static QName _SigueAUsuario_QNAME = new QName("http://webServices/", "sigueAUsuario");
    private final static QName _SigueAUsuarioResponse_QNAME = new QName("http://webServices/", "sigueAUsuarioResponse");
    private final static QName _UnFollowUser_QNAME = new QName("http://webServices/", "unFollowUser");
    private final static QName _UnFollowUserResponse_QNAME = new QName("http://webServices/", "unFollowUserResponse");
    private final static QName _RegistroUsuarioArg6_QNAME = new QName("", "arg6");
    private final static QName _GetImgResponseReturn_QNAME = new QName("", "return");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: webservices
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DtoPropuesta }
     * 
     */
    public DtoPropuesta createDtoPropuesta() {
        return new DtoPropuesta();
    }

    /**
     * Create an instance of {@link DtoPropuesta.Comentarios }
     * 
     */
    public DtoPropuesta.Comentarios createDtoPropuestaComentarios() {
        return new DtoPropuesta.Comentarios();
    }

    /**
     * Create an instance of {@link CancelarColaboracion }
     * 
     */
    public CancelarColaboracion createCancelarColaboracion() {
        return new CancelarColaboracion();
    }

    /**
     * Create an instance of {@link CancelarColaboracionResponse }
     * 
     */
    public CancelarColaboracionResponse createCancelarColaboracionResponse() {
        return new CancelarColaboracionResponse();
    }

    /**
     * Create an instance of {@link DtoCategoria }
     * 
     */
    public DtoCategoria createDtoCategoria() {
        return new DtoCategoria();
    }

    /**
     * Create an instance of {@link DtoColaboracion }
     * 
     */
    public DtoColaboracion createDtoColaboracion() {
        return new DtoColaboracion();
    }

    /**
     * Create an instance of {@link DtoColaborador }
     * 
     */
    public DtoColaborador createDtoColaborador() {
        return new DtoColaborador();
    }

    /**
     * Create an instance of {@link DtoProponente }
     * 
     */
    public DtoProponente createDtoProponente() {
        return new DtoProponente();
    }

    /**
     * Create an instance of {@link DtoRegistroEstado }
     * 
     */
    public DtoRegistroEstado createDtoRegistroEstado() {
        return new DtoRegistroEstado();
    }

    /**
     * Create an instance of {@link ListaDTOUsuarios }
     * 
     */
    public ListaDTOUsuarios createListaDTOUsuarios() {
        return new ListaDTOUsuarios();
    }

    /**
     * Create an instance of {@link ListaDTOUsuariosResponse }
     * 
     */
    public ListaDTOUsuariosResponse createListaDTOUsuariosResponse() {
        return new ListaDTOUsuariosResponse();
    }

    /**
     * Create an instance of {@link Seguidos }
     * 
     */
    public Seguidos createSeguidos() {
        return new Seguidos();
    }

    /**
     * Create an instance of {@link SeguidosResponse }
     * 
     */
    public SeguidosResponse createSeguidosResponse() {
        return new SeguidosResponse();
    }

    /**
     * Create an instance of {@link DtoUsuario }
     * 
     */
    public DtoUsuario createDtoUsuario() {
        return new DtoUsuario();
    }

    /**
     * Create an instance of {@link AltaPropuesta }
     * 
     */
    public AltaPropuesta createAltaPropuesta() {
        return new AltaPropuesta();
    }

    /**
     * Create an instance of {@link AltaPropuestaResponse }
     * 
     */
    public AltaPropuestaResponse createAltaPropuestaResponse() {
        return new AltaPropuestaResponse();
    }

    /**
     * Create an instance of {@link Colaboraciones }
     * 
     */
    public Colaboraciones createColaboraciones() {
        return new Colaboraciones();
    }

    /**
     * Create an instance of {@link ColaboracionesResponse }
     * 
     */
    public ColaboracionesResponse createColaboracionesResponse() {
        return new ColaboracionesResponse();
    }

    /**
     * Create an instance of {@link EliminarProponente }
     * 
     */
    public EliminarProponente createEliminarProponente() {
        return new EliminarProponente();
    }

    /**
     * Create an instance of {@link EliminarProponenteResponse }
     * 
     */
    public EliminarProponenteResponse createEliminarProponenteResponse() {
        return new EliminarProponenteResponse();
    }

    /**
     * Create an instance of {@link EmailUsado }
     * 
     */
    public EmailUsado createEmailUsado() {
        return new EmailUsado();
    }

    /**
     * Create an instance of {@link EmailUsadoResponse }
     * 
     */
    public EmailUsadoResponse createEmailUsadoResponse() {
        return new EmailUsadoResponse();
    }

    /**
     * Create an instance of {@link Existe }
     * 
     */
    public Existe createExiste() {
        return new Existe();
    }

    /**
     * Create an instance of {@link ExisteResponse }
     * 
     */
    public ExisteResponse createExisteResponse() {
        return new ExisteResponse();
    }

    /**
     * Create an instance of {@link ExisteUsuario }
     * 
     */
    public ExisteUsuario createExisteUsuario() {
        return new ExisteUsuario();
    }

    /**
     * Create an instance of {@link ExisteUsuarioResponse }
     * 
     */
    public ExisteUsuarioResponse createExisteUsuarioResponse() {
        return new ExisteUsuarioResponse();
    }

    /**
     * Create an instance of {@link GetCategorias }
     * 
     */
    public GetCategorias createGetCategorias() {
        return new GetCategorias();
    }

    /**
     * Create an instance of {@link GetCategoriasResponse }
     * 
     */
    public GetCategoriasResponse createGetCategoriasResponse() {
        return new GetCategoriasResponse();
    }

    /**
     * Create an instance of {@link GetDTOColaborador }
     * 
     */
    public GetDTOColaborador createGetDTOColaborador() {
        return new GetDTOColaborador();
    }

    /**
     * Create an instance of {@link GetDTOColaboradorResponse }
     * 
     */
    public GetDTOColaboradorResponse createGetDTOColaboradorResponse() {
        return new GetDTOColaboradorResponse();
    }

    /**
     * Create an instance of {@link GetDTOProponente }
     * 
     */
    public GetDTOProponente createGetDTOProponente() {
        return new GetDTOProponente();
    }

    /**
     * Create an instance of {@link GetDTOProponenteResponse }
     * 
     */
    public GetDTOProponenteResponse createGetDTOProponenteResponse() {
        return new GetDTOProponenteResponse();
    }

    /**
     * Create an instance of {@link GetFavoritas }
     * 
     */
    public GetFavoritas createGetFavoritas() {
        return new GetFavoritas();
    }

    /**
     * Create an instance of {@link GetFavoritasResponse }
     * 
     */
    public GetFavoritasResponse createGetFavoritasResponse() {
        return new GetFavoritasResponse();
    }

    /**
     * Create an instance of {@link GetImg }
     * 
     */
    public GetImg createGetImg() {
        return new GetImg();
    }

    /**
     * Create an instance of {@link GetImgResponse }
     * 
     */
    public GetImgResponse createGetImgResponse() {
        return new GetImgResponse();
    }

    /**
     * Create an instance of {@link GetPropuestasCreadasPorProponente }
     * 
     */
    public GetPropuestasCreadasPorProponente createGetPropuestasCreadasPorProponente() {
        return new GetPropuestasCreadasPorProponente();
    }

    /**
     * Create an instance of {@link GetPropuestasCreadasPorProponenteResponse }
     * 
     */
    public GetPropuestasCreadasPorProponenteResponse createGetPropuestasCreadasPorProponenteResponse() {
        return new GetPropuestasCreadasPorProponenteResponse();
    }

    /**
     * Create an instance of {@link GetSeguidores }
     * 
     */
    public GetSeguidores createGetSeguidores() {
        return new GetSeguidores();
    }

    /**
     * Create an instance of {@link GetSeguidoresResponse }
     * 
     */
    public GetSeguidoresResponse createGetSeguidoresResponse() {
        return new GetSeguidoresResponse();
    }

    /**
     * Create an instance of {@link Hello }
     * 
     */
    public Hello createHello() {
        return new Hello();
    }

    /**
     * Create an instance of {@link HelloResponse }
     * 
     */
    public HelloResponse createHelloResponse() {
        return new HelloResponse();
    }

    /**
     * Create an instance of {@link IsProponente }
     * 
     */
    public IsProponente createIsProponente() {
        return new IsProponente();
    }

    /**
     * Create an instance of {@link IsProponenteResponse }
     * 
     */
    public IsProponenteResponse createIsProponenteResponse() {
        return new IsProponenteResponse();
    }

    /**
     * Create an instance of {@link Login }
     * 
     */
    public Login createLogin() {
        return new Login();
    }

    /**
     * Create an instance of {@link LoginResponse }
     * 
     */
    public LoginResponse createLoginResponse() {
        return new LoginResponse();
    }

    /**
     * Create an instance of {@link RankingUsuarios }
     * 
     */
    public RankingUsuarios createRankingUsuarios() {
        return new RankingUsuarios();
    }

    /**
     * Create an instance of {@link RankingUsuariosResponse }
     * 
     */
    public RankingUsuariosResponse createRankingUsuariosResponse() {
        return new RankingUsuariosResponse();
    }

    /**
     * Create an instance of {@link RegistroUsuario }
     * 
     */
    public RegistroUsuario createRegistroUsuario() {
        return new RegistroUsuario();
    }

    /**
     * Create an instance of {@link RegistroUsuarioResponse }
     * 
     */
    public RegistroUsuarioResponse createRegistroUsuarioResponse() {
        return new RegistroUsuarioResponse();
    }

    /**
     * Create an instance of {@link Seguir }
     * 
     */
    public Seguir createSeguir() {
        return new Seguir();
    }

    /**
     * Create an instance of {@link SeguirResponse }
     * 
     */
    public SeguirResponse createSeguirResponse() {
        return new SeguirResponse();
    }

    /**
     * Create an instance of {@link SigueAUsuario }
     * 
     */
    public SigueAUsuario createSigueAUsuario() {
        return new SigueAUsuario();
    }

    /**
     * Create an instance of {@link SigueAUsuarioResponse }
     * 
     */
    public SigueAUsuarioResponse createSigueAUsuarioResponse() {
        return new SigueAUsuarioResponse();
    }

    /**
     * Create an instance of {@link UnFollowUser }
     * 
     */
    public UnFollowUser createUnFollowUser() {
        return new UnFollowUser();
    }

    /**
     * Create an instance of {@link UnFollowUserResponse }
     * 
     */
    public UnFollowUserResponse createUnFollowUserResponse() {
        return new UnFollowUserResponse();
    }

    /**
     * Create an instance of {@link LocalDate }
     * 
     */
    public LocalDate createLocalDate() {
        return new LocalDate();
    }

    /**
     * Create an instance of {@link Categoria }
     * 
     */
    public Categoria createCategoria() {
        return new Categoria();
    }

    /**
     * Create an instance of {@link Pago }
     * 
     */
    public Pago createPago() {
        return new Pago();
    }

    /**
     * Create an instance of {@link LocalDateTime }
     * 
     */
    public LocalDateTime createLocalDateTime() {
        return new LocalDateTime();
    }

    /**
     * Create an instance of {@link DtoPropuesta.Comentarios.Entry }
     * 
     */
    public DtoPropuesta.Comentarios.Entry createDtoPropuestaComentariosEntry() {
        return new DtoPropuesta.Comentarios.Entry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelarColaboracion }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CancelarColaboracion }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "CancelarColaboracion")
    public JAXBElement<CancelarColaboracion> createCancelarColaboracion(CancelarColaboracion value) {
        return new JAXBElement<CancelarColaboracion>(_CancelarColaboracion_QNAME, CancelarColaboracion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelarColaboracionResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CancelarColaboracionResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "CancelarColaboracionResponse")
    public JAXBElement<CancelarColaboracionResponse> createCancelarColaboracionResponse(CancelarColaboracionResponse value) {
        return new JAXBElement<CancelarColaboracionResponse>(_CancelarColaboracionResponse_QNAME, CancelarColaboracionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DtoCategoria }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DtoCategoria }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "DTOCategoria")
    public JAXBElement<DtoCategoria> createDTOCategoria(DtoCategoria value) {
        return new JAXBElement<DtoCategoria>(_DTOCategoria_QNAME, DtoCategoria.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DtoColaboracion }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DtoColaboracion }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "DTOColaboracion")
    public JAXBElement<DtoColaboracion> createDTOColaboracion(DtoColaboracion value) {
        return new JAXBElement<DtoColaboracion>(_DTOColaboracion_QNAME, DtoColaboracion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DtoColaborador }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DtoColaborador }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "DTOColaborador")
    public JAXBElement<DtoColaborador> createDTOColaborador(DtoColaborador value) {
        return new JAXBElement<DtoColaborador>(_DTOColaborador_QNAME, DtoColaborador.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DtoProponente }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DtoProponente }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "DTOProponente")
    public JAXBElement<DtoProponente> createDTOProponente(DtoProponente value) {
        return new JAXBElement<DtoProponente>(_DTOProponente_QNAME, DtoProponente.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DtoPropuesta }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DtoPropuesta }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "DTOPropuesta")
    public JAXBElement<DtoPropuesta> createDTOPropuesta(DtoPropuesta value) {
        return new JAXBElement<DtoPropuesta>(_DTOPropuesta_QNAME, DtoPropuesta.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DtoRegistroEstado }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DtoRegistroEstado }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "DTORegistro_Estado")
    public JAXBElement<DtoRegistroEstado> createDTORegistroEstado(DtoRegistroEstado value) {
        return new JAXBElement<DtoRegistroEstado>(_DTORegistroEstado_QNAME, DtoRegistroEstado.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListaDTOUsuarios }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListaDTOUsuarios }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "ListaDTOUsuarios")
    public JAXBElement<ListaDTOUsuarios> createListaDTOUsuarios(ListaDTOUsuarios value) {
        return new JAXBElement<ListaDTOUsuarios>(_ListaDTOUsuarios_QNAME, ListaDTOUsuarios.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListaDTOUsuariosResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListaDTOUsuariosResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "ListaDTOUsuariosResponse")
    public JAXBElement<ListaDTOUsuariosResponse> createListaDTOUsuariosResponse(ListaDTOUsuariosResponse value) {
        return new JAXBElement<ListaDTOUsuariosResponse>(_ListaDTOUsuariosResponse_QNAME, ListaDTOUsuariosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Seguidos }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Seguidos }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "Seguidos")
    public JAXBElement<Seguidos> createSeguidos(Seguidos value) {
        return new JAXBElement<Seguidos>(_Seguidos_QNAME, Seguidos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SeguidosResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SeguidosResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "SeguidosResponse")
    public JAXBElement<SeguidosResponse> createSeguidosResponse(SeguidosResponse value) {
        return new JAXBElement<SeguidosResponse>(_SeguidosResponse_QNAME, SeguidosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DtoUsuario }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DtoUsuario }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "Usuario")
    public JAXBElement<DtoUsuario> createUsuario(DtoUsuario value) {
        return new JAXBElement<DtoUsuario>(_Usuario_QNAME, DtoUsuario.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AltaPropuesta }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AltaPropuesta }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "altaPropuesta")
    public JAXBElement<AltaPropuesta> createAltaPropuesta(AltaPropuesta value) {
        return new JAXBElement<AltaPropuesta>(_AltaPropuesta_QNAME, AltaPropuesta.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AltaPropuestaResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AltaPropuestaResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "altaPropuestaResponse")
    public JAXBElement<AltaPropuestaResponse> createAltaPropuestaResponse(AltaPropuestaResponse value) {
        return new JAXBElement<AltaPropuestaResponse>(_AltaPropuestaResponse_QNAME, AltaPropuestaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Colaboraciones }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Colaboraciones }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "colaboraciones")
    public JAXBElement<Colaboraciones> createColaboraciones(Colaboraciones value) {
        return new JAXBElement<Colaboraciones>(_Colaboraciones_QNAME, Colaboraciones.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ColaboracionesResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ColaboracionesResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "colaboracionesResponse")
    public JAXBElement<ColaboracionesResponse> createColaboracionesResponse(ColaboracionesResponse value) {
        return new JAXBElement<ColaboracionesResponse>(_ColaboracionesResponse_QNAME, ColaboracionesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EliminarProponente }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EliminarProponente }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "eliminarProponente")
    public JAXBElement<EliminarProponente> createEliminarProponente(EliminarProponente value) {
        return new JAXBElement<EliminarProponente>(_EliminarProponente_QNAME, EliminarProponente.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EliminarProponenteResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EliminarProponenteResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "eliminarProponenteResponse")
    public JAXBElement<EliminarProponenteResponse> createEliminarProponenteResponse(EliminarProponenteResponse value) {
        return new JAXBElement<EliminarProponenteResponse>(_EliminarProponenteResponse_QNAME, EliminarProponenteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmailUsado }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EmailUsado }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "emailUsado")
    public JAXBElement<EmailUsado> createEmailUsado(EmailUsado value) {
        return new JAXBElement<EmailUsado>(_EmailUsado_QNAME, EmailUsado.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmailUsadoResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EmailUsadoResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "emailUsadoResponse")
    public JAXBElement<EmailUsadoResponse> createEmailUsadoResponse(EmailUsadoResponse value) {
        return new JAXBElement<EmailUsadoResponse>(_EmailUsadoResponse_QNAME, EmailUsadoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Existe }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Existe }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "existe")
    public JAXBElement<Existe> createExiste(Existe value) {
        return new JAXBElement<Existe>(_Existe_QNAME, Existe.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExisteResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ExisteResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "existeResponse")
    public JAXBElement<ExisteResponse> createExisteResponse(ExisteResponse value) {
        return new JAXBElement<ExisteResponse>(_ExisteResponse_QNAME, ExisteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExisteUsuario }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ExisteUsuario }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "existeUsuario")
    public JAXBElement<ExisteUsuario> createExisteUsuario(ExisteUsuario value) {
        return new JAXBElement<ExisteUsuario>(_ExisteUsuario_QNAME, ExisteUsuario.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExisteUsuarioResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ExisteUsuarioResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "existeUsuarioResponse")
    public JAXBElement<ExisteUsuarioResponse> createExisteUsuarioResponse(ExisteUsuarioResponse value) {
        return new JAXBElement<ExisteUsuarioResponse>(_ExisteUsuarioResponse_QNAME, ExisteUsuarioResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCategorias }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetCategorias }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "getCategorias")
    public JAXBElement<GetCategorias> createGetCategorias(GetCategorias value) {
        return new JAXBElement<GetCategorias>(_GetCategorias_QNAME, GetCategorias.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCategoriasResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetCategoriasResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "getCategoriasResponse")
    public JAXBElement<GetCategoriasResponse> createGetCategoriasResponse(GetCategoriasResponse value) {
        return new JAXBElement<GetCategoriasResponse>(_GetCategoriasResponse_QNAME, GetCategoriasResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDTOColaborador }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetDTOColaborador }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "getDTOColaborador")
    public JAXBElement<GetDTOColaborador> createGetDTOColaborador(GetDTOColaborador value) {
        return new JAXBElement<GetDTOColaborador>(_GetDTOColaborador_QNAME, GetDTOColaborador.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDTOColaboradorResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetDTOColaboradorResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "getDTOColaboradorResponse")
    public JAXBElement<GetDTOColaboradorResponse> createGetDTOColaboradorResponse(GetDTOColaboradorResponse value) {
        return new JAXBElement<GetDTOColaboradorResponse>(_GetDTOColaboradorResponse_QNAME, GetDTOColaboradorResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDTOProponente }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetDTOProponente }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "getDTOProponente")
    public JAXBElement<GetDTOProponente> createGetDTOProponente(GetDTOProponente value) {
        return new JAXBElement<GetDTOProponente>(_GetDTOProponente_QNAME, GetDTOProponente.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDTOProponenteResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetDTOProponenteResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "getDTOProponenteResponse")
    public JAXBElement<GetDTOProponenteResponse> createGetDTOProponenteResponse(GetDTOProponenteResponse value) {
        return new JAXBElement<GetDTOProponenteResponse>(_GetDTOProponenteResponse_QNAME, GetDTOProponenteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFavoritas }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetFavoritas }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "getFavoritas")
    public JAXBElement<GetFavoritas> createGetFavoritas(GetFavoritas value) {
        return new JAXBElement<GetFavoritas>(_GetFavoritas_QNAME, GetFavoritas.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFavoritasResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetFavoritasResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "getFavoritasResponse")
    public JAXBElement<GetFavoritasResponse> createGetFavoritasResponse(GetFavoritasResponse value) {
        return new JAXBElement<GetFavoritasResponse>(_GetFavoritasResponse_QNAME, GetFavoritasResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetImg }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetImg }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "getImg")
    public JAXBElement<GetImg> createGetImg(GetImg value) {
        return new JAXBElement<GetImg>(_GetImg_QNAME, GetImg.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetImgResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetImgResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "getImgResponse")
    public JAXBElement<GetImgResponse> createGetImgResponse(GetImgResponse value) {
        return new JAXBElement<GetImgResponse>(_GetImgResponse_QNAME, GetImgResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPropuestasCreadasPorProponente }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetPropuestasCreadasPorProponente }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "getPropuestasCreadasPorProponente")
    public JAXBElement<GetPropuestasCreadasPorProponente> createGetPropuestasCreadasPorProponente(GetPropuestasCreadasPorProponente value) {
        return new JAXBElement<GetPropuestasCreadasPorProponente>(_GetPropuestasCreadasPorProponente_QNAME, GetPropuestasCreadasPorProponente.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPropuestasCreadasPorProponenteResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetPropuestasCreadasPorProponenteResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "getPropuestasCreadasPorProponenteResponse")
    public JAXBElement<GetPropuestasCreadasPorProponenteResponse> createGetPropuestasCreadasPorProponenteResponse(GetPropuestasCreadasPorProponenteResponse value) {
        return new JAXBElement<GetPropuestasCreadasPorProponenteResponse>(_GetPropuestasCreadasPorProponenteResponse_QNAME, GetPropuestasCreadasPorProponenteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSeguidores }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetSeguidores }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "getSeguidores")
    public JAXBElement<GetSeguidores> createGetSeguidores(GetSeguidores value) {
        return new JAXBElement<GetSeguidores>(_GetSeguidores_QNAME, GetSeguidores.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSeguidoresResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetSeguidoresResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "getSeguidoresResponse")
    public JAXBElement<GetSeguidoresResponse> createGetSeguidoresResponse(GetSeguidoresResponse value) {
        return new JAXBElement<GetSeguidoresResponse>(_GetSeguidoresResponse_QNAME, GetSeguidoresResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Hello }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Hello }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "hello")
    public JAXBElement<Hello> createHello(Hello value) {
        return new JAXBElement<Hello>(_Hello_QNAME, Hello.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link HelloResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "helloResponse")
    public JAXBElement<HelloResponse> createHelloResponse(HelloResponse value) {
        return new JAXBElement<HelloResponse>(_HelloResponse_QNAME, HelloResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsProponente }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link IsProponente }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "isProponente")
    public JAXBElement<IsProponente> createIsProponente(IsProponente value) {
        return new JAXBElement<IsProponente>(_IsProponente_QNAME, IsProponente.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsProponenteResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link IsProponenteResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "isProponenteResponse")
    public JAXBElement<IsProponenteResponse> createIsProponenteResponse(IsProponenteResponse value) {
        return new JAXBElement<IsProponenteResponse>(_IsProponenteResponse_QNAME, IsProponenteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Login }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Login }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "login")
    public JAXBElement<Login> createLogin(Login value) {
        return new JAXBElement<Login>(_Login_QNAME, Login.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LoginResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "loginResponse")
    public JAXBElement<LoginResponse> createLoginResponse(LoginResponse value) {
        return new JAXBElement<LoginResponse>(_LoginResponse_QNAME, LoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RankingUsuarios }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RankingUsuarios }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "rankingUsuarios")
    public JAXBElement<RankingUsuarios> createRankingUsuarios(RankingUsuarios value) {
        return new JAXBElement<RankingUsuarios>(_RankingUsuarios_QNAME, RankingUsuarios.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RankingUsuariosResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RankingUsuariosResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "rankingUsuariosResponse")
    public JAXBElement<RankingUsuariosResponse> createRankingUsuariosResponse(RankingUsuariosResponse value) {
        return new JAXBElement<RankingUsuariosResponse>(_RankingUsuariosResponse_QNAME, RankingUsuariosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistroUsuario }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RegistroUsuario }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "registroUsuario")
    public JAXBElement<RegistroUsuario> createRegistroUsuario(RegistroUsuario value) {
        return new JAXBElement<RegistroUsuario>(_RegistroUsuario_QNAME, RegistroUsuario.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistroUsuarioResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RegistroUsuarioResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "registroUsuarioResponse")
    public JAXBElement<RegistroUsuarioResponse> createRegistroUsuarioResponse(RegistroUsuarioResponse value) {
        return new JAXBElement<RegistroUsuarioResponse>(_RegistroUsuarioResponse_QNAME, RegistroUsuarioResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Seguir }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Seguir }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "seguir")
    public JAXBElement<Seguir> createSeguir(Seguir value) {
        return new JAXBElement<Seguir>(_Seguir_QNAME, Seguir.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SeguirResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SeguirResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "seguirResponse")
    public JAXBElement<SeguirResponse> createSeguirResponse(SeguirResponse value) {
        return new JAXBElement<SeguirResponse>(_SeguirResponse_QNAME, SeguirResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SigueAUsuario }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SigueAUsuario }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "sigueAUsuario")
    public JAXBElement<SigueAUsuario> createSigueAUsuario(SigueAUsuario value) {
        return new JAXBElement<SigueAUsuario>(_SigueAUsuario_QNAME, SigueAUsuario.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SigueAUsuarioResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SigueAUsuarioResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "sigueAUsuarioResponse")
    public JAXBElement<SigueAUsuarioResponse> createSigueAUsuarioResponse(SigueAUsuarioResponse value) {
        return new JAXBElement<SigueAUsuarioResponse>(_SigueAUsuarioResponse_QNAME, SigueAUsuarioResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnFollowUser }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UnFollowUser }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "unFollowUser")
    public JAXBElement<UnFollowUser> createUnFollowUser(UnFollowUser value) {
        return new JAXBElement<UnFollowUser>(_UnFollowUser_QNAME, UnFollowUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnFollowUserResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UnFollowUserResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices/", name = "unFollowUserResponse")
    public JAXBElement<UnFollowUserResponse> createUnFollowUserResponse(UnFollowUserResponse value) {
        return new JAXBElement<UnFollowUserResponse>(_UnFollowUserResponse_QNAME, UnFollowUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     */
    @XmlElementDecl(namespace = "", name = "arg6", scope = RegistroUsuario.class)
    public JAXBElement<byte[]> createRegistroUsuarioArg6(byte[] value) {
        return new JAXBElement<byte[]>(_RegistroUsuarioArg6_QNAME, byte[].class, RegistroUsuario.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     */
    @XmlElementDecl(namespace = "", name = "return", scope = GetImgResponse.class)
    public JAXBElement<byte[]> createGetImgResponseReturn(byte[] value) {
        return new JAXBElement<byte[]>(_GetImgResponseReturn_QNAME, byte[].class, GetImgResponse.class, ((byte[]) value));
    }

}
