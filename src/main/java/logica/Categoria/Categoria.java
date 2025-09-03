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
    @ManyToOne
    @JoinColumn(name = "categoria_padre") // crea columna fk en tabla categoria
    private Categoria catPadre; // pongo esto para que JPA MAPE ese atributo que es la fk categoria /catpadre   esa seria las columnas 

    @OneToMany(mappedBy = "catPadre", cascade = CascadeType.ALL)
    private Set<Categoria> subcategorias;



    public Categoria() {}

    //conjtructor para crear categoria sin padre 
    public Categoria(String nombreCategoria) 
    {
        this.nombreCategoria = nombreCategoria;
        this.catPadre = null;
        this.subcategorias = new HashSet<>();
    }

    // Para añadir catPadre desde el inicio...
    public Categoria(String _nombreCategoria, Categoria padre) 
    {
        this.nombreCategoria = _nombreCategoria;
        this.catPadre = padre;
        this.subcategorias = new HashSet<>();

    }

    public Categoria getCatPadre() 
    {
        return catPadre;
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

    public void setSubcategorias(Set<DTOCategoria> _subcategorias) //Elimina las existentes y agrega nuevas.
    {
        Iterator<DTOCategoria> it = _subcategorias.iterator();

        subcategorias.clear();

        while (it.hasNext()) 
        {
            //subcategorias.add(DTO_a_Cat(it.next()));
        }
    }

    public void eliminarSubcategoria(String subCatAEliminar) 
    {
        Iterator<Categoria> iterator = subcategorias.iterator();

        while (iterator.hasNext()) 
        {
            Categoria subcategoriasIT = iterator.next();

            if (subcategoriasIT.getNombreCategoria().equals("subCatAEliminar")) 
            {
                iterator.remove();
            }
        }
    }

    public void addSubcategoria(Categoria _subCat) //Añade una nueva.
    {
        subcategorias.add(_subCat);
    }

    public void addDTOSubcategoria(DTOCategoria _subCat) //Añade una nueva.
    {
        subcategorias.add(DTO_a_Cat(_subCat));
    }

    public DTOCategoria CrearDT() 
    {
        return new DTOCategoria(this.nombreCategoria);
    }

    public Categoria DTO_a_Cat(DTOCategoria input) 
    {
        Categoria aux = new Categoria(input.getNombreCategoria());

        return aux;
    }
    

    public String toString() 
    {
        return nombreCategoria;
    }
    
    public boolean existeSubCat(String nombreSubCat) //true si existe subcat con ese string.
    {
        Iterator<Categoria> it = subcategorias.iterator();

        while (it.hasNext()) 
        {
            if (it.next().getNombreCategoria().equals(nombreSubCat)) 
            {
                return true;
            }
        }

        return false;
    }
    
    public void setCatPadre(Categoria input)
    {
        catPadre = input;
    }
}
