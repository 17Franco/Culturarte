package logica.DTO;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;
import logica.Categoria.Categoria;

@XmlRootElement(name = "DTOCategoria") 
@XmlAccessorType(XmlAccessType.FIELD)
public class DTOCategoria {

    private String nombreCategoria;
    private Set <DTOCategoria> subcategorias;
    private String catPadre;     //Es para saber si la ingreso como subcategoría o si no.
    private Categoria catPadreNodo; //LPM
    
    public DTOCategoria()
    {
        subcategorias = new HashSet<>();
    }

    public DTOCategoria(String _nombreCategoria) {
        nombreCategoria = _nombreCategoria;
        subcategorias = new HashSet<>();
        catPadreNodo = null;
    }

    public DTOCategoria(String _nombreCategoria, Categoria _nodoCatPadre) {
        catPadreNodo = _nodoCatPadre;
        nombreCategoria = _nombreCategoria;
        subcategorias = new HashSet<>();
        catPadreNodo = null;
    }

    public DTOCategoria(String _nombreCategoria,Categoria _nodoCatPadre, String _catPadre) 
    {
        nombreCategoria = _nombreCategoria;
        catPadre = _catPadre;
        catPadreNodo = _nodoCatPadre;
        subcategorias = new HashSet<>();
    }

    
    public DTOCategoria(String _nombreCategoria, Categoria _nodoCatPadre, String _catPadre, Set<Categoria> _subcategorias)
    {
        nombreCategoria = _nombreCategoria;
        catPadre = _catPadre;
        catPadreNodo = _nodoCatPadre;
        
        if (_subcategorias != null) //Si set contiene algo
        {
           subcategorias = getDTOSubcategorias(_subcategorias);
        }

        if (_subcategorias == null) //Si set estaba vacío
        {
            subcategorias = new HashSet<>();

        }
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public String getCatPadre() {
        return catPadre;
    }
    
    public Categoria getCatPadreNodo()
    {
        return catPadreNodo;
    }

    public Set<DTOCategoria> getSubcategorias() 
    {
        return subcategorias;
    }
    
    private Set<DTOCategoria> getDTOSubcategorias(Set<Categoria> _subCategoriasInput)
    {
        Set<DTOCategoria> almacen = new HashSet<>();

        if (_subCategoriasInput == null) 
        {
            return almacen;
        }

        for (Categoria ct : _subCategoriasInput) 
        {
            DTOCategoria temp = new DTOCategoria(ct.getNombreCategoria(),ct.getCatPadre(),nombreCategoria);

            // Recursión: obtener subcategorías del hijo
            temp.setSubcategorias(getDTOSubcategorias(ct.getSubcategorias()));

            almacen.add(temp);
        }

        return almacen;
    }

    public void setNombreCategoria(String _nombreCategoria) {
        nombreCategoria = _nombreCategoria;
    }

    public void setCatPadre(String _catPadre) {
        catPadre = _catPadre;
    }

    public void setSubcategorias(Set<DTOCategoria> _subcategorias) 
    {
        subcategorias = _subcategorias;
    }
    
    public void setNodoPadre(Categoria _padre)
    {
        catPadreNodo = _padre;
    }

    public String toString() 
    {
        return nombreCategoria;
    }
    
 }
