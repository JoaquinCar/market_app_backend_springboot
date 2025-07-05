package com.tecdesoftware.market_app.domain;
import com.fasterxml.jackson.annotation.JsonProperty;
public class Category {

    @JsonProperty("idCategoria")
    private Integer categoryId;

    @JsonProperty("descripcion")
    private String description;

    @JsonProperty("estado")
    private Boolean active;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}