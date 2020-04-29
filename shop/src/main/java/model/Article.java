package model;

import java.util.ArrayList;
import java.util.List;

public class Article {
    private String id;
    private String name;
    private String description;

    public List<PcPart> getParts() {
        return parts;
    }

    private List<PcPart> parts;

    public void setParts(List<PcPart> parts) {
        this.parts = parts;
    }

    public void addParts(PcPart part) {
        if(parts == null) {
            parts = new ArrayList();
        }
        parts.add(part);
    }

    public Article(String id, String name, String description, List<PcPart> parts) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.parts = parts;
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
