/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.Services;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import logica.DTO.DTOPago;
/**
 *
 * @author klaas
 */

public class ServicioEMail 
{

    private String mailHost = "localhost";
    private int mailPort = 2500;
    private static final String EMAIL_FROM = "noreply@culturarte.com.uy";
    private String urlBase = "http://localhost:8080/Lab2PA";

    public ServicioEMail() 
    {
    }

    public ServicioEMail(int port) 
    {
        this.mailPort = port;
    }

    public void notificarPago(DTOPago infoPago, String[] emails, String[] nombres, String tituloPropuesta) 
    {
        try 
        {
            enviarEmail(1, emails, nombres, tituloPropuesta, infoPago); // Proponente
            enviarEmail(2, emails, nombres, tituloPropuesta, infoPago); // Colaborador
        } 
        catch (MessagingException e) 
        {
            e.printStackTrace();
        }
    }

    private void enviarEmail(int opcion, String[] emails, String[] nombres, String tituloPropuesta, DTOPago infoPago) throws MessagingException 
    {
        String destinatario = (opcion == 1) ? emails[1] : emails[0];

        Properties props = new Properties();
        props.put("mail.smtp.host", mailHost);
        props.put("mail.smtp.port", String.valueOf(mailPort));
        props.put("mail.smtp.auth", "false");
        props.put("mail.smtp.starttls.enable", "false");

        Session session = Session.getInstance(props);

        MimeMessage mensaje = new MimeMessage(session);
        mensaje.setFrom(new InternetAddress(EMAIL_FROM));
        mensaje.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
        mensaje.setSubject("[Culturarte] [" + infoPago.getFechaPago() + "] Pago de colaboración registrado", "UTF-8");
        mensaje.setContent(crearHtml(opcion, nombres, tituloPropuesta, infoPago), "text/html; charset=UTF-8");

        Transport.send(mensaje);
    }

    private String crearHtml(int opcion, String[] nombres, String tituloPropuesta, DTOPago infoPago) 
    {
        
        String urlConstancia = urlBase + "/DetallesDePropuesta?id=" + URLEncoder.encode(tituloPropuesta, StandardCharsets.UTF_8);

        if (opcion == 2) // Colaborador
        {
            return "<!DOCTYPE html>"
                    + "<head>"
                    + "    <meta charset='UTF-8'>"
                    + "</head>"
                    + "<body>"
                    + "    <h1>Estimado " + nombres[0] + ". el pago correspondiente a la colaboración de la propuesta " + tituloPropuesta + " realizada por usted fué registrada exitosamente.</h1><br><br>"
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
        else // Proponente
        {
            return "<!DOCTYPE html>"
                    + "<head>"
                    + "    <meta charset='UTF-8'>"
                    + "</head>"
                    + "<body>"
                    + "    <h1>Estimado " + nombres[1] + ". el pago correspondiente a la colaboración de la propuesta " + tituloPropuesta + " realizada por " + nombres[0] + " fué registrada exitosamente.</h1><br><br>"
                    + "    <p>Detalles de la colaboración</p><br>"
                    + "    <p>Propouesta: " + tituloPropuesta + "</p><br>"
                    + "    <p>Proponente: " + nombres[1] + "</p><br>"
                    + "    <p>Colaborador: " + nombres[0] + "</p><br>"
                    + "    <p>Monto: " + infoPago.getMonto() + "</p><br><br>"
                    + "</body>"
                    + "</html>";
        }
    }

    public void setHost(String host) 
    {
        this.mailHost = host;
    }

    public void setPort(int port) 
    {
        this.mailPort = port;
    }

    public void setUrlBase(String urlBase) 
    {
        this.urlBase = urlBase;
    }
}
