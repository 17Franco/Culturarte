package persistencia;
import logica.DTO.DTOCategoria;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import logica.Categoria.Categoria;
import logica.Categoria.Categoria;

public class ManejadorCategoria 
{
    private Map<String, Categoria> AlmacenCategorias; //El nombre de la categorpia al ser unico servirá para usar como id.
    private static ManejadorCategoria instancia = null;
    
    private ManejadorCategoria() 
    {
        AlmacenCategorias = new HashMap<String, Categoria>();
        
    /*    
        DTOCategoria arte = new Categoria("Arte");
        Categoria tecnologia = new Categoria("Tecnología");
        Categoria musica = new Categoria("Música");

        DTOCategoria dtoSub1 = new DTOCategoria("Pintura", null);
        DTOCategoria dtoSub2 = new DTOCategoria("Escultura", null);
        arte.addSubcategoria(dtoSub1);
        arte.addSubcategoria(dtoSub2);

        DTOCategoria dtoSub3 = new DTOCategoria("Software", null);
        DTOCategoria dtoSub4 = new DTOCategoria("Hardware", null);
        tecnologia.addSubcategoria(dtoSub3);
        tecnologia.addSubcategoria(dtoSub4);

        AlmacenCategorias.put(arte.getNombreCategoria(), arte);
        AlmacenCategorias.put(tecnologia.getNombreCategoria(), tecnologia);
        AlmacenCategorias.put(musica.getNombreCategoria(), musica);*/
    }
    
    public static ManejadorCategoria getInstance() 
    {
        if (instancia == null)
            instancia = new ManejadorCategoria();
        return instancia;
    }
    
    public Map<String, Categoria> getCategorias()
    {
        return AlmacenCategorias;
    }
    
    public DTOCategoria obtenerCategoriaPorNombre(String nombreCategoria)
    {
        Categoria temp = AlmacenCategorias.get(nombreCategoria);
       Set<DTOCategoria> setCat=new HashSet<>();
        for(Categoria c: temp.getSubcategorias()){
            DTOCategoria cat=new DTOCategoria(c.getNombreCategoria(),c.getCatPadre().getNombreCategoria(),null);
            setCat.add(cat);
        }
        DTOCategoria almacen = new DTOCategoria(temp.getNombreCategoria(),"",setCat);
        
        return almacen;
    }
    
    public boolean addCategoria(DTOCategoria categoriaIngresada)
    { 
        if(categoriaIngresada.getCatPadre() == null)  //Si no agregó como subcategoria
        {       //la condición está negada.
           Categoria cat1 = new Categoria(categoriaIngresada.getNombreCategoria(),null,null);
            AlmacenCategorias.put(cat1.getNombreCategoria(),cat1);
            return true;    //agregado correctamente.
        }
        
        if(categoriaIngresada.getCatPadre() != null)  //Si es una subcategoria:
        {
            Categoria temp = AlmacenCategorias.get(categoriaIngresada.getCatPadre());    //Obtengo y almaceno puntero a cat padre.
            
            if(temp != null)
            {
                temp.addSubcategoria(categoriaIngresada);
                return true;
            }

            return false;

        }


        return false;

    }

    public boolean addCategoriaB(DTOCategoria categoriaIngresada) //Sin uso, ingresa una categoria padre como subcategoría.
    {
        Categoria temp = AlmacenCategorias.get(categoriaIngresada.getCatPadre());   //Se obtiene puntero de categoria padre.

            if(temp != null)
            {
                temp.addSubcategoria(categoriaIngresada);
                return true;
            }
        
        
        return false;
    }


    public Categoria buscadorC(String nombreCat){
        Categoria u = AlmacenCategorias.get(nombreCat); 
        
        return u;
    }
   
    public int existe(DTOCategoria categoriaIngresada)
    {
        //Devuelve 0 si no existe como Categoría padre
        //Devuelve 1 en casos excepcionales.
        //Devuelve 2 si la subcategoría ya existía en determinada cat padre
        //Devuelve 3 si existe como categoría padre y además se intenta que sea Subcategoría
        //Devuelve 4 si la categoría padre ingresada por user para una subCat no existe.
        //Devuelve 5 si la categoria padre nueva ingresada ya existe
        //Devuelve 6 si se intenta ingresar una subcategoria como categoría padre

        
        if(categoriaIngresada.getCatPadre() != null)   //Si se agregó como subcategoría
        {
            if(!AlmacenCategorias.containsKey(categoriaIngresada.getCatPadre()))    //Si no existe tal categoría padre que se ingresa....
            {
                return 4;
            }

            if(AlmacenCategorias.containsKey(categoriaIngresada.getCatPadre())) //Si la categoría padre existe...
            {
                Categoria temp = AlmacenCategorias.get(categoriaIngresada.getCatPadre());    //Puntero a nodo de cat padre.

                if (temp.existeSubCat(categoriaIngresada.getNombreCategoria())) //Pregunta si ya existia una subcat con ese nombre.
                {
                    return 2;
                }
            }

            if(AlmacenCategorias.containsKey(categoriaIngresada.getNombreCategoria()))
            {
                    return 3;   //Si se intenta que una cat padre sea subcategoria
            }
        }
        
        if(AlmacenCategorias.containsKey(categoriaIngresada.getNombreCategoria()))
        {
             return 5;     //Si la categoría padre ingresada nueva ya existía.
        }

        if(!AlmacenCategorias.containsKey(categoriaIngresada.getNombreCategoria())) //Busca si ya existe Categoria padre.
        {


            Iterator <Map.Entry <String, Categoria>> ct = AlmacenCategorias.entrySet().iterator();

            while(ct.hasNext())
            {
                Map.Entry<String, Categoria> entry = ct.next();

                if(entry.getValue().existeSubCat(categoriaIngresada.getNombreCategoria()))
                {
                    return 6;
                }

            }

            return 0;
        }





        return 1;
    }
  
}
