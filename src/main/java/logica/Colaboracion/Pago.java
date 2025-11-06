package logica.Colaboracion;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import logica.DTO.DTOPago;

@Entity
public class Pago 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int monto;

    @Column(nullable = false)
    private String formaPago; // "tarjeta", "transferencia" o "paypal"

    private LocalDateTime fechaPago;


    //Uso estos String genéricos y los adapto a cada circunstancia dependiendo de la variable "formaPago" (arriba).
    
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

    public Pago() 
    {
    }
    
    public Pago(DTOPago input) 
    {
        //Para saber como funciona fijarse en el constructor de DTOPago que tiene toda la info
        
        this.monto = input.getMonto();
        this.formaPago = input.getFormaPago().toLowerCase();    //Lo agrego acá primero para poder usarlo más fácil
        this.fechaPago = input.getFechaPago();

        if (this.formaPago.equals("tarjeta")) 
        {
            this.dato1 = input.getDato1(); // nombreTitularTarjeta
            this.dato2 = input.getDato2(); // numeroTarjeta
            this.dato3 = input.getDato3(); // nombreTarjeta
            this.dato4 = input.getDato4(); // vencimientoTarjeta
            this.dato5 = input.getDato5(); // cvc
            
        } 
        
        if (this.formaPago.equals("transferencia")) 
        {
            this.dato1 = input.getDato1(); // nombreTitularCuenta
            this.dato2 = input.getDato2(); // numeroCuentaBancaria
            this.dato3 = input.getDato3(); // nombreBanco
            this.dato4 = "";
            this.dato5 = "";
            
        }
        
        if (this.formaPago.equals("paypal")) 
        {
            this.dato1 = input.getDato1(); // nombreTitularPaypal
            this.dato2 = input.getDato2(); // numeroCuentaPaypal
            this.dato3 = "";
            this.dato4 = "";
            this.dato5 = "";
        }
        
    }



    public Long getId() 
    {
        return id;
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
