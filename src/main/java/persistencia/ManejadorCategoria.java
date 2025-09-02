package persistencia;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import logica.DTO.DTOCategoria;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import logica.Categoria.Categoria;
import logica.Categoria.Categoria;

public class ManejadorCategoria 
{
    private Map<String, Categoria> AlmacenCategorias; //El nombre de la categorpia al ser unico servirá para usar como id.
    private static ManejadorCategoria instancia = null;
    private EntityManager em;
    private ManejadorCategoria() {
        //AlmacenCategorias = new HashMap<String, Categoria>();
        
       
        /* DTOCategoria arte = new DTOCategoria("Arte",null);
        DTOCategoria tecnologia = new DTOCategoria("Tecnología",null);
        DTOCategoria musica = new DTOCategoria("Música",null);

        DTOCategoria dtoSub1 = new DTOCategoria("Pintura", "Arte");
        DTOCategoria dtoSub2 = new DTOCategoria("Escultura","Arte");
        //arte.addSubcategoria(dtoSub1);
       // arte.addSubcategoria(dtoSub2);

        DTOCategoria dtoSub3 = new DTOCategoria("Software", "Tecnología");
        DTOCategoria dtoSub4 = new DTOCategoria("Hardware", "Música");
        
        boolean aux=addCategoria(arte);
        aux=addCategoria(tecnologia);
        aux=addCategoria(musica);
        
        aux=addCategoria(dtoSub1);
        aux=addCategoria(dtoSub2);
        aux=addCategoria(dtoSub3);
        aux=addCategoria(dtoSub4);
        
        
        //tecnologia.addSubcategoria(dtoSub3);
       // tecnologia.addSubcategoria(dtoSub4);
        
        //AlmacenCategorias.put(arte.getNombreCategoria(), arte);
        //AlmacenCategorias.put(tecnologia.getNombreCategoria(), tecnologia);
        //AlmacenCategorias.put(musica.getNombreCategoria(), musica);*/
    }
    
    public static ManejadorCategoria getInstance() 
    {
        if (instancia == null)
            instancia = new ManejadorCategoria();
        return instancia;
    }
    
    public Map<String, DTOCategoria> getCategorias() {
        Map<String, DTOCategoria> resu = new HashMap<>();
         em= PersistenciaManager.getEntityManager();
         List<Categoria> lista = em.createQuery("FROM Categoria", Categoria.class).getResultList();
         try{
             for(Categoria u: lista){
                if(u.getCatPadre() == null){
                    //DTOCategoria c= new DTOCategoria(u.getNombreCategoria());
                    resu.put(u.getNombreCategoria(), crearDTORecursivo(u));
                }
            }
         }finally{
             em.close();
         }
         /*
        for (Categoria c : AlmacenCategorias.values()) {
            // Solo agregar categorías que no tengan padre para evitar duplicados
            if (c.getCatPadre() == null) {
                resu.put(c.getNombreCategoria(), crearDTORecursivo(c));
            }
        }*/
        return resu;
    }

    private DTOCategoria crearDTORecursivo(Categoria c) {
        DTOCategoria dto = new DTOCategoria(c.getNombreCategoria());
        for (Categoria sub : c.getSubcategorias()) {
            dto.addSubcategoria(crearDTORecursivo(sub));  // aquí se llama recursivamente
        }
        return dto;
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
 
    
    public boolean addCategoria(DTOCategoria categoriaIngresada){ 
        em= PersistenciaManager.getEntityManager();
        EntityTransaction t = em.getTransaction();
        Categoria cat1 = new Categoria(categoriaIngresada.getNombreCategoria().trim());
        try{
            if(categoriaIngresada.getCatPadre() == null ){//la condición está negada.
               t.begin();
               em.persist(cat1);
               t.commit();
                return true;    //agregado correctamente.
            }else{
                Categoria temp = em.find(Categoria.class,categoriaIngresada.getCatPadre().trim()); 
                Categoria aux=  new Categoria(categoriaIngresada.getNombreCategoria().trim(),temp);
                if(temp != null){      
                    t.begin();
                    temp.addSubcategoria(aux);
                    em.persist(aux);
                    t.commit();
                    return true;
                } 
            } 
        }catch(Exception e){
        
        }finally{
            em.close();
        }
          return false;
    }


    public Categoria buscadorC(String nombreCat){
         em= PersistenciaManager.getEntityManager();
        Categoria c = em.find(Categoria.class,nombreCat); 
        
       /*  DTOCategoria resu=new DTOCategoria();
        if(c!=null){
            resu.setNombreCategoria(c.getNombreCategoria());
            if(c.getCatPadre()!=null){
                resu.setCatPadre(c.getCatPadre().getNombreCategoria());
            }
            /*if(c.getSubcategorias()!=null && !c.getSubcategorias().isEmpty()){ esto es por si queres traer las sub cat directas no va mas abajo en la subcat de la subcat
                    Set<DTOCategoria> subCatDTO = new HashSet<>();
                    for (Categoria sub : c.getSubcategorias()) {
                        DTOCategoria subDTO = new DTOCategoria();
                        subDTO.setNombreCategoria(sub.getNombreCategoria());
                        if (sub.getCatPadre() != null) {
                            subDTO.setCatPadre(sub.getCatPadre().getNombreCategoria());
                        }   
                    
                    subCatDTO.add(subDTO);
                    }
                resu.setSubcategorias(subCatDTO); 
            }
    
        }*/
        //}   
        return c;
    }
   
    public int existe(DTOCategoria categoriaIngresada){
        //Devuelve 0 si no existe como Categoría padre
        //Devuelve 1 en casos excepcionales.
        //Devuelve 2 si la subcategoría ya existía en determinada cat padre
        //Devuelve 3 si existe como categoría padre y además se intenta que sea Subcategoría
        //Devuelve 4 si la categoría padre ingresada por user para una subCat no existe.
        //Devuelve 5 si la categoria padre nueva ingresada ya existe
        //Devuelve 6 si se intenta ingresar una subcategoria como categoría padre
        
         em= PersistenciaManager.getEntityManager();
         //Si se agregó como subcategoría
        if(categoriaIngresada.getCatPadre() != null){
            Categoria nombrePadre =em.find(Categoria.class, categoriaIngresada.getCatPadre().trim()) ;
           
            if(nombrePadre==null)    //Si no existe tal categoría padre que se ingresa devuelvo 4 y doy mensjae
            {
                return 4;
            }

            if(nombrePadre!=null) //Si la categoría padre existe
            {
                //Categoria temp = AlmacenCategorias.get(categoriaIngresada.getCatPadre());    //Puntero a nodo de cat padre.
                
                //Pregunta si ya existia una subcat con ese nombre.
                if (nombrePadre.existeSubCat(categoriaIngresada.getNombreCategoria())) {
                    return 2;
                }
            }
                Categoria c=em.find(Categoria.class,categoriaIngresada.getNombreCategoria());
            if(c!=null)
            {
                    return 3;   //Si se intenta que una cat padre sea subcategoria
            }
        }
        
            Categoria c=em.find(Categoria.class,categoriaIngresada.getNombreCategoria());
            //AlmacenCategorias.containsKey(categoriaIngresada.getNombreCategoria())
        if(c!=null)
        {
             return 5;     //Si la categoría padre ingresada nueva ya existía.
        }
        //Busca si ya existe Categoria padre.  falta esto no lo entiendo  mepa que nunca entra aca 
        if(!AlmacenCategorias.containsKey(categoriaIngresada.getNombreCategoria())) 
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
