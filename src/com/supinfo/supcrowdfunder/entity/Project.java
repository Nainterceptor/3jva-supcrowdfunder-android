package com.supinfo.supcrowdfunder.entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.supinfo.supcrowdfunder.util.TextHelper;

/**
 * Created by Robin on 15/12/13.
 */
public class Project implements Parcelable {
    protected Long id;
    protected String name;
    protected String details;
    protected User user;
    protected Long needCredits;
    protected String term;
    protected Category category;
    protected String createdAt;

    public Project() {
        id = null;
        name = null;
        details = null;
        user = null;
        needCredits = null;
        term = null;
        category = null;
        createdAt = null;
    }

    public Project(Long cId, String cName, String cDetails, User cUser, Long cNeedCredits, String cTerm, Category cCategory,
                   String cCreatedAt) {
        id = cId;
        name = cName;
        details = cDetails;
        user = cUser;
        needCredits = cNeedCredits;
        term = cTerm;
        category = cCategory;
        createdAt = cCreatedAt.substring(0, 10);
    }

    @Override
    public int describeContents() {
        //On renvoie 0, car notre classe ne contient pas de FileDescriptor
        return 0;
    }

    public Long percentToEnd(Long resp) {
        return resp * 100L / getNeedCredits();
    }

    public String shortDescribe() {
        return this.shortDescribe(50);
    }

    public String shortDescribe(int n) {
        return (TextHelper.truncateAfterWords(n, details));
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // On ajoute les objets dans l'ordre dans lequel on les a déclarés
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(details);
        dest.writeParcelable(user, flags);
        dest.writeLong(needCredits);
        dest.writeString(term);
        dest.writeParcelable(category, flags);
        dest.writeString(createdAt);
    }

    public static final Parcelable.Creator<Project> CREATOR = new Parcelable.Creator<Project>() {
        @Override
        public Project createFromParcel(Parcel source) {
            return new Project(source);
        }

        @Override
        public Project[] newArray(int size) {
            return new Project[size];
        }
    };

    public Project(Parcel in) {
        id = in.readLong();
        name = in.readString();
        details = in.readString();
        user = in.readParcelable(getClass().getClassLoader());
        needCredits = in.readLong();
        term = in.readString();
        category = in.readParcelable(getClass().getClassLoader());
        createdAt = in.readString();
    }

    public Project setCategory(Category category) {
        this.category = category;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Long getId() {
        return id;
    }

    public Project setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Project setName(String name) {
        this.name = name;
        return this;
    }

    public String getDetails() {
        return details;
    }

    public Project setDetails(String details) {
        this.details = details;
        return this;
    }

    public Long getNeedCredits() {
        return needCredits;
    }

    public Project setNeedCredits(Long needCredits) {
        this.needCredits = needCredits;
        return this;
    }

    public String getTerm() {
        return term.substring(0, 10);
    }

    public Project setTerm(String term) {
        this.term = term.substring(0, 10);
        return this;
    }

    public User getUser() {
        return user;
    }

    public Project setUser(User user) {
        this.user = user;
        return this;
    }

    public String getCreatedAt() {
        return createdAt.substring(0, 10);
    }

    public Project setCreatedAt(String createdAt) {
        this.createdAt = createdAt.substring(0, 10);
        return this;
    }
}
