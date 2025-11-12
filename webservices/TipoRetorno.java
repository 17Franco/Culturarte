
package webservices;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tipoRetorno.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="tipoRetorno"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="EntradaGratis"/&gt;
 *     &lt;enumeration value="PorcentajeGanancia"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "tipoRetorno")
@XmlEnum
public enum TipoRetorno {

    @XmlEnumValue("EntradaGratis")
    ENTRADA_GRATIS("EntradaGratis"),
    @XmlEnumValue("PorcentajeGanancia")
    PORCENTAJE_GANANCIA("PorcentajeGanancia");
    private final String value;

    TipoRetorno(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TipoRetorno fromValue(String v) {
        for (TipoRetorno c: TipoRetorno.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
