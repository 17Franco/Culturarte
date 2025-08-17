package logica.Categoria;
import logica.DTO.DTOCategoria;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class ManejadorCategoria 
{
    private Map<String, Categoria> AlmacenCategorias; //El nombre de la categorpia al ser unico servirá para usar como id.
    private static ManejadorCategoria instancia = null;
    
    private ManejadorCategoria() 
    {
        AlmacenCategorias = new HashMap<>();
        iniciarBDTempCategoria();
    }
    
    public static ManejadorCategoria getInstance() 
    {
        if (instancia == null)
            instancia = new ManejadorCategoria();
        return instancia;
    }
    
    private void iniciarBDTempCategoria() 
    {
        Set<DTOCategoria> subcategoriasArteVisual = new HashSet<>();
        subcategoriasArteVisual.add(new DTOCategoria("Pintura"));
        subcategoriasArteVisual.add(new DTOCategoria("Escultura"));
        subcategoriasArteVisual.add(new DTOCategoria("Fotografía"));

        Set<DTOCategoria> subcategoriasMusica = new HashSet<>();
        subcategoriasMusica.add(new DTOCategoria("Rock"));
        subcategoriasMusica.add(new DTOCategoria("Jazz"));
        subcategoriasMusica.add(new DTOCategoria("Clásica"));

        Set<DTOCategoria> subcategoriasTeatro = new HashSet<>();
        subcategoriasTeatro.add(new DTOCategoria("Teatro Contemporáneo"));
        subcategoriasTeatro.add(new DTOCategoria("Teatro Clásico"));
        subcategoriasTeatro.add(new DTOCategoria("Teatro Experimental"));

        Categoria arteVisual = new Categoria("Arte Visual", subcategoriasArteVisual);
        Categoria musica = new Categoria("Música", subcategoriasMusica);
        Categoria teatro = new Categoria("Teatro", subcategoriasTeatro);

        AlmacenCategorias.put(arteVisual.getNombreCategoria(), arteVisual);
        AlmacenCategorias.put(musica.getNombreCategoria(), musica);
        AlmacenCategorias.put(teatro.getNombreCategoria(), teatro);

    }
}
