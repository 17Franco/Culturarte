
package webservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtoCategoria complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="dtoCategoria"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="nombreCategoria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="subcategorias" type="{http://webServices/}dtoCategoria" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="catPadre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="catPadreNodo" type="{http://webServices/}categoria" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoCategoria", propOrder = {
    "nombreCategoria",
    "subcategorias",
    "catPadre",
    "catPadreNodo"
})
public class DtoCategoria {

    protected String nombreCategoria;
    @XmlElement(nillable = true)
    protected List<DtoCategoria> subcategorias;
    protected String catPadre;
    protected Categoria catPadreNodo;

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

    /**
     * Gets the value of the subcategorias property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subcategorias property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubcategorias().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtoCategoria }
     * 
     * 
     */
    public List<DtoCategoria> getSubcategorias() {
        if (subcategorias == null) {
            subcategorias = new ArrayList<DtoCategoria>();
        }
        return this.subcategorias;
    }

    /**
     * Obtiene el valor de la propiedad catPadre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCatPadre() {
        return catPadre;
    }

    /**
     * Define el valor de la propiedad catPadre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCatPadre(String value) {
        this.catPadre = value;
    }

    /**
     * Obtiene el valor de la propiedad catPadreNodo.
     * 
     * @return
     *     possible object is
     *     {@link Categoria }
     *     
     */
    public Categoria getCatPadreNodo() {
        return catPadreNodo;
    }

    /**
     * Define el valor de la propiedad catPadreNodo.
     * 
     * @param value
     *     allowed object is
     *     {@link Categoria }
     *     
     */
    public void setCatPadreNodo(Categoria value) {
        this.catPadreNodo = value;
    }

}
