
package webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtoUsuario complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtoUsuario"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="nickname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pass" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="apellido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fecha" type="{http://webServices/}localDate" minOccurs="0"/&gt;
 *         &lt;element name="fechaString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="rutaImg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tipoUsr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cantSeguidores" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoUsuario", propOrder = {
    "nickname",
    "pass",
    "nombre",
    "apellido",
    "email",
    "fecha",
    "fechaString",
    "rutaImg",
    "tipoUsr",
    "cantSeguidores"
})
@XmlSeeAlso({
    DtoColaborador.class,
    DtoProponente.class
})
public class DtoUsuario {

    protected String nickname;
    protected String pass;
    protected String nombre;
    protected String apellido;
    protected String email;
    protected LocalDate fecha;
    protected String fechaString;
    protected String rutaImg;
    protected String tipoUsr;
    protected int cantSeguidores;

    /**
     * Gets the value of the nickname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Sets the value of the nickname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNickname(String value) {
        this.nickname = value;
    }

    /**
     * Gets the value of the pass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPass() {
        return pass;
    }

    /**
     * Sets the value of the pass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPass(String value) {
        this.pass = value;
    }

    /**
     * Gets the value of the nombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the value of the nombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Gets the value of the apellido property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Sets the value of the apellido property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellido(String value) {
        this.apellido = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the fecha property.
     * 
     * @return
     *     possible object is
     *     {@link LocalDate }
     *     
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Sets the value of the fecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalDate }
     *     
     */
    public void setFecha(LocalDate value) {
        this.fecha = value;
    }

    /**
     * Gets the value of the fechaString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaString() {
        return fechaString;
    }

    /**
     * Sets the value of the fechaString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaString(String value) {
        this.fechaString = value;
    }

    /**
     * Gets the value of the rutaImg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRutaImg() {
        return rutaImg;
    }

    /**
     * Sets the value of the rutaImg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRutaImg(String value) {
        this.rutaImg = value;
    }

    /**
     * Gets the value of the tipoUsr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoUsr() {
        return tipoUsr;
    }

    /**
     * Sets the value of the tipoUsr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoUsr(String value) {
        this.tipoUsr = value;
    }

    /**
     * Gets the value of the cantSeguidores property.
     * 
     */
    public int getCantSeguidores() {
        return cantSeguidores;
    }

    /**
     * Sets the value of the cantSeguidores property.
     * 
     */
    public void setCantSeguidores(int value) {
        this.cantSeguidores = value;
    }

}
