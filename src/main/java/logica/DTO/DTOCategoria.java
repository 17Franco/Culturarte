package logica.DTO;

import java.util.HashSet;
import java.util.Set;

public class DTOCategoria 
{
    private String nombreCategoria;
    private Set<DTOCategoria> subcategorias;

    public DTOCategoria()  //Si no se agrega nada, se inicializará el set unicamente para dejarlo preparado.
    {
        subcategorias = new HashSet<>();
    }


    public DTOCategoria(String _nombreCategoria) 
    {
        nombreCategoria = _nombreCategoria;
        subcategorias = new HashSet<>();
    }

    
    public DTOCategoria(String _nombreCategoria, Set<DTOCategoria> _subcategorias)
    {
        nombreCategoria = _nombreCategoria;

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
        subcategorias = _subcategorias;
    }

  

 }