package com.supinfo.supcrowdfunder.entity;

/**
 * Created by Robin on 15/12/13.
 */
public class Categorie {
    protected Long id;
    protected String name;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Categorie setName(String name) {
        this.name = name;
        return this;
    }
}
