package logica.DTO;
import java.util.HashSet;
import java.util.Set;

public class DTOCategoria 
{
    private String nombreCategoria;
    private Set<DTOCategoria> subcategorias;
    private String catPadre;     //Es para saber si la ingreso como subcategoría o si no.

    public DTOCategoria()
    {
        subcategorias = new HashSet<>();
    }
    
    public DTOCategoria(String _nombreCategoria) 
    {
        nombreCategoria = _nombreCategoria;
        subcategorias = new HashSet<>();
    }

    public DTOCategoria(String _nombreCategoria, String _catPadre) 
    {
        nombreCategoria = _nombreCategoria;
        catPadre = _catPadre;
        subcategorias = new HashSet<>();
    }

    
    public DTOCategoria(String _nombreCategoria, String _catPadre, Set<DTOCategoria> _subcategorias)
    {
        nombreCategoria = _nombreCategoria;
        catPadre = _catPadre;

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

    public Set<DTOCategoria> getSubcategorias() 
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

    public void setSubcategorias(Set<DTOCategoria> _subcategorias) 
    {
        subcategorias = _subcategorias;
    }

 }