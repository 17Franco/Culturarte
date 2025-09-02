package logica.DTO;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import logica.Categoria.Categoria;

public class DTOCategoria 
{
    private String nombreCategoria;
    private String catPadre;     //Es para saber si la ingreso como subcategoría o si no.
    private Set<DTOCategoria> subcategorias= new HashSet<>();
    

    public DTOCategoria()
    {
        
    }
    
    public DTOCategoria(String _nombreCategoria) 
    {
        this.nombreCategoria = _nombreCategoria;
        this.catPadre=null;
        this.subcategorias = new HashSet<>();
    }

    public DTOCategoria(String _nombreCategoria, String _catPadre) 
    {
        this.nombreCategoria = _nombreCategoria;
        this.catPadre = _catPadre;
        this.subcategorias = new HashSet<>();
    }

  /*  public DTOCategoria(Categoria c){
        this.nombreCategoria=c.getNombreCategoria();
        this.catPadre=c.getCatPadre().getNombreCategoria();
        this.subcategorias=c.getSubcategorias();
    }*/
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
    public void addSubCategoria(DTOCategoria c){
        subcategorias.add(c);
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
        this.catPadre = _catPadre;
    }

    public void setSubcategorias(Set<DTOCategoria> _subcategorias) 
    {
        subcategorias = _subcategorias;
    }
    public void addSubcategoria(DTOCategoria c){
        subcategorias.add(c);
    }
    
    
     public String StringSubcategorias()
    {
        String almacen = "";

        Iterator<DTOCategoria> iterator = subcategorias.iterator();

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
     
     
 }