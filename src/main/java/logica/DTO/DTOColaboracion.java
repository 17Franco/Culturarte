
package logica.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.time.LocalDate;
import java.time.LocalDateTime;
import logica.Colaboracion.Colaboracion;
import logica.Colaboracion.Pago;

@XmlRootElement(name = "DTOColaboracion") 
@XmlAccessorType(XmlAccessType.FIELD)
public class DTOColaboracion {
        private TipoRetorno tipoRetorno;

        private int monto;
        private String imgDePropuesta;
        private String  colaborador;
        private String propuesta;
        @XmlTransient
        @JsonIgnore
        private DTOPropuesta propuestaP;// veo inesesarios tener los punteros en el dtoColaboracion
        @XmlTransient
        private DTOColaborador colaboradorP;
        private LocalDate creado;
        private String creadoString;
        private Pago datosPago;
    
    private Long id;

public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public boolean equals(DTOColaboracion obj) {
        if (this.monto != obj.monto) {
            return false;
        }
        if (this.tipoRetorno != obj.tipoRetorno) {
            return false;
        }
        if (!this.colaborador.equals(obj.colaborador)) {
            return false;
        }
        if (this.propuesta.equals(obj.propuesta) ) {
            return false;
        }
        return this.creado.equals(obj.creado);
    }
    public DTOColaboracion(){}
    
    
    public DTOColaboracion(TipoRetorno tipoRetorno, int monto, String colaborador, String propuesta, LocalDate creado, Pago pago) {
        this.tipoRetorno = tipoRetorno;
        this.monto = monto;
        this.colaborador = colaborador;
        this.propuesta = propuesta;
        this.creado = creado;
        this.datosPago = pago;
    }

    public void setCreadoString(String creadoString) {
        this.creadoString = creadoString;
    }

    public String getCreadoString() {
        return creadoString;
    }
    
    
    public DTOColaboracion(TipoRetorno tipoRetorno, int monto, String colaborador, String propuesta, LocalDate creado) {
        this.tipoRetorno = tipoRetorno;
        this.monto = monto;
        this.colaborador = colaborador;
        this.propuesta = propuesta;
        this.creado = creado;
    }
    
    public DTOColaboracion(TipoRetorno tipoRetorno, int monto, String colaborador, String propuesta, LocalDate creado, DTOColaborador colaboradorP, DTOPropuesta propuestaP) 
    {
        this.tipoRetorno = tipoRetorno;
        this.monto = monto;
        this.colaborador = colaborador;
        this.propuesta = propuesta;
        this.colaboradorP = colaboradorP;
        this.propuestaP = propuestaP;
        this.creado = creado;
    }
    
     public DTOColaboracion(Colaboracion colaboracion){
         id=colaboracion.getId();
         tipoRetorno=colaboracion.getTipoRetorno();
         monto=colaboracion.getMonto();
         creado=colaboracion.getCreado();
         creadoString=colaboracion.getCreado().toString();
         colaborador=colaboracion.getColaborador().getNickname();
         propuesta=colaboracion.getPropuesta().getTitulo();
         imgDePropuesta=colaboracion.getPropuesta().getImagen();
         datosPago = colaboracion.getDatosPago();
     }

     
    public LocalDate getCreado() {
        return creado;
    }

    public String getImgDePropuesta() {
        return imgDePropuesta;
    }

    
    public void setCreado(LocalDate creado) {
        this.creado = creado;
    }


    public String getPropuesta() {
        return propuesta;
    }

    public void setPropuesta(String propuesta) {
        this.propuesta = propuesta;
    }


   
    public String getColaborador() {
        return colaborador;
    }

    
    public void setColaborador(String  colaborador) {
        this.colaborador = colaborador;
    }


    public int getMonto() {
        return monto;
    }

  
    public void setMonto(int monto) {
        this.monto = monto;
    }


    public TipoRetorno getTipoRetorno() {
        return tipoRetorno;
    }

    public Pago getDatosPago()
    {
        return datosPago;
    }
    
    public void setTipoRetorno(TipoRetorno tipoRetorno) {
        this.tipoRetorno = tipoRetorno;
    }
    
    public void setPropuestaP (DTOPropuesta propuestaP)
    {
        this.propuestaP = propuestaP;
    }
    
    public void setColaboradorP (DTOColaborador colaboradorP)
    {
       this.colaboradorP = colaboradorP;
    }
    
    public DTOPropuesta getPropuestaP()
    {
        return propuestaP;
    }
    
    public DTOColaborador getColaboradorP()
    {
       return colaboradorP;
    }
    
    public void setDatosPago(Pago input)
    {
        datosPago = input;
    }
    
}
