package persistencia;
import logica.DTO.DTOCategoria;
import java.util.HashMap;
import java.util.Map;
import logica.Categoria.Categoria;
import logica.Categoria.Categoria;

public class ManejadorCategoria 
{
    private Map<String, Categoria> AlmacenCategorias; //El nombre de la categorpia al ser unico servirá para usar como id.
    private static ManejadorCategoria instancia = null;
    
    private ManejadorCategoria() 
    {
        AlmacenCategorias = new HashMap<String, Categoria>();
        
        
        Categoria arte = new Categoria("Arte");
        Categoria tecnologia = new Categoria("Tecnología");
        Categoria musica = new Categoria("Música");

        DTOCategoria dtoSub1 = new DTOCategoria("Pintura", "Arte", null);
        DTOCategoria dtoSub2 = new DTOCategoria("Escultura", "Arte", null);
        arte.addSubcategoria(dtoSub1);
        arte.addSubcategoria(dtoSub2);

        DTOCategoria dtoSub3 = new DTOCategoria("Software", "Tecnología", null);
        DTOCategoria dtoSub4 = new DTOCategoria("Hardware", "Tecnología", null);
        tecnologia.addSubcategoria(dtoSub3);
        tecnologia.addSubcategoria(dtoSub4);

        AlmacenCategorias.put(arte.getNombreCategoria(), arte);
        AlmacenCategorias.put(tecnologia.getNombreCategoria(), tecnologia);
        AlmacenCategorias.put(musica.getNombreCategoria(), musica);
    }
    
    public static ManejadorCategoria getInstance() 
    {
        if (instancia == null)
            instancia = new ManejadorCategoria();
        return instancia;
    }
    
    public Map<String, Categoria> getCategorias()
    {
        return AlmacenCategorias;
    }
    
    public DTOCategoria obtenerCategoriaPorNombre(String nombreCategoria)
    {
        Categoria temp = AlmacenCategorias.get(nombreCategoria);
        
        DTOCategoria almacen = new DTOCategoria(temp.getNombreCategoria(),"",temp.getSubcategorias());
        
        return almacen;
    }
    
    public boolean addCategoria(DTOCategoria categoriaIngresada)
    { 
        if(categoriaIngresada.getCatPadre() == null)  //Si no agregó como subcategoria
        {       //la condición está negada.
            Categoria cat1 = new Categoria(categoriaIngresada.getNombreCategoria());
            AlmacenCategorias.put(cat1.getNombreCategoria(),cat1);
            return true;    //agregado correctamente.
        }
        
        if(categoriaIngresada.getCatPadre() != null)  //Si es una subcategoria:
        {
            Categoria temp = new Categoria();
            temp = AlmacenCategorias.get(categoriaIngresada.getCatPadre());    //Obtengo y almaceno puntero a cat padre.
            
            temp.addSubcategoria(categoriaIngresada);
            
            return true;

        }
        
        
        return false;
 
    }
    public Categoria buscadorC(String nombreCat){
        Categoria u = AlmacenCategorias.get(nombreCat); 
        
        return u;
    }
   
    public int existe(DTOCategoria categoriaIngresada)  //Devuelve 1 si no esta la parte "Categoria-nombre" devuelve 2 si no existe con el nombre "categoria-padre"
    {   //Devuelve 0 si existe alguna coincidencia de nombre o de categoria padre.
        
        if(!AlmacenCategorias.containsKey(categoriaIngresada.getNombreCategoria())) //Busca si ya existe Categoria.
        {       //Si no está y no está vacio da true, ver que es condicion negada
            return 1;
        }
        //Esta opcion aun no tiene uso previsto
        if(!AlmacenCategorias.containsKey(categoriaIngresada.getCatPadre())) //Busca si ya existe SubCategoria como categoria grande.
        {       //Si no está y no está vacio da true, ver que es condicion negada
            return 2;
        }
        
        return 0;
    }
  
}
