/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.Propuesta;

import logica._enum.TipoRetorno;
import logica.DTO.DTOCategoria;
import logica.DTO.DTFecha;
import java.util.List;
import java.util.Date;
public class Propuesta {
    
    private String Titulo;
    private String Descripcion;
    private String Tipo;
  //  private File Imagen;
    private String Lugar;
    private DTFecha Fecha;
    private String Precio;
    private String MontoTotal;
    private Date FechaPublicacion;
    private TipoRetorno Retorno;
    private DTOCategoria cat;
    
// private List<Registro_Estado> historialEstados;
// private List<Registro> Aporte;
            
    public Propuesta(){}
    public Propuesta(String Titulo,String Descripcion,String Tipo, String Lugar, DTFecha Fecha, String Precio, String MontoTotal,Date FechaPublicacion,TipoRetorno Retorno)
    {
        this.Titulo=Titulo;
        this.Descripcion=Descripcion;
        this.Tipo=Tipo;
        this.Lugar=Lugar;
        this.Fecha=Fecha;
        this.Precio=Precio;
        this.MontoTotal=MontoTotal;
        this.FechaPublicacion=FechaPublicacion;
        this.Retorno=Retorno;
    }
    public  String getTitulo() {
        return Titulo;
    }
    public  String getDescripcion() {
        return Descripcion;
    }
    public  String getTipo() {
        return Tipo;
    }
    public  String getLugar() {
        return Lugar;
    }
    public  DTFecha getFecha() {
        return Fecha;
    }
    public String getPrecio() {
        return Precio;
    }
    public String getMontoTotal() {
        return MontoTotal;
    }
    public Date getFechaPublicacion() {
        return FechaPublicacion;
    }
    public TipoRetorno getRetorno() {
        return Retorno;
    }
  /*  public List<Registro_Estado> getHistorialEstados() {
        return historialEstados;
    }
    */
    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public void setLugar(String lugar) {
        Lugar = lugar;
    }

    public void setFecha(DTFecha fecha) {
        Fecha = fecha;
    }

    public void setPrecio(String precio) {
        Precio = precio;
    }

    public void setMontoTotal(String montoTotal) {
        MontoTotal = montoTotal;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        FechaPublicacion = fechaPublicacion;
    }

    public void setRetorno(TipoRetorno retorno) {
        Retorno = retorno;
    } 
}