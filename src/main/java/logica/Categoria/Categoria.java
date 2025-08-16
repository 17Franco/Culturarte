package logica.Categoria;

import logica.DTO.DTOCategoria;
import java.util.HashSet;
import java.util.Set;

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
    public Categoria(String _nombreCategoria, String subCat) //se puede modificar para recibir una lista de string.
	{
        nombreCategoria = _nombreCategoria;

       subcategorias = new HashSet<>(); 

		if(subCat != null && subCat != "") //Si se especifican una o más subcategorías...
		{   
				//Pendiente añadir una función que devuelva uno o más DataType buscando a partir de un string o set de string con categorías para agregar al set...

				//subcategorias.add(DTO.DTOCategoria);
		}
		
	}

    public String getNombreCategoria() 
	{
        return nombreCategoria;
    }

   
    //public Set<DTO.DTOCategoria> getSubcategorias(){return subcategorias; }


    public void setNombreCategoria(String _nombreCategoria) 
	{
        nombreCategoria = _nombreCategoria;
    }
 
    
    public void setSubcategorias(Set<DTOCategoria> _subcategorias) 
	{
        subcategorias = _subcategorias;
    }

 
	/*
	public void eliminarSubcategoria(String categoria)
	{
		Pendiente buscador set de DTO.DTOCategoria por string o set de string
        subcategorias.remove(DTO.DTOCategoria);
	}
	*/

	/*
	public void agregarSubcategoria(String categoria)
	{
		 subcategorias.add(DTO.DTOCategoria);
	}
	*/



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