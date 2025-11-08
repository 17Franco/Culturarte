/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.DTO;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import logica.Registros.RegistrosAccesoWeb;

/**
 *
 * @author klaas
 */

@XmlRootElement(name = "DTORegistrosAccesoWeb") 
@XmlAccessorType(XmlAccessType.FIELD)
public class DTORegistrosAccesoWeb 
{
    private int id; 
    
    private String ip;
    
    private String navegadorWeb;
    
    private String so;
    
    private String url;
    
    private LocalDate fechaReg;
    
    
    public DTORegistrosAccesoWeb() {}
    
    public DTORegistrosAccesoWeb(String _ip, String _navegadorWeb, String _so, String _url, LocalDate fecha) 
    {   
        ip = _ip;
        navegadorWeb = _navegadorWeb;
        so = _so;
        url = _url;
        
        if(fecha == null)
        {
           fechaReg = LocalDate.now();
        }
        
        fechaReg = fecha;   
    }
    
    
    public DTORegistrosAccesoWeb(RegistrosAccesoWeb input) //Exporto directo del objeto
    {
        id = input.getId();
        ip = input.getIp();
        navegadorWeb = input.getNavegadorWeb();
        so = input.getSO();
        url = input.getUrl();
        fechaReg = input.getFechaRegistro();
    }
    
    public void setId(int _id) 
    {
        id = _id;
    }
    
    public void setFechaReg(LocalDate fecha) 
    {
        fechaReg = fecha;
    }
    
    public void setIp(String _ip) 
    {
        ip = _ip;
    }
        
    public void setUrl(String _url) 
    {
        url = _url;
    }
        
    public void setNavegadorWeb(String navegador) 
    {
        navegadorWeb = navegador;
    }
    
    public void setSO(String _so) 
    {
        so = _so;
    }
    
    public String getNavegadorWeb() 
    {
        return navegadorWeb;
    }
    
    public String getSO()
    {
        return so;
    }
    
    public LocalDate getFechaReg()
    {
        return fechaReg;
    }
  
    public Integer getId() 
    {
        return id;
    }
    
    public String getIp() 
    {
        return ip;
    }
    
    public String getUrl() 
    {
        return url;
    }

    public String getAcceso() 
    {
        String numeroID = String.valueOf(id);
        return numeroID + "  " + ip + "  " + url + "  " + navegadorWeb + "  " + so;
    }
}
