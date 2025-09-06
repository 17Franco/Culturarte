package persistencia;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import logica.DTO.DTOCategoria;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.List;
import logica.Categoria.Categoria;

public class ManejadorCategoria {

    private Map<String, Categoria> AlmacenCategorias; //El nombre de la categorpia al ser unico servirá para usar como id.
    private static ManejadorCategoria instancia = null; 
    private EntityManager dbManager;    //Conector a base de datos.
    
    
    private ManejadorCategoria() 
    {
        AlmacenCategorias = new HashMap<String, Categoria>();
    }

    public static ManejadorCategoria getInstance() 
    {
        if (instancia == null) 
        {
            instancia = new ManejadorCategoria();
        }
        return instancia;
    }

    public List<DTOCategoria> getCategorias()
    {
        List<DTOCategoria> almacen = new ArrayList<>();
        
        dbManager = PersistenciaManager.getEntityManager(); //Se asigna base de datos
        
        try 
        {
            List<Categoria> datosImportadosDb = dbManager.createQuery("select catImport from Categoria catImport where catImport.catPadre is NULL", Categoria.class).getResultList();   //Se traen todos los nodos padre.

            for (Categoria ct : datosImportadosDb) 
            {
                DTOCategoria temp = ct.Cat_a_DTO(); //Se pasa a DTO.
                almacen.add(temp);                  //Se almacena en la lista.
            }    
        } 
        finally 
        {
            dbManager.close();
        }
        
        return almacen;
    }

    public DTOCategoria obtenerCategoriaPorNombre(String nombreCategoria) 
    {
        Categoria temp = AlmacenCategorias.get(nombreCategoria);
        
        DTOCategoria almacen = new DTOCategoria(temp.getNombreCategoria(), null,"",temp.getSubcategorias());
        
        return almacen;
    }

    public boolean addCategoria(DTOCategoria categoriaIngresada) 
    { 
        boolean pass = false;
        
        dbManager = PersistenciaManager.getEntityManager();
        EntityTransaction transaccionActual = dbManager.getTransaction();

        transaccionActual.begin(); //Se inicia el token de db
        
        
        if(categoriaIngresada.getCatPadre().isEmpty()) //Si es categoría padre
        {       
            
            Categoria cat1 = new Categoria(categoriaIngresada.getNombreCategoria(),null);
            
            //Se almacena en memoria RAM:
            //AlmacenCategorias.put(cat1.getNombreCategoria(), cat1); 

            //Parte almacenamiento en base de datos:
            try
            {
                dbManager.persist(cat1);
                transaccionActual.commit(); //Se cierra token de db
                System.out.print("\nSe almacenaron datos de categoría " + cat1.getNombreCategoria() + " en la base de datos\n");  //Esto es para corroborar en consola

                pass = true;    //Aviso en bool que fué todo asignado correctamente o a la espera de posible exception db.
            }
            catch(Exception wtf)
            {
                if(transaccionActual.isActive())    //Si no hubo desconexión inesperada...
                {
                    System.out.print("\nError, no se alacenaron datos de categoría " + cat1.getNombreCategoria() + " en la base de datos\n");  //Esto es para corroborar en consola
                    transaccionActual.rollback();   //Se cancela la transacción y se deshace cualquier cambio.
                }
                
                pass = false;   //Almaceno en bool que hubo un error inesperado al ingresar los datos...
            }
            finally
            {
                dbManager.close(); 
            }  
        }

        if (!categoriaIngresada.getCatPadre().isEmpty()) //Si es una subcategoria:
        {

            
            //Almacenamiento en DB
            Categoria temp = dbManager.find(Categoria.class,categoriaIngresada.getCatPadre());
            
            if(temp != null && dbManager.find(Categoria.class, categoriaIngresada.getNombreCategoria()) == null)    //Si encuentra algo y la subcat ingresada no existe...
            {
                categoriaIngresada.setNodoPadre(temp);              //Se le asigna en nodo padre a la subcat...
                temp.addDTOSubcategoria(categoriaIngresada);        //Se actualiza el nodo padre.

                try
                {
                    dbManager.merge(temp);              //Se sobreescribe al que estaba antes.
                    transaccionActual.commit();         //Se cierra token de db alv
                    
                    if(temp.getSubcategorias() != null)
                    {
                        System.out.print("\nSe almacenaron datos de subcategoría " + categoriaIngresada.getNombreCategoria() + " en la categoría " + temp.getNombreCategoria() + " en la base de datos\n");  //Esto es para corroborar en consola
                    }
                    
                    pass = true;    //Aviso en bool que fué todo asignado correctamente o a la espera de posible exception db.
                }
                catch(Exception wtf2)
                {
                    if(transaccionActual.isActive())    //Si no hubo desconexión inesperada...
                    {
                        System.out.print("\nError, no se alacenaron datos de subcategoría " + temp.getNombreCategoria() + " en la base de datos\n");  //Esto es para corroborar en consola
                        transaccionActual.rollback();   //Se cancela la transacción y se deshace cualquier cambio.
                    }

                    pass = false;   //Almaceno en bool que hubo un error inesperado al ingresar los datos...
                }
                finally
                {
                    dbManager.close(); 
                } 
            }
            
                        
            //Almacenamiento en RAM
            Categoria tempRAM = AlmacenCategorias.get(categoriaIngresada.getCatPadre());    //Obtengo y almaceno puntero a cat padre.
            
            if(tempRAM != null) 
            {
                tempRAM.addDTOSubcategoria(categoriaIngresada);
                //pass = true;
            }
        }
        
        return pass;    //Retorno de mensaje de estado...
    }

    public boolean addCategoriaB(DTOCategoria categoriaIngresada) //Sin uso (Ingreso de datos opción B (7) sólo para RAM) Ingreso de subcategoría en subcategorías.
    { 
        boolean pass = false;
        
        dbManager = PersistenciaManager.getEntityManager();
        EntityTransaction transaccionActual = dbManager.getTransaction();

        transaccionActual.begin(); //Se inicia el token de db
        
        Categoria nodoPadre = dbManager.find(Categoria.class, categoriaIngresada.getCatPadre());    //Se busca y almacena nodo padre en db
        
        if(nodoPadre != null) //Si no existe tal categoría padre que se ingresa...
        {
            categoriaIngresada.setNodoPadre(nodoPadre);         //Lo mismo de antes, se asigna el nodo padre.
            nodoPadre.addDTOSubcategoria(categoriaIngresada);   //Actualizado.
            
                try
                {
                    dbManager.merge(nodoPadre);              //Se sobreescribe al que estaba antes.
                    transaccionActual.commit();         //Se cierra token de db alv
                    
                    if(nodoPadre.getSubcategorias() != null)
                    {
                        System.out.print("\nSe almacenaron datos de subcategoría " + categoriaIngresada.getNombreCategoria() + " en la categoría " + nodoPadre.getNombreCategoria() + " en la base de datos\n");  //Esto es para corroborar en consola
                    }
                    
                    pass = true;    //Aviso en bool que fué todo asignado correctamente o a la espera de posible exception db.
                }
                catch(Exception ouNao)
                {
                    if(transaccionActual.isActive())    //Si no hubo desconexión inesperada...
                    {
                        System.out.print("\nError, no se alacenaron datos de subcategoría " + nodoPadre.getNombreCategoria() + " en la base de datos\n");  //Esto es para corroborar en consola
                        transaccionActual.rollback();   //Se cancela la transacción y se deshace cualquier cambio.
                    }

                    pass = false;   //Almaceno en bool que hubo un error inesperado al ingresar los datos...
                }
                finally
                {
                    dbManager.close(); 
                } 
                        
        }
        
        
        //Almacenamiento en RAM
        if (!AlmacenCategorias.containsKey(categoriaIngresada.getCatPadre())) //Si no existe tal categoría padre que se ingresa....
        {
            for (Categoria ct : AlmacenCategorias.values()) //Aca itera cada raiz 
            {
                Categoria nodoPadre1 = buscadorSubcategorias(ct,categoriaIngresada.getCatPadre()); //Recursividad para buscar al nodo ese. (null o !null)
                
                if (nodoPadre1 != null) 
                {
                    Categoria temp = new Categoria(categoriaIngresada.getNombreCategoria(),nodoPadre1);
                    
                    nodoPadre1.addSubcategoria(temp);
                    
                    return true;    //Retorna que se asignó correctamente.
                    
                }
            }

            return false;
        }
        //________________________________________________________________
        
        return pass;
    }

    public Categoria buscadorC(String nombreCat) {
        EntityManager em = PersistenciaManager.getEntityManager();
        try {
            return em.find(Categoria.class, nombreCat);
        } finally {
            em.close();
        }
    }

    public int existe(DTOCategoria categoriaIngresada) //En desuso para almacenamiento volatil.
    {
        //Esta funcion solo sirve para busqueda en RAM.
        
        //Devuelve 0 si no existe como Categoría padre
        //Devuelve 1 en casos excepcionales.
        //Devuelve 2 si la subcategoría ya existía en determinada cat padre
        //Devuelve 3 si existe como categoría padre y además se intenta que sea Subcategoría
        //Devuelve 4 si la categoría ingresada será una subcategoría de otras subcategorías.
        //Devuelve 5 si la categoria padre nueva ingresada ya existe
        //Devuelve 6 si se intenta ingresar una subcategoria como categoría padre
        //Devuelve 7 si la categoria padre ingresada no existe.

        if (categoriaIngresada.getCatPadre() != null) //Si se agregó como subcategoría
        {
            if (!AlmacenCategorias.containsKey(categoriaIngresada.getCatPadre())) //Si no existe tal categoría padre que se ingresa....
            {
                for(Categoria ct : AlmacenCategorias.values())         //Aca itera cada raiz 
                {    

                    if(buscadorSubcategorias(ct,categoriaIngresada.getCatPadre()) != null)  //Recursividad para buscar al nodo ese. (null o !null)
                    {
                        return 7;  
                    }
                }
                
                return 4;
            }

            if (AlmacenCategorias.containsKey(categoriaIngresada.getCatPadre())) //Si la categoría padre existe...
            {
                Categoria temp = AlmacenCategorias.get(categoriaIngresada.getCatPadre());    //Puntero a nodo de cat padre.

                if (temp.existeSubCat(categoriaIngresada.getNombreCategoria())) //Pregunta si ya existia una subcat con ese nombre.
                {
                    return 2;
                }
            }

            if (AlmacenCategorias.containsKey(categoriaIngresada.getNombreCategoria())) {
                return 3;   //Si se intenta que una cat padre sea subcategoria
            }
        }

        if (AlmacenCategorias.containsKey(categoriaIngresada.getNombreCategoria())) {
            return 5;     //Si la categoría padre ingresada nueva ya existía.
        }

        if (!AlmacenCategorias.containsKey(categoriaIngresada.getNombreCategoria())) //Busca si ya existe Categoria padre.
        {

            Iterator<Map.Entry<String, Categoria>> ct = AlmacenCategorias.entrySet().iterator();

            while (ct.hasNext()) {
                Map.Entry<String, Categoria> entry = ct.next();

                if (entry.getValue().existeSubCat(categoriaIngresada.getNombreCategoria())) {
                    return 6;
                }

            }

            return 0;
        }

        return 1;
    }
  


    Categoria buscadorSubcategorias(Categoria importSubCategorias, String subCatBuscada) //Acá llega el arbol sin la raíz (la raiz se itera afuera).
    {
        
        if(importSubCategorias == null) //Este estaba vacío.
        {
            return null;
        }

        if(importSubCategorias.getNombreCategoria().equals(subCatBuscada))
        {
            return importSubCategorias;
        }

        for(Categoria ct : importSubCategorias.getSubcategorias()) //Accede a las subcategorias de esa categoria 
        {
            Categoria nodo = buscadorSubcategorias(ct,subCatBuscada);
            
            if(nodo != null) //Sin esto no me guarda la coincidencia del !null de arriba.
            {
                return nodo;
            }
//            else
//            {
//                return null;
//            }
        }

        return null;
    }
    



}
