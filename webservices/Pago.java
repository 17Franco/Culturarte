
package webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para pago complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="pago"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dato1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dato2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dato3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dato4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dato5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fechaPago" type="{http://webServices/}localDateTime" minOccurs="0"/&gt;
 *         &lt;element name="formaPago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="monto" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pago", propOrder = {
    "dato1",
    "dato2",
    "dato3",
    "dato4",
    "dato5",
    "fechaPago",
    "formaPago",
    "monto"
})
public class Pago {

    protected String dato1;
    protected String dato2;
    protected String dato3;
    protected String dato4;
    protected String dato5;
    protected LocalDateTime fechaPago;
    protected String formaPago;
    protected int monto;

    /**
     * Obtiene el valor de la propiedad dato1.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDato1() {
        return dato1;
    }

    /**
     * Define el valor de la propiedad dato1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDato1(String value) {
        this.dato1 = value;
    }

    /**
     * Obtiene el valor de la propiedad dato2.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDato2() {
        return dato2;
    }

    /**
     * Define el valor de la propiedad dato2.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDato2(String value) {
        this.dato2 = value;
    }

    /**
     * Obtiene el valor de la propiedad dato3.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDato3() {
        return dato3;
    }

    /**
     * Define el valor de la propiedad dato3.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDato3(String value) {
        this.dato3 = value;
    }

    /**
     * Obtiene el valor de la propiedad dato4.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDato4() {
        return dato4;
    }

    /**
     * Define el valor de la propiedad dato4.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDato4(String value) {
        this.dato4 = value;
    }

    /**
     * Obtiene el valor de la propiedad dato5.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDato5() {
        return dato5;
    }

    /**
     * Define el valor de la propiedad dato5.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDato5(String value) {
        this.dato5 = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaPago.
     * 
     * @return
     *     possible object is
     *     {@link LocalDateTime }
     *     
     */
    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    /**
     * Define el valor de la propiedad fechaPago.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalDateTime }
     *     
     */
    public void setFechaPago(LocalDateTime value) {
        this.fechaPago = value;
    }

    /**
     * Obtiene el valor de la propiedad formaPago.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormaPago() {
        return formaPago;
    }

    /**
     * Define el valor de la propiedad formaPago.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormaPago(String value) {
        this.formaPago = value;
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

}
