/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.Propuesta;

/**
 *
 * @author klaas
 */
public class Comentario 
{
    private String nickUsuario;
    private String comentario;
    
    public Comentario() {}
    
    public Comentario(String nickUsuario, String comentario) 
    {
        this.nickUsuario = nickUsuario;
        this.comentario = comentario;
    }
    
    public String getNickUsuario() 
    {
        return nickUsuario;
    }
    
    public void setNickUsuario(String nickUsuario) 
    {
        this.nickUsuario = nickUsuario;
    }
    
    public String getComentario() 
    {
        return comentario;
    }
    
    public void setComentario(String comentario) 
    {
        this.comentario = comentario;
    }
}
