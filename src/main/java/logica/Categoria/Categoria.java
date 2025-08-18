package logica.Categoria;
import logica.DTO.DTOCategoria;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

public class Categoria 
{
    private String nombreCategoria;
    private Set<DTOCategoria> subcategorias;
    
 
    public Categoria(String nombreCategoria) 
    {
        this.nombreCategoria = nombreCategoria;
        this.subcategorias = new HashSet<>(); //Son lo mismo hashSet == nullptr
    }
    
    // Para añadir subcategoría desde el inicio...
    public Categoria(String _nombreCategoria, Set<DTOCategoria> subCat) //se puede modificar para recibir una lista de string.
    {
        nombreCategoria = _nombreCategoria;

        subcategorias = new HashSet<>(); 

        subcategorias = subCat;
        
		
    }

    public String getNombreCategoria() 
    {
        return nombreCategoria;
    }

   
   public Set<DTOCategoria> getSubcategorias()
   {
       return subcategorias; 
   }


    public void setNombreCategoria(String _nombreCategoria) 
	{
        nombreCategoria = _nombreCategoria;
    }
 
    
    public void setSubcategorias(Set<DTOCategoria> _subcategorias) 
    {
        subcategorias.addAll(_subcategorias);   //.addAll para sumarizar set anterior y nuevo
    }

 
    public void eliminarSubcategoria(String subCatAEliminar)
    {
        Iterator<DTOCategoria> iterator = subcategorias.iterator();
        
        while(iterator.hasNext()) 
        {
            DTOCategoria subcategoriasIT = iterator.next();
            
            if (subcategoriasIT.getNombreCategoria().equals("subCatAEliminar")) 
            {
                iterator.remove();
            }
        }
    }


    public void agregarSubcategoria(Set<DTOCategoria> addCat)
    {
	 subcategorias.addAll(addCat);  //Sumariza subCats
    }



    /*
    public set<DTO.DTOCategoria> buscadorCategorias (set<String> inputCategorias)
    {
        subcategorias = new HashSet<>();

        Pendiente

        if(inputCategorias != null || inputCategorias != "")
        {
            //Pendiente hasta obtener acceso a base de datos
        }

        return subcategorias
    }

    */
    
}