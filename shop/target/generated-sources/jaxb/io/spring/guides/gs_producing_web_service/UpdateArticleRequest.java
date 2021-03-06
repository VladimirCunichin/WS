//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.05.27 at 10:56:13 AM EEST 
//


package io.spring.guides.gs_producing_web_service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="newid" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="partId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="pcpartid" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="manufacturer" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="pcpartname" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "id",
    "newid",
    "name",
    "description",
    "partId",
    "pcpartid",
    "manufacturer",
    "pcpartname",
    "price",
    "type"
})
@XmlRootElement(name = "updateArticleRequest")
public class UpdateArticleRequest {

    @XmlElement(required = true)
    protected String id;
    @XmlElement(required = true)
    protected String newid;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String description;
    protected long partId;
    protected long pcpartid;
    @XmlElement(required = true)
    protected String manufacturer;
    @XmlElement(required = true)
    protected String pcpartname;
    @XmlElement(required = true)
    protected String price;
    @XmlElement(required = true)
    protected String type;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the newid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewid() {
        return newid;
    }

    /**
     * Sets the value of the newid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewid(String value) {
        this.newid = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the partId property.
     * 
     */
    public long getPartId() {
        return partId;
    }

    /**
     * Sets the value of the partId property.
     * 
     */
    public void setPartId(long value) {
        this.partId = value;
    }

    /**
     * Gets the value of the pcpartid property.
     * 
     */
    public long getPcpartid() {
        return pcpartid;
    }

    /**
     * Sets the value of the pcpartid property.
     * 
     */
    public void setPcpartid(long value) {
        this.pcpartid = value;
    }

    /**
     * Gets the value of the manufacturer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Sets the value of the manufacturer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setManufacturer(String value) {
        this.manufacturer = value;
    }

    /**
     * Gets the value of the pcpartname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPcpartname() {
        return pcpartname;
    }

    /**
     * Sets the value of the pcpartname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPcpartname(String value) {
        this.pcpartname = value;
    }

    /**
     * Gets the value of the price property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrice(String value) {
        this.price = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

}
