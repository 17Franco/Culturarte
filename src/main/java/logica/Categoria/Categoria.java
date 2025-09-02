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
        this.subcategorias = new HashSet<>(); 
    }
    
    // Para añadir subcategoría desde el inicio...
    public Categoria(String _nombreCategoria, Set<DTOCategoria> subCat)
    {
        nombreCategoria = _nombreCategoria;

        subcategorias = new HashSet<>(); 


        Iterator<DTOCategoria> it = subCat.iterator();

        while (it.hasNext())
        {
            subcategorias.add(DTO_a_Cat(it.next()));
        }
        
		
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
 
    
    public void setSubcategorias(Set<DTOCategoria> _subcategorias)  //Elimina las existentes y agrega nuevas.
    {
        Iterator<DTOCategoria> it = _subcategorias.iterator();

        subcategorias.clear();

        while (it.hasNext())
        {
            subcategorias.add(DTO_a_Cat(it.next()));
        }
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
    
    public void addSubcategoria(DTOCategoria _subCat)   //Añade una nueva.
    {
        subcategorias.add(DTO_a_Cat(_subCat));

    }

    public DTOCategoria CrearDT() {
        return new DTOCategoria(this.nombreCategoria);
    }

    public Categoria DTO_a_Cat(DTOCategoria input)
    {
        Categoria aux = new Categoria(input.getNombreCategoria());

        return aux;
    }

    public String StringSubcategorias()
    {
        String almacen = "";

        Iterator<Categoria> iterator = subcategorias.iterator();

        while(iterator.hasNext())
        {
            if(almacen.isEmpty())
            {
                almacen += "                | SC: ";
                almacen += iterator.next().getNombreCategoria();    //Se obtiene el nombre de cada subcategoria
            }
            else
            {
                almacen += ", ";
                almacen += iterator.next().getNombreCategoria();
            }
        }

        return almacen;
    }

    public boolean existeSubCat(String nombreSubCat)    //true si existe subcat con ese string.
    {
        Iterator<Categoria> it = subcategorias.iterator();

        while (it.hasNext())
        {
            if(it.next().getNombreCategoria().equals(nombreSubCat))
            {
                return true;
            }
        }

        return false;
    }
}