package com.supinfo.supcrowdfunder.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Robin on 15/12/13.
 */
public class Category implements Parcelable {
    protected Long id;
    protected String name;

    @Override
    public int describeContents() {
        //On renvoie 0, car notre classe ne contient pas de FileDescriptor
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // On ajoute les objets dans l'ordre dans lequel on les a déclarés
        dest.writeLong(id);
        dest.writeString(name);
    }

    public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel source) {
            return new Category(source);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public Category(Parcel in) {
        id = in.readLong();
        name = in.readString();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category setName(String name) {
        this.name = name;
        return this;
    }

}
