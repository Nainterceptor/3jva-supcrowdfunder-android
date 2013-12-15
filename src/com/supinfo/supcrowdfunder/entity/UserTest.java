package com.supinfo.supcrowdfunder.entity;

import java.sql.Timestamp;

/**
 * Created by Robin on 15/12/13.
 */
public class UserTest {
    protected Long id;
    protected String email;
    protected String password;
    protected String salt;
    protected String firstname;
    protected String lastname;
    protected String address;
    protected String zipCode;
    protected String city;
    protected Boolean sex;
    protected Boolean admin;
    protected Timestamp createdAt;

    public Long getId() {
        return id;
    }

    public UserTest setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserTest setEmail(String email) {
        this.email = email.toLowerCase();
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserTest setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getSalt() {
        return salt;
    }

    public UserTest setSalt(String salt) {
        this.salt = salt;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public UserTest setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public UserTest setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public UserTest setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getZipCode() {
        return zipCode;
    }

    public UserTest setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public String getCity() {
        return city;
    }

    public UserTest setCity(String city) {
        this.city = city;
        return this;
    }

    public Boolean getSex() {
        return sex;
    }

    public UserTest setSex(Boolean sex) {
        this.sex = sex;
        return this;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public UserTest setAdmin(Boolean admin) {
        this.admin = admin;
        return this;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
