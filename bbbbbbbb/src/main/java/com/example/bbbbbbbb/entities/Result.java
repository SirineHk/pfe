package com.example.bbbbbbbb.entities;
import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.Map;

@XmlRootElement(name = "result")
@XmlAccessorType(XmlAccessType.FIELD)
public class Result {
    private String action;

    private String status;
    private Map<String, List<Produit>> list;

    private List<Renfort> renforts;
    private List<Option> options;

    public List<Renfort> getRenforts() {
        return renforts;
    }

    public void setRenforts(List<Renfort> renforts) {
        this.renforts = renforts;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, List<Produit>> getList() {
        return list;
    }

    public void setList(Map<String, List<Produit>> list) {
        this.list = list;
    }

    // Getters and setters
}
