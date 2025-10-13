/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package logica;

import java.time.LocalDate;
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

/**
 *
 * @author fran
 */
public class ControllerTest {
    
    public ControllerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
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
    public void testAltaUsuario() {
        System.out.println("altaUsuario");
        DTOUsuario usu = null;
        Controller instance = new Controller();
        instance.altaUsuario(usu);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerPathImg method, of class Controller.
     */
    @Test
    public void testObtenerPathImg() {
        System.out.println("obtenerPathImg");
        String nick = "";
        byte[] contenido = null;
        String nombreArchivo = "";
        Controller instance = new Controller();
        String expResult = "";
        String result = instance.obtenerPathImg(nick, contenido, nombreArchivo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSeguidores method, of class Controller.
     */
    @Test
    public void testGetSeguidores() {
        System.out.println("getSeguidores");
        String nick = "";
        Controller instance = new Controller();
        List<DTOUsuario> expResult = null;
        List<DTOUsuario> result = instance.getSeguidores(nick);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registroUsuario method, of class Controller.
     */
    @Test
    public void testRegistroUsuario() {
        System.out.println("registroUsuario");
        String nickname = "";
        String pass = "";
        String nombre = "";
        String apellido = "";
        String email = "";
        LocalDate fecha = null;
        byte[] contenido = null;
        String nombreArchivo = "";
        boolean isProponente = false;
        String direccion = "";
        String web = "";
        String Biografia = "";
        Controller instance = new Controller();
        instance.registroUsuario(nickname, pass, nombre, apellido, email, fecha, contenido, nombreArchivo, isProponente, direccion, web, Biografia);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ListaDTOUsuarios method, of class Controller.
     */
    @Test
    public void testListaDTOUsuarios() {
        System.out.println("ListaDTOUsuarios");
        Controller instance = new Controller();
        List<DTOUsuario> expResult = null;
        List<DTOUsuario> result = instance.ListaDTOUsuarios();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sigueAUsuario method, of class Controller.
     */
    @Test
    public void testSigueAUsuario() {
        System.out.println("sigueAUsuario");
        String seguidor = "";
        String Seguido = "";
        Controller instance = new Controller();
        boolean expResult = false;
        boolean result = instance.sigueAUsuario(seguidor, Seguido);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getImg method, of class Controller.
     */
    @Test
    public void testGetImg() {
        System.out.println("getImg");
        String ruta = "";
        Controller instance = new Controller();
        byte[] expResult = null;
        byte[] result = instance.getImg(ruta);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFavoritas method, of class Controller.
     */
    @Test
    public void testGetFavoritas() {
        System.out.println("getFavoritas");
        String nick = "";
        Controller instance = new Controller();
        List<DTOPropuesta> expResult = null;
        List<DTOPropuesta> result = instance.getFavoritas(nick);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of login method, of class Controller.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        String nick = "";
        String Pass = "";
        Controller instance = new Controller();
        boolean expResult = false;
        boolean result = instance.login(nick, Pass);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isProponente method, of class Controller.
     */
    @Test
    public void testIsProponente() {
        System.out.println("isProponente");
        String nick = "";
        Controller instance = new Controller();
        boolean expResult = false;
        boolean result = instance.isProponente(nick);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of existeUsuario method, of class Controller.
     */
    @Test
    public void testExisteUsuario() {
        System.out.println("existeUsuario");
        String nick = "";
        String email = "";
        Controller instance = new Controller();
        boolean expResult = false;
        boolean result = instance.existeUsuario(nick, email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of emailUsado method, of class Controller.
     */
    @Test
    public void testEmailUsado() {
        System.out.println("emailUsado");
        String email = "";
        Controller instance = new Controller();
        boolean expResult = false;
        boolean result = instance.emailUsado(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of existe method, of class Controller.
     */
    @Test
    public void testExiste() {
        System.out.println("existe");
        String nick = "";
        Controller instance = new Controller();
        boolean expResult = false;
        boolean result = instance.existe(nick);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Seguidos method, of class Controller.
     */
    @Test
    public void testSeguidos() {
        System.out.println("Seguidos");
        String nick = "";
        Controller instance = new Controller();
        List<DTOUsuario> expResult = null;
        List<DTOUsuario> result = instance.Seguidos(nick);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ListaUsuarios method, of class Controller.
     */
    @Test
    public void testListaUsuarios() {
        System.out.println("ListaUsuarios");
        Controller instance = new Controller();
        List<String> expResult = null;
        List<String> result = instance.ListaUsuarios();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
    
    //test Propuestas
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
    
    //FIN TEST Usuarios
    
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

    //FIN TEST Propouestas
    /**
     * Test of altaDeCategoria method, of class Controller.
     */
    
    //Test CATEGORIA
    @Test
    public void testAltaDeCategoria() {
        System.out.println("altaDeCategoria");
        DTOCategoria categoriaIngresada = null;
        Controller instance = new Controller();
        boolean expResult = false;
        boolean result = instance.altaDeCategoria(categoriaIngresada);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
