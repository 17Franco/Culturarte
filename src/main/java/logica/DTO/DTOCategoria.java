package logica.DTO;
import java.util.HashSet;
import java.util.Set;
import logica.Categoria.Categoria;

public class DTOCategoria 
{
    private String nombreCategoria;
    private Set<Categoria> subcategorias;
    private String catPadre;     //Es para saber si la ingreso como subcategoría o si no.
    private Categoria catPadreNodo;
    
    public DTOCategoria()
    {
        subcategorias = new HashSet<>();
    }
    
    public DTOCategoria(String _nombreCategoria) 
    {
        nombreCategoria = _nombreCategoria;
        subcategorias = new HashSet<>();
        catpadreNodo = null;
    }

    public DTOCategoria(String _nombreCategoria, String _catPadre, Categoria _nodoCatPadre) 
    {
        nombreCategoria = _nombreCategoria;
        catPadre = _catPadre;
        catPadreNodo = _nodoCatPadre;
        subcategorias = new HashSet<>();
    }

    
    public DTOCategoria(String _nombreCategoria, String _catPadre, Categoria _nodoCatPadre, Set<Categoria> _subcategorias)
    {
        nombreCategoria = _nombreCategoria;
        catPadre = _catPadre;
        catPadreNodo = _nodoCatPadre;
        if (_subcategorias != null) //Si set contiene algo
        {
            subcategorias = _subcategorias;
        }


        if(_subcategorias == null) //Si set estaba vacío
        {
             subcategorias = new HashSet<>();

        }
    }

    public String getNombreCategoria() 
    {
        return nombreCategoria;
    }
    
    public String getCatPadre()
    {
        return catPadre;
    }
    
    public String getCatPadreNodo()
    {
        return catPadreNodo;
    }

    public Set<Categoria> getSubcategorias() 
    {
        return subcategorias;
    }

    public void setNombreCategoria(String _nombreCategoria) 
    {
        nombreCategoria = _nombreCategoria;
    }
    
    public void setCatPadre(String _catPadre)
    {
        catPadre = _catPadre;
    }

    public void setSubcategorias(Set<Categoria> _subcategorias) 
    {
        subcategorias = _subcategorias;
    }

 }
