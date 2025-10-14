/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package logica;

import java.util.Arrays;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import logica.Colaboracion.Colaboracion;
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
import static org.mockito.Mockito.*;
import persistencia.ManejadorUsuario;
import persistencia.ManejadorPropuesta;
import org.mockito.Mock;

/**
 *
 * @author fran
 */
public class ControllerTest {

    public ControllerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        Controller instance = new Controller();
        ManejadorUsuario mUsuario=ManejadorUsuario.getInstance();
        //los elimino por si quedaron anteriormente 
        mUsuario.EliminarUsuario("Pedro2025");
        mUsuario.EliminarUsuario("Maria2025");
        //creo a dos usuarios uno Proponente y otro colaborador para los test
        DTOColaborador usuC = new DTOColaborador();
        if(!instance.existe("Maria2025")){
            usuC.setNickname("Maria2025");
            usuC.setNombre("Maria");
            usuC.setApellido("Suárez");
            usuC.setEmail("Maria.suarez@example.com");
            usuC.setFecha(LocalDate.of(1992, 3, 14));
            usuC.setRutaImg("IMG/Maria2025/perfil.png");
            //aca agrego el Colaborador y inserto en la bd
            instance.altaUsuario(usuC);
        }
        DTOProponente usuP = new DTOProponente();
        if(!instance.existe("Pedro2025")){
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
            instance.altaUsuario(usuP);
        }
        
        //registroUsuario (el registro que usa la web)
        byte[] contenido = new byte[]{};
        instance.registroUsuario("FrancoP", "123", "Franco", "echaide", "algo@gmail.com", LocalDate.of(2002,05,17),contenido,"img.jpg",true,"25 metros","www.web.com","soy usuario Proponente");
        instance.registroUsuario("FrancoC", "123", "Franco", "echaide", "algo@gmail.com", LocalDate.of(2002,05,17),contenido,"",false,"","","");

//        //creo propuesta de prueba 
//        List<TipoRetorno> Retorno = new ArrayList<>();
//        Retorno.add(TipoRetorno.EntradaGratis);
//        //Estado est=Estado.INGRESADA;
//        instance.altaPropuesta("Prueba1","Esto es una prueba","","Piriapolis", LocalDate.of(2025,10,14), 100, 10000, LocalDate.of(2025,10,20), Retorno, "Musica", "Pedro2025", Estado.INGRESADA);
        
    }
    
    @AfterAll
    public static void tearDownClass() {
        Controller instance = new Controller();
        ManejadorUsuario mUsuario=ManejadorUsuario.getInstance();
        //los elimino
        mUsuario.EliminarUsuario("Pedro2025");
        mUsuario.EliminarUsuario("Maria2025");
        mUsuario.EliminarUsuario("FrancoP");
        mUsuario.EliminarUsuario("FrancoC");
        
          
         
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }
    //Test de USUSARIO
    /**
     * Test of altaUsuario method, of class Controller.
     */
    @Test
    public void testAltaProponente() {
        System.out.println("altaUsuario");
        
        Controller instance = new Controller();
        //ME TRAIGO AL USUARIO Creado y compruebo datos
        DTOProponente resultado = instance.getDTOProponente("Pedro2025");
        assertNotNull(resultado, "El proponente no fue agregado correctamente.");
        //assertEquals("Pedro2025", resultado.getNickname());
        assertEquals("Pedro", resultado.getNombre());
        assertEquals("Suárez", resultado.getApellido());
        assertEquals("pedro.suarez@gmail.com", resultado.getEmail());
        
        assertEquals(LocalDate.of(1992, 3, 14), resultado.getFecha());
        assertEquals("IMG/pedro2025/perfil.png", resultado.getRutaImg());
        assertEquals("Av. Italia 2456, Montevideo", resultado.getDireccion());
        assertEquals("Emprendedor uruguayo apasionado por la tecnología y la innovación social.", resultado.getBiografia());
        assertEquals("WWW.pedrosuarez.dev", resultado.getWebSite());
    }
    
    @Test
    public void testAltaColaborador() {
        System.out.println("altaUsuario");
        Controller instance = new Controller();
        
        //nesesito comprobar de que se inserto bien y que se guardaron bien los datos
        DTOColaborador resultado = instance.getDTOColaborador("Maria2025");
        assertNotNull(resultado, "El proponente no fue agregado correctamente.");
        assertEquals("Maria", resultado.getNombre());
        assertEquals("Suárez", resultado.getApellido());
        assertEquals("Maria.suarez@example.com", resultado.getEmail());
        assertEquals(LocalDate.of(1992, 3, 14), resultado.getFecha());
        assertEquals("IMG/Maria2025/perfil.png", resultado.getRutaImg());
       
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
        Controller instance = new Controller();
        //instance.registroUsuario("FrancoP", "123", "Franco", "echaide", "algo@gmail.com", LocalDate.of(2002,05,17),contenido,"",true,"25 metros","www.web.com","soy usuario Proponente");
        //nesesito comprobar de que se inserto bien y que se guardaron bien los datos
        DTOProponente resultado = instance.getDTOProponente("FrancoP");
        assertNotNull(resultado, "El proponente no fue agregado correctamente.");
        //assertEquals("Pedro2025", resultado.getNickname());
        assertEquals("Franco", resultado.getNombre());
        assertEquals("echaide", resultado.getApellido());
        assertEquals("algo@gmail.com", resultado.getEmail());
        
        assertEquals(LocalDate.of(2002, 05, 17), resultado.getFecha());
        assertEquals("IMG/FrancoP/img.jpg", resultado.getRutaImg()); //el registro sin img
        assertEquals("25 metros", resultado.getDireccion());
        assertEquals("soy usuario Proponente", resultado.getBiografia());
        assertEquals("www.web.com", resultado.getWebSite());
    }
    
     @Test
    public void testregistroUsuarioC(){
        //compruebo que el registroUsuario (el que usa al registrarse por web funcione bien)
        Controller instance = new Controller();
       
        //nesesito comprobar de que se inserto bien y que se guardaron bien los datos
        DTOColaborador resultado = instance.getDTOColaborador("FrancoC");
        assertNotNull(resultado, "El proponente no fue agregado correctamente.");
        assertEquals("Franco", resultado.getNombre());
        assertEquals("echaide", resultado.getApellido());
        assertEquals("algo@gmail.com", resultado.getEmail());
        
        assertEquals(LocalDate.of(2002, 05, 17), resultado.getFecha());
        assertEquals("", resultado.getRutaImg()); //el registro sin img
        
    }
   
    //test cuando no hay seguidores
    @Test
    public void testGetSeguidoresF() {
        System.out.println("getSeguidores");
        
        //quiero saber los seguidores de Maria2025
        String nick = "Maria2025";
        Controller instance = new Controller();
        List<DTOUsuario> expResult = new ArrayList<>();//se espera vacios
        List<DTOUsuario> result = instance.getSeguidores(nick);
        assertEquals(expResult, result);
        
    }
    //test cuando hay seguidores
    @Test
    public void testGetSeguidoresE() {
        System.out.println("getSeguidores");
        
        //quiero saber los seguidores de Maria2025
        
        String nick = "Pedro2025";
        Controller instance = new Controller();
        //agrego un seguidor a pedro 
        instance.seguir("Maria2025", "Pedro2025");//un colaborador
        instance.seguir("FrancoP", "Pedro2025");//un proponente
        List<DTOUsuario> expResult = new ArrayList<>();
        DTOUsuario aux =instance.getDTOColaborador("Maria2025");
        DTOProponente aux2=instance.getDTOProponente("FrancoP");
        expResult.add(aux);
        expResult.add(aux2);
        
        List<DTOUsuario> result = instance.getSeguidores(nick);
        
        instance.unFollowUser("Maria2025","Pedro2025"); //lo dejo de seguir para que se borre bien luego
        instance.unFollowUser("FrancoP","Pedro2025");
        //compruebo 
        assertNotNull(result);
        assertEquals(2, result.size());
        
    }


    /**
     * Test of sigueAUsuario method, of class Controller.
     */
    //cuando no sigue
    @Test
    public void testSigueAUsuarioF() {
        System.out.println("sigueAUsuario");
        Controller instance = new Controller();
        String seguidor = "Maria2025";
        String Seguido = "Pedro2025";
        
        
        boolean expResult = false;
        boolean result = instance.sigueAUsuario(seguidor, Seguido);
        assertEquals(expResult, result);
        
    }
     //cuando lo sigue 
    @Test
    public void testSigueAUsuarioE() {
        System.out.println("sigueAUsuario");
        Controller instance = new Controller();
        String seguidor = "Maria2025";
        String Seguido = "Pedro2025";
        instance.seguir(seguidor, Seguido);
        
        boolean expResult = true;
        boolean result = instance.sigueAUsuario(seguidor, Seguido);
        instance.unFollowUser(seguidor,Seguido);
        assertEquals(expResult, result);  
    }
    

    /**
     * Test of getFavoritas method, of class Controller.
     */
    
    //cuando no tiene prop favoritas
//    @Test
//    public void testGetFavoritasF() {
//        System.out.println("getFavoritas");
//        String nick = "";
//        Controller instance = new Controller();
//        List<DTOPropuesta> expResult = null;
//        List<DTOPropuesta> result = instance.getFavoritas(nick);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    //cuando no existe el usuario
//    @Test
//    public void testGetFavoritasFu() {
//        System.out.println("getFavoritas");
//        String nick = "";
//        Controller instance = new Controller();
//        List<DTOPropuesta> expResult = null;
//        List<DTOPropuesta> result = instance.getFavoritas(nick);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    //cuando tiene fav y existe el usuario
//    @Test
//    public void testGetFavoritasE() {
//        System.out.println("getFavoritas");
//        String nick = "";
//        Controller instance = new Controller();
//        List<DTOPropuesta> expResult = null;
//        List<DTOPropuesta> result = instance.getFavoritas(nick);
//        assertEquals(expResult, result);
//        
//    }

    //cuando la pas o nick no son correctos
    @Test
    public void testLoginF() {
        System.out.println("loginF");
        String nick = "FrancoP";
        String Pass = "1234";
        Controller instance = new Controller();
        boolean expResult = false;
        boolean result = instance.login(nick, Pass);
        assertEquals(expResult, result);
        
    }
    //login proponente
    @Test
    public void testLoginEP() {
        System.out.println("loginEP");
        String nick = "FrancoP";
        String Pass = "123";
        Controller instance = new Controller();
        boolean expResult = true;
        boolean result = instance.login(nick, Pass);
        assertEquals(expResult, result);
        
    }
    //login colaborador
    @Test
    public void testLoginEC() {
        System.out.println("loginEC");
        String nick = "FrancoC";
        String Pass = "123";
        Controller instance = new Controller();
        boolean expResult = true;
        boolean result = instance.login(nick, Pass);
        assertEquals(expResult, result);  
    }

    /**
     * Test of isProponente method, of class Controller.
     */
    //cuando es proponente 
    @Test
    public void testIsProponenteF() {
        System.out.println("isProponenteF");
        String nick = "FrancoC";
        Controller instance = new Controller();
        boolean expResult = false;
        boolean result = instance.isProponente(nick);
        assertEquals(expResult, result);
       
    }
    //cuando no es proponente
    @Test
    public void testIsProponenteE() {
        System.out.println("isProponenteE");
        String nick = "FrancoP";
        Controller instance = new Controller();
        boolean expResult = true;
        boolean result = instance.isProponente(nick);
        assertEquals(expResult, result);  
    }
    
    
    /**
     * Test of existeUsuario method, of class Controller.
     */
    //existe usuario con mismo nick y email
    @Test
    public void testExisteUsuarioeE() {
        System.out.println("existeUsuarioNickEmailUsados");
        String nick = "FrancoP";
        String email = "algo@gmail.com";
        Controller instance = new Controller();
        boolean expResult = true;
        boolean result = instance.existeUsuario(nick, email);
        assertEquals(expResult, result);
        
    }
    //existe usuario con mismo nick pero no email
    @Test
    public void testExisteUsuarioeEN() {
        System.out.println("existeUsuarioNickUsuado");
        String nick = "FrancoP";
        String email = "algo2@gmail.com";
        Controller instance = new Controller();
        boolean expResult = true;
        boolean result = instance.existeUsuario(nick, email);
        assertEquals(expResult, result);
        
    }
    //existe usuario con mismo email pero no nick
    @Test
    public void testExisteUsuarioeEE() {
        System.out.println("existeUsuarioEmailUsado");
        String nick = "FrancoP2";
        String email = "algo@gmail.com";
        Controller instance = new Controller();
        boolean expResult = true;
        boolean result = instance.existeUsuario(nick, email);
        assertEquals(expResult, result);
        
    }
    //No existe usuario 
    @Test
    public void testExisteUsuarioeF() {
        System.out.println("existeUsuarioNoExisteUsuario");
        String nick = "FrancoP2";
        String email = "algo2@gmail.com";
        Controller instance = new Controller();
        boolean expResult = false;
        boolean result = instance.existeUsuario(nick, email);
        assertEquals(expResult, result);
        
    }
    //email nulo 
    @Test
    public void testExisteUsuarioeEn() {
        System.out.println("existeUsuarioEmailNulo");
        String nick = "FrancoP2";
        String email = null;
        Controller instance = new Controller();
        boolean expResult = false;
        boolean result = instance.existeUsuario(nick, email);
        assertEquals(expResult, result);
        
    }
    

    /**
     * Test of emailUsado method, of class Controller.
     */
    //es usado el email
    @Test
    public void testEmailUsadoE() {
        System.out.println("emailYaUsado");
        String email = "algo@gmail.com";
        Controller instance = new Controller();
        boolean expResult = true;
        boolean result = instance.emailUsado(email);
        assertEquals(expResult, result);
        
    }
    
    @Test
    public void testEmailUsadoF() {
        System.out.println("emailNoUsado");
        String email = "algo2@gmail.com";
        Controller instance = new Controller();
        boolean expResult = false;
        boolean result = instance.emailUsado(email);
        assertEquals(expResult, result);
        
    }
    @Test
    public void testEmailUsadoV() {
        System.out.println("emailVacio");
        String email = null;
        Controller instance = new Controller();
        boolean expResult = false;
        boolean result = instance.emailUsado(email);
        assertEquals(expResult, result);
        
    }
    

    //existe usuario con ese nick
    @Test
    public void testExisteE() {
        System.out.println("existe");
        String nick = "FrancoP";
        Controller instance = new Controller();
        boolean expResult = true;
        boolean result = instance.existe(nick);
        assertEquals(expResult, result);
        
    }
    //NO existe usuario con ese nick
    @Test
    public void testExisteF() {
        System.out.println("existe");
        String nick = "FrancoP2";
        Controller instance = new Controller();
        boolean expResult = false;
        boolean result = instance.existe(nick);
        assertEquals(expResult, result);
        
    }
    
    
    /**
     * Test of Seguidos method, of class Controller.
     */
    //cuando no sigue a nadie devuelve lista vacia
    @Test
    public void testSeguidosF() {
        System.out.println("SeguidosF");
        String nick = "Maria2025";
        Controller instance = new Controller();
        List<DTOUsuario> expResult = new ArrayList<>();
        List<DTOUsuario> result = instance.Seguidos(nick);
        assertEquals(expResult, result);
        
    }
    //cuando sigue a otros devuelve lista con contenido
    @Test
    public void testSeguidosE() {
        System.out.println("SeguidosE");
        String nick = "Maria2025";
        Controller instance = new Controller();
        instance.seguir("Maria2025", "Pedro2025");//proponente
        instance.seguir("Maria2025", "FrancoC");
        
        //List<DTOUsuario> expResult = new ArrayList<>();
        List<DTOUsuario> result = instance.Seguidos(nick);
        instance.unFollowUser("Maria2025", "Pedro2025");
        instance.unFollowUser("Maria2025", "FrancoC");
        assertNotNull(result);
        assertEquals(2, result.size());
 
    }

    /**
     * Test of ListaUsuarios method, of class Controller.
     */
    @Test
    public void testListaUsuarios() {
        System.out.println("ListaUsuarios");
        Controller instance = new Controller();
       List<String> nicksEsperados = Arrays.asList(
            "Maria2025", 
            "Pedro2025", 
            "FrancoP", 
            "FrancoC"
        );

        // 3. Ejecución
        List<String> result = instance.ListaUsuarios();

        assertNotNull(result);

        assertTrue(result.containsAll(nicksEsperados));  
    }

    /**
     * Test of ListaProponentes method, of class Controller.
     */
    @Test
    public void testListaProponentes() {
        System.out.println("ListaProponentes");
        Controller instance = new Controller();
        List<String> expResult = null;
        List<String> result = instance.ListaProponentes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ListaColaborador method, of class Controller.
     */
    @Test
    public void testListaColaborador() {
        System.out.println("ListaColaborador");
        Controller instance = new Controller();
        List<String> expResult = null;
        List<String> result = instance.ListaColaborador();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    /**
     * Test of ListaSeguidosPorUsuario method, of class Controller.
     */
    @Test
    public void testListaSeguidosPorUsuario() {
        System.out.println("ListaSeguidosPorUsuario");
        String nick = "";
        Controller instance = new Controller();
        List<String> expResult = null;
        List<String> result = instance.ListaSeguidosPorUsuario(nick);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
 
    /**
     * Test of marcarComoFavorita method, of class Controller.
     */
    
    
    @Test
    public void testMarcarComoFavorita() {
        System.out.println("marcarComoFavorita");
        String nickname = "";
        String tituloPropuesta = "";
        Controller instance = new Controller();
        instance.marcarComoFavorita(nickname, tituloPropuesta);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of quitarFavorita method, of class Controller.
     */
    @Test
    public void testQuitarFavorita() {
        System.out.println("quitarFavorita");
        String nickname = "";
        String tituloPropuesta = "";
        Controller instance = new Controller();
        instance.quitarFavorita(nickname, tituloPropuesta);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of esFavorita method, of class Controller.
     */
    @Test
    public void testEsFavorita() {
        System.out.println("esFavorita");
        String nickname = "";
        String tituloPropuesta = "";
        Controller instance = new Controller();
        boolean expResult = false;
        boolean result = instance.esFavorita(nickname, tituloPropuesta);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    

   

    /**
     * Test of seguir method, of class Controller.
     */
    @Test
    public void testSeguir() {
        System.out.println("seguir");
        String nick1 = "";
        String nick2 = "";
        Controller instance = new Controller();
        boolean expResult = false;
        boolean result = instance.seguir(nick1, nick2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unFollowUser method, of class Controller.
     */
    @Test
    public void testUnFollowUser() {
        System.out.println("unFollowUser");
        String usuarioActual = "";
        String usuarioToUnfollow = "";
        Controller instance = new Controller();
        boolean expResult = false;
        boolean result = instance.unFollowUser(usuarioActual, usuarioToUnfollow);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    
     /**
     * Test of getDTOProponente method, of class Controller.
     */
    @Test
    public void testGetDTOProponente() {
        System.out.println("getDTOProponente");
        String nick = "";
        Controller instance = new Controller();
        DTOProponente expResult = null;
        DTOProponente result = instance.getDTOProponente(nick);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    
    /**
     * Test of getDTOColaborador method, of class Controller.
     */
    @Test
    public void testGetDTOColaborador() {
        System.out.println("getDTOColaborador");
        String nick = "";
        Controller instance = new Controller();
        DTOColaborador expResult = null;
        DTOColaborador result = instance.getDTOColaborador(nick);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
   // FIN TEST Usuarios
    
    /**
     * Test of getDTOAporte method, of class Controller.
     */
    @Test
    public void testGetDTOAporte() {
        System.out.println("getDTOAporte");
        Colaboracion r = null;
        String titulo = "";
        Controller instance = new Controller();
        DTOColaboracion expResult = null;
        DTOColaboracion result = instance.getDTOAporte(r, titulo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    
    //test PROPUESTAS
    /**
     * Test of getDTORegistroEstado method, of class Controller.
     */
    @Test
    public void testGetDTORegistroEstado() {
        System.out.println("getDTORegistroEstado");
        Registro_Estado r = null;
        Controller instance = new Controller();
        DTORegistro_Estado expResult = null;
        DTORegistro_Estado result = instance.getDTORegistroEstado(r);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDTOPropuesta method, of class Controller.
     */
    @Test
    public void testGetDTOPropuesta() {
        System.out.println("getDTOPropuesta");
        Propuesta p = null;
        DTOProponente prop = null;
        Controller instance = new Controller();
        DTOPropuesta expResult = null;
        DTOPropuesta result = instance.getDTOPropuesta(p, prop);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

   

    /**
     * Test of getPropuestasCreadasPorProponente method, of class Controller.
     */
    @Test
    public void testGetPropuestasCreadasPorProponente() {
        System.out.println("getPropuestasCreadasPorProponente");
        String nick = "";
        Controller instance = new Controller();
        Set<DTOPropuesta> expResult = null;
        Set<DTOPropuesta> result = instance.getPropuestasCreadasPorProponente(nick);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    

    /**
     * Test of altaPropuesta method, of class Controller.
     */
    @Test
    public void testAltaPropuesta() {
        System.out.println("altaPropuesta");
        String Titulo = "";
        String Descripcion = "";
        String Imagen = "";
        String Lugar = "";
        LocalDate Fecha = null;
        int Precio = 0;
        int MontoTotal = 0;
        LocalDate fechaPublicacio = null;
        List<TipoRetorno> Retorno = null;
        String cat = "";
        String usr = "";
        Estado est = null;
        Controller instance = new Controller();
        instance.altaPropuesta(Titulo, Descripcion, Imagen, Lugar, Fecha, Precio, MontoTotal, fechaPublicacio, Retorno, cat, usr, est);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerPropuestas method, of class Controller.
     */
    @Test
    public void testObtenerPropuestas() {
        System.out.println("obtenerPropuestas");
        String estado = "";
        Controller instance = new Controller();
        Set<DTOPropuesta> expResult = null;
        Set<DTOPropuesta> result = instance.obtenerPropuestas(estado);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerPropuestasExceptoINGRESADAS method, of class Controller.
     */
    @Test
    public void testObtenerPropuestasExceptoINGRESADAS() {
        System.out.println("obtenerPropuestasExceptoINGRESADAS");
        Controller instance = new Controller();
        Set<DTOPropuesta> expResult = null;
        Set<DTOPropuesta> result = instance.obtenerPropuestasExceptoINGRESADAS();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of accionSobrePropuesta method, of class Controller.
     */
    @Test
    public void testAccionSobrePropuesta() {
        System.out.println("accionSobrePropuesta");
        String nickUsuario = "";
        DTOPropuesta propuestaSel = null;
        Controller instance = new Controller();
        int expResult = 0;
        int result = instance.accionSobrePropuesta(nickUsuario, propuestaSel);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPropuestaDTO method, of class Controller.
     */
    @Test
    public void testGetPropuestaDTO() {
        System.out.println("getPropuestaDTO");
        String propuestaSel = "";
        Controller instance = new Controller();
        DTOPropuesta expResult = null;
        DTOPropuesta result = instance.getPropuestaDTO(propuestaSel);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of existeProp method, of class Controller.
     */
    @Test
    public void testExisteProp() {
        System.out.println("existeProp");
        String Titulo = "";
        Controller instance = new Controller();
        boolean expResult = false;
        boolean result = instance.existeProp(Titulo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of creadorPropuesta method, of class Controller.
     */
    @Test
    public void testCreadorPropuesta() {
        System.out.println("creadorPropuesta");
        String titulo = "";
        Controller instance = new Controller();
        String expResult = "";
        String result = instance.creadorPropuesta(titulo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of estadoPropuestas method, of class Controller.
     */
    @Test
    public void testEstadoPropuestas() {
        System.out.println("estadoPropuestas");
        String titulo = "";
        Controller instance = new Controller();
        String expResult = "";
        String result = instance.estadoPropuestas(titulo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    
     /**
     * Test of modificarPropuesta method, of class Controller.
     */
    @Test
    public void testModificarPropuesta() {
        System.out.println("modificarPropuesta");
        String titulo = "";
        String descripcion = "";
        String rutaImagen = "";
        String lugar = "";
        LocalDate fechaEvento = null;
        int precio = 0;
        int montoTotal = 0;
        List<TipoRetorno> retorno = null;
        String categoria = "";
        String usuarios = "";
        Estado estado = null;
        Controller instance = new Controller();
        instance.modificarPropuesta(titulo, descripcion, rutaImagen, lugar, fechaEvento, precio, montoTotal, retorno, categoria, usuarios, estado);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
      /**
     * Test of ListarPropuestas method, of class Controller.
     */
    @Test
    public void testListarPropuestas() {
        System.out.println("ListarPropuestas");
        String estado1 = "";
        String estado2 = "";
        Controller instance = new Controller();
        Set<DTOPropuesta> expResult = null;
        Set<DTOPropuesta> result = instance.ListarPropuestas(estado1, estado2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    /**
     * Test of extenderOCancelarPropuesta method, of class Controller.
     */
    @Test
    public void testExtenderOCancelarPropuesta() {
        System.out.println("extenderOCancelarPropuesta");
        String accionUsuario = "";
        String tituloPropuesta = "";
        Controller instance = new Controller();
        int expResult = 0;
        int result = instance.extenderOCancelarPropuesta(accionUsuario, tituloPropuesta);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
     /**
     * Test of accionesSobrePropuesta method, of class Controller.
     */
    @Test
    public void testAccionesSobrePropuesta() {
        System.out.println("accionesSobrePropuesta");
        String userNick = "";
        int permisos = 0;
        String accionUsuario = "";
        String comentario = "";
        DTOPropuesta propuestaActual = null;
        String montoStr = "";
        String tipoRetorno = "";
        Controller instance = new Controller();
        int expResult = 0;
        int result = instance.accionesSobrePropuesta(userNick, permisos, accionUsuario, comentario, propuestaActual, montoStr, tipoRetorno);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        System.out.println("permisosSobrePropuesta_USER_NULL");
        
        DTOPropuesta propTest = mock(DTOPropuesta.class);
        
        //Acá se programa lo que el dto devuelve:
        when(propTest.nickProponenteToString()).thenReturn("rodolfo");
        when(propTest.getTitulo()).thenReturn(null);
        
        
        String userNick = "";                                //Estos datos no los uso.
        String tipoUsuario = null;                            //Estos datos no los uso.
        
        
        Controller instance = new Controller();
        
        int expResult = 0;                                  //Se espera que sea 0 el resultado.
        
        int result = instance.permisosSobrePropuesta(userNick, tipoUsuario, propTest);
        
        assertEquals(expResult, result);    //Esto evaluará el resultado y lo esperado

    }
    
    @Test
    public void testPermisosSobrePropuesta_esProponenteYpermiso3() 
    {
        //Caso tipoUsuario es null.
        //UserNick igual a "visitante" 
        //Y el titulo de la propuesta es null.
        System.out.println("permisosSobrePropuesta_USER_NULL");
        
        DTOPropuesta propTest = mock(DTOPropuesta.class);
        
        //Acá se programa lo que el dto devuelve:
        when(propTest.getTitulo()).thenReturn("PropuestaAlgo");                               
        String userNick = "AFAEFD";                                //Estos datos no los uso.
        String tipoUsuario = "Proponente";                            //Estos datos no los uso.
        
        //Se simula la funcion para que no entre al segundo if y por ende tampoco al tercero:
        when(propTest.usuarioHaComentadoSN(anyString())).thenReturn(true);
        
        Controller instance = new Controller();
        
        int expResult = 0;                                  //Se espera que sea 0 el resultado.
        
        int result = instance.permisosSobrePropuesta(userNick, tipoUsuario, propTest);
        
        assertEquals(expResult, result);    //Esto evaluará el resultado y lo esperado

    }
    /**
     * Test of nuevoComentario method, of class Controller.
     */
    
    @Mock
    private ManejadorPropuesta mPropuesta;  //creo un mock del manejador para saltar la base de datos
    
    @Test
    public void testNuevoComentario() {
        System.out.println("nuevoComentario");
        String comentario = "";
        String userNick = "";
        String tituloPropuesta = "";
        Controller instance = new Controller();
        boolean expResult = false;
        boolean result = instance.nuevoComentario(comentario, userNick, tituloPropuesta);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testNuevoComentario__fallaCuandoUserNickEsNull() 
    {
        System.out.println("testNuevoComentario__fallaCuandoUserNickEsNull");
        String userNick = null;
        String comentario = "algo comentado";
        String tituloPropuesta = "Pinsel Shock";
        
        Controller instance = new Controller();
        boolean resultado = instance.nuevoComentario(comentario, userNick, tituloPropuesta);

        assertFalse(resultado); //Al ser null el nick, el if nunca debería entrar por lo tanto debería dar false
    }
    @Test
    public void testNuevoComentario_CasoUserNickVacio() 
    {
        System.out.println("testNuevoComentario_casoUserNickVacio");
        String comentario = "algo comentado";
        String userNick = "";   //Aca fuerzo la reacción del if
        String tituloPropuesta = "Pinsel Lock";
        Controller instance = new Controller();
        
        when(mPropuesta.nuevoComentario(comentario, userNick, tituloPropuesta)).thenReturn(true);   //En este caso devuelve true siempre que se consulta

        boolean resultado = instance.nuevoComentario(comentario, userNick, tituloPropuesta);

        assertTrue(resultado);
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
        Controller instance = new Controller();

        boolean result = instance.altaDeCategoria(null);

        assertFalse(result); //Devuelve el mensaje en formato del test
    }
    
        @Test
    void testAltaDeCategoria_caso_CategoriaPadreValida() 
    { 
        System.out.print("testAltaDeCategoria_caso_CategoriaPadreValida");
        
        Controller instance = new Controller();
        
        DTOCategoria catTest = new DTOCategoria();
        
        catTest.setNombreCategoria("Musica");
        
        catTest.setCatPadre("");                          //Para que sea reconocida como cat padre.

        boolean result = instance.altaDeCategoria(catTest);

        assertTrue(result); //Devuelve el mensaje en formato del test
    }

    /**
     * Test of getCategorias method, of class Controller.
     */
    @Test
    public void testGetCategorias() {
        System.out.println("getCategorias");
        Controller instance = new Controller();
        List<DTOCategoria> expResult = null;
        List<DTOCategoria> result = instance.getCategorias();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ListaCategoria method, of class Controller.
     */
    @Test
    public void testListaCategoria() {
        System.out.println("ListaCategoria");
        Controller instance = new Controller();
        List<String> expResult = null;
        List<String> result = instance.ListaCategoria();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    //FIN TEST CATEGORIA 
   

    /**
     * Test of altaColaboracion method, of class Controller.
     */
    //TEST Colaboracion
    
     /**
     * Test of colaboraciones method, of class Controller.
     */
    @Test
    public void testColaboraciones() {
        System.out.println("colaboraciones");
        String nick = "";
        Controller instance = new Controller();
        List<DTOColaboracion> expResult = null;
        List<DTOColaboracion> result = instance.colaboraciones(nick);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of colaboradoresAPropuesta method, of class Controller.
     */
    @Test
    public void testColaboradoresAPropuesta() {
        System.out.println("colaboradoresAPropuesta");
        String titulo = "";
        Controller instance = new Controller();
        List<String> expResult = null;
        List<String> result = instance.colaboradoresAPropuesta(titulo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    @Test
    public void testAltaColaboracion() {
        System.out.println("altaColaboracion");
        DTOColaboracion colaboracion = null;
        Controller instance = new Controller();
        instance.altaColaboracion(colaboracion);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ListarColaboradores method, of class Controller.
     */
    @Test
    public void testListarColaboradores() {
        System.out.println("ListarColaboradores");
        Controller instance = new Controller();
        Set<DTOColaborador> expResult = null;
        Set<DTOColaborador> result = instance.ListarColaboradores();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of CancelarColaboracion method, of class Controller.
     */
    @Test
    public void testCancelarColaboracion() {
        System.out.println("CancelarColaboracion");
        Long id = null;
        Controller instance = new Controller();
        instance.CancelarColaboracion(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }



    /**
     * Test of getMontoRecaudado method, of class Controller.
     */
    @Test
    public void testGetMontoRecaudado() {
        System.out.println("getMontoRecaudado");
        String titulo = "";
        Controller instance = new Controller();
        int expResult = 0;
        int result = instance.getMontoRecaudado(titulo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of colaboracionExiste method, of class Controller.
     */
    @Test
    public void testColaboracionExiste() {
        System.out.println("colaboracionExiste");
        String colaborador = "";
        String titulo = "";
        Controller instance = new Controller();
        boolean expResult = false;
        boolean result = instance.colaboracionExiste(colaborador, titulo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDTOColaboraciones method, of class Controller.
     */
    @Test
    public void testGetDTOColaboraciones() {
        System.out.println("getDTOColaboraciones");
        Controller instance = new Controller();
        Set<DTOColaboracion> expResult = null;
        Set<DTOColaboracion> result = instance.getDTOColaboraciones();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    
    //test de DATOS DE PREUBA [LOS DEJO PERO NI IDEA SI HAY QUE HACER ALGO SON FIJOS]
    /**
     * Test of cargarDatosPruebaProponente method, of class Controller.
     */
    @Test
    public void testCargarDatosPruebaProponente() {
        System.out.println("cargarDatosPruebaProponente");
        Controller instance = new Controller();
        instance.cargarDatosPruebaProponente();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cargarDatosPruebaColaborador method, of class Controller.
     */
    @Test
    public void testCargarDatosPruebaColaborador() {
        System.out.println("cargarDatosPruebaColaborador");
        Controller instance = new Controller();
        instance.cargarDatosPruebaColaborador();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cargarSeguidos method, of class Controller.
     */
    @Test
    public void testCargarSeguidos() {
        System.out.println("cargarSeguidos");
        Controller instance = new Controller();
        instance.cargarSeguidos();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cargarPropuesta method, of class Controller.
     */
    @Test
    public void testCargarPropuesta() {
        System.out.println("cargarPropuesta");
        Controller instance = new Controller();
        instance.cargarPropuesta();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cargarCategorias method, of class Controller.
     */
    @Test
    public void testCargarCategorias() {
        System.out.println("cargarCategorias");
        Controller instance = new Controller();
        instance.cargarCategorias();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cargarColaboraciones method, of class Controller.
     */
    @Test
    public void testCargarColaboraciones() {
        System.out.println("cargarColaboraciones");
        Controller instance = new Controller();
        instance.cargarColaboraciones();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
 
    
}
