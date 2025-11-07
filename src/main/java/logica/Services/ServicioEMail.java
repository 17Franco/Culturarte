/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.Services;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import logica.DTO.DTOPago;
/**
 *
 * @author klaas
 */
public class ServicioEMail 
{
    //Será un email tipo SPTM
    
    private String mailHost = "localhost";    //Al usar devnull lo dejo en localhost abajo en la función de enviar email
    private int mailPort;
    private String urlBase;
    private static final String emailCulturarte = "noreply@culturarte.com.uy";        //Lo dejo por las dudas pero devnull no lo usará, lo dejo por si llego a necesitar un server real

    public ServicioEMail(){}

    public ServicioEMail(String smtpHost, int _mailPort) 
    {
        mailPort = _mailPort;
        
        urlBase = "http://localhost:8080/Lab2PA";  //Luego debo agregar la dirección extra para el acceso directo a la descarga del pdf
        
    }

    public void notificarPagoConfirmado(DTOPago infoPago, String[] emails, String[] nombres, String tituloPropuesta) 
    {
        
        //Array de String email: 0 es el del colaborador, 1 es el del proponente.
        //Array de String nombres: 0 es el del colaborador, 1 es el del proponente.
        
        try 
        {
            // Al proponente
            enviarEmail(1, emails, nombres, tituloPropuesta, infoPago);

            // Al colaborador
            enviarEmail(2, emails, nombres, tituloPropuesta, infoPago);

        } 
        catch (MessagingException e) 
        {
            e.printStackTrace();
        }
    }

    //Funcion que prepara email
    private void enviarEmail(int opcion, String[] emails, String[] nombres, String tituloPropuesta, DTOPago infoPago) throws MessagingException 
    {
        //Opciones de email personalizado:
        
        // 1: Proponente
        // 2: Colaborador
        
        if(opcion == 2)
        {
            //Caso colaborador
            String asunto_mail = "[Culturarte] [" + infoPago.getFechaPago() + "] Pago de colaboración registrado";
            String mensaje_mail = crearHtml(opcion, nombres, tituloPropuesta, infoPago);
            
            enviarEmail(2, emails, asunto_mail, mensaje_mail);
        }
        
        if(opcion == 1)
        {
            //Caso proponente
            String asunto_mail = "[Culturarte] [" + infoPago.getFechaPago() + "] Pago de colaboración registrado";
            String mensaje_mail = crearHtml(opcion, nombres, tituloPropuesta, infoPago);
            
            enviarEmail(1, emails, asunto_mail, mensaje_mail);
            
        }
     
    }

    private void enviarEmail(int op, String[] emails, String asunto, String mensaje_mail) throws MessagingException 
    {
        //Parámetros para el server de email
        Properties parametros = new Properties();
        
        parametros.put("mail.smtp.host", mailHost);                 //Lo dejo en localhost para devnull desde la misma clase (arriba)
        parametros.put("mail.smtp.port", String.valueOf(mailPort));  
        
        //Dejo la seguridad en false
        //parametros.put("mail.smtp.auth", "false");                  
        //parametros.put("mail.smtp.starttls.enable", "false");

        Session session = Session.getInstance(parametros);

        //Config del email
        MimeMessage mensaje = new MimeMessage(session);
        mensaje.setFrom(new InternetAddress(emailCulturarte));
        
        if(op == 2) //A colaborador
        {
            mensaje.setRecipient(Message.RecipientType.TO, new InternetAddress(emails[0]));
        }
        
        if(op == 1) //A proponente
        {
            mensaje.setRecipient(Message.RecipientType.TO, new InternetAddress(emails[1]));
        }
        mensaje.setSubject(asunto, "UTF-8");
        mensaje.setContent(mensaje_mail, "text/html; charset=UTF-8");

        Transport.send(mensaje);
    }

    private String crearHtml(int opcion, String[] nombres, String tituloPropuesta, DTOPago infoPago) 
    {
        

        if(opcion == 2) //Caso colaborador
        {    
            //Ejemplo: http:/localhost:8080/Lab2PA/DetallesDePropuesta?id=Cine%20en%20el%20Botanico
            
            //Paso el tituloPropuesta a una string entendible por el navegador web con URLEncoder:
            String urlConstancia = urlBase + "/DetallesDePropuesta?id=" + URLEncoder.encode(tituloPropuesta, StandardCharsets.UTF_8);

            return "<!DOCTYPE html>"
                    + "<head>"
                    + "    <meta charset='UTF-8'>"
                    + "</head>"
                    + "<body>"
                    + "    <h1>Estimado " + nombres[0] + ". el pago correspondiente a la colaboración de la propuesta " + tituloPropuesta + "realizada por usted fué registrada exitosamente.</h1><br><br>"
                    + "    <p>Detalles de la colaboración</p><br>"
                    + "    <p>Propouesta: " + tituloPropuesta + "</p><br>"
                    + "    <p>Proponente: " + nombres[1] + "</p><br>"
                    + "    <p>Colaborador: " + nombres[0] + "</p><br>"
                    + "    <p>Monto: " + infoPago.getMonto() + "</p><br><br>"
                    + "    <p>Link de la propuesta:</p><br>"
                    + "    <p><a href='" + urlConstancia + "'> Desde acá puedes btener la constancia de pago</a></p>"
                    + "</body>"
                    + "</html>";
        }
        
        
        if(opcion == 1) //Caso proponente
        {
            return "<!DOCTYPE html>"
                    + "<head>"
                    + "    <meta charset='UTF-8'>"
                    + "</head>"
                    + "<body>"
                    + "    <h1>Estimado " + nombres[1] + ". el pago correspondiente a la colaboración de la propuesta " + tituloPropuesta + "realizada por " + nombres[0] + "fué registrada exitosamente.</h1><br><br>"
                    + "    <p>Detalles de la colaboración</p><br>"
                    + "    <p>Propouesta: " + tituloPropuesta + "</p><br>"
                    + "    <p>Proponente: " + nombres[1] + "</p><br>"
                    + "    <p>Colaborador: " + nombres[0] + "</p><br>"
                    + "    <p>Monto: " + infoPago.getMonto() + "</p><br><br>"
                    + "</body>"
                    + "</html>";
        }
        
        return "";
    }

    public void setHost(String input) 
    {
        mailHost = input;
    }

    public void setPort(int input) 
    {
        mailPort = input;
    }

    public void setUrlBase(String input) 
    {
        urlBase = input;
    }

}
