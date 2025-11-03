/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.Registros;
import jakarta.persistence.*;
import java.time.LocalDate;

/**
 *
 * @author klaas
 */
@Entity
@Table(name = "registros_acceso_web")
public class RegistrosAccesoWeb 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //no puedo dejarlo en int porque da problemas al hacer commit.
    
    @Column(name = "ip", nullable = false, length = 50)
    private String ip;
    
    @Column(name = "navegador_web", length = 500)
    private String navegadorWeb;
    
    @Column(name = "so", length = 100)
    private String so;
    
    @Column(name = "url", nullable = false, length = 512)
    private String url;
    
    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fechaRegistro;
    
    
    public RegistrosAccesoWeb() {}
    
    public RegistrosAccesoWeb(String _ip, String _navegadorWeb, String _so, String _url, LocalDate fecha) 
    {
        ip = _ip;
        navegadorWeb = _navegadorWeb;
        so = _so;
        url = _url;
        fechaRegistro = fecha;
    }
    
    public Integer getId() 
    {
        return id;
    }
    
    public String getIp()
    {
        return ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }
    
    public String getNavegadorWeb() 
    {
        return navegadorWeb;
    }
    
    public String getSO()
    {
        return so;
    }
    
    public void setNavegadorWeb(String navegador) 
    {
        this.navegadorWeb = navegador;
    }
    
    public String getUrl() 
    {
        return url;
    }
    
    public void setUrl(String url) 
    {
        this.url = url;
    }
    
    public void setSO(String _so) 
    {
        so = _so;
    }
    
    public LocalDate getFechaRegistro() 
    {
        return fechaRegistro;
    }
    
    public void setFechaRegistro(LocalDate _fechaRegistro) 
    {
        fechaRegistro = _fechaRegistro;
    }
}
