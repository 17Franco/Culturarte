package logica.Categoria;
import logica.DTO.DTOCategoria;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import jakarta.persistence.*;
import java.util.*;

@Entity
public class Categoria 
{
    @Id
    private String nombreCategoria;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "subCategorias") 
    private Set<Categoria> subcategorias;
    
    public Categoria() 
    {
    }
 
    public Categoria(String nombreCategoria) 
    {
        this.nombreCategoria = nombreCategoria;
        this.subcategorias = new HashSet<>(); //Son lo mismo hashSet == nullptr
    }
    
    // Para añadir subcategoría desde el inicio...
    public Categoria(String _nombreCategoria, Set<Categoria> subCat) //se puede modificar para recibir una lista de string.
    {
        nombreCategoria = _nombreCategoria;

        subcategorias = new HashSet<>(); 

        subcategorias = subCat;
        
		
    }

    public String getNombreCategoria() 
    {
        return nombreCategoria;
    }

   
   public Set<Categoria> getSubcategorias()
   {
       return subcategorias; 
   }


    public void setNombreCategoria(String _nombreCategoria) 
	{
        nombreCategoria = _nombreCategoria;
    }
 
    
    public void setSubcategorias(Set<Categoria> _subcategorias) 
    {
        subcategorias.addAll(_subcategorias);   //.addAll para sumarizar set anterior y nuevo
    }

 
    public void eliminarSubcategoria(String subCatAEliminar)
    {
        Iterator<Categoria> iterator = subcategorias.iterator();
        
        while(iterator.hasNext()) 
        {
            Categoria subcategoriasIT = iterator.next();
            
            if (subcategoriasIT.getNombreCategoria().equals("subCatAEliminar")) 
            {
                iterator.remove();
            }
        }
    }
    
    public void addSubcategoria(Categoria _subCat) 
    {
        subcategorias.add(_subCat);
    }
    public DTOCategoria CrearDT() {
        return new DTOCategoria(this.nombreCategoria);
    }
}