/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package logica;


import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import logica.DTO.DTOCategoria;
import logica.DTO.DTOColaboracion;

import logica.DTO.DTOColaborador;
import logica.DTO.DTOProponente;
import logica.DTO.DTOPropuesta;
import logica.DTO.DTORegistro_Estado;

import logica.DTO.DTOUsuario;
import logica.DTO.Estado;
import logica.DTO.TipoRetorno;
import logica.Propuesta.Propuesta;
import logica.Propuesta.Registro_Estado;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import logica.Usuario.Colaborador;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import persistencia.ManejadorUsuario;
import persistencia.ManejadorPropuesta;
import persistencia.ManejadorCategoria;
import org.mockito.Mock;

/**
 *
 * @author fran
 */
public class ControllerTest {

    public ControllerTest() {
    }
    
    @Mock
    private ManejadorUsuario mUsuarioMock;
        
    @Mock
    private ManejadorPropuesta mPropuesta;
    
    @Mock 
    private ManejadorCategoria mCategoria;
    
    @InjectMocks
    private Controller controller;
    
    @BeforeAll
    public static void setUpClass() {
 
    }
    
    @AfterAll
    public static void tearDownClass() {
    
    }
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @AfterEach
    public void tearDown() {
    }
    //Test de USUSARIO
   
    @Test
    public void testAltaProponente() {
        System.out.println("altaUsuario");
        // Arrange (Preparación): Creación con datos específicos.
        DTOProponente usuP = new DTOProponente();
        usuP.setNickname("Pedro2025");
        usuP.setNombre("Pedro");
        usuP.setApellido("Suárez");
        usuP.setEmail("pedro.suarez@gmail.com");
        usuP.setFecha(LocalDate.of(1992, 3, 14));
        usuP.setRutaImg("IMG/pedro2025/perfil.png");
        usuP.setDireccion("Av. Italia 2456, Montevideo");
        usuP.setBiografia("Emprendedor uruguayo apasionado por la tecnología y la innovación social.");
        usuP.setWebSite("WWW.pedrosuarez.dev");
        //aca agrego el Proponente y inserto en la bd
        //instance.altaUsuario(usuP);

        // Act (Acción)
        controller.altaUsuario(usuP);

        verify(mUsuarioMock, times(1)).addProponente(eq(usuP));  
    }
    
    @Test
    public void testAltaColaborador() {
        System.out.println("altaUsuario");
        DTOColaborador usuC = new DTOColaborador();
        usuC.setNickname("Maria2025");
        usuC.setNombre("Maria");
        usuC.setApellido("Suárez");
        usuC.setEmail("Maria.suarez@example.com");
        usuC.setFecha(LocalDate.of(1992, 3, 14));
        usuC.setRutaImg("IMG/Maria2025/perfil.png");
        //aca agrego el Colaborador y inserto en la bd
        //instance.altaUsuario(usuC);
        
        // Act (Acción)
        controller.altaUsuario(usuC);

        verify(mUsuarioMock, times(1)).addColaborador(eq(usuC));
       
    }

    //test cuando no elije img
    @Test
    public void testObtenerPathImgF() {
        System.out.println("obtenerPathImg");
        String nick = "Pedro"; 
        byte[] contenido = new byte[]{}; //vacio
        String nombreArchivo = ""; //vacio
        Controller instance = new Controller();
        String expResult = ""; //se espera que devuelva vacio
        String result = instance.obtenerPathImg(nick, contenido, nombreArchivo);
        assertEquals(expResult, result);
        
    }
   
     //test cuando se elije img
    @Test
    public void testObtenerPathImgE() {
        System.out.println("obtenerPathImg");
        //String RUTA_IMAGENES_BASE = "/home/fran/Escritorio/Lab2PA/IMG";
        Controller instance = new Controller();
        String nick = "Pedro";
        //byte[] contenido = null;
        byte[] contenido = new byte[]{10, 20, 30, 40, 50, 60, 70, 80};
        String nombreArchivo = "img.jpg";
       
        String expResult = "IMG/Pedro/img.jpg"; //esto deberia ser la ruta que guardo en db
       
        String result = instance.obtenerPathImg(nick, contenido, nombreArchivo);
        //compruebo que la ruta que guartdo en bd sea la correcta
        assertEquals(expResult, result);    
    }
    
    @Test
    public void testregistroUsuarioP(){
        //compruebo que el registroUsuario (el que usa al registrarse por web funcione bien)
        byte[] contenido = new byte[]{};
        controller.registroUsuario("FrancoP", "123", "Franco", "echaide", "algo@gmail.com", LocalDate.of(2002,05,17),contenido,"",true,"25 metros","www.web.com","soy usuario Proponente");
 
        verify(mUsuarioMock, times(1)).addProponente(any(DTOProponente.class)); 
    
        verify(mUsuarioMock, never()).addColaborador(any());

    }
    
     @Test
    public void testregistroUsuarioC(){
        //compruebo que el registroUsuario (el que usa al registrarse por web funcione bien)
        byte[] contenido = new byte[]{};
        controller.registroUsuario("FrancoC", "123", "Franco", "echaide", "algo@gmail.com", LocalDate.of(2002,05,17),contenido,"",false,"","","");
        verify(mUsuarioMock, times(1)).addColaborador(any(DTOColaborador.class)); 

        verify(mUsuarioMock, never()).addProponente(any()); 
    }
   
    //test cuando no hay seguidores
    @Test
    public void testGetSeguidoresF() {
        System.out.println("getSeguidores");
       String nick = "Maria2025";
       
        List<DTOUsuario> expResult = new ArrayList<>(); // lo que deberia devolver
        
        when(mUsuarioMock.obtenerSeguidores(eq(nick))).thenReturn(expResult);
        
        List<DTOUsuario> result = controller.getSeguidores(nick);
        
        //verificamos que se llama al metodo de mUsuario 
        verify(mUsuarioMock, times(1)).obtenerSeguidores(eq(nick));
        assertNotNull(result);
        assertEquals(0, result.size());
    }
    //test cuando hay seguidores
    @Test
    public void testGetSeguidoresE() {
        System.out.println("getSeguidores");
        
     
        String nick = "Maria2025";
        
        DTOUsuario seguidor1 = new DTOUsuario(); // Asumo setters para cargar DTOs
        seguidor1.setNickname("Juan");
        DTOUsuario seguidor2 = new DTOUsuario();
        seguidor2.setNickname("AnaR");
    
        List<DTOUsuario> devolver = Arrays.asList(seguidor1, seguidor2);
        
        
        when(mUsuarioMock.obtenerSeguidores(eq(nick))).thenReturn(devolver);
        
        List<DTOUsuario> result = controller.getSeguidores(nick);
        
        //verificamos que se llama al metodo de mUsuario 
        verify(mUsuarioMock, times(1)).obtenerSeguidores(eq(nick));
        
        assertEquals(2, result.size());
      
        assertEquals(devolver, result);
        
    }


    /**
     * Test of sigueAUsuario method, of class Controller.
     */
    //cuando no sigue
    @Test
    public void testSigueAUsuarioF() {
        System.out.println("sigueAUsuario");
        String seguidor = "Maria2025";
        String Seguido = "Pedro2025";
        when(mUsuarioMock.sigue(eq(seguidor),eq(Seguido))).thenReturn(false);
        boolean expResult = false;
        boolean result = controller.sigueAUsuario(seguidor, Seguido);
        verify(mUsuarioMock, times(1)).sigue(eq(seguidor),eq(Seguido));
        assertEquals(expResult, result);
        
    }
     //cuando lo sigue 
    @Test
    public void testSigueAUsuarioE() {
        System.out.println("sigueAUsuario");
        String seguidor = "Maria2025";
        String Seguido = "Pedro2025";
        when(mUsuarioMock.sigue(eq(seguidor),eq(Seguido))).thenReturn(true);
        boolean expResult = true;
        boolean result = controller.sigueAUsuario(seguidor, Seguido);
        verify(mUsuarioMock, times(1)).sigue(eq(seguidor),eq(Seguido));
        assertEquals(expResult, result); 
    }
//    
//
    /**
     * Test of getFavoritas method, of class Controller.
     */
    
    //cuando no tiene prop favoritas
    @Test
    public void testGetFavoritasF() {
        String nickname = "Pedro2025";
        List<DTOPropuesta> listaVacia = new ArrayList<>();

        
        when(mUsuarioMock.getFavoritas(eq(nickname))).thenReturn(listaVacia);

        List<DTOPropuesta> result = controller.getFavoritas(nickname);

        verify(mUsuarioMock, times(1)).getFavoritas(eq(nickname));

        assertNotNull(result);
        assertTrue(result.isEmpty());
        assertEquals(listaVacia, result); 
    }
    
    @Test
    public void testLogin() {
        System.out.println("loginF");
        String nick = "FrancoP";
        String Pass = "1234";
        when(mUsuarioMock.verificarCredenciales(eq(nick),eq(Pass))).thenReturn(true);
        boolean expResult = true;
        boolean result = controller.login(nick, Pass);
        verify(mUsuarioMock, times(1)).verificarCredenciales(eq(nick),eq(Pass));
        assertEquals(expResult, result);  
    }
    
    //cuando no es proponente 
    @Test
    public void testIsProponenteF() {
        System.out.println("isProponenteF");
        String nick = "FrancoP";
        when(mUsuarioMock.isProponente(eq(nick))).thenReturn(true);
        boolean expResult = true;
        boolean result = controller.isProponente(nick);
        //verifico que haga la llamada 
        verify(mUsuarioMock, times(1)).isProponente(eq(nick));
        assertEquals(expResult, result);  
    }
   
    //existe usuario usando el email
    @Test
    public void testExisteUsuarioeE() {
        System.out.println("existeUsuario");
        String nick = "FrancoP";
        String email = "algo@gmail.com";

        when(mUsuarioMock.existe(eq(nick))).thenReturn(false);
        
        when(mUsuarioMock.emailUsado(eq(email))).thenReturn(true);
        
        boolean expResult = true;
        boolean result = controller.existeUsuario(nick, email);
        //en este caso se llaman ambos metodos
        verify(mUsuarioMock, times(1)).existe(eq(nick));
        verify(mUsuarioMock, times(1)).emailUsado(eq(email));
        assertEquals(expResult, result);
    }
    //existe usuario con mismo nick pero no email
    @Test
    public void testExisteUsuarioeEN() {
       System.out.println("existeUsuario");
        String nick = "FrancoP";
        String email = "algo@gmail.com";

        when(mUsuarioMock.existe(eq(nick))).thenReturn(true);
        
        when(mUsuarioMock.emailUsado(eq(email))).thenReturn(false);
        
        boolean expResult = true;
        boolean result = controller.existeUsuario(nick, email);
        //solo entra al primero 
        verify(mUsuarioMock, times(1)).existe(eq(nick));
        verify(mUsuarioMock, times(0)).emailUsado(eq(email));
        assertEquals(expResult, result);

    }
    //no existe
    @Test
    public void testExisteUsuarioeEE() {
       System.out.println("existeUsuario");
        String nick = "FrancoP";
        String email = "algo@gmail.com";

        when(mUsuarioMock.existe(eq(nick))).thenReturn(false);
        
        when(mUsuarioMock.emailUsado(eq(email))).thenReturn(false);
        
        boolean expResult = false;
        boolean result = controller.existeUsuario(nick, email);
        //ambos se llaman
        verify(mUsuarioMock, times(1)).existe(eq(nick));
        verify(mUsuarioMock, times(1)).emailUsado(eq(email));
        assertEquals(expResult, result);
        
    }

    //es usado el email
    @Test
    public void testEmailUsado() {
        System.out.println("emailYaUsado");
        String email = "algo@gmail.com";
        when(mUsuarioMock.emailUsado(eq(email))).thenReturn(false);
        boolean expResult = false;
        boolean result = controller.emailUsado(email);
        //verificacion
        verify(mUsuarioMock, times(1)).emailUsado(eq(email));
        assertEquals(expResult, result);     
    }
    //existe usuario con ese nick
    @Test
    public void testExiste() {
        System.out.println("existe");
        String nick = "FrancoP";
        when(mUsuarioMock.existe(eq(nick))).thenReturn(false);
        
        boolean expResult = false;
        boolean result = controller.existe(nick);
        //verificacion
        verify(mUsuarioMock, times(1)).existe(eq(nick));
        
        assertEquals(expResult, result);
    }

   
    @Test
    public void testSeguidos() {
        System.out.println("SeguidosF");
        String nick = "Maria2025";
        List<DTOUsuario> listaVacia = new ArrayList<>();

        when(mUsuarioMock.getSeguidos(eq(nick))).thenReturn(listaVacia);
 
        List<DTOUsuario> result = controller.Seguidos(nick);
        
        verify(mUsuarioMock, times(1)).getSeguidos(eq(nick));

        assertNotNull(result);
        assertTrue(result.isEmpty());
        assertEquals(listaVacia, result);
    }
    @Test
    public void testListaUsuariosE(){
        System.out.println("ListaUsuarios");
        //simulacion  
        Map<String,DTOUsuario> listaUsuarios=new HashMap<>();
        //debo cargar con datos un colaborador y un proponente
        
        //Colaborador
        LocalDate fechaNacimiento = LocalDate.of(1990, 10, 15);
        DTOColaborador c = new DTOColaborador("CarlosC","Carlos","Gimenez","carlos.g@corp.com",fechaNacimiento,"/img/CarlosC/img.jpg");
        listaUsuarios.put("CarlosC", c);
        //Proponente
        DTOProponente p = new DTOProponente("25 metros","soy un proponente","www.algo.com","CarlosP","Carlos","Gimenez","carlos.g@corp.com",fechaNacimiento,"/img/CarlosP/img.jpg");
        listaUsuarios.put("CarlosP", p);
        //esto devuelve un map de Usuario tanto Proponente como Colaboradores
        when(mUsuarioMock.getUsuarios()).thenReturn(listaUsuarios);
        
        //lo que deberia devolver el metodo de controller
        List<String> nicksEsperados = Arrays.asList( 
            "CarlosP",
            "CarlosC"
        );

        // devuelve una lista de string de usuarios
        List<String> result = controller.ListaUsuarios();
        
        //verifico que se haga la llamada al metodo del manejador 
        verify(mUsuarioMock, times(1)).getUsuarios();
        //que el resultado tenga mismo tamano
        assertEquals(nicksEsperados.size(), result.size());
        //que contenga al proponente 
        assertTrue(result.containsAll(nicksEsperados));
        
    }
    @Test
    public void testListaUsuariosF(){
        System.out.println("ListaUsuarios");
        //simulacion  
        Map<String,DTOUsuario> listaUsuarios=new HashMap<>();
        //debo cargar con datos un colaborador y un proponente
       
        //esto devuelve un map de Usuario tanto Proponente como Colaboradores
        when(mUsuarioMock.getUsuarios()).thenReturn(listaUsuarios);
        
       
       //lo que deberia devolver el metodo de controller
        List<String> nicksEsperados = new ArrayList<>();

        // devuelve una lista de string de usuarios
        List<String> result = controller.ListaUsuarios();
        
       //verifico que se haga la llamada al metodo del manejador 
        verify(mUsuarioMock, times(1)).getUsuarios();
        assertNotNull(result);//debe devolver lista vacia no null
        assertTrue(result.isEmpty());//que sea vacia
        assertEquals(nicksEsperados.size(), result.size());
        
    }
    @Test
    public void testListaProponentesE() {
        System.out.println("ListaProponentes");
        
        //simulacion  
        Map<String,DTOUsuario> listaUsuarios=new HashMap<>();
        //debo cargar con datos un colaborador y un proponente
        
        //Colaborador
        LocalDate fechaNacimiento = LocalDate.of(1990, 10, 15);
        DTOColaborador c = new DTOColaborador("CarlosC","Carlos","Gimenez","carlos.g@corp.com",fechaNacimiento,"/img/CarlosC/img.jpg");
        listaUsuarios.put("CarlosC", c);
        //Proponente
        DTOProponente p = new DTOProponente("25 metros","soy un proponente","www.algo.com","CarlosP","Carlos","Gimenez","carlos.g@corp.com",fechaNacimiento,"/img/CarlosP/img.jpg");
        listaUsuarios.put("CarlosP", p);
        //esto devuelve un map de Usuario tanto Proponente como Colaboradores
        when(mUsuarioMock.getUsuarios()).thenReturn(listaUsuarios);
        
        //lo que deberia devolver el metodo de controller
        List<String> nicksEsperados = Arrays.asList( 
            "CarlosP"
        );

        // devuelve una lista de string de proponente
        List<String> result = controller.ListaProponentes();
        
        //verifico que se haga la llamada al metodo del manejador 
        verify(mUsuarioMock, times(1)).getUsuarios();
        //que el resultado tenga mismo tamano
        assertEquals(nicksEsperados.size(), result.size());
        //que contenga al proponente 
        assertTrue(result.containsAll(nicksEsperados));
        
    }
    
     @Test
    public void testListaProponentesF() {
        System.out.println("ListaProponentes");
        
        //simulacion  vacio
        Map<String,DTOUsuario> listaUsuarios=new HashMap<>();

        //esto devuelve un map de Usuario tanto Proponente como Colaboradores
        when(mUsuarioMock.getUsuarios()).thenReturn(listaUsuarios);
        
        //lo que deberia devolver el metodo de controller
        List<String> nicksEsperados = new ArrayList<>();

        // devuelve una lista vacia
        List<String> result = controller.ListaProponentes();
        
        //verifico que se haga la llamada al metodo del manejador 
        verify(mUsuarioMock, times(1)).getUsuarios();
        assertNotNull(result);//debe devolver lista vacia no null
        assertTrue(result.isEmpty());//que sea vacia
        assertEquals(nicksEsperados.size(), result.size());
        
    }

    @Test
    public void testListaColaborador() {
        System.out.println("ListaColaborador");
          
        //simulacion  
        Map<String,DTOUsuario> listaUsuarios=new HashMap<>();
        //debo cargar con datos un colaborador y un proponente
        
        //Colaborador
        LocalDate fechaNacimiento = LocalDate.of(1990, 10, 15);
        DTOColaborador c = new DTOColaborador("CarlosC","Carlos","Gimenez","carlos.g@corp.com",fechaNacimiento,"/img/CarlosC/img.jpg");
        listaUsuarios.put("CarlosC", c);
        //Proponente
        DTOProponente p = new DTOProponente("25 metros","soy un proponente","www.algo.com","CarlosP","Carlos","Gimenez","carlos.g@corp.com",fechaNacimiento,"/img/CarlosP/img.jpg");
        listaUsuarios.put("CarlosP", p);
        //esto devuelve un map de Usuario tanto Proponente como Colaboradores
        when(mUsuarioMock.getUsuarios()).thenReturn(listaUsuarios);
        
        //lo que deberia devolver el metodo de controller
        List<String> nicksEsperados = Arrays.asList( 
            "CarlosC"
        );

        // devuelve una lista de string de proponente
        List<String> result = controller.ListaColaborador();
        
        //verifico que se haga la llamada al metodo del manejador 
        verify(mUsuarioMock, times(1)).getUsuarios();
        //que el resultado tenga mismo tamano
        assertEquals(nicksEsperados.size(), result.size());
        //que contenga al proponente 
        assertTrue(result.containsAll(nicksEsperados));
    }
  
//    /**
//     * Test of ListaSeguidosPorUsuario method, of class Controller.
//     */
//    @Test
//    public void testListaSeguidosPorUsuario() {
//        System.out.println("ListaSeguidosPorUsuario");
//        String nick = "";
//        Controller instance = new Controller();
//        List<String> expResult = null;
//        List<String> result = instance.ListaSeguidosPorUsuario(nick);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
// 
//    /**
//     * Test of marcarComoFavorita method, of class Controller.
//     */
//    
//    
//    @Test
//    public void testMarcarComoFavorita() {
//        System.out.println("marcarComoFavorita");
//        String nickname = "";
//        String tituloPropuesta = "";
//        Controller instance = new Controller();
//        instance.marcarComoFavorita(nickname, tituloPropuesta);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of quitarFavorita method, of class Controller.
//     */
//    @Test
//    public void testQuitarFavorita() {
//        System.out.println("quitarFavorita");
//        String nickname = "";
//        String tituloPropuesta = "";
//        Controller instance = new Controller();
//        instance.quitarFavorita(nickname, tituloPropuesta);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of esFavorita method, of class Controller.
//     */
//    @Test
//    public void testEsFavorita() {
//        System.out.println("esFavorita");
//        String nickname = "";
//        String tituloPropuesta = "";
//        Controller instance = new Controller();
//        boolean expResult = false;
//        boolean result = instance.esFavorita(nickname, tituloPropuesta);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    
//
//   
//
//    /**
//     * Test of seguir method, of class Controller.
//     */
//    @Test
//    public void testSeguir() {
//        System.out.println("seguir");
//        String nick1 = "";
//        String nick2 = "";
//        Controller instance = new Controller();
//        boolean expResult = false;
//        boolean result = instance.seguir(nick1, nick2);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of unFollowUser method, of class Controller.
//     */
//    @Test
//    public void testUnFollowUser() {
//        System.out.println("unFollowUser");
//        String usuarioActual = "";
//        String usuarioToUnfollow = "";
//        Controller instance = new Controller();
//        boolean expResult = false;
//        boolean result = instance.unFollowUser(usuarioActual, usuarioToUnfollow);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
//    
//     /**
//     * Test of getDTOProponente method, of class Controller.
//     */
//    @Test
//    public void testGetDTOProponente() {
//        System.out.println("getDTOProponente");
//        String nick = "";
//        Controller instance = new Controller();
//        DTOProponente expResult = null;
//        DTOProponente result = instance.getDTOProponente(nick);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
//    
//    /**
//     * Test of getDTOColaborador method, of class Controller.
//     */
//    @Test
//    public void testGetDTOColaborador() {
//        System.out.println("getDTOColaborador");
//        String nick = "";
//        Controller instance = new Controller();
//        DTOColaborador expResult = null;
//        DTOColaborador result = instance.getDTOColaborador(nick);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
//   // FIN TEST Usuarios
//    
    
//    @Test
//    public void testGetDTOAporte() {
//        System.out.println("getDTOAporte");
//        Colaboracion r = null;
//        String titulo = "";
//        Controller instance = new Controller();
//        DTOColaboracion expResult = null;
//        DTOColaboracion result = instance.getDTOAporte(r, titulo);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
//    
//    //test PROPUESTAS
//    /**
//     * Test of getDTORegistroEstado method, of class Controller.
//     */
//    @Test
//    public void testGetDTORegistroEstado() {
//        System.out.println("getDTORegistroEstado");
//        Registro_Estado r = null;
//        Controller instance = new Controller();
//        DTORegistro_Estado expResult = null;
//        DTORegistro_Estado result = instance.getDTORegistroEstado(r);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getDTOPropuesta method, of class Controller.
//     */
//    @Test
//    public void testGetDTOPropuesta() {
//        System.out.println("getDTOPropuesta");
//        Propuesta p = null;
//        DTOProponente prop = null;
//        Controller instance = new Controller();
//        DTOPropuesta expResult = null;
//        DTOPropuesta result = instance.getDTOPropuesta(p, prop);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//   
//
//    /**
//     * Test of getPropuestasCreadasPorProponente method, of class Controller.
//     */
//    @Test
//    public void testGetPropuestasCreadasPorProponente() {
//        System.out.println("getPropuestasCreadasPorProponente");
//        String nick = "";
//        Controller instance = new Controller();
//        Set<DTOPropuesta> expResult = null;
//        Set<DTOPropuesta> result = instance.getPropuestasCreadasPorProponente(nick);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    
//
//    /**
//     * Test of altaPropuesta method, of class Controller.
//     */
//    @Test
//    public void testAltaPropuesta() {
//        System.out.println("altaPropuesta");
//        String Titulo = "";
//        String Descripcion = "";
//        String Imagen = "";
//        String Lugar = "";
//        LocalDate Fecha = null;
//        int Precio = 0;
//        int MontoTotal = 0;
//        LocalDate fechaPublicacio = null;
//        List<TipoRetorno> Retorno = null;
//        String cat = "";
//        String usr = "";
//        Estado est = null;
//        Controller instance = new Controller();
//        instance.altaPropuesta(Titulo, Descripcion, Imagen, Lugar, Fecha, Precio, MontoTotal, fechaPublicacio, Retorno, cat, usr, est);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of obtenerPropuestas method, of class Controller.
//     */
//    @Test
//    public void testObtenerPropuestas() {
//        System.out.println("obtenerPropuestas");
//        String estado = "";
//        Controller instance = new Controller();
//        Set<DTOPropuesta> expResult = null;
//        Set<DTOPropuesta> result = instance.obtenerPropuestas(estado);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of obtenerPropuestasExceptoINGRESADAS method, of class Controller.
//     */
//    @Test
//    public void testObtenerPropuestasExceptoINGRESADAS() {
//        System.out.println("obtenerPropuestasExceptoINGRESADAS");
//        Controller instance = new Controller();
//        Set<DTOPropuesta> expResult = null;
//        Set<DTOPropuesta> result = instance.obtenerPropuestasExceptoINGRESADAS();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of accionSobrePropuesta method, of class Controller.
//     */
//    @Test
//    public void testAccionSobrePropuesta() {
//        System.out.println("accionSobrePropuesta");
//        String nickUsuario = "";
//        DTOPropuesta propuestaSel = null;
//        Controller instance = new Controller();
//        int expResult = 0;
//        int result = instance.accionSobrePropuesta(nickUsuario, propuestaSel);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getPropuestaDTO method, of class Controller.
//     */
//    @Test
//    public void testGetPropuestaDTO() {
//        System.out.println("getPropuestaDTO");
//        String propuestaSel = "";
//        Controller instance = new Controller();
//        DTOPropuesta expResult = null;
//        DTOPropuesta result = instance.getPropuestaDTO(propuestaSel);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of existeProp method, of class Controller.
//     */
//    @Test
//    public void testExisteProp() {
//        System.out.println("existeProp");
//        String Titulo = "";
//        Controller instance = new Controller();
//        boolean expResult = false;
//        boolean result = instance.existeProp(Titulo);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of creadorPropuesta method, of class Controller.
//     */
//    @Test
//    public void testCreadorPropuesta() {
//        System.out.println("creadorPropuesta");
//        String titulo = "";
//        Controller instance = new Controller();
//        String expResult = "";
//        String result = instance.creadorPropuesta(titulo);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of estadoPropuestas method, of class Controller.
//     */
//    @Test
//    public void testEstadoPropuestas() {
//        System.out.println("estadoPropuestas");
//        String titulo = "";
//        Controller instance = new Controller();
//        String expResult = "";
//        String result = instance.estadoPropuestas(titulo);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
//    
//     /**
//     * Test of modificarPropuesta method, of class Controller.
//     */
//    @Test
//    public void testModificarPropuesta() {
//        System.out.println("modificarPropuesta");
//        String titulo = "";
//        String descripcion = "";
//        String rutaImagen = "";
//        String lugar = "";
//        LocalDate fechaEvento = null;
//        int precio = 0;
//        int montoTotal = 0;
//        List<TipoRetorno> retorno = null;
//        String categoria = "";
//        String usuarios = "";
//        Estado estado = null;
//        Controller instance = new Controller();
//        instance.modificarPropuesta(titulo, descripcion, rutaImagen, lugar, fechaEvento, precio, montoTotal, retorno, categoria, usuarios, estado);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
//      /**
//     * Test of ListarPropuestas method, of class Controller.
//     */
//    @Test
//    public void testListarPropuestas() {
//        System.out.println("ListarPropuestas");
//        String estado1 = "";
//        String estado2 = "";
//        Controller instance = new Controller();
//        Set<DTOPropuesta> expResult = null;
//        Set<DTOPropuesta> result = instance.ListarPropuestas(estado1, estado2);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
//    /**
//     * Test of extenderOCancelarPropuesta method, of class Controller.
//     */
//    @Test
//    public void testExtenderOCancelarPropuesta() {
//        System.out.println("extenderOCancelarPropuesta");
//        String accionUsuario = "";
//        String tituloPropuesta = "";
//        Controller instance = new Controller();
//        int expResult = 0;
//        int result = instance.extenderOCancelarPropuesta(accionUsuario, tituloPropuesta);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
//     /**
//     * Test of accionesSobrePropuesta method, of class Controller.
//     */
//    @Test
//    public void testAccionesSobrePropuesta() {
//        System.out.println("accionesSobrePropuesta");
//        String userNick = "";
//        int permisos = 0;
//        String accionUsuario = "";
//        String comentario = "";
//        DTOPropuesta propuestaActual = null;
//        String montoStr = "";
//        String tipoRetorno = "";
//        Controller instance = new Controller();
//        int expResult = 0;
//        int result = instance.accionesSobrePropuesta(userNick, permisos, accionUsuario, comentario, propuestaActual, montoStr, tipoRetorno);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
    /**
     * Test of extenderOCancelarPropuesta method, of class Controller.
     */
    
    @Test
    public void testExtenderOCancelarPropuesta_casoCancelar() 
    {
        System.out.println("testExtenderOCancelarPropuesta_casoCancelar");
        
        String accionUsuario = "CANCELAR";
        String tituloPropuesta = "propTest";

        doNothing().when(mPropuesta).cancelarPropuestaSeleccionada(tituloPropuesta);    //Me salto esta función

        int resultado = controller.extenderOCancelarPropuesta(accionUsuario, tituloPropuesta);

        assertEquals(2, resultado);
    }
    
    @Test
    public void testExtenderOCancelarPropuesta_casoExtender() 
    {
        System.out.println("testExtenderOCancelarPropuesta_casoExtender");

        when(mPropuesta.extenderFinanciacion("propTest")).thenReturn(true);

        int resultado = controller.extenderOCancelarPropuesta("EXTENDER", "propTest");

        assertEquals(3, resultado);
    }
    
    @Test
    public void testExtenderOCancelarPropuesta_casoAnomalia() 
    {
        System.out.println("testExtenderOCancelarPropuesta_casoAnomalia");
        
        String accionUsuario = "????";
        String tituloPropuesta = "propTest";

        doNothing().when(mPropuesta).cancelarPropuestaSeleccionada(tituloPropuesta);    //Me salto esta función

        int resultado = controller.extenderOCancelarPropuesta(accionUsuario, tituloPropuesta);

        assertEquals(0, resultado);
    }
    
    @Test
    public void testExtenderOCancelarPropuesta_casoNULL() 
    {
        System.out.println("testExtenderOCancelarPropuesta_casoNULL");
        
        String accionUsuario = null;
        String tituloPropuesta = null;

        int resultado = controller.extenderOCancelarPropuesta(accionUsuario, tituloPropuesta);

        assertEquals(0, resultado);
    }
    
     /**
     * Test of accionesSobrePropuesta method, of class Controller.
     */
    @Test
    public void testAccionesSobrePropuesta_casoProponenteExtiende() 
    {
        System.out.println("accionesSobrePropuesta_casoProponenteExtiende");
        
        String userNick = "Juan";
        int permisos = 1;
        String accionUsuario = "EXTENDER";
        String comentario = ""; 
        String montoStr = "";
        String tipoRetorno = null;
        
        DTOPropuesta propuestaActual = mock(DTOPropuesta.class);
        when(propuestaActual.nickProponenteToString()).thenReturn(userNick);    //Para que entre el if del for
        when(propuestaActual.getTitulo()).thenReturn("prop");
        
        Set<DTOPropuesta> propuestasTest = new HashSet<>();                     //Armo un set simple de prueba con ese solo elemento
        propuestasTest.add(propuestaActual);
        
        when(controller.getPropuestasCreadasPorProponente(userNick)).thenReturn(propuestasTest);    //Cambia uso de función por retorno del set creado arriba.
        when(mPropuesta.extenderFinanciacion("prop")).thenReturn(true);                         //Que retorne 3.
        
        int result = controller.accionesSobrePropuesta(userNick, permisos, accionUsuario, comentario, propuestaActual, montoStr, tipoRetorno);

        assertEquals(3, result);
    }
    @Test
    public void testAccionesSobrePropuesta_casoProponenteCancela() 
    {
        System.out.println("accionesSobrePropuesta_casoProponenteCancela");
        
        String userNick = "Juan";
        int permisos = 1;
        String accionUsuario = "CANCELAR";  //No hace efecto pero lo dejo igual.
        String comentario = ""; 
        String montoStr = "";
        String tipoRetorno = null;
        
        DTOPropuesta propuestaActual = mock(DTOPropuesta.class);
        
        when(propuestaActual.nickProponenteToString()).thenReturn(userNick);    //Para que entre el if del for
        when(propuestaActual.getTitulo()).thenReturn("prop");
        
        Set<DTOPropuesta> propuestasTest = new HashSet<>();                     //Armo un set simple de prueba con ese solo elemento
        propuestasTest.add(propuestaActual);
        
        when(controller.getPropuestasCreadasPorProponente(userNick)).thenReturn(propuestasTest);    //Cambia uso de función por retorno del set creado arriba.
        doNothing().when(mPropuesta).cancelarPropuestaSeleccionada("prop");   //Que retorne 2.
        
        int result = controller.accionesSobrePropuesta(userNick, permisos, accionUsuario, comentario, propuestaActual, montoStr, tipoRetorno);

        assertEquals(2, result);
    }
    
    @Test
    public void testAccionesSobrePropuesta_casoProponenteSinAccion() 
    {
        System.out.println("accionesSobrePropuesta_casoProponenteSinAccion");
        
        String userNick = "Joseph";
        int permisos = 1;
        String accionUsuario = "NADA";  //No hace efecto pero lo dejo igual.
        String comentario = ""; 
        String montoStr = "";
        String tipoRetorno = null;
        
        DTOPropuesta propuestaActual = mock(DTOPropuesta.class);
        
        when(propuestaActual.nickProponenteToString()).thenReturn(userNick);    //Para que entre el if del for
        when(propuestaActual.getTitulo()).thenReturn("prop");
        
        Set<DTOPropuesta> propuestasTest = new HashSet<>();                     //Armo un set simple de prueba con ese solo elemento
        propuestasTest.add(propuestaActual);
        
        when(controller.getPropuestasCreadasPorProponente(userNick)).thenReturn(propuestasTest);    //Cambia uso de función por retorno del set creado arriba.
        
        int result = controller.accionesSobrePropuesta(userNick, permisos, accionUsuario, comentario, propuestaActual, montoStr, tipoRetorno);

        assertEquals(0, result);
    }
    
    @Test
    public void testAccionesSobrePropuesta_casoAnromalSinPermisosPeroAccede() 
    {
        System.out.println("testAccionesSobrePropuesta_casoAnromalSinPermisosPeroAccede");
        
        String userNick = "DESCONOCIDO";
        int permisos = 0;
        String accionUsuario = "???";
        String comentario = "???"; 
        String montoStr = "1000000000";
        String tipoRetorno = null;
                
        int result = controller.accionesSobrePropuesta(userNick, permisos, accionUsuario, comentario, null, montoStr, tipoRetorno);

        assertEquals(0, result);

    }
    
    @Test
    public void testAccionesSobrePropuesta_casoNormalColaboradorConColabQueComenta() 
    {
        System.out.println("accionesSobrePropuesta_casoNormalColaboradorConColabQueComenta");
        
        String userNick = "Diego";
        int permisos = 2;
        String accionUsuario = "COMENTAR";
        String comentario = "Hola"; 
        String montoStr = "";
        String tipoRetorno = "";
        
        DTOPropuesta propuestaActual = mock(DTOPropuesta.class);
        when(propuestaActual.getTitulo()).thenReturn("test");
        when(mPropuesta.nuevoComentario(anyString(), anyString(), anyString())).thenReturn(true);        
        int result = controller.accionesSobrePropuesta(userNick, permisos, accionUsuario, comentario, propuestaActual, montoStr, tipoRetorno);

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

        Controller controllerSpy = spy(controller);

        doReturn(colaboradorTest).when(controllerSpy).getDTOColaborador(userNick);

        doNothing().when(controllerSpy).altaColaboracion(any(DTOColaboracion.class));

        int result = controllerSpy.accionesSobrePropuesta(userNick, permisos, accionUsuario, comentario, propuestaActual, montoStr, tipoRetorno);

        assertEquals(4, result);
    }
    
      /**
     * Test of permisosSobrePropuesta method, of class Controller.
     */
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
        
        //Acá se programa lo que el dto devuelve:
        when(propTest.getTitulo()).thenReturn("El quijote endemoniado");                               

        //Se simula la funcion para que no entre al segundo if y por ende tampoco al tercero:
        when(propTest.usuarioHaComentadoSN(userNick)).thenReturn(true);
        //Que la funcion retorne 3, ya que no ha comentado
        when(controller.accionSobrePropuesta(userNick,propTest)).thenReturn(3);
        
        int expResult = 0;                                  //Se espera que sea 0 el resultado.
        
        int result = controller.permisosSobrePropuesta(userNick, tipoUsuario, propTest);
        
        assertEquals(expResult, result);    //Esto evaluará el resultado y lo esperado

    }
    /**
     * Test of nuevoComentario method, of class Controller.
     */
    
    @Test
    public void testNuevoComentario_casoTodoNormal() 
    {
        System.out.println("testNuevoComentario_casoTodoNormal");
        
        String comentario = "algo";
        String userNick = "Rick Ricker";
        String tituloPropuesta = "Pilsner Lock";
        
        when(mPropuesta.nuevoComentario(comentario, userNick, tituloPropuesta)).thenReturn(true);   //Enmascaro la función esta con un true directo así entra en el if
        
        boolean resultado = controller.nuevoComentario(comentario, userNick, tituloPropuesta);
        
        assertTrue(resultado); 
    }

    @Test
    public void testNuevoComentario__fallaCuandoUserNickEsNull() 
    {
        System.out.println("testNuevoComentario__fallaCuandoUserNickEsNull");
        
        String userNick = null;
        String comentario = "algo comentado";
        String tituloPropuesta = "Pinsel Shock";
        
        boolean resultado = controller.nuevoComentario(comentario, userNick, tituloPropuesta);

        assertFalse(resultado); //Al ser null el nick, el if nunca debería entrar por lo tanto debería dar false
    }
    
    @Test
    public void testNuevoComentario_CasoUserNickVacio() 
    {
        System.out.println("testNuevoComentario_casoUserNickVacio");
        
        String comentario = "algo comentado";
        String userNick = "";   //Aca fuerzo la reacción del if
        String tituloPropuesta = "Pinsel Lock";
        
        when(mPropuesta.nuevoComentario(comentario, userNick, tituloPropuesta)).thenReturn(true);   //En este caso devuelve true siempre que se consulta

        boolean resultado = controller.nuevoComentario(comentario, userNick, tituloPropuesta);

        assertFalse(resultado);
    }

    //FIN TEST Propouestas
    /**
     * Test of altaDeCategoria method, of class Controller.
     */
    
    //Test CATEGORIA
    @Test
    void testAltaDeCategoria_caso_InputNull() 
    {
        System.out.print("testAltaDeCategoria_caso_InputNull");
        
        //No necesito hacer mocks ya que al ser null no entra nunca a la base de datos.

        boolean result = controller.altaDeCategoria(null);

        assertFalse(result); //Devuelve el mensaje en formato del test
    }
    
    @Test
    void testAltaDeCategoria_caso_CategoriaPadreValida() 
    { 
        System.out.print("testAltaDeCategoria_caso_CategoriaPadreValida");
        
        DTOCategoria catTest = new DTOCategoria();
        
        catTest.setNombreCategoria("Musica");
        
        catTest.setCatPadre("");                          //Para que sea reconocida como cat padre.
        when(mCategoria.addCategoria(catTest)).thenReturn(true);
        
        boolean result = controller.altaDeCategoria(catTest);

        assertTrue(result); //Devuelve el mensaje en formato del test
    }

//    /**
//     * Test of getCategorias method, of class Controller.
//     */
//    @Test
//    public void testGetCategorias() {
//        System.out.println("getCategorias");
//        Controller instance = new Controller();
//        List<DTOCategoria> expResult = null;
//        List<DTOCategoria> result = instance.getCategorias();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of ListaCategoria method, of class Controller.
//     */
//    @Test
//    public void testListaCategoria() {
//        System.out.println("ListaCategoria");
//        Controller instance = new Controller();
//        List<String> expResult = null;
//        List<String> result = instance.ListaCategoria();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    //FIN TEST CATEGORIA 
//   
//
//    /**
//     * Test of altaColaboracion method, of class Controller.
//     */
//    //TEST Colaboracion
//    
//     /**
//     * Test of colaboraciones method, of class Controller.
//     */
//    @Test
//    public void testColaboraciones() {
//        System.out.println("colaboraciones");
//        String nick = "";
//        Controller instance = new Controller();
//        List<DTOColaboracion> expResult = null;
//        List<DTOColaboracion> result = instance.colaboraciones(nick);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of colaboradoresAPropuesta method, of class Controller.
//     */
//    @Test
//    public void testColaboradoresAPropuesta() {
//        System.out.println("colaboradoresAPropuesta");
//        String titulo = "";
//        Controller instance = new Controller();
//        List<String> expResult = null;
//        List<String> result = instance.colaboradoresAPropuesta(titulo);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    @Test
//    public void testAltaColaboracion() {
//        System.out.println("altaColaboracion");
//        DTOColaboracion colaboracion = null;
//        Controller instance = new Controller();
//        instance.altaColaboracion(colaboracion);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of ListarColaboradores method, of class Controller.
//     */
//    @Test
//    public void testListarColaboradores() {
//        System.out.println("ListarColaboradores");
//        Controller instance = new Controller();
//        Set<DTOColaborador> expResult = null;
//        Set<DTOColaborador> result = instance.ListarColaboradores();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of CancelarColaboracion method, of class Controller.
//     */
//    @Test
//    public void testCancelarColaboracion() {
//        System.out.println("CancelarColaboracion");
//        Long id = null;
//        Controller instance = new Controller();
//        instance.CancelarColaboracion(id);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//
//
//    /**
//     * Test of getMontoRecaudado method, of class Controller.
//     */
//    @Test
//    public void testGetMontoRecaudado() {
//        System.out.println("getMontoRecaudado");
//        String titulo = "";
//        Controller instance = new Controller();
//        int expResult = 0;
//        int result = instance.getMontoRecaudado(titulo);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of colaboracionExiste method, of class Controller.
//     */
//    @Test
//    public void testColaboracionExiste() {
//        System.out.println("colaboracionExiste");
//        String colaborador = "";
//        String titulo = "";
//        Controller instance = new Controller();
//        boolean expResult = false;
//        boolean result = instance.colaboracionExiste(colaborador, titulo);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getDTOColaboraciones method, of class Controller.
//     */
//    @Test
//    public void testGetDTOColaboraciones() {
//        System.out.println("getDTOColaboraciones");
//        Controller instance = new Controller();
//        Set<DTOColaboracion> expResult = null;
//        Set<DTOColaboracion> result = instance.getDTOColaboraciones();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
//    
//    //test de DATOS DE PREUBA [LOS DEJO PERO NI IDEA SI HAY QUE HACER ALGO SON FIJOS]
//    /**
//     * Test of cargarDatosPruebaProponente method, of class Controller.
//     */
//    @Test
//    public void testCargarDatosPruebaProponente() {
//        System.out.println("cargarDatosPruebaProponente");
//        Controller instance = new Controller();
//        instance.cargarDatosPruebaProponente();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of cargarDatosPruebaColaborador method, of class Controller.
//     */
//    @Test
//    public void testCargarDatosPruebaColaborador() {
//        System.out.println("cargarDatosPruebaColaborador");
//        Controller instance = new Controller();
//        instance.cargarDatosPruebaColaborador();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of cargarSeguidos method, of class Controller.
//     */
//    @Test
//    public void testCargarSeguidos() {
//        System.out.println("cargarSeguidos");
//        Controller instance = new Controller();
//        instance.cargarSeguidos();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of cargarPropuesta method, of class Controller.
//     */
//    @Test
//    public void testCargarPropuesta() {
//        System.out.println("cargarPropuesta");
//        Controller instance = new Controller();
//        instance.cargarPropuesta();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of cargarCategorias method, of class Controller.
//     */
//    @Test
//    public void testCargarCategorias() {
//        System.out.println("cargarCategorias");
//        Controller instance = new Controller();
//        instance.cargarCategorias();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of cargarColaboraciones method, of class Controller.
//     */
//    @Test
//    public void testCargarColaboraciones() {
//        System.out.println("cargarColaboraciones");
//        Controller instance = new Controller();
//        instance.cargarColaboraciones();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
// 
    
}
