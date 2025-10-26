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
import logica.DTO.DTOProponente;
import logica.DTO.DTOPropuesta;
import logica.DTO.DTOUsuario;
import logica.DTO.Estado;
import logica.DTO.TipoRetorno;
import logica.Propuesta.Propuesta;
import logica.Usuario.Proponente;
import logica.Usuario.Usuario;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
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
    }
    
    //INICIO USUARIOS
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
    public void testMarcarComoFavorita() {
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
    
    //FIN CATEGORIA
    

    @Test
    public void testAltaPropuesta_CasoProponenteNULL() 
    {
        System.out.println("altaPropuesta_CasoProponenteNULL");
                    
        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) 
        {
            
            doNothing().when(mockTransaction).begin();
            doNothing().when(mockTransaction).commit();
            doNothing().when(mockEntityManager).persist(any(Categoria.class));
            doNothing().when(mockEntityManager).close();
            
            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            String titulo = "TestingPropuesta";
            String descripcion = "TestDescrip";
            String imagen = "";
            String lugar = "TestLugar";
            LocalDate fecha = LocalDate.of(2025, 12, 10);
            int precio = 100;
            int montoTotal = 1000;
            LocalDate fechaPub = LocalDate.of(2025, 10, 15);
            List<TipoRetorno> retornos = new ArrayList();
            retornos.add(TipoRetorno.EntradaGratis);
            String categoriaNombre = "fiesta";
            String usuarioNombre = "Jose";
            Estado estado = Estado.PUBLICADA;
            
            controller.altaPropuesta(titulo, descripcion, imagen, lugar, fecha, precio, montoTotal, fechaPub, retornos, categoriaNombre, usuarioNombre, estado);

        
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
        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {

            // Mockear operaciones de JPA
            doNothing().when(mockEntityManager).close();

            // Crear Query mock
            jakarta.persistence.TypedQuery<Categoria> mockQuery = mock(jakarta.persistence.TypedQuery.class);

            // Crear categorías padre REALES para retornar
            Categoria teatro = new Categoria("Teatro", null);
            Categoria musica = new Categoria("Música", null);
            Categoria cine = new Categoria("Cine", null);

            List<Categoria> categoriasDB = new ArrayList<>();
            categoriasDB.add(teatro);
            categoriasDB.add(musica);
            categoriasDB.add(cine);

            // Configurar el query para que retorne las categorías
            when(mockEntityManager.createQuery(
                eq("select catImport from Categoria catImport where catImport.catPadre is NULL"), 
                eq(Categoria.class)))
                .thenReturn(mockQuery);
            when(mockQuery.getResultList()).thenReturn(categoriasDB);

            mockedStatic.when(PersistenciaManager::getEntityManager)
                       .thenReturn(mockEntityManager);

            // Ejecutar
            List<DTOCategoria> resultado = controller.getCategorias();

            // Verificaciones
            assertNotNull(resultado, "El resultado no debería ser null");
            assertEquals(3, resultado.size(), "Debería retornar 3 categorías");

            // Verificar que se llamaron los métodos correctos
            verify(mockEntityManager, times(1)).createQuery(
                eq("select catImport from Categoria catImport where catImport.catPadre is NULL"),
                eq(Categoria.class));
            verify(mockQuery, times(1)).getResultList();
            verify(mockEntityManager, times(1)).close();

            // Verificar el contenido de las categorías retornadas
            assertEquals("Teatro", resultado.get(0).getNombreCategoria());
            assertEquals("Música", resultado.get(1).getNombreCategoria());
            assertEquals("Cine", resultado.get(2).getNombreCategoria());

            // Verificar que todas son categorías padre (sin padre)
            for (DTOCategoria dto : resultado) {
                assertTrue(dto.getCatPadre() == null || dto.getCatPadre().isEmpty(), 
                    "Todas deberían ser categorías padre");
            }
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
        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {

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
    public void testGetCategorias_ExceptionEnQuery() {
        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) {

            doNothing().when(mockEntityManager).close();

            when(mockEntityManager.createQuery(anyString(), eq(Categoria.class))).thenThrow(new RuntimeException("Error en query"));

            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);


            assertThrows(RuntimeException.class, () -> {controller.getCategorias();});


        }
    }

    //INICIO PROPUESTA
    @Test
    public void testAltaPropuesta_CasoProponenteExiste() 
    {
        System.out.println("altaPropuesta_CasoProponenteExiste");
                    
        try (MockedStatic<PersistenciaManager> mockedStatic = mockStatic(PersistenciaManager.class)) 
        {
            
            doNothing().when(mockTransaction).begin();
            doNothing().when(mockTransaction).commit();
            doNothing().when(mockEntityManager).persist(any(Propuesta.class));
            doNothing().when(mockEntityManager).close();
            
            mockedStatic.when(PersistenciaManager::getEntityManager).thenReturn(mockEntityManager);

            String titulo = "TestingPropuesta";
            String descripcion = "TestDescrip";
            String imagen = "";
            String lugar = "TestLugar";
            LocalDate fecha = LocalDate.of(2025, 12, 10);
            int precio = 100;
            int montoTotal = 1000;
            LocalDate fechaPub = LocalDate.of(2025, 10, 15);
            List<TipoRetorno> retornos = new ArrayList();
            retornos.add(TipoRetorno.EntradaGratis);
            String categoriaNombre = "fiesta";
            String usuarioNombre = "diegop";
            Estado estado = Estado.PUBLICADA;

            controller.altaPropuesta(titulo, descripcion, imagen, lugar, fecha, precio, montoTotal, fechaPub, retornos, categoriaNombre, usuarioNombre, estado);

        
        }
    }
    //FIN PROPUESTA
    
    
    //INICIO COLABORACION
    //FIN COLABORACION
}