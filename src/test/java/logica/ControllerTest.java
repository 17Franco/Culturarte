package logica;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import logica.Manejadores.ManejadorCategoria;
import persistencia.PersistenciaManager;

import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import logica.Colaboracion.Colaboracion;
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
import org.junit.jupiter.api.io.TempDir;
import static org.mockito.Mockito.*;
import logica.Manejadores.ManejadorPropuesta;
import logica.Manejadores.ManejadorUsuario;

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
    public void testgetListaDTOUsuarios() {
        System.out.println("ListaDTOUsuarios");

        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {
            doNothing().when(mockEntityManager).close();

            List<Usuario> listaVacia = new ArrayList<>();

            TypedQuery<Usuario> mockQuery = mock(TypedQuery.class);
            when(mockQuery.getResultList()).thenReturn(listaVacia);
            when(mockEntityManager.createQuery(eq("FROM Usuario"), eq(Usuario.class))).thenReturn(mockQuery);

            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            List<DTOUsuario> result = controller.ListaDTOUsuarios();

            assertNotNull(result);
        }
    }
    @Test
    public void testListaColaboradores() {
        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {
            doNothing().when(mockEntityManager).close();

            Colaborador colab1 = new Colaborador(
                    "colab1", "Juan", "Pérez",
                    "juan@test.com", LocalDate.of(1990, 5, 15), "img1.jpg"
            );

            Colaborador colab2 = new Colaborador(
                    "colab2", "María", "González",
                    "maria@test.com", LocalDate.of(1992, 8, 20), "img2.jpg"
            );

            List<Colaborador> listaColaboradores = Arrays.asList(colab1, colab2);
            TypedQuery<Colaborador> mockQuery = mock(TypedQuery.class);
            when(mockQuery.getResultList()).thenReturn(listaColaboradores);
            when(mockEntityManager.createQuery(anyString(), eq(Colaborador.class))).thenReturn(mockQuery);

            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            Set<DTOColaborador> result = controller.ListarColaboradores();

            assertNotNull(result);
            assertEquals(2, result.size());
        }
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

            doNothing().when(mockTransaction).begin();
            doNothing().when(mockTransaction).commit();
            doNothing().when(mockEntityManager).persist(any(Colaborador.class));
            doNothing().when(mockEntityManager).close();

            when(mockEntityManager.find(Usuario.class, "testcolab")).thenReturn(null);
            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            DTOColaborador dtoColaborador = new DTOColaborador("TestC", "123", "algotest", "algo", "test2@g.com", LocalDate.now(), "", "Colaborador");
            controller.altaUsuario(dtoColaborador);
        }
    }

    @Test
    public void testAltaUsuarioProponente() {
        System.out.println("altaUsuario - Proponente");

        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {

            doNothing().when(mockTransaction).begin();
            doNothing().when(mockTransaction).commit();
            doNothing().when(mockEntityManager).persist(any(Proponente.class));
            doNothing().when(mockEntityManager).close();

            when(mockEntityManager.find(Usuario.class, "testprop")).thenReturn(null);

            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            DTOProponente dtoProponente = new DTOProponente("algo", "algo", "www.comt.com", "testP", "123", "test", "pacotest", "test@g.com", LocalDate.now(), "", "Proponente");
            controller.altaUsuario(dtoProponente);
        }
    }
    @Test
    public void testDejarDeSeguirUsuarioExitoso() {
        System.out.println("dejarDeSeguirUsuario - Exitoso");

        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {

            doNothing().when(mockTransaction).begin();
            doNothing().when(mockTransaction).commit();
            doNothing().when(mockTransaction).rollback();
            when(mockTransaction.isActive()).thenReturn(false);
            doNothing().when(mockEntityManager).close();

            Usuario usuario1 = mock(Usuario.class);
            Usuario usuario2 = mock(Usuario.class);

            Map<String, Usuario> usuariosSeguidos = new HashMap<>();
            usuariosSeguidos.put("usuario2", usuario2);

            when(usuario1.getUsuarioSeguido()).thenReturn(usuariosSeguidos);
            when(mockEntityManager.find(Usuario.class, "usuario1")).thenReturn(usuario1);
            when(mockEntityManager.find(Usuario.class, "usuario2")).thenReturn(usuario2);
            when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);

            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            boolean resultado = controller.unFollowUser("usuario1", "usuario2");

            assertTrue(resultado);
        }
    }

    @Test
    public void testDejarDeSeguirUsuarioNoSeguia() {
        System.out.println("dejarDeSeguirUsuario - No seguía");

        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {

            doNothing().when(mockEntityManager).close();

            Usuario usuario1 = mock(Usuario.class);
            Usuario usuario2 = mock(Usuario.class);

            Map<String, Usuario> usuariosSeguidos = new HashMap<>();
            when(usuario1.getUsuarioSeguido()).thenReturn(usuariosSeguidos);

            when(mockEntityManager.find(Usuario.class, "usuario1")).thenReturn(usuario1);
            when(mockEntityManager.find(Usuario.class, "usuario2")).thenReturn(usuario2);
            when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);

            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            boolean resultado = controller.unFollowUser("usuario1", "usuario2");

            assertFalse(resultado);
        }
    }

    @Test
    public void testDejarDeSeguirUsuarioPrimerUsuarioNoExiste() {
        System.out.println("dejarDeSeguirUsuario - Primer usuario no existe");

        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {

            doNothing().when(mockEntityManager).close();

            Usuario usuario2 = mock(Usuario.class);

            when(mockEntityManager.find(Usuario.class, "noexiste")).thenReturn(null);
            when(mockEntityManager.find(Usuario.class, "usuario2")).thenReturn(usuario2);
            when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);

            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            boolean resultado = controller.unFollowUser("noexiste", "usuario2");

            assertFalse(resultado);
        }
    }

    @Test
    public void testDejarDeSeguirUsuarioSegundoUsuarioNoExiste() {
        System.out.println("dejarDeSeguirUsuario - Segundo usuario no existe");

        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {

            doNothing().when(mockEntityManager).close();

            Usuario usuario1 = mock(Usuario.class);

            when(mockEntityManager.find(Usuario.class, "usuario1")).thenReturn(usuario1);
            when(mockEntityManager.find(Usuario.class, "noexiste")).thenReturn(null);
            when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);

            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            boolean resultado = controller.unFollowUser("usuario1", "noexiste");

            assertFalse(resultado);
        }
    }

    @Test
    public void testDejarDeSeguirUsuarioMismoNickname() {
        System.out.println("dejarDeSeguirUsuario - Mismo nickname");

        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {

            doNothing().when(mockEntityManager).close();

            Usuario usuario = mock(Usuario.class);

            when(mockEntityManager.find(Usuario.class, "mismo")).thenReturn(usuario);
            when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);

            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            boolean resultado = controller.unFollowUser("mismo", "mismo");

            assertFalse(resultado);
        }
    }

    @Test
    public void testDejarDeSeguirUsuarioConExcepcion() {
        System.out.println("dejarDeSeguirUsuario - Con excepción");

        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {

            doNothing().when(mockTransaction).begin();
            doNothing().when(mockTransaction).rollback();
            when(mockTransaction.isActive()).thenReturn(true);
            doNothing().when(mockEntityManager).close();

            Usuario usuario1 = mock(Usuario.class);
            Usuario usuario2 = mock(Usuario.class);

            Map<String, Usuario> usuariosSeguidos = new HashMap<>();
            usuariosSeguidos.put("usuario2", usuario2);

            when(usuario1.getUsuarioSeguido()).thenReturn(usuariosSeguidos);

            doThrow(new RuntimeException("Error simulado")).when(usuario1).unfollow(usuario2);

            when(mockEntityManager.find(Usuario.class, "usuario1")).thenReturn(usuario1);
            when(mockEntityManager.find(Usuario.class, "usuario2")).thenReturn(usuario2);
            when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);

            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            boolean resultado = controller.unFollowUser("usuario1", "usuario2");

            assertFalse(resultado);
        }
    }

    @Test
    public void testDejarDeSeguirUsuarioMultiplesSeguidores() {
        System.out.println("dejarDeSeguirUsuario - Usuario sigue a múltiples");

        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {

            doNothing().when(mockTransaction).begin();
            doNothing().when(mockTransaction).commit();
            when(mockTransaction.isActive()).thenReturn(false);
            doNothing().when(mockEntityManager).close();

            Usuario usuario1 = mock(Usuario.class);
            Usuario usuario2 = mock(Usuario.class);
            Usuario usuario3 = mock(Usuario.class);

            Map<String, Usuario> usuariosSeguidos = new HashMap<>();
            usuariosSeguidos.put("usuario2", usuario2);
            usuariosSeguidos.put("usuario3", usuario3);

            when(usuario1.getUsuarioSeguido()).thenReturn(usuariosSeguidos);

            when(mockEntityManager.find(Usuario.class, "usuario1")).thenReturn(usuario1);
            when(mockEntityManager.find(Usuario.class, "usuario2")).thenReturn(usuario2);
            when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);

            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            boolean resultado = controller.unFollowUser("usuario1", "usuario2");

            assertTrue(resultado);
            verify(usuario1, times(1)).unfollow(usuario2);
            verify(mockTransaction, times(1)).commit();
            verify(mockEntityManager, times(1)).close();
        }
    }

    @Test
    public void testDejarDeSeguirUsuarioAmbosUsuariosNull() {
        System.out.println("dejarDeSeguirUsuario - Ambos usuarios null");

        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {

            doNothing().when(mockEntityManager).close();

            when(mockEntityManager.find(Usuario.class, "noexiste1")).thenReturn(null);
            when(mockEntityManager.find(Usuario.class, "noexiste2")).thenReturn(null);
            when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);

            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            boolean resultado = controller.unFollowUser("noexiste1", "noexiste2");

            assertFalse(resultado);
            verify(mockTransaction, never()).begin();
            verify(mockEntityManager, times(1)).close();
        }
    }
    @Test
    public void testSeguirUsrExitoso() {
        System.out.println("seguirUsr - Exitoso");
        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {
            doNothing().when(mockTransaction).begin();
            doNothing().when(mockTransaction).commit();
            doNothing().when(mockTransaction).rollback();
            doNothing().when(mockEntityManager).close();

            Usuario usuario1 = mock(Usuario.class);
            Usuario usuario2 = mock(Usuario.class);

            Map<String, Usuario> usuariosSeguidos = new HashMap<>();
            when(usuario1.getUsuarioSeguido()).thenReturn(usuariosSeguidos);

            when(mockEntityManager.find(Usuario.class, "usuario1")).thenReturn(usuario1);
            when(mockEntityManager.find(Usuario.class, "usuario2")).thenReturn(usuario2);
            when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);
            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            boolean resultado = controller.seguir("usuario1", "usuario2");

            assertTrue(resultado);
        }
    }

    @Test
    public void testSeguirUsrYaSeguia() {
        System.out.println("seguirUsr - Ya seguía");
        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {
            doNothing().when(mockEntityManager).close();

            Usuario usuario1 = mock(Usuario.class);
            Usuario usuario2 = mock(Usuario.class);

            Map<String, Usuario> usuariosSeguidos = new HashMap<>();
            usuariosSeguidos.put("usuario2", usuario2);
            when(usuario1.getUsuarioSeguido()).thenReturn(usuariosSeguidos);

            when(mockEntityManager.find(Usuario.class, "usuario1")).thenReturn(usuario1);
            when(mockEntityManager.find(Usuario.class, "usuario2")).thenReturn(usuario2);
            when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);
            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            boolean resultado = controller.seguir("usuario1", "usuario2");

            assertFalse(resultado);
        }
    }

    @Test
    public void testSeguirUsrPrimerUsuarioNoExiste() {
        System.out.println("seguirUsr - Primer usuario no existe");
        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {
            doNothing().when(mockEntityManager).close();

            Usuario usuario2 = mock(Usuario.class);

            when(mockEntityManager.find(Usuario.class, "noexiste")).thenReturn(null);
            when(mockEntityManager.find(Usuario.class, "usuario2")).thenReturn(usuario2);
            when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);
            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            boolean resultado = controller.seguir("noexiste", "usuario2");

            assertFalse(resultado);
        }
    }

    @Test
    public void testSeguirUsrSegundoUsuarioNoExiste() {
        System.out.println("seguirUsr - Segundo usuario no existe");
        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {
            doNothing().when(mockEntityManager).close();

            Usuario usuario1 = mock(Usuario.class);

            when(mockEntityManager.find(Usuario.class, "usuario1")).thenReturn(usuario1);
            when(mockEntityManager.find(Usuario.class, "noexiste")).thenReturn(null);
            when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);
            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            boolean resultado = controller.seguir("usuario1", "noexiste");

            assertFalse(resultado);
        }
    }

    @Test
    public void testSeguirUsrMismoNickname() {
        System.out.println("seguirUsr - Mismo nickname");
        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {
            doNothing().when(mockEntityManager).close();

            Usuario usuario = mock(Usuario.class);

            when(mockEntityManager.find(Usuario.class, "mismo")).thenReturn(usuario);
            when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);
            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            boolean resultado = controller.seguir("mismo", "mismo");

            assertFalse(resultado);
        }
    }

    @Test
    public void testSeguirUsrConExcepcion() {
        System.out.println("seguirUsr - Con excepción");
        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {
            doNothing().when(mockTransaction).begin();
            doNothing().when(mockTransaction).rollback();
            doNothing().when(mockEntityManager).close();

            Usuario usuario1 = mock(Usuario.class);
            Usuario usuario2 = mock(Usuario.class);

            Map<String, Usuario> usuariosSeguidos = new HashMap<>();
            when(usuario1.getUsuarioSeguido()).thenReturn(usuariosSeguidos);

            doThrow(new RuntimeException("Error simulado")).when(usuario1).seguir(usuario2);

            when(mockEntityManager.find(Usuario.class, "usuario1")).thenReturn(usuario1);
            when(mockEntityManager.find(Usuario.class, "usuario2")).thenReturn(usuario2);
            when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);
            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            boolean resultado = controller.seguir("usuario1", "usuario2");

            assertFalse(resultado);
        }
    }

    @Test
    public void testSeguirUsrAmbosUsuariosNull() {
        System.out.println("seguirUsr - Ambos usuarios null");
        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {
            doNothing().when(mockEntityManager).close();

            when(mockEntityManager.find(Usuario.class, "noexiste1")).thenReturn(null);
            when(mockEntityManager.find(Usuario.class, "noexiste2")).thenReturn(null);
            when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);
            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            boolean resultado = controller.seguir("noexiste1", "noexiste2");

            assertFalse(resultado);
        }
    }

    @Test
    public void testSeguirUsrMultiplesSeguidores() {
        System.out.println("seguirUsr - Seguir a múltiples usuarios");
        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {
            doNothing().when(mockTransaction).begin();
            doNothing().when(mockTransaction).commit();
            doNothing().when(mockEntityManager).close();

            Usuario usuario1 = mock(Usuario.class);
            Usuario usuario2 = mock(Usuario.class);
            Usuario usuario3 = mock(Usuario.class);

            Map<String, Usuario> usuariosSeguidos = new HashMap<>();
            usuariosSeguidos.put("usuario3", usuario3);
            when(usuario1.getUsuarioSeguido()).thenReturn(usuariosSeguidos);

            when(mockEntityManager.find(Usuario.class, "usuario1")).thenReturn(usuario1);
            when(mockEntityManager.find(Usuario.class, "usuario2")).thenReturn(usuario2);
            when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);
            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            boolean resultado = controller.seguir("usuario1", "usuario2");

            assertTrue(resultado);
        }
    }

    @Test
    public void testSeguirUsrSeguirYDejarDeSeguir() {
        System.out.println("seguirUsr - Seguir y luego dejar de seguir");
        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {
            doNothing().when(mockTransaction).begin();
            doNothing().when(mockTransaction).commit();
            doNothing().when(mockEntityManager).close();

            Usuario usuario1 = mock(Usuario.class);
            Usuario usuario2 = mock(Usuario.class);

            Map<String, Usuario> usuariosSeguidos1 = new HashMap<>();
            when(usuario1.getUsuarioSeguido()).thenReturn(usuariosSeguidos1);

            when(mockEntityManager.find(Usuario.class, "usuario1")).thenReturn(usuario1);
            when(mockEntityManager.find(Usuario.class, "usuario2")).thenReturn(usuario2);
            when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);
            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            boolean resultado1 = controller.seguir("usuario1", "usuario2");
            assertTrue(resultado1);

            Map<String, Usuario> usuariosSeguidos2 = new HashMap<>();
            usuariosSeguidos2.put("usuario2", usuario2);
            when(usuario1.getUsuarioSeguido()).thenReturn(usuariosSeguidos2);

            boolean resultado2 = controller.seguir("usuario1", "usuario2");
            assertFalse(resultado2);
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
        String nick = "";
        String email = "";

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
            doNothing().when(mockTransaction).begin();
            doNothing().when(mockTransaction).commit();
            doNothing().when(mockTransaction).rollback();
            doNothing().when(mockEntityManager).persist(any(Usuario.class));
            doNothing().when(mockEntityManager).close();
            when(mockEntityManager.find(eq(Usuario.class), anyString())).thenReturn(null);

            when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);

            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            controller.cargarSeguidos();

        }
    }
    @Test
    public void testGetImg_exception() throws Exception {
    String ruta = "archivo.jpg";
    String rutaCompleta = "/home/fran/Escritorio/Lab2PA" + File.separator + ruta;

    Controller controller = new Controller();

    try (MockedStatic<Files> filesMock = mockStatic(Files.class)) {
        filesMock.when(() -> Files.readAllBytes(Paths.get(rutaCompleta))).thenThrow(new IOException("Error simulado"));

        byte[] resultado = controller.getImg(ruta);

        assertNull(resultado);
    }
    }
    @Test
    public void testObtenerPathImg_exception() throws Exception {
    String nick = "Rick";
    String nombreArchivo = "foto.jpg";
    byte[] contenido = new byte[]{1,2,3};

    Controller controller = new Controller();
    String rutaEsperada = "IMG" + File.separator + nick + File.separator + nombreArchivo;
    String carpetaDestino = "/home/fran/Escritorio/Lab1PA/IMG" + File.separator + nick;
    Path destino = Paths.get(carpetaDestino, nombreArchivo);

    try (MockedStatic<Files> filesMock = mockStatic(Files.class)) {
        filesMock.when(() -> Files.write(destino, contenido))
                 .thenThrow(new IOException("Error simulado"));

        String resultado = controller.obtenerPathImg(nick, contenido, nombreArchivo);

        assertNull(resultado); 
    }
    }

    
    @Test
    public void testCargarColaborador() {
        System.out.println("CargarColaborador");

        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {
            doNothing().when(mockTransaction).begin();
            doNothing().when(mockTransaction).commit();
            doNothing().when(mockTransaction).rollback();
            doNothing().when(mockEntityManager).persist(any(Colaborador.class));
            doNothing().when(mockEntityManager).close();

            when(mockEntityManager.find(eq(Colaborador.class), anyString())).thenReturn(null);

            when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);

            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            controller.cargarDatosPruebaColaborador();

        }
    }
    @Test
    public void testCargarProponente() {
        System.out.println("CargarProponente");

        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {
            doNothing().when(mockTransaction).begin();
            doNothing().when(mockTransaction).commit();
            doNothing().when(mockTransaction).rollback();
            doNothing().when(mockEntityManager).persist(any(Proponente.class));
            doNothing().when(mockEntityManager).close();

            when(mockEntityManager.find(eq(Proponente.class), anyString())).thenReturn(null);
            when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);

            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            controller.cargarDatosPruebaProponente();
            
        }
    }
    @Test
    public void testGetDTOColaborador() {
        System.out.println("getDTOColaborador");

        String nick = "chino"; 

        DTOColaborador resultado = controller.getDTOColaborador(nick);

        assertNotNull(resultado);
        assertEquals(nick, resultado.getNickname());
    }
    @Test
    public void testGetDTOColaboradorF() {
        System.out.println("getDTOColaborador donde no existe");
        String nick = "test";
        try {
            DTOColaborador resultado = controller.getDTOColaborador(nick);
            assertNull(resultado);
        } catch (Exception e) {
            assertTrue(true);
        }
    }
    @Test
    public void testGetDTOProponente() {
        System.out.println("getDTOProponente");

        String nick = "diegop";

        DTOProponente resultado = controller.getDTOProponente(nick);

        assertNotNull(resultado);
        assertEquals(nick, resultado.getNickname());
    }

    @Test
    public void testGetDTOProponenteF() {
        System.out.println("getDTOProponente donde no existe");
        String nick = "test";
        try {
            DTOProponente resultado = controller.getDTOProponente(nick);
            assertNull(resultado);
        } catch (Exception e) {
            assertTrue(true);
        }
    }
    @Test
    public void getPropuestasCreadasPorProponente() {
        System.out.println("getPropuestasCreadasPorProponente");

        String nick = "diegop";

        Set<DTOPropuesta> resultado = controller.getPropuestasCreadasPorProponente(nick);

        assertNotNull(resultado);
    }
    @Test
    public void testesFavorita() {
        System.out.println("esFavorita");
        String nick = "diegop";
        String titulo = "Romeo y Julieta";
        boolean result = controller.esFavorita(nick, titulo);
        assertEquals(false, result);
    }
@Test
public void testGetFavoritasConPropuestas() {
    System.out.println("getFavoritas - Con propuestas favoritas");
    
    try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {
        
        doNothing().when(mockEntityManager).close();
        
        Categoria categoria = new Categoria("TestCat");
        
        Proponente proponente = new Proponente("Test1", "Test1", "www.Test1.com", "TestUsu", "TestProp", "Test1", "Test1@g.com", LocalDate.now(), "");
        
        Propuesta propuesta1 = new Propuesta("Propuesta Test 1","Descripción test","img1.jpg","Lugar Test",LocalDate.now().plusDays(10),100,1000,LocalDate.now(),new ArrayList<>(),categoria,proponente,Estado.PUBLICADA);
        
        Propuesta propuesta2 = new Propuesta("Propuesta Test 2","Descripción 2","img2.jpg","Lugar 2",LocalDate.now().plusDays(15),200,2000,LocalDate.now(),new ArrayList<>(),categoria,proponente,Estado.FINANCIADA);
        
        Usuario usuario = mock(Usuario.class);
        Map<String, Propuesta> favoritas = new HashMap<>();
        favoritas.put("prop1", propuesta1);
        favoritas.put("prop2", propuesta2);
        
        when(usuario.getPropFavorita()).thenReturn(favoritas);
        when(mockEntityManager.find(Usuario.class, "testusuario")).thenReturn(usuario);
        mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);
        
        List<DTOPropuesta> resultado = controller.getFavoritas("testusuario");

        assertNotNull(resultado);

        DTOPropuesta dto1 = resultado.stream().filter(p -> "Propuesta Test 1".equals(p.getTitulo())).findFirst().orElse(null);
        
        assertNotNull(dto1);
        assertEquals("Propuesta Test 1", dto1.getTitulo());
    }
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
    public void testListaSeguidosPorUsuario() {
        System.out.println("ListaColaboradores");
        String nick = "diegop";
        List<String> result = controller.ListaSeguidosPorUsuario(nick);
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
            
            controller.marcarComoFavorita("TestNick", "Testing");

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
    @Test
    public void testQuitarFavorita() {
        System.out.println("testQuitarFavorita");

        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {

            EntityManager mockEntityManager = mock(EntityManager.class);
            EntityTransaction mockTransaction = mock(EntityTransaction.class);

            doNothing().when(mockTransaction).begin();
            doNothing().when(mockTransaction).commit();
            doNothing().when(mockEntityManager).close();
            when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);

            Proponente usuario = new Proponente("Test1", "Test1", "www.Test1.com", "TestUsu", "TestProp", "Test1", "Test1@g.com", LocalDate.now(), "");
            Categoria cat = new Categoria("TestCat");
            Propuesta propuesta = new Propuesta("Testing", "Testing", "", "Testing", LocalDate.now(), 100, 100, LocalDate.now(), new ArrayList<>(), cat, usuario, Estado.PUBLICADA);

            usuario.Favorita(propuesta);
            assertTrue(usuario.getPropFavorita().containsKey("Testing"));

            when(mockEntityManager.find(Usuario.class, "TestNick")).thenReturn(usuario);
            when(mockEntityManager.find(Propuesta.class, "Testing")).thenReturn(propuesta);

            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            controller.quitarFavorita("TestNick", "Testing");

            assertFalse(usuario.getPropFavorita().containsKey("Testing"));
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
    public void testAltaDeCategoriaFail_CategoriaPadre_exeption() 
    {
        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) 
        {
            
            doNothing().when(mockTransaction).begin();
            doThrow(new RuntimeException()).when(mockTransaction).commit();
            doNothing().when(mockEntityManager).persist(any(Categoria.class));
            doNothing().when(mockEntityManager).close();
            
            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);
            
            DTOCategoria categoriaIngresada = mock(DTOCategoria.class);
            when(categoriaIngresada.getNombreCategoria()).thenReturn("Teatro");
            when(categoriaIngresada.getCatPadre()).thenReturn("");
            
            

            
            boolean resultado = controller.altaDeCategoria(categoriaIngresada);
            
            assertFalse(resultado);

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
    public void testAltaDeCategoriaFail_Subcategoria_exeption() 
    {
        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) 
        {

            doNothing().when(mockTransaction).begin();
            doNothing().when(mockTransaction).rollback(); 
            doNothing().when(mockEntityManager).persist(any(Categoria.class));
            doNothing().when(mockEntityManager).close();

            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            DTOCategoria categoriaIngresada = mock(DTOCategoria.class);
            when(categoriaIngresada.getNombreCategoria()).thenReturn("Comedia");
            when(categoriaIngresada.getCatPadre()).thenReturn("Teatro");

            Categoria catPadre = mock(Categoria.class);

            when(mockEntityManager.find(Categoria.class, "Teatro")).thenReturn(catPadre);
            when(mockEntityManager.find(Categoria.class, "Comedia")).thenReturn(null);

            doThrow(new RuntimeException()).when(mockEntityManager).merge(catPadre);

            boolean resultado = controller.altaDeCategoria(categoriaIngresada);

            assertFalse(resultado);
        }
    }

    @Test
    public void testListaCategoria_casoNomal() 
    {
        System.out.println("ListaCategoria_casoNomal");

        List<String> result = controller.ListaCategoria(); 
        
        assertNotNull(result);
        
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
    
    @Test
    public void testCargarCategorias() 
    {
        System.out.println("cargarCategorias");

        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) 
        {

            doNothing().when(mockTransaction).begin();
            doNothing().when(mockTransaction).commit();
            doNothing().when(mockEntityManager).persist(any(Categoria.class));
            doNothing().when(mockEntityManager).close();

            //Simulo el null en los fiid para que sí entre a los if...
            when(mockEntityManager.find(eq(Categoria.class), anyString())).thenReturn(null);

            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            controller.cargarCategorias();
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
    public void testAltaPropuesta_casoProponenteNULL() 
    {
        System.out.println("altaPropuesta_casoProponenteNULL");
                    
        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) 
        {
            
            //Me salto la modificación de la bd
            doNothing().when(mockTransaction).begin();
            doNothing().when(mockTransaction).commit();
            doNothing().when(mockEntityManager).persist(any(Propuesta.class));
            doNothing().when(mockEntityManager).close();
        
            Categoria categoriaTest = new Categoria("fiesta", null);
            
            //Mock en find dentro del manejador para que devuelva objeto sikmulado con mock
            when(mockEntityManager.find(Categoria.class, "fiesta")).thenReturn(categoriaTest);
            
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
            String usuarioNombre = "nada";
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
    public void testAccionSobrePropuesta_casoEsColaborador() {
   
        Controller controllerMock = mock(Controller.class);

        DTOPropuesta propuestaMock = mock(DTOPropuesta.class);
        DTOColaborador dtoMock = mock(DTOColaborador.class);

        String userNick = "colab1";
        String accionUsuario = "COLABORAR";
        String comentario = "";
        String montoStr = "1000";
        String tipoRetorno = "PorcentajeGanancia";
        int permisos = 3;

        when(propuestaMock.getTitulo()).thenReturn("PropuestaTest");
        when(dtoMock.getNickname()).thenReturn("colab1");
        
        when(controllerMock.getDTOColaborador("colab1")).thenReturn(dtoMock);
        doNothing().when(controllerMock).altaColaboracion(any(DTOColaboracion.class));

        when(controllerMock.accionesSobrePropuesta(anyString(), anyInt(), anyString(), anyString(), any(DTOPropuesta.class), anyString(), anyString())).thenCallRealMethod();

        int resultado = controllerMock.accionesSobrePropuesta(userNick, permisos, accionUsuario, comentario, propuestaMock, montoStr, tipoRetorno);

        assertEquals(4, resultado);
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
    public void testGetMontoRecaudado() 
    {
        System.out.println("GetMontoRecaudado");

        controller.getMontoRecaudado("Cine en el Botanico");      
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
    public void testPermisosSobrePropuesta_primerIF_false() 
    {
        //Caso tipoUsuario es null.
        //UserNick igual a "visitante" 
        //Y el titulo de la propuesta es null.
        System.out.println("permisosSobrePropuesta_casoPrimerIF_false");

        String userNick = "VISITANTE";
        String tipoUsuario = null;

        DTOPropuesta propTest = mock(DTOPropuesta.class);

        //Acá se programa lo que el dto devuelve:
        when(propTest.nickProponenteToString()).thenReturn("rodolfo");
        when(propTest.getTitulo()).thenReturn(null);

        int expResult = 0;                                  //Se espera que sea 0 el resultado.

        int result = controller.permisosSobrePropuesta(userNick, tipoUsuario, propTest);

        assertEquals(expResult, result);
    }
    
    @Test
    public void testPermisosSobrePropuesta_esProponenteYpermiso3() 
    {
        //Caso tipoUsuario es proponente.
        //UserNick no es "visitante"
        //No ha comentado
        //Y el titulo de la propuesta no es null.
        
        System.out.println("testPermisosSobrePropuesta_esProponenteYpermiso3");
        
        DTOPropuesta propTest = mock(DTOPropuesta.class);
        String userNick = "propo";                             
        String tipoUsuario = "Proponente";
        
        List<DTOColaboracion> senuelo = new ArrayList();
        
        when(propTest.getAporte()).thenReturn(senuelo); //Para saltarme el for e if de accionSobreProp
        
        //Acá se programa lo que el dto devuelve:
        when(propTest.getTitulo()).thenReturn("El quijote endemoniado");    
        when(propTest.nickProponenteToString()).thenReturn("otro");         //Obligo que falle el if que retorna 1 en la funcion accionSobreProp

        //Se simula la funcion para que entre al segundo if y por ende tampoco al tercero:
        when(propTest.usuarioHaComentadoSN(userNick)).thenReturn(false);
        
        //Se simula la función que da permisos si no ha comentado, retorna 3.
        //when(controller.accionSobrePropuesta(userNick,propTest)).thenReturn(3);
        
        int expResult = 0;                                  //Se espera que sea 0 el resultado.
        
        int result = controller.permisosSobrePropuesta(userNick, tipoUsuario, propTest);
        
        assertEquals(expResult, result);    //Esto evaluará el resultado y lo esperado

    }
    
    @Test
    public void testPermisosSobrePropuesta_esProponenteYpermiso3HaComentado() 
    {
        //Caso tipoUsuario es proponente.
        //UserNick no es "visitante"
        //No ha comentado
        //Y el titulo de la propuesta no es null.
        
        System.out.println("testPermisosSobrePropuesta_esProponenteYpermiso3HaComentado");
        
        DTOPropuesta propTest = mock(DTOPropuesta.class);
        String userNick = "propo";                             
        String tipoUsuario = "Proponente";
        
        List<DTOColaboracion> senuelo = new ArrayList();
        
        when(propTest.getAporte()).thenReturn(senuelo); //Para saltarme el for e if de accionSobreProp
        
        //Acá se programa lo que el dto devuelve:
        when(propTest.getTitulo()).thenReturn("El quijote endemoniado");    
        when(propTest.nickProponenteToString()).thenReturn("otro");         //Obligo que falle el if que retorna 1 en la funcion accionSobreProp

        //Se simula la funcion para que entre al segundo if y por ende tampoco al tercero:
        when(propTest.usuarioHaComentadoSN(userNick)).thenReturn(true);
        
        int expResult = 0;                                  //Se espera que sea 0 el resultado.
        
        int result = controller.permisosSobrePropuesta(userNick, tipoUsuario, propTest);
        
        assertEquals(expResult, result);    //Esto evaluará el resultado y lo esperado
    }
    
    @Test
    public void testAccionesSobrePropuesta_casoProponenteExtiende() 
    {
        System.out.println("accionesSobrePropuesta_casoProponenteExtiende");
        
        Controller controllerR = new Controller();   //Debe ser un spy por que necesito que se ejecute la lógica en el controller, el mock falla y retorna 0
        Controller controllerClonado = spy(controllerR);

        String userNick = "Juan";
        int permisos = 1;
        String accionUsuario = "EXTENDER";
        String comentario = ""; 
        String montoStr = "";
        String tipoRetorno = null;
                
        DTOProponente mockUsuario = mock(DTOProponente.class);
        when(mockUsuario.getNickname()).thenReturn("Juan");
        
        DTOPropuesta propuestaActual = new DTOPropuesta();
        propuestaActual.setTitulo("prop");
        propuestaActual.setProponente(mockUsuario);

        Set<DTOPropuesta> propuestasTest = new HashSet<>();                     //Armo un set simple de prueba con ese solo elemento
        propuestasTest.add(propuestaActual);
        
        doReturn(propuestasTest).when(controllerClonado).getPropuestasCreadasPorProponente(userNick);
        doReturn(3).when(controllerClonado).extenderOCancelarPropuesta(accionUsuario, "prop");        //Que retorne 3.
        
        int result = controllerClonado.accionesSobrePropuesta(userNick, permisos, accionUsuario, comentario, propuestaActual, montoStr, tipoRetorno);

        assertEquals(3, result);
    }
    
    @Test
    public void testAccionesSobrePropuesta_casoNormalColaboradorConColabQueComenta() 
    {
        System.out.println("accionesSobrePropuesta_casoNormalColaboradorConColabQueComenta");
        
        Controller controllerR = new Controller();   //Debe ser un spy por que necesito que se ejecute la lógica en el controller, el mock falla y retorna 0
        Controller controllerClonado = spy(controllerR);
        
        String userNick = "Diego";
        int permisos = 2;
        String accionUsuario = "COMENTAR";
        String comentario = "Hola"; 
        String montoStr = "";
        String tipoRetorno = "";
        
        DTOPropuesta propuestaActual = new DTOPropuesta();
        propuestaActual.setTitulo("test");
               
        doReturn(true).when(controllerClonado).nuevoComentario(anyString(), anyString(), anyString()); 
        
        int result = controllerClonado.accionesSobrePropuesta(userNick, permisos, accionUsuario, comentario, propuestaActual, montoStr, tipoRetorno);

        assertEquals(1, result);

    }
    
    @Test
    public void testAccionesSobrePropuesta_casoNormalColaboradorSinColab() 
    {
        System.out.println("accionesSobrePropuesta_casoNormalColaboradorSinColab");

        String userNick = "Diego";
        int permisos = 3;
        String accionUsuario = "COLABORAR";
        String comentario = "";
        String montoStr = "15000";
        String tipoRetorno = "EntradaGratis";

        DTOPropuesta propuestaActual = mock(DTOPropuesta.class);
        when(propuestaActual.getTitulo()).thenReturn("El jijote de la manchea");

        DTOColaborador colaboradorTest = mock(DTOColaborador.class);
        when(colaboradorTest.getNickname()).thenReturn(userNick); 

        Controller controllerSpy = spy(controller);     //No me queda otra que usar un spy para omitir la función "getDTOColaborador"

        doReturn(colaboradorTest).when(controllerSpy).getDTOColaborador(userNick);      //Con spy, cambia la sintaxis de la devolución del mock

        doNothing().when(controllerSpy).altaColaboracion(any(DTOColaboracion.class));   //Lo mismo acá

        int result = controllerSpy.accionesSobrePropuesta(userNick, permisos, accionUsuario, comentario, propuestaActual, montoStr, tipoRetorno);

        assertEquals(4, result);
    }
    
    @Test
    public void testGetDTOPropuesta() 
    {
        System.out.println("getDTOPropuesta");
        
        Propuesta mockPropuesta = mock(Propuesta.class);
        Categoria mockCategoria = mock(Categoria.class);
        
        Registro_Estado mockRegistro = mock(Registro_Estado.class);
        
        DTOProponente mockProponente = mock(DTOProponente.class);
        
        DTORegistro_Estado dtoRegistroMock = mock(DTORegistro_Estado.class);
        
        when(mockPropuesta.getHistorialEstados()).thenReturn(List.of(mockRegistro));
        when(mockPropuesta.getAporte()).thenReturn(List.of());

        when(mockPropuesta.getCategoria()).thenReturn(mockCategoria);
        when(mockCategoria.Cat_a_DTO()).thenReturn(mock(DTOCategoria.class));
        
        Controller spyController = spy(controller);

        doReturn(dtoRegistroMock).when(spyController).getDTORegistroEstado(mockRegistro);

        DTOPropuesta result = spyController.getDTOPropuesta(mockPropuesta, mockProponente);

        assertEquals(mockProponente, result.getUsr());
    }

    @Test
    public void testNuevoComentario_errorPropuestaNoExiste()
    {
        
        System.out.println("NuevoComentario_errorPropuestaNoExiste");
        
        String comentario = "algo";
        String userNick = "Rick Ricker";
        String tituloPropuesta = "Pilsner Lock";    //Para que falle al buscarlo en el manejador
        boolean resultado = controller.nuevoComentario(comentario, userNick, tituloPropuesta);


        assertFalse(resultado);
    }

     @Test
    public void testColaboradoresAPropuesta() 
    {
        System.out.println("colaboradoresAPropuesta");
        
        String titulo = "Cine en el Botanico";
        List<String> result = controller.colaboradoresAPropuesta(titulo);
        assertNotNull(result);

    }
    
    @Test
    public void testObtenerPropuestaPorSubCategoria() 
    {
        System.out.println("ObtenerPropuestaPorSubCategoria");
        
        String subCat = "Ballet";
        Set<DTOPropuesta> result = controller.ObtenerPropuestaPorSubCategoria(subCat);
        assertNotNull(result);

    }
    
    @Test
    public void testBuscarPropuestas() 
    {
        System.out.println("BuscarPropuestas");
        
        String titulo = "Cine en el Botanico";
        List<DTOPropuesta> result = controller.BuscarPropuestas(titulo);
        assertNotNull(result);

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
    @Test
    public void testGetDTOColaboraciones() {
        System.out.println("getDTOColaboraciones");

        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {
            doNothing().when(mockEntityManager).close();

            Colaborador colab1 = mock(Colaborador.class);
            when(colab1.getNickname()).thenReturn("novick");

            Propuesta prop1 = mock(Propuesta.class);
            when(prop1.getTitulo()).thenReturn("Cine en el Botánico");

            Colaboracion colaboracion1 = mock(Colaboracion.class);
            when(colaboracion1.getId()).thenReturn(1L);
            when(colaboracion1.getTipoRetorno()).thenReturn(TipoRetorno.PorcentajeGanancia);
            when(colaboracion1.getMonto()).thenReturn(50000);
            when(colaboracion1.getColaborador()).thenReturn(colab1);
            when(colaboracion1.getPropuesta()).thenReturn(prop1);
            when(colaboracion1.getCreado()).thenReturn(LocalDate.of(2017, 5, 20));
            when(colaboracion1.getEstado()).thenReturn("Activo");
            
            Colaborador colab2 = mock(Colaborador.class);
            when(colab2.getNickname()).thenReturn("robinh");

            Propuesta prop2 = mock(Propuesta.class);
            when(prop2.getTitulo()).thenReturn("Romeo y Julieta");

            Colaboracion colaboracion2 = mock(Colaboracion.class);
            when(colaboracion2.getId()).thenReturn(2L);
            when(colaboracion2.getTipoRetorno()).thenReturn(TipoRetorno.EntradaGratis);
            when(colaboracion2.getMonto()).thenReturn(1000);
            when(colaboracion2.getColaborador()).thenReturn(colab2);
            when(colaboracion2.getPropuesta()).thenReturn(prop2);
            when(colaboracion2.getCreado()).thenReturn(LocalDate.of(2017, 6, 15));
            when(colaboracion2.getEstado()).thenReturn("Activo");

            List<Colaboracion> listaColaboraciones = Arrays.asList(colaboracion1, colaboracion2);
            
            TypedQuery<Colaboracion> mockQuery = mock(TypedQuery.class);
            when(mockQuery.getResultList()).thenReturn(listaColaboraciones);
            when(mockEntityManager.createQuery(eq("SELECT c FROM Colaboracion c"), eq(Colaboracion.class)))
                    .thenReturn(mockQuery);

            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            Set<DTOColaboracion> resultado = controller.getDTOColaboraciones();

            assertNotNull(resultado);
            assertEquals(2, resultado.size());

            assertTrue(resultado.stream().anyMatch(dto-> "novick".equals(dto.getColaborador()) && "Cine en el Botánico".equals(dto.getPropuesta())));
            assertTrue(resultado.stream().anyMatch(dto-> "robinh".equals(dto.getColaborador()) && "Romeo y Julieta".equals(dto.getPropuesta())));
        }
    }
    @Test
    public void testCancelarColaboracion() {
        System.out.println("CancelarColaboracion");

        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {
            doNothing().when(mockTransaction).begin();
            doNothing().when(mockTransaction).commit();
            doNothing().when(mockTransaction).rollback();
            doNothing().when(mockEntityManager).remove(any());
            doNothing().when(mockEntityManager).close();

            Colaboracion colaboracion = mock(Colaboracion.class);
            when(colaboracion.getId()).thenReturn(1L);
            when(colaboracion.getMonto()).thenReturn(50000);

            Propuesta propuesta = mock(Propuesta.class);
            when(propuesta.getTitulo()).thenReturn("Test Propuesta");
            when(propuesta.getMontoTotal()).thenReturn(100000);

            List<Colaboracion> aportes = new ArrayList<>();
            aportes.add(colaboracion);
            when(propuesta.getAporte()).thenReturn(aportes);

            when(colaboracion.getPropuesta()).thenReturn(propuesta);

            when(mockEntityManager.find(Colaboracion.class, 1L)).thenReturn(colaboracion);
            when(mockEntityManager.find(Propuesta.class, "Test Propuesta")).thenReturn(propuesta);

            when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);
            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            controller.CancelarColaboracion(1L);

            verify(mockTransaction, times(1)).begin();
            verify(mockTransaction, times(1)).commit();
            verify(mockEntityManager, times(1)).remove(colaboracion);
            verify(mockEntityManager, times(1)).close();
        }
    }
    @Test
    public void testColaboraciones() {
        System.out.println("colaboraciones");

        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {
            doNothing().when(mockEntityManager).close();

            Colaborador colaborador = mock(Colaborador.class);
            when(colaborador.getNickname()).thenReturn("novick");

            Propuesta prop1 = mock(Propuesta.class);
            when(prop1.getTitulo()).thenReturn("Cine en el Botánico");

            Propuesta prop2 = mock(Propuesta.class);
            when(prop2.getTitulo()).thenReturn("Religiosamente");

            Colaboracion colab1 = mock(Colaboracion.class);
            when(colab1.getId()).thenReturn(1L);
            when(colab1.getTipoRetorno()).thenReturn(TipoRetorno.PorcentajeGanancia);
            when(colab1.getMonto()).thenReturn(50000);
            when(colab1.getColaborador()).thenReturn(colaborador);
            when(colab1.getPropuesta()).thenReturn(prop1);
            when(colab1.getCreado()).thenReturn(LocalDate.of(2017, 5, 20));

            Colaboracion colab2 = mock(Colaboracion.class);
            when(colab2.getId()).thenReturn(2L);
            when(colab2.getTipoRetorno()).thenReturn(TipoRetorno.PorcentajeGanancia);
            when(colab2.getMonto()).thenReturn(50000);
            when(colab2.getColaborador()).thenReturn(colaborador);
            when(colab2.getPropuesta()).thenReturn(prop2);
            when(colab2.getCreado()).thenReturn(LocalDate.of(2017, 7, 10));

            List<Colaboracion> listaColaboraciones = Arrays.asList(colab1, colab2);
            when(colaborador.getColaboraciones()).thenReturn(listaColaboraciones);

            when(mockEntityManager.find(Colaborador.class, "novick")).thenReturn(colaborador);
            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            List<DTOColaboracion> resultado = controller.colaboraciones("novick");

            assertNotNull(resultado);
        }
    }
    @Test
    public void testColaboracionExisteTrue() {
        System.out.println("colaboracionExiste");

        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {
            
            TypedQuery<Long> mockQuery = mock(TypedQuery.class);
            when(mockQuery.setParameter(eq("colaborador"), anyString())).thenReturn(mockQuery);
            when(mockQuery.setParameter(eq("titulo"), anyString())).thenReturn(mockQuery);
            when(mockQuery.getSingleResult()).thenReturn(1L); // Existe (count > 0)

            when(mockEntityManager.createQuery(anyString(), eq(Long.class))).thenReturn(mockQuery);
            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            boolean resultado = controller.colaboracionExiste("novick", "Cine en el Botánico");

            assertTrue(resultado);
        }
    }

    @Test
    public void testColaboracionExisteFalse() {
        System.out.println("colaboracionExiste");

        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {

            TypedQuery<Long> mockQuery = mock(TypedQuery.class);
            when(mockQuery.setParameter(eq("colaborador"), anyString())).thenReturn(mockQuery);
            when(mockQuery.setParameter(eq("titulo"), anyString())).thenReturn(mockQuery);
            when(mockQuery.getSingleResult()).thenReturn(0L); // No existe (count = 0)

            when(mockEntityManager.createQuery(anyString(), eq(Long.class))).thenReturn(mockQuery);
            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            boolean resultado = controller.colaboracionExiste("novick", "Propuesta Inexistente");

            assertFalse(resultado);
        }
    }
    @Test
    public void testGetDTOAporte() {
        System.out.println("getDTOAporte");

        Colaborador mockColab = mock(Colaborador.class);
        when(mockColab.getNickname()).thenReturn("novick");

        Colaboracion mockColabEntity = mock(Colaboracion.class);
        when(mockColabEntity.getTipoRetorno()).thenReturn(TipoRetorno.PorcentajeGanancia);
        when(mockColabEntity.getMonto()).thenReturn(2500);
        when(mockColabEntity.getColaborador()).thenReturn(mockColab);
        when(mockColabEntity.getCreado()).thenReturn(LocalDate.of(2023, 10, 15));

        String tituloPropuesta = "Romeo y Julieta";

        DTOColaboracion dto = controller.getDTOAporte(mockColabEntity, tituloPropuesta);

        assertNotNull(dto);
        assertEquals(TipoRetorno.PorcentajeGanancia, dto.getTipoRetorno());
        assertEquals(2500, dto.getMonto());
        assertEquals("novick", dto.getColaborador());
        assertEquals("Romeo y Julieta", dto.getPropuesta());
        assertEquals(LocalDate.of(2023, 10, 15), dto.getCreado());

    }
    @Test
    public void testAltaColaboracion() {
        System.out.println("altaColaboracion");

        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {

            doNothing().when(mockTransaction).begin();
            doNothing().when(mockTransaction).commit();
            doNothing().when(mockTransaction).rollback();
            doNothing().when(mockEntityManager).persist(any(Colaboracion.class));
            doNothing().when(mockEntityManager).close();

            when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);
            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            DTOColaboracion dto = new DTOColaboracion(TipoRetorno.PorcentajeGanancia,2500,"testaltacol", "testPropAC",LocalDate.of(2023, 10, 15));
            controller.altaColaboracion(dto);
        }
    }
@Test
    public void testCargarColaboraciones() {
        System.out.println("cargarColaboraciones");

        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {

            doNothing().when(mockTransaction).begin();
            doNothing().when(mockTransaction).commit();
            doNothing().when(mockEntityManager).persist(any());
            doNothing().when(mockEntityManager).close();

            TypedQuery<Long> mockQuery = mock(TypedQuery.class);
            when(mockQuery.setParameter(anyString(), any())).thenReturn(mockQuery);
            when(mockQuery.getResultList()).thenReturn(new ArrayList<>());

            when(mockQuery.getSingleResult()).thenReturn(0L);

            when(mockEntityManager.createQuery(anyString(), any(Class.class))).thenReturn(mockQuery);

            when(mockEntityManager.find(eq(Colaborador.class), anyString())).thenAnswer(inv -> {
                Colaborador c = mock(Colaborador.class);
                String nick = inv.getArgument(1);
                when(c.getNickname()).thenReturn(nick);
                return c;
            });

            when(mockEntityManager.find(eq(Propuesta.class), anyString())).thenAnswer(inv -> {
                Propuesta p = mock(Propuesta.class);
                String titulo = inv.getArgument(1);
                when(p.getTitulo()).thenReturn(titulo);
                return p;
            });

            when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);
            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            controller.cargarColaboraciones();

            verify(mockEntityManager, atLeastOnce()).persist(any());
        }
    }
    
    //FIN COLABORACION
}
