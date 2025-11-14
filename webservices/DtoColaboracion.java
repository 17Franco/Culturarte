
package webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtoColaboracion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
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
     * Obtiene el valor de la propiedad tipoRetorno.
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
     * Define el valor de la propiedad tipoRetorno.
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
     * Obtiene el valor de la propiedad monto.
     * 
     */
    public int getMonto() {
        return monto;
    }

    /**
     * Define el valor de la propiedad monto.
     * 
     */
    public void setMonto(int value) {
        this.monto = value;
    }

    /**
     * Obtiene el valor de la propiedad imgDePropuesta.
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
     * Define el valor de la propiedad imgDePropuesta.
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
     * Obtiene el valor de la propiedad colaborador.
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
     * Define el valor de la propiedad colaborador.
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
     * Obtiene el valor de la propiedad propuesta.
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
     * Define el valor de la propiedad propuesta.
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
     * Obtiene el valor de la propiedad creado.
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
     * Define el valor de la propiedad creado.
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
     * Obtiene el valor de la propiedad creadoString.
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
     * Define el valor de la propiedad creadoString.
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
     * Obtiene el valor de la propiedad datosPago.
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
     * Define el valor de la propiedad datosPago.
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
     * Obtiene el valor de la propiedad id.
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
     * Define el valor de la propiedad id.
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
