
package webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para categoria complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="categoria"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="catPadre" type="{http://webServices/}categoria" minOccurs="0"/&gt;
 *         &lt;element name="nombreCategoria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "categoria", propOrder = {
    "catPadre",
    "nombreCategoria"
})
public class Categoria {

    protected Categoria catPadre;
    protected String nombreCategoria;

    /**
     * Obtiene el valor de la propiedad catPadre.
     * 
     * @return
     *     possible object is
     *     {@link Categoria }
     *     
     */
    public Categoria getCatPadre() {
        return catPadre;
    }

    /**
     * Define el valor de la propiedad catPadre.
     * 
     * @param value
     *     allowed object is
     *     {@link Categoria }
     *     
     */
    public void setCatPadre(Categoria value) {
        this.catPadre = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreCategoria.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreCategoria() {
        return nombreCategoria;
    }

    /**
     * Define el valor de la propiedad nombreCategoria.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreCategoria(String value) {
        this.nombreCategoria = value;
    }

}
