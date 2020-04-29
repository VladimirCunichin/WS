package model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import java.util.Random;


public class PcPart {


    public PcPart() {

    }

    public PcPart(Integer id, String manufacturer, String name, String type, String price) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.name = name;
        this.type = type;
        this.price = price;
    }
    public PcPart(PcPart part){
        this.id = part.id;
        this.manufacturer = part.manufacturer;
        this.name = part.name;
        this.type = part.type;
        this.price = part.price;
    }
    public PcPart(Integer id){
        this.id = id;
    }
    //@JsonProperty("id")
    private Integer id;

    //@JsonProperty("manufacturer")
    private String manufacturer;


    //@JsonProperty("name")
    private String name;

    //@JsonProperty("type")
    private String type;

    //@JsonProperty("price")
    private String price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }




    public void generateId() {
        Random r = new Random();
        id = r.nextInt(100-19)+20;
    }


}