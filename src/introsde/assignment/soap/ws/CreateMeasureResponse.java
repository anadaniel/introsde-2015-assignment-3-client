
package introsde.assignment.soap.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createMeasureResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createMeasureResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="createMeasure" type="{http://ws.soap.assignment.introsde/}measure" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createMeasureResponse", propOrder = {
    "createMeasure"
})
public class CreateMeasureResponse {

    protected Measure createMeasure;

    /**
     * Gets the value of the createMeasure property.
     * 
     * @return
     *     possible object is
     *     {@link Measure }
     *     
     */
    public Measure getCreateMeasure() {
        return createMeasure;
    }

    /**
     * Sets the value of the createMeasure property.
     * 
     * @param value
     *     allowed object is
     *     {@link Measure }
     *     
     */
    public void setCreateMeasure(Measure value) {
        this.createMeasure = value;
    }

}
