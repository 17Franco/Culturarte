
package webservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtoPropuesta complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtoPropuesta"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Titulo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Imagen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Lugar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Fecha" type="{http://webServices/}localDate" minOccurs="0"/&gt;
 *         &lt;element name="FechaPublicacion" type="{http://webServices/}localDate" minOccurs="0"/&gt;
 *         &lt;element name="fechaExpiracion" type="{http://webServices/}localDate" minOccurs="0"/&gt;
 *         &lt;element name="FechaString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FechaPublicacionString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fechaExpiracionString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Precio" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="MontoTotal" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="cat" type="{http://webServices/}dtoCategoria" minOccurs="0"/&gt;
 *         &lt;element name="categoria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="usr" type="{http://webServices/}dtoProponente" minOccurs="0"/&gt;
 *         &lt;element name="EstadoAct" type="{http://webServices/}estado" minOccurs="0"/&gt;
 *         &lt;element name="historialEstados" type="{http://webServices/}dtoRegistroEstado" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="aporte" type="{http://webServices/}dtoColaboracion" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="aporteNick" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="comentarios"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="entry" maxOccurs="unbounded" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoPropuesta", propOrder = {
    "titulo",
    "descripcion",
    "imagen",
    "lugar",
    "fecha",
    "fechaPublicacion",
    "fechaExpiracion",
    "fechaString",
    "fechaPublicacionString",
    "fechaExpiracionString",
    "precio",
    "montoTotal",
    "cat",
    "categoria",
    "usr",
    "estadoAct",
    "historialEstados",
    "aporte",
    "aporteNick",
    "comentarios"
})
public class DtoPropuesta {

    @XmlElement(name = "Titulo")
    protected String titulo;
    @XmlElement(name = "Descripcion")
    protected String descripcion;
    @XmlElement(name = "Imagen")
    protected String imagen;
    @XmlElement(name = "Lugar")
    protected String lugar;
    @XmlElement(name = "Fecha")
    protected LocalDate fecha;
    @XmlElement(name = "FechaPublicacion")
    protected LocalDate fechaPublicacion;
    protected LocalDate fechaExpiracion;
    @XmlElement(name = "FechaString")
    protected String fechaString;
    @XmlElement(name = "FechaPublicacionString")
    protected String fechaPublicacionString;
    protected String fechaExpiracionString;
    @XmlElement(name = "Precio")
    protected int precio;
    @XmlElement(name = "MontoTotal")
    protected int montoTotal;
    protected DtoCategoria cat;
    protected String categoria;
    protected DtoProponente usr;
    @XmlElement(name = "EstadoAct")
    @XmlSchemaType(name = "string")
    protected Estado estadoAct;
    @XmlElement(nillable = true)
    protected List<DtoRegistroEstado> historialEstados;
    @XmlElement(nillable = true)
    protected List<DtoColaboracion> aporte;
    @XmlElement(nillable = true)
    protected List<String> aporteNick;
    @XmlElement(required = true)
    protected DtoPropuesta.Comentarios comentarios;

    /**
     * Gets the value of the titulo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Sets the value of the titulo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitulo(String value) {
        this.titulo = value;
    }

    /**
     * Gets the value of the descripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets the value of the descripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Gets the value of the imagen property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Sets the value of the imagen property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImagen(String value) {
        this.imagen = value;
    }

    /**
     * Gets the value of the lugar property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * Sets the value of the lugar property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLugar(String value) {
        this.lugar = value;
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
     * Gets the value of the fechaPublicacion property.
     * 
     * @return
     *     possible object is
     *     {@link LocalDate }
     *     
     */
    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    /**
     * Sets the value of the fechaPublicacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalDate }
     *     
     */
    public void setFechaPublicacion(LocalDate value) {
        this.fechaPublicacion = value;
    }

    /**
     * Gets the value of the fechaExpiracion property.
     * 
     * @return
     *     possible object is
     *     {@link LocalDate }
     *     
     */
    public LocalDate getFechaExpiracion() {
        return fechaExpiracion;
    }

    /**
     * Sets the value of the fechaExpiracion property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalDate }
     *     
     */
    public void setFechaExpiracion(LocalDate value) {
        this.fechaExpiracion = value;
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
     * Gets the value of the fechaPublicacionString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaPublicacionString() {
        return fechaPublicacionString;
    }

    /**
     * Sets the value of the fechaPublicacionString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaPublicacionString(String value) {
        this.fechaPublicacionString = value;
    }

    /**
     * Gets the value of the fechaExpiracionString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaExpiracionString() {
        return fechaExpiracionString;
    }

    /**
     * Sets the value of the fechaExpiracionString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaExpiracionString(String value) {
        this.fechaExpiracionString = value;
    }

    /**
     * Gets the value of the precio property.
     * 
     */
    public int getPrecio() {
        return precio;
    }

    /**
     * Sets the value of the precio property.
     * 
     */
    public void setPrecio(int value) {
        this.precio = value;
    }

    /**
     * Gets the value of the montoTotal property.
     * 
     */
    public int getMontoTotal() {
        return montoTotal;
    }

    /**
     * Sets the value of the montoTotal property.
     * 
     */
    public void setMontoTotal(int value) {
        this.montoTotal = value;
    }

    /**
     * Gets the value of the cat property.
     * 
     * @return
     *     possible object is
     *     {@link DtoCategoria }
     *     
     */
    public DtoCategoria getCat() {
        return cat;
    }

    /**
     * Sets the value of the cat property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoCategoria }
     *     
     */
    public void setCat(DtoCategoria value) {
        this.cat = value;
    }

    /**
     * Gets the value of the categoria property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Sets the value of the categoria property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoria(String value) {
        this.categoria = value;
    }

    /**
     * Gets the value of the usr property.
     * 
     * @return
     *     possible object is
     *     {@link DtoProponente }
     *     
     */
    public DtoProponente getUsr() {
        return usr;
    }

    /**
     * Sets the value of the usr property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoProponente }
     *     
     */
    public void setUsr(DtoProponente value) {
        this.usr = value;
    }

    /**
     * Gets the value of the estadoAct property.
     * 
     * @return
     *     possible object is
     *     {@link Estado }
     *     
     */
    public Estado getEstadoAct() {
        return estadoAct;
    }

    /**
     * Sets the value of the estadoAct property.
     * 
     * @param value
     *     allowed object is
     *     {@link Estado }
     *     
     */
    public void setEstadoAct(Estado value) {
        this.estadoAct = value;
    }

    /**
     * Gets the value of the historialEstados property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the historialEstados property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHistorialEstados().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtoRegistroEstado }
     * 
     * 
     */
    public List<DtoRegistroEstado> getHistorialEstados() {
        if (historialEstados == null) {
            historialEstados = new ArrayList<DtoRegistroEstado>();
        }
        return this.historialEstados;
    }

    /**
     * Gets the value of the aporte property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aporte property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAporte().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtoColaboracion }
     * 
     * 
     */
    public List<DtoColaboracion> getAporte() {
        if (aporte == null) {
            aporte = new ArrayList<DtoColaboracion>();
        }
        return this.aporte;
    }

    /**
     * Gets the value of the aporteNick property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aporteNick property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAporteNick().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAporteNick() {
        if (aporteNick == null) {
            aporteNick = new ArrayList<String>();
        }
        return this.aporteNick;
    }

    /**
     * Gets the value of the comentarios property.
     * 
     * @return
     *     possible object is
     *     {@link DtoPropuesta.Comentarios }
     *     
     */
    public DtoPropuesta.Comentarios getComentarios() {
        return comentarios;
    }

    /**
     * Sets the value of the comentarios property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoPropuesta.Comentarios }
     *     
     */
    public void setComentarios(DtoPropuesta.Comentarios value) {
        this.comentarios = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="entry" maxOccurs="unbounded" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "entry"
    })
    public static class Comentarios {

        protected List<DtoPropuesta.Comentarios.Entry> entry;

        /**
         * Gets the value of the entry property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the entry property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEntry().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DtoPropuesta.Comentarios.Entry }
         * 
         * 
         */
        public List<DtoPropuesta.Comentarios.Entry> getEntry() {
            if (entry == null) {
                entry = new ArrayList<DtoPropuesta.Comentarios.Entry>();
            }
            return this.entry;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "key",
            "value"
        })
        public static class Entry {

            protected String key;
            protected String value;

            /**
             * Gets the value of the key property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getKey() {
                return key;
            }

            /**
             * Sets the value of the key property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setKey(String value) {
                this.key = value;
            }

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

        }

    }

}
