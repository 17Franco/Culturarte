/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.Registros;
import jakarta.persistence.*;

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
    
    @Column(name = "navegador_web_so", length = 500)    //Almaceno el SO y el navegador web en el mismo string
    private String navegadorWebSO;
    
    @Column(name = "url", nullable = false, length = 512)
    private String url;
    
    
    public RegistrosAccesoWeb() {}
    
    public RegistrosAccesoWeb(String _ip, String _navegadorWebSO, String _url) 
    {
        ip = _ip;
        navegadorWebSO = _navegadorWebSO;
        url = _url;

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
    
    public String getNavegadorWebSO() 
    {
        return navegadorWebSO;
    }
    
    public void setNavegadorWebSO(String navegador) 
    {
        this.navegadorWebSO = navegador;
    }
    
    public String getUrl() 
    {
        return url;
    }
    
    public void setUrl(String url) 
    {
        this.url = url;
    }
}
