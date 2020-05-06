package model;

import java.util.ArrayList;
import java.util.List;

public class Article {
    private String id;
    private String name;
    private String description;
    private long partId;

    public long getPartId() {
        return partId;
    }

    public void setPartId(long partId) {
        this.partId = partId;
    }




    public Article(String id, String name, String description, long partId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.partId = partId;
    }

    public Article(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Article() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
