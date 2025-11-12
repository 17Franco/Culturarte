
package webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtoColaboracion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtoColaboracion"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="tipoRetorno" type="{http://webServices/}tipoRetorno" minOccurs="0"/&gt;
 *         &lt;element name="monto" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="imgDePropuesta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="colaborador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="propuesta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="creado" type="{http://webServices/}localDate" minOccurs="0"/&gt;
 *         &lt;element name="creadoString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="datosPago" type="{http://webServices/}pago" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoColaboracion", propOrder = {
    "tipoRetorno",
    "monto",
    "imgDePropuesta",
    "colaborador",
    "propuesta",
    "creado",
    "creadoString",
    "datosPago",
    "id"
})
public class DtoColaboracion {

    @XmlSchemaType(name = "string")
    protected TipoRetorno tipoRetorno;
    protected int monto;
    protected String imgDePropuesta;
    protected String colaborador;
    protected String propuesta;
    protected LocalDate creado;
    protected String creadoString;
    protected Pago datosPago;
    protected Long id;

    /**
     * Gets the value of the tipoRetorno property.
     * 
     * @return
     *     possible object is
     *     {@link TipoRetorno }
     *     
     */
    public TipoRetorno getTipoRetorno() {
        return tipoRetorno;
    }

    /**
     * Sets the value of the tipoRetorno property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoRetorno }
     *     
     */
    public void setTipoRetorno(TipoRetorno value) {
        this.tipoRetorno = value;
    }

    /**
     * Gets the value of the monto property.
     * 
     */
    public int getMonto() {
        return monto;
    }

    /**
     * Sets the value of the monto property.
     * 
     */
    public void setMonto(int value) {
        this.monto = value;
    }

    /**
     * Gets the value of the imgDePropuesta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImgDePropuesta() {
        return imgDePropuesta;
    }

    /**
     * Sets the value of the imgDePropuesta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImgDePropuesta(String value) {
        this.imgDePropuesta = value;
    }

    /**
     * Gets the value of the colaborador property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColaborador() {
        return colaborador;
    }

    /**
     * Sets the value of the colaborador property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColaborador(String value) {
        this.colaborador = value;
    }

    /**
     * Gets the value of the propuesta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropuesta() {
        return propuesta;
    }

    /**
     * Sets the value of the propuesta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropuesta(String value) {
        this.propuesta = value;
    }

    /**
     * Gets the value of the creado property.
     * 
     * @return
     *     possible object is
     *     {@link LocalDate }
     *     
     */
    public LocalDate getCreado() {
        return creado;
    }

    /**
     * Sets the value of the creado property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalDate }
     *     
     */
    public void setCreado(LocalDate value) {
        this.creado = value;
    }

    /**
     * Gets the value of the creadoString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreadoString() {
        return creadoString;
    }

    /**
     * Sets the value of the creadoString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreadoString(String value) {
        this.creadoString = value;
    }

    /**
     * Gets the value of the datosPago property.
     * 
     * @return
     *     possible object is
     *     {@link Pago }
     *     
     */
    public Pago getDatosPago() {
        return datosPago;
    }

    /**
     * Sets the value of the datosPago property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pago }
     *     
     */
    public void setDatosPago(Pago value) {
        this.datosPago = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

}
