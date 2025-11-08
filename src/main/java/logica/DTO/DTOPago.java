package logica.DTO;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

/**
 *
 * @author klaas
 */

@XmlRootElement(name = "DTOPago") 
@XmlAccessorType(XmlAccessType.FIELD)
public class DTOPago 
{

    private Long id;
    private int monto;
    private String formaPago;   // "tarjeta", "transferencia" o "paypal"
    private LocalDateTime fechaPago;
    
    //Uso estos String genéricos y los adapto a cada circunstancia dependiendo de la variable "formaPago" (arriba).
    //Así no queda con 300 variables sin uso por cada método de pago
    
    //INFO para el que caiga acá:
    
    //dato1 se usa para: nombreTitularTarjeta , nombreTitularCuenta o nombreTitularPaypal
    //dato2 se usa para: numeroTarjeta , numeroCuentaBancaria o numeroCuentaPaypal
    //dato3 se usa para: nombreTarjeta o nombreBanco
    //dato4 se usa para: vencimientoTarjeta
    //dato5 se usa para: cvc
    
    private String dato1;
    private String dato2;
    private String dato3;
    private String dato4;
    private String dato5;

    public DTOPago(){}
    
    
    public DTOPago(int monto, String formaPago, LocalDateTime fechaPago, String dato1, String dato2, String dato3, String dato4, String dato5) 
    {
        //Acá también lo dejo con la info de cada uno:
        
        this.monto = monto;
        this.formaPago = formaPago;
        this.fechaPago = fechaPago;

        if (formaPago.equals("tarjeta")) 
        {
            // dato1 = nombreTitularTarjeta
            // dato2 = numeroTarjeta
            // dato3 = nombreTarjeta
            // dato4 = vencimientoTarjeta
            // dato5 = cvc
            
            this.dato1 = dato1;
            this.dato2 = dato2;
            this.dato3 = dato3;
            this.dato4 = dato4;
            this.dato5 = dato5;   
        }
        
        if (formaPago.equals("transferencia")) 
        {
            // dato1 = nombreTitularCuenta
            // dato2 = numeroCuentaBancaria
            // dato3 = nombreBanco
            
            this.dato1 = dato1;
            this.dato2 = dato2;
            this.dato3 = dato3;
            this.dato4 = "";
            this.dato5 = "";    
        }
        
        if (formaPago.equals("paypal")) 
        {
            // dato1 = nombreTitularPaypal
            // dato2 = numeroCuentaPaypal
            
            this.dato1 = dato1;
            this.dato2 = dato2;
            this.dato3 = "";
            this.dato4 = "";
            this.dato5 = "";
        }

    }


    public Long getId() 
    {
        return id;
    }

    public void setId(Long id) 
    {
        this.id = id;
    }

    public int getMonto() 
    {
        return monto;
    }

    public void setMonto(int monto) 
    {
        this.monto = monto;
    }

    public String getFormaPago() 
    {
        return formaPago;
    }

    public void setFormaPago(String formaPago) 
    {
        this.formaPago = formaPago;
    }

    public LocalDateTime getFechaPago()
    {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) 
    {
        this.fechaPago = fechaPago;
    }

    public String getDato1() 
    {
        return dato1;
    }

    public void setDato1(String dato1) 
    {
        this.dato1 = dato1;
    }

    public String getDato2() 
    {
        return dato2;
    }

    public void setDato2(String dato2) 
    {
        this.dato2 = dato2;
    }

    public String getDato3()
    {
        return dato3;
    }

    public void setDato3(String dato3) 
    {
        this.dato3 = dato3;
    }

    public String getDato4() 
    {
        return dato4;
    }

    public void setDato4(String dato4) 
    {
        this.dato4 = dato4;
    }

    public String getDato5() 
    {
        return dato5;
    }

    public void setDato5(String dato5) 
    {
        this.dato5 = dato5;
    }
}
