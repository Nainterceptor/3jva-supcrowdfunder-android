package com.supinfo.supcrowdfunder.entity;

import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by Robin on 15/12/13.
 */
public class ProjectTest {
    protected Long id;
    protected String name;
    protected String details;
    protected UserTest user;
    protected Long needCredits;
    protected Timestamp term;
    protected Categorie categories;
    protected Timestamp createdAt;

    public ProjectTest setCategories(Categorie categories) {
        this.categories = categories;
        return this;
    }

    public Categorie getCategories() {
        return categories;
    }

    public Long getId() {
        return id;
    }

    public ProjectTest setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProjectTest setName(String name) {
        this.name = name;
        return this;
    }

    public String getDetails() {
        return details;
    }

    public ProjectTest setDetails(String details) {
        this.details = details;
        return this;
    }

    public Long getNeedCredits() {
        return needCredits;
    }

    public ProjectTest setNeedCredits(Long needCredits) {
        this.needCredits = needCredits;
        return this;
    }

    public Timestamp getTerm() {
        return term;
    }

    public ProjectTest setTerm(Timestamp term) {
        this.term = term;
        return this;
    }

    public UserTest getUser() {
        return user;
    }

    public ProjectTest setUser(UserTest user) {
        this.user = user;
        return this;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public ProjectTest setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
        return this;
    }
}
