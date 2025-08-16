package logica.DTO;

import logica.DTO.DTOCategoria;
import logica.DTO.DTORegistro_Estado;
import java.util.Date;
import logica._enum.TipoRetorno;

public class DTOPropuesta 
{
    private String titulo;
    private String descripcion;
 // private IMG imagen; Pendiente manejo de imagenes...
    private String tipo;
    private String lugar;
    private DTFecha fecha;
    private String precio;
    private String montoFinal;
    private Date fechaPublicacion;
    private TipoRetorno retorno;
    private DTOCategoria categoria;
    private DTORegistro_Estado registro;
    

    
    public DTOPropuesta() 
    {
      
    }

    public DTOPropuesta(String _titulo, String _descripcion, /*IMG_imagen,*/String _tipo, String _lugar, DTFecha _fecha, String _precio, String _montoFinal, Date _fechaPublicacion, TipoRetorno _retorno, DTOCategoria _categoria, DTORegistro_Estado _registro)
    {
        titulo = _titulo;
        descripcion = _descripcion;
        //imagen = _imagen;
        tipo = _tipo;
        lugar = _lugar;
        fecha = _fecha;
        precio = _precio;
        montoFinal = _montoFinal;
        fechaPublicacion = _fechaPublicacion;
        retorno = _retorno;
        categoria = _categoria;
        registro = _registro;

    }

    public String getTitulo() 
    {
        return titulo;
    }

    public String getDescripcion() 
    {
        return descripcion;
    }

   // public IMG getImagen() 
    {
   //     return imagen;
    }

    public String getTipo() 
    {
        return tipo;
    }

    public String getLugar() 
    {
        return lugar;
    }

    public DTFecha getFecha() 
    {
        return fecha;
    }

    public String getPrecio() 
    {
        return precio;
    }

    public String getMontoFinal() 
    {
        return montoFinal;
    }

    public Date getFechaPublicacion() 
    {
        return fechaPublicacion;
    }

    public DTOCategoria getCategoria()
     {
          return categoria;
      }

    public DTORegistro_Estado getRegEstado()
     {
          return registro;
      }

    public TipoRetorno getRetorno() 
    {
        return retorno;
    }

    public void setTitulo(String _titulo) 
    {
        titulo = _titulo;
    }

    public void setDescripcion(String _descripcion) 
    {
        descripcion = _descripcion;
    }

   /*
    public void setImagen(IMG _imagen)
    {
        imagen = _imagen;
    }
    */


    public void setTipo(String _tipo) 
    {
        tipo = _tipo;
    }

    public void setLugar(String _lugar) 
    {
        lugar = _lugar;
    }

    public void setFecha(DTFecha _fecha) 
    {
        fecha = _fecha;
    }

    public void setPrecio(String _precio) 
    {
        precio = _precio;
    }

    public void setMontoFinal(String _montoFinal) 
    {
        montoFinal = _montoFinal;
    }

    public void setFechaPublicacion(Date _fechaPublicacion) 
    {
        fechaPublicacion = _fechaPublicacion;
    }

    public void setRetorno(TipoRetorno _retorno) 
    {
        retorno = _retorno;
    }

    public void setCategoria(DTOCategoria _categoria)
    {
        categoria = _categoria;
     }

    public void setCategoria(DTORegistro_Estado _registro)
    {
        registro = _registro;
     }

 }