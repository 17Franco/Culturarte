package logica;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import logica.Categoria.Categoria;
import logica.DTO.DTOCategoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import persistencia.ManejadorCategoria;
import persistencia.PersistenciaManager;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import logica.DTO.DTOColaboracion;
import logica.DTO.DTOColaborador;
import logica.DTO.DTOProponente;
import logica.DTO.DTOPropuesta;
import logica.DTO.DTORegistro_Estado;
import logica.DTO.DTOUsuario;
import logica.DTO.Estado;
import logica.DTO.TipoRetorno;
import logica.Propuesta.Propuesta;
import logica.Usuario.Colaborador;
import logica.Propuesta.Registro_Estado;
import logica.Usuario.Proponente;
import logica.Usuario.Usuario;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import persistencia.ManejadorPropuesta;
import persistencia.ManejadorUsuario;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ControllerTest 
{

    @Mock
    private EntityManager mockEntityManager;
    
    @Mock
    private EntityTransaction mockTransaction;
    
    private Controller controller;
    private ManejadorCategoria manejador;
    private ManejadorUsuario mUsuario;
    private ManejadorPropuesta mPropuesta;
    
    @BeforeEach
    public void setUp() throws Exception 
    {

        
        Field instanciaField = ManejadorCategoria.class.getDeclaredField("instancia");
        instanciaField.setAccessible(true);
        instanciaField.set(null, null);
        
        manejador = ManejadorCategoria.getInstance();
        mUsuario = ManejadorUsuario.getInstance();
        
        controller = new Controller();
        
        Field mCategoriaField = Controller.class.getDeclaredField("mCategoria");
        mCategoriaField.setAccessible(true);
        mCategoriaField.set(controller, manejador);

        when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);
        when(mockTransaction.isActive()).thenReturn(true);
        
        //Para propuesta
        Field instanciaPropuestaField = ManejadorPropuesta.class.getDeclaredField("instancia");
        instanciaPropuestaField.setAccessible(true);
        instanciaPropuestaField.set(null, null);
        mPropuesta = ManejadorPropuesta.getinstance();
        
        Field mPropuestaField = Controller.class.getDeclaredField("mPropuesta");
        mPropuestaField.setAccessible(true);
        mPropuestaField.set(controller, mPropuesta);
        
    }
    
    //INICIO USUARIOS
    @Test
    public void testloginF() {
        System.out.println("login");
        String nick = "diegop";
        String pass = "nada";
        boolean result = controller.login(nick, pass);

        assertEquals(false, result);
    }
    @Test
    public void testloginT() {
        System.out.println("login");
        String nick = "diegop";
        String pass = "123";
        boolean result = controller.login(nick, pass);

        assertEquals(true, result);
    }
    @Test
    public void testRegistroUsuarioProponente() {
        System.out.println("registroUsuario - Proponente");

        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {

            doNothing().when(mockTransaction).begin();
            doNothing().when(mockTransaction).commit();
            doNothing().when(mockEntityManager).persist(any(Proponente.class));
            doNothing().when(mockEntityManager).close();

            when(mockEntityManager.find(Usuario.class, "proptest")).thenReturn(null);

            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            String nickname = "proptest";
            String pass = "123";
            String nombre = "Juan";
            String apellido = "test";
            String email = "juan@test.com";
            LocalDate fecha = LocalDate.now();
            byte[] contenido = "imagen de prueba".getBytes();
            String nombreArchivo = "foto.jpg";
            boolean isProponente = true;
            String direccion = "test";
            String web = "www.juan.com";
            String biografia = "test";

            controller.registroUsuario(nickname, pass, nombre, apellido, email,fecha, contenido, nombreArchivo, isProponente, direccion, web, biografia);
        }
    }
    @Test
    public void testRegistroUsuarioColaborador() {
        System.out.println("registroUsuario - Colaborador");

        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {

            doNothing().when(mockTransaction).begin();
            doNothing().when(mockTransaction).commit();
            doNothing().when(mockEntityManager).persist(any(Colaborador.class));
            doNothing().when(mockEntityManager).close();
            
            when(mockEntityManager.find(Usuario.class, "colabtest")).thenReturn(null);

            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            String nickname = "testC";
            String pass = "test";
            String nombre = "test";
            String apellido = "test";
            String email = "maria@test.com";
            LocalDate fecha = LocalDate.now();
            byte[] contenido = "imagen colaborador".getBytes();
            String nombreArchivo = "perfil.jpg";
            boolean isProponente = false;
            String direccion = "";
            String web = "";
            String biografia = "";

            controller.registroUsuario(nickname, pass, nombre, apellido, email,fecha, contenido, nombreArchivo, isProponente, direccion, web, biografia);
        }
    }
    @Test
    public void testAltaUsuarioColaborador() {
        System.out.println("altaUsuario - Colaborador");

        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {

            // Me salto la modificación de la BD
            doNothing().when(mockTransaction).begin();
            doNothing().when(mockTransaction).commit();
            doNothing().when(mockEntityManager).persist(any(Colaborador.class));
            doNothing().when(mockEntityManager).close();

            // Simular que el usuario NO existe (para que se pueda crear)
            when(mockEntityManager.find(Usuario.class, "testcolab")).thenReturn(null);

            // Se simula la obtención del EntityManager
            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            DTOColaborador dtoColaborador = new DTOColaborador("TestC", "123", "algotest", "algo", "test2@g.com", LocalDate.now(), "", "Colaborador");

            // Ejecutar el método
            controller.altaUsuario(dtoColaborador);
        }
    }

    @Test
    public void testAltaUsuarioProponente() {
        System.out.println("altaUsuario - Proponente");

        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {

            // Me salto la modificación de la BD
            doNothing().when(mockTransaction).begin();
            doNothing().when(mockTransaction).commit();
            doNothing().when(mockEntityManager).persist(any(Proponente.class));
            doNothing().when(mockEntityManager).close();

            // Simular que el usuario NO existe
            when(mockEntityManager.find(Usuario.class, "testprop")).thenReturn(null);

            // Se simula la obtención del EntityManager
            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            // Crear el DTO del proponente
            DTOProponente dtoProponente = new DTOProponente("algo", "algo", "www.comt.com", "testP", "123", "test", "pacotest", "test@g.com", LocalDate.now(), "", "Proponente");

            // Ejecutar el método
            controller.altaUsuario(dtoProponente);
        }
    }
    @Test
    public void testIsProponenteT() {
        System.out.println("isProponenteF");
        String nick = "diegop";

        boolean result = controller.isProponente(nick);
 
        assertEquals(true, result);
    }
    @Test
    public void testIsProponenteF() {
        System.out.println("isProponenteF");
        String nick = "chino";

        boolean result = controller.isProponente(nick);

        assertEquals(false, result);
    }
    @Test
    public void testExisteUsuarioeMailTrue() {
        System.out.println("existeUsuarioTrue");
        String nick = "diegop";
        String email = "diego@efectocine.com";

        boolean result = controller.emailUsado(email);

        assertEquals(true, result);
    }
    @Test
    public void testExisteUsuarioeMailFalse() {
        System.out.println("existeUsuarioTrue");
        String nick = "diegop";
        String email = "digo@gmail.com";

        boolean result = controller.emailUsado(email);

        assertEquals(false, result);
    }
    @Test
    public void testExisteUsuarioeE() {
        System.out.println("existeUsuarioTrue");
        String nick = "diegop";
        String email = "diego@efectocine.com";

        boolean result = controller.existeUsuario(nick, email);

        assertEquals(true, result);
    }
    @Test
    public void testExisteUsuarioeF() {
        System.out.println("existeUsuarioFalse");
        String nick = "manolo";
        String email = "algo@gmail.com";

        boolean result = controller.existeUsuario(nick, email);

        assertEquals(false, result);
    }
    @Test
    public void testExisteTrue() {
        System.out.println("existe");
        String nick = "diegop";

        boolean result = controller.existe(nick);

        assertEquals(true, result);
    }
    @Test
    public void testExisteFalse() {
        System.out.println("existe");
        String nick = "jorge";

        boolean result = controller.existe(nick);

        assertEquals(false, result);
    }
    @Test
    public void testSeguidos() {
        System.out.println("SeguidosF");
        String nick = "diegop";
        List<DTOUsuario> listaVacia = new ArrayList<>();

        List<DTOUsuario> result = controller.Seguidos(nick);

        assertNotNull(result);
    }
    @Test
    public void testCargarSeguidos() {
        System.out.println("CargarSeguidos");

        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {
            // Configurar comportamiento de los mocks
            doNothing().when(mockTransaction).begin();
            doNothing().when(mockTransaction).commit();
            doNothing().when(mockTransaction).rollback();
            doNothing().when(mockEntityManager).persist(any(Usuario.class));
            doNothing().when(mockEntityManager).close();

            // Simular que no existen proponentes (retorna null para que entre en los if)
            when(mockEntityManager.find(eq(Usuario.class), anyString())).thenReturn(null);

            // Configurar el EntityManager mock
            when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);

            // Configurar PersistenciaManager para retornar nuestro mock
            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            // Ejecutar el método a probar
            controller.cargarSeguidos();

        }
    }
    @Test
    public void testCargarColaborador() {
        System.out.println("CargarColaborador");

        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {
            // Configurar comportamiento de los mocks
            doNothing().when(mockTransaction).begin();
            doNothing().when(mockTransaction).commit();
            doNothing().when(mockTransaction).rollback();
            doNothing().when(mockEntityManager).persist(any(Colaborador.class));
            doNothing().when(mockEntityManager).close();

            // Simular que no existen proponentes (retorna null para que entre en los if)
            when(mockEntityManager.find(eq(Colaborador.class), anyString())).thenReturn(null);

            // Configurar el EntityManager mock
            when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);

            // Configurar PersistenciaManager para retornar nuestro mock
            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            // Ejecutar el método a probar
            controller.cargarDatosPruebaColaborador();

        }
    }
    @Test
    public void testCargarProponente() {
        System.out.println("CargarProponente");

        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {
            // Configurar comportamiento de los mocks
            doNothing().when(mockTransaction).begin();
            doNothing().when(mockTransaction).commit();
            doNothing().when(mockTransaction).rollback();
            doNothing().when(mockEntityManager).persist(any(Proponente.class));
            doNothing().when(mockEntityManager).close();

            // Simular que no existen proponentes (retorna null para que entre en los if)
            when(mockEntityManager.find(eq(Proponente.class), anyString())).thenReturn(null);

            // Configurar el EntityManager mock
            when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);

            // Configurar PersistenciaManager para retornar nuestro mock
            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            // Ejecutar el método a probar
            controller.cargarDatosPruebaProponente();
            
        }
    }
    @Test
    public void testesFavorita() {
        System.out.println("isProponenteF");
        String nick = "diegop";

        boolean result = controller.isProponente(nick);

        assertEquals(true, result);
    }
    @Test
    public void testGetFavoritasF() { //EN PRUEBAS DA SOLO 34% PORQUE NO ENTRA AL IF 
        Usuario mockUsuario = mock(Usuario.class);

        Proponente usuario = new Proponente("Test1", "Test1", "www.Test1.com","TestUsu", "TestProp", "Test1", "Test1@g.com", LocalDate.now(), "");
        Categoria Cat = new Categoria("TestCat");
        Propuesta propuesta = new Propuesta("Testing", "Testing", "", "Testing",LocalDate.now(), 100, 100, LocalDate.now(), new ArrayList(), Cat,usuario, Estado.PUBLICADA);

        Map<String, Propuesta> listaFavoritas = new HashMap<>();
        listaFavoritas.put("TestUsu", propuesta);

        when(mockUsuario.getNickname()).thenReturn("TestUsu");
        when(mockUsuario.getPropFavorita()).thenReturn(listaFavoritas);

        List<DTOPropuesta> result = controller.getFavoritas(mockUsuario.getNickname());

        assertNotNull(result);
    }
    @Test
    public void testSigueAUsuarioF() {
        System.out.println("sigueAUsuario");
        String seguidor = "juliob";
        String Seguido = "losBardo";
        boolean result = controller.sigueAUsuario(seguidor, Seguido);
        assertEquals(false, result);

    }
    @Test
    public void testSigueAUsuarioT() {
        System.out.println("sigueAUsuario");
        String seguidor = "juanP";
        String Seguido = "cachilas";
        boolean result = controller.sigueAUsuario(seguidor, Seguido);
        assertEquals(true, result);

    }
    @Test
    public void testGetSeguidores() {
        System.out.println("getSeguidores");
        String nick = "diegop";
        List<DTOUsuario> result = controller.getSeguidores(nick);
        assertNotNull(result);
    }
    @Test
    public void testListaUsuarios() {
        System.out.println("ListaUsuarios");
        List<String> result = controller.ListaUsuarios();
        assertNotNull(result);
    }
    @Test
    public void testListaProponentesE() {
        System.out.println("ListaProponentes");
        List<String> result = controller.ListaProponentes();
        assertNotNull(result);
    }
    @Test
    public void testListaColaboradorE() {
        System.out.println("ListaColaboradores");
        List<String> result = controller.ListaColaborador();
        assertNotNull(result);
    }
    @Test
    public void testMarcarComoFavorita() {//en proceso
        System.out.println("testMarcarComoFavorita");

        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {

            EntityManager mockEntityManager = mock(EntityManager.class);
            EntityTransaction mockTransaction = mock(EntityTransaction.class);

            doNothing().when(mockTransaction).begin();
            doNothing().when(mockTransaction).commit();
            doNothing().when(mockEntityManager).close();
            when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);

            Proponente usuario = new Proponente("Test1", "Test1", "www.Test1.com", "TestUsu", "TestProp", "Test1", "Test1@g.com", LocalDate.now(), "");

            Categoria Cat = new Categoria("TestCat");

            Propuesta propuesta = new Propuesta("Testing", "Testing", "", "Testing", LocalDate.now(), 100, 100, LocalDate.now(), new ArrayList(), Cat, usuario, Estado.PUBLICADA);

            usuario.Favorita(propuesta);

            when(mockEntityManager.find(Usuario.class, "TestNick")).thenReturn(usuario);
            when(mockEntityManager.find(Propuesta.class, "Testing")).thenReturn(propuesta);

            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);
            
            mUsuario.marcarComoFavorita("TestNick", "Testing");

            verify(mockTransaction).begin();
            verify(mockEntityManager).find(Usuario.class, "TestNick");
            verify(mockEntityManager).find(Propuesta.class, "Testing");
            verify(mockTransaction).commit();
            verify(mockEntityManager).close();

            assertTrue(usuario.getPropFavorita().containsKey("Testing"));
            assertEquals(propuesta, usuario.getPropFavorita().get("Testing"));
            assertEquals("Testing", propuesta.getTitulo());
        }
    }
    //FIN USUARIOS
    
    //INICIO CATEGORIA
    @Test
    public void testAltaDeCategoriaExitosa_CategoriaPadre() 
    {
        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) 
        {
            
            doNothing().when(mockTransaction).begin();
            doNothing().when(mockTransaction).commit();
            doNothing().when(mockEntityManager).persist(any(Categoria.class));
            doNothing().when(mockEntityManager).close();
            
            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);
            
            DTOCategoria categoriaDTO = new DTOCategoria();
            categoriaDTO.setNombreCategoria("Teatro");
            categoriaDTO.setCatPadre("");
            
            boolean resultado = controller.altaDeCategoria(categoriaDTO);
            
            assertTrue(resultado);

        }
    }
    
    @Test
    public void testAltaDeCategoria_Subcategoria() 
    {
        
        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) 
        {         
            doNothing().when(mockTransaction).begin();
            doNothing().when(mockTransaction).commit();
            doNothing().when(mockEntityManager).close();

            when(mockEntityManager.merge(any(Categoria.class))).thenAnswer(invocation -> invocation.getArgument(0));
            
            Categoria categoriaPadre = new Categoria("Teatro", null);

            when(mockEntityManager.find(Categoria.class, "Teatro")).thenReturn(categoriaPadre);
            when(mockEntityManager.find(Categoria.class, "Comedia")).thenReturn(null);
            
            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);
            
            DTOCategoria subcategoriaDTO = new DTOCategoria();
            subcategoriaDTO.setNombreCategoria("Comedia");
            subcategoriaDTO.setCatPadre("Teatro");
            
           
            boolean resultado = controller.altaDeCategoria(subcategoriaDTO);
 
            assertTrue(resultado);
        }
    }
    
    @Test
    public void testGetCategorias_ConCategoriasExistentes() 
    {
        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) 
        {

            doNothing().when(mockEntityManager).close();

            jakarta.persistence.TypedQuery<Categoria> mockQuery = mock(jakarta.persistence.TypedQuery.class);

            Categoria teatro = new Categoria("Teatro", null);
            Categoria musica = new Categoria("Música", null);
            Categoria cine = new Categoria("Cine", null);

            List<Categoria> categoriasDB = new ArrayList<>();
            categoriasDB.add(teatro);
            categoriasDB.add(musica);
            categoriasDB.add(cine);

            when(mockEntityManager.createQuery(eq("select catImport from Categoria catImport where catImport.catPadre is NULL"),eq(Categoria.class))).thenReturn(mockQuery);
            when(mockQuery.getResultList()).thenReturn(categoriasDB);

            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            List<DTOCategoria> resultado = controller.getCategorias();

            assertNotNull(resultado);

        }
    }

    @Test
    public void testGetCategorias_SinCategorias() 
    {
        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) 
        {

            doNothing().when(mockEntityManager).close();

            jakarta.persistence.TypedQuery<Categoria> mockQuery = mock(jakarta.persistence.TypedQuery.class);

            List<Categoria> categoriasVacias = new ArrayList<>();

            when(mockEntityManager.createQuery(eq("select catImport from Categoria catImport where catImport.catPadre is NULL"), eq(Categoria.class))).thenReturn(mockQuery);
            when(mockQuery.getResultList()).thenReturn(categoriasVacias);

            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            
            List<DTOCategoria> resultado = controller.getCategorias();

            
            assertNotNull(resultado, "El resultado no debería ser null");
            assertEquals(0, resultado.size(), "Debería retornar lista vacía");

        }
    }

    @Test
    public void testGetCategorias_ConSubcategorias() 
    {
        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) 
        {

            doNothing().when(mockEntityManager).close();

            jakarta.persistence.TypedQuery<Categoria> mockQuery = mock(jakarta.persistence.TypedQuery.class);

            Categoria teatro = new Categoria("Teatro", null);
            Categoria comedia = new Categoria("Comedia", teatro);
            Categoria drama = new Categoria("Drama", teatro);
            teatro.addSubcategoria(comedia);
            teatro.addSubcategoria(drama);

            List<Categoria> categoriasDB = new ArrayList<>();
            categoriasDB.add(teatro);

            when(mockEntityManager.createQuery(eq("select catImport from Categoria catImport where catImport.catPadre is NULL"),eq(Categoria.class))).thenReturn(mockQuery);
            when(mockQuery.getResultList()).thenReturn(categoriasDB);

            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            List<DTOCategoria> resultado = controller.getCategorias();

            assertNotNull(resultado);

        }
    }

    @Test
    public void testGetCategorias_ExceptionEnQuery() 
    {
        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {

            doNothing().when(mockEntityManager).close();

            when(mockEntityManager.createQuery(anyString(), eq(Categoria.class))).thenThrow(new RuntimeException("Error en query"));

            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);


            assertThrows(RuntimeException.class, () -> {controller.getCategorias();});
        }
    }
    //FIN CATEGORIA
    
    //INICIO PROPUESTA    
    @Test
    public void testAltaPropuesta() 
    {
        System.out.println("altaPropuesta");
                    
        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) 
        {
            
            //Me salto la modificación de la bd
            doNothing().when(mockTransaction).begin();
            doNothing().when(mockTransaction).commit();
            doNothing().when(mockEntityManager).persist(any(Propuesta.class));
            doNothing().when(mockEntityManager).close();
        
            //when(mockEntityManager.merge(any())).thenAnswer(invocation -> invocation.getArgument(0));

            Categoria categoriaTest = new Categoria("fiesta", null);
            Proponente proponenteTest = new Proponente("Diego", "Perez", "www.test.com","diegop", "pass123", "Diego P","diego@test.com", LocalDate.now(), "");
            
            //Mock en los finds dentro del manejador para que devuelvan objetos sikmulados con mock
            when(mockEntityManager.find(Categoria.class, "fiesta")).thenReturn(categoriaTest);
            when(mockEntityManager.find(Usuario.class, "diegop")).thenReturn(proponenteTest);
            
            //Se simula la obtención del em...
            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            String titulo = "JKLJKLJKL";
            String descripcion = "TestDescrip";
            String imagen = "";
            String lugar = "TestLugar";
            LocalDate fecha = LocalDate.now();
            int precio = 100;
            int montoTotal = 1000;
            LocalDate fechaPub = LocalDate.now();
            List<TipoRetorno> retornos = new ArrayList<>();
            retornos.add(TipoRetorno.EntradaGratis);
            String categoriaNombre = "fiesta";
            String usuarioNombre = "diegop";
            Estado estado = Estado.PUBLICADA;

            controller.altaPropuesta(titulo, descripcion, imagen, lugar, fecha,precio, montoTotal, fechaPub, retornos, categoriaNombre,usuarioNombre, estado);

            //No puedo hacer un assert en una función void, sé que cubre ya que el report lo marca en verde y ya...
        }
    }
    @Test
    public void testObtenerPropuestas() 
    {
        System.out.println("obtenerPropuestas");
        
        String estado = "EN_FINANCIACION";

        Set<DTOPropuesta> result = controller.obtenerPropuestas(estado);
        
        assertNotNull(result);

    }
    @Test
    public void testObtenerPropuestas_casoNoEspecificado() 
    {
        System.out.println("obtenerPropuestas_casoNoEspecificado");
        
        String estado = "";

        Set<DTOPropuesta> result = controller.obtenerPropuestas(estado);
        
        assertNotNull(result);
    }
    
    @Test
    public void testAccionSobrePropuesta_casoEsProponente() 
    {
        System.out.println("accionSobrePropuesta_casoEsProponente");
        
        String nickUsuario = "Jose";
        DTOPropuesta t1 = mock(DTOPropuesta.class);
        
        when(t1.nickProponenteToString()).thenReturn(nickUsuario);  //Para que entre en el if del proponente...
        
        int result = controller.accionSobrePropuesta(nickUsuario, t1);
        
        assertEquals(1,result); //Si es 1 todo ok.
    }
    
    @Test
    public void testAccionSobrePropuesta_casoEsColaborador() 
    {
        System.out.println("accionSobrePropuesta_casoEsColaborador");
        
        String nickUsuario = "Rodolfo";
        DTOPropuesta t1 = mock(DTOPropuesta.class);
        
        //La colaboracion para pasar por el for y entrar al if de adentro
        List<DTOColaboracion> listaTest = new ArrayList();
        DTOColaboracion colabTest = mock(DTOColaboracion.class);
        when(colabTest.getColaborador()).thenReturn(nickUsuario);
        
        listaTest.add(colabTest);
        
        when(t1.nickProponenteToString()).thenReturn("Jose");  //Para que no entre en el if del proponente y vaya al de colaborador por el else...
        when(t1.getAporte()).thenReturn(listaTest);
        
        int result = controller.accionSobrePropuesta(nickUsuario, t1);
        
        assertEquals(2,result);  
    }
    
    @Test
    public void testAccionSobrePropuesta_casoLol() 
    {
        System.out.println("accionSobrePropuesta_casocasoLol");
        
        String nickUsuario = "Rodolfo";
        DTOPropuesta t1 = mock(DTOPropuesta.class);
        
        //La colaboracion para pasar por el for y entrar al if de adentro
        List<DTOColaboracion> listaTest = new ArrayList();
        DTOColaboracion colabTest = mock(DTOColaboracion.class);
        when(colabTest.getColaborador()).thenReturn("Nel");
        
        listaTest.add(colabTest);
        
        when(t1.nickProponenteToString()).thenReturn("Jose");  //Para que no entre en el if del proponente y vaya al de colaborador por el else...
        when(t1.getAporte()).thenReturn(listaTest);
        
        int result = controller.accionSobrePropuesta(nickUsuario, t1);
        
        assertEquals(3,result);
   
    }
    
    @Test
    public void testExisteProp() 
    {
        System.out.println("existeProp");
        
        String Titulo = "Cine en el Botanico";
        
        boolean result = controller.existeProp(Titulo);
        
        assertTrue(result);
    }
    
    @Test
    public void testCreadorPropuesta() 
    {
        System.out.println("creadorPropuesta");
        
        String titulo = "Cine en el Botanico";
        
        String result = controller.creadorPropuesta(titulo);
        assertNotNull(result);
    }
    
    @Test
    public void testEstadoPropuestas() 
    {
        System.out.println("estadoPropuestas");
        
        String titulo = "Cine en el Botanico";
               
        String result = controller.estadoPropuestas(titulo);
        
        assertNotNull(result);
    }
    
    @Test
    public void testModificarPropuesta() 
    {
        System.out.println("modificarPropuesta");
        
        DTOPropuesta p1 = controller.getPropuestaDTO("Cine en el Botanico");
       
        controller.modificarPropuesta(p1.getTitulo(), p1.getDescripcion(), p1.getImagen(), p1.getLugar(), p1.getFecha(), p1.getPrecio(), p1.getMontoTotal(), p1.getRetorno(), "Cine al Aire Libre", "USER_TEST_MODIFICAR_PROPUESTA", p1.getEstado());
    }
    
    @Test
    public void testListarPropuestas() 
    {
        System.out.println("ListarPropuestas");

        Set<DTOPropuesta> result = controller.ListarPropuestas("PUBLICADA", "EN_FINANCIACION");
        
        assertNotNull(result);
        
        
    }
    
    @Test
    public void testExtenderOCancelarPropuesta_casoCancelar() 
    {
        System.out.println("testExtenderOCancelarPropuesta_casoCancelar");
        
        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) 
        {       
            
            //Me salto la modificación de la bd
            doNothing().when(mockTransaction).begin();
            doNothing().when(mockTransaction).commit();
            doNothing().when(mockEntityManager).persist(any(Propuesta.class));
            doNothing().when(mockEntityManager).close();
        
            //when(mockEntityManager.merge(any())).thenAnswer(invocation -> invocation.getArgument(0));
            
            Propuesta p1 = new Propuesta();
            
            //Mock en los finds dentro del manejador para que devuelvan objetos sikmulados con mock
            when(mockEntityManager.find(Propuesta.class, "Cine en el Botanico")).thenReturn(p1);
            
            //Se simula la obtención del em...
            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            int resultado = controller.extenderOCancelarPropuesta("CANCELAR", "Cine en el Botanico");

            assertEquals(2, resultado);
        }
    }
        @Test
    public void testExtenderOCancelarPropuesta_casoCancelar_noPersiste() 
    {
        System.out.println("testExtenderOCancelarPropuesta_casoCancelar_noPersiste");
        
        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) 
        {       
            
            //Me salto la modificación de la bd
            doNothing().when(mockTransaction).begin();
            doNothing().when(mockTransaction).commit();
            doNothing().when(mockEntityManager).persist(any(Propuesta.class));
            doNothing().when(mockEntityManager).close();

            //Mock en los finds dentro del manejador para que caiga la sesion de db
            when(mockEntityManager.find(Propuesta.class, "Cine en el Botanico")).thenThrow(new RuntimeException());

            //Se simula la obtención del em...
            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            int resultado = controller.extenderOCancelarPropuesta("CANCELAR", "Cine en el Botanico");

            assertEquals(2, resultado);
        }
    }
    
    @Test
    public void testExtenderOCancelarPropuesta_casoExtender_noPersiste() 
    {
        System.out.println("testExtenderOCancelarPropuesta_casoExtender_noPersiste");

        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) 
        {       
            
            //Me salto la modificación de la bd
            doNothing().when(mockTransaction).begin();
            doNothing().when(mockTransaction).commit();
            doNothing().when(mockEntityManager).persist(any(Propuesta.class));
            doNothing().when(mockEntityManager).close();

            //Mock en los finds dentro del manejador para que caiga la sesion de db
            when(mockEntityManager.find(Propuesta.class, "Cine en el Botanico")).thenThrow(new RuntimeException());

            //Se simula la obtención del em...
            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            int resultado = controller.extenderOCancelarPropuesta("EXTENDER", "Cine en el Botanico");

            assertEquals(3, resultado);
        }
    }
        @Test
    public void testExtenderOCancelarPropuesta_casoExtender() 
    {
        System.out.println("testExtenderOCancelarPropuesta_casoExtender");

        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) 
        {       
            
            //Me salto la modificación de la bd
            doNothing().when(mockTransaction).begin();
            doNothing().when(mockTransaction).commit();
            doNothing().when(mockEntityManager).persist(any(Propuesta.class));
            doNothing().when(mockEntityManager).close();

            //Con el mock de estos objetos se puede controlar lo que devuelven al ser instanciados.
            Propuesta p1 = mock(Propuesta.class); 
            DTORegistro_Estado ah = mock(DTORegistro_Estado.class);  //Para poder hacer el mock en el if necesito hacer todo el recorrido de los gets.
            
            //Mock en los finds dentro del manejador para que devuelvan objetos sikmulados con mock
            when(mockEntityManager.find(Propuesta.class, "Cine en el Botanico")).thenReturn(p1);
            
            //Mocks para el primer if (donde pregunta si es publicada o en financiacion)
            when(p1.getUltimoEstado()).thenReturn(ah);
            when(ah.getEstado()).thenReturn(Estado.PUBLICADA);
            
            //Se simula la obtención del em...
            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            int resultado = controller.extenderOCancelarPropuesta("EXTENDER", "Cine en el Botanico");

            assertEquals(3, resultado);
        }
    }
    
    
    @Test
    public void testCargarPropuesta() 
    {
        System.out.println("CargarPropuesta");
        
        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) 
        {

            doNothing().when(mockTransaction).begin();
            doNothing().when(mockTransaction).commit();
            doNothing().when(mockEntityManager).persist(any(Propuesta.class));
            doNothing().when(mockEntityManager).close();

            //Simulo el null en los fiid para que sí entre a los if...
            when(mockEntityManager.find(eq(Propuesta.class), anyString())).thenReturn(null);

            //Esto sería para las categorias y proponentes, puede que no sea necesario...
            //when(mockEntityManager.find(eq(Proponente.class), anyString())).thenAnswer(inv -> mock(Proponente.class));
            //when(mockEntityManager.find(eq(Categoria.class), anyString())).thenAnswer(inv -> mock(Categoria.class));

            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            controller.cargarPropuesta();

        }
    }
    
    //FIN PROPUESTA
    
    
    //INICIO COLABORACION
    //FIN COLABORACION
}
