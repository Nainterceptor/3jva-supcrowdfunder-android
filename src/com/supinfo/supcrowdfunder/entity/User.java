package com.supinfo.supcrowdfunder.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Robin on 15/12/13.
 */
public class User implements Parcelable {
    protected Long id;
    protected String email;
    protected String password;
    protected String salt;
    protected String firstname;
    protected String lastname;
    protected String address;
    protected String zipCode;
    protected String city;
    protected Integer sex;
    protected Integer admin;
    protected String createdAt;

    @Override
    public int describeContents() {
        //On renvoie 0, car notre classe ne contient pas de FileDescriptor
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // On ajoute les objets dans l'ordre dans lequel on les a déclarés
        dest.writeLong(id);
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(salt);
        dest.writeString(firstname);
        dest.writeString(lastname);
        dest.writeString(address);
        dest.writeString(zipCode);
        dest.writeString(city);
        dest.writeInt(sex);
        dest.writeInt(admin);
        dest.writeString(createdAt);
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public User(Parcel in) {
        id = in.readLong();
        email = in.readString();
        password = in.readString();
        salt = in.readString();
        firstname = in.readString();
        lastname = in.readString();
        address = in.readString();
        zipCode = in.readString();
        city = in.readString();
        sex = in.readInt();
        admin = in.readInt();
        createdAt = in.readString();
    }
    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email.toLowerCase();
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getSalt() {
        return salt;
    }

    public User setSalt(String salt) {
        this.salt = salt;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public User setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public User setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getZipCode() {
        return zipCode;
    }

    public User setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public String getCity() {
        return city;
    }

    public User setCity(String city) {
        this.city = city;
        return this;
    }

    public Integer getSex() {
        return sex;
    }

    public User setSex(Integer sex) {
        this.sex = sex;
        return this;
    }

    public Integer getAdmin() {
        return admin;
    }

    public User setAdmin(Integer admin) {
        this.admin = admin;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String  createdAt) {
        this.createdAt = createdAt;
    }
}
