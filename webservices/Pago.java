
package webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for pago complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
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
     * Gets the value of the dato1 property.
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
     * Sets the value of the dato1 property.
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
     * Gets the value of the dato2 property.
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
     * Sets the value of the dato2 property.
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
     * Gets the value of the dato3 property.
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
     * Sets the value of the dato3 property.
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
     * Gets the value of the dato4 property.
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
     * Sets the value of the dato4 property.
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
     * Gets the value of the dato5 property.
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
     * Sets the value of the dato5 property.
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
     * Gets the value of the fechaPago property.
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
     * Sets the value of the fechaPago property.
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
     * Gets the value of the formaPago property.
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
     * Sets the value of the formaPago property.
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

}
