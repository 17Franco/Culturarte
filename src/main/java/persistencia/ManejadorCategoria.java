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

        Categoria dtoSub1 = new Categoria("Pintura", null);
        Categoria dtoSub2 = new Categoria("Escultura", null);
        arte.addSubcategoria(dtoSub1);
        arte.addSubcategoria(dtoSub2);

        Categoria dtoSub3 = new Categoria("Software", null);
        Categoria dtoSub4 = new Categoria("Hardware", null);
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
    
    public Categoria obtenerCategoriaPorNombre(String nombreCategoria)
    {
        Categoria temp = AlmacenCategorias.get(nombreCategoria);
        
        Categoria almacen = new Categoria(temp.getNombreCategoria(),temp.getSubcategorias());
        
        return almacen;
    }
    
    public boolean addCategoria(Categoria categoriaIngresada)
    { 
        /*if(categoriaIngresada.getCatPadre() == null)  //Si no agregó como subcategoria
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

        }*/
        
        
        return false;
 
    }
    public Categoria buscadorC(String nombreCat){
        Categoria u = AlmacenCategorias.get(nombreCat); 
        
        return u;
    }
   
    public int existe(Categoria categoriaIngresada)  //Devuelve 1 si no esta la parte "Categoria-nombre" devuelve 2 si no existe con el nombre "categoria-padre"
    {   //Devuelve 0 si existe alguna coincidencia de nombre o de categoria padre.
        
       /* if(!AlmacenCategorias.containsKey(categoriaIngresada.getNombreCategoria())) //Busca si ya existe Categoria.
        {       //Si no está y no está vacio da true, ver que es condicion negada
            return 1;
        }
        //Esta opcion aun no tiene uso previsto
        if(!AlmacenCategorias.containsKey(categoriaIngresada.getCatPadre())) //Busca si ya existe SubCategoria como categoria grande.
        {       //Si no está y no está vacio da true, ver que es condicion negada
            return 2;
        }
        */
        return 0;
    }
  
}
