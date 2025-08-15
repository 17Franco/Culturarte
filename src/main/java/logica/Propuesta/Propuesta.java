/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.Propuesta;

import logica._enum.TipoRetorno;
import logica.DTO.DTFecha;
import java.util.ArrayList;
import java.util.List;

public class Propuesta {
    
    String Titulo;
    String Descripcion;
    String Tipo;
    String Lugar;
    DTFecha Fecha;
    String Precio;
    String MontoTotal;
    DTFecha FechaPublicacion;
    String Retorno;
 // private List<Registro_Estado> historialEstados = new ArrayList<>();
            
    public Propuesta(){}
    public Propuesta(String Titulo,String Descripcion,String Tipo, String Lugar, DTFecha Fecha, String Precio, String MontoTotal,DTFecha FechaPublicacion,String Retorno)
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
    public DTFecha getFechaPublicacion() {
        return FechaPublicacion;
    }
    public String getRetorno() {
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

    public void setFechaPublicacion(DTFecha fechaPublicacion) {
        FechaPublicacion = fechaPublicacion;
    }

    public void setRetorno(String retorno) {
        Retorno = retorno;
    } 
}