package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("record")
@Entity
@Table(name = "addressbook")

public class RecordData {
    @XStreamOmitField
    @Id
    @Column(name = "id")
    private  int id = Integer.MAX_VALUE;

    @Expose
    @Column(name = "firstname")
    private String firstname;

    @Expose
    @Column(name = "lastname")
    private String lastname;

    @Expose
    @Column(name = "address")
    @Type(type = "text")
    private String address;

    @Column(name = "address2")
    @Type(type = "text")
    private String address2;

    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String homephone;

    @Column(name = "work")
    @Type(type = "text")
    private String workphone;

    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilephone;

    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String email;

    @Transient
    private String email2;

    @Transient
    private String email3;

 /*   @Expose
    @Transient
    private String group;*/

 @ManyToMany (fetch = FetchType.EAGER)
 @JoinTable (name = "address_in_groups",
         joinColumns = @JoinColumn (name = "id"), inverseJoinColumns = @JoinColumn (name = "group_id"))
 private Set<GroupData> groups = new HashSet<GroupData>();

    @Expose
    @Column(name = "photo")
    @Type(type = "text")
    private String photo;

    @Transient
    private String allphones;

    @Transient
    private String allemails;

    @Transient
    private String alladdress;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecordData that = (RecordData) o;
        return id == that.id &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash
                (id, firstname, lastname);
    }

    public File getPhoto() {
        return new File (photo);
    }

    public RecordData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public String getAlladdress() {
        return alladdress;
    }

    public RecordData withAlladdress(String alladdress) {
        this.alladdress = alladdress;
        return this;
    }


    public String getAddress2() {
        return address2;
    }

    public RecordData withAddress2(String address2) {
        this.address2 = address2;
        return this;
    }

    public String getAllemails() {
        return allemails;
    }

    public RecordData withAllemails(String allemails) {
        this.allemails = allemails;
        return this;
    }




    public String getEmail3() {
        return email3;
    }

    public RecordData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }


    public String getEmail2() {
        return email2;
    }

    public RecordData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public String getAllphones() {
        return allphones;
    }

    public RecordData withAllphones(String allphones) {
        this.allphones = allphones;
        return this;
    }

    public RecordData withId(int id) {
        this.id = id;
        return this;
    }

    public RecordData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public RecordData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public RecordData withAddress(String address) {
        this.address = address;
        return this;
    }

    public RecordData withHomePhone(String homephone) {
        this.homephone = homephone;
        return this;
    }

    public RecordData withWorkPhone(String workphone) {
        this.workphone = workphone;
        return this;
    }

    public RecordData withMobilePhone(String mobilephone) {
        this.mobilephone = mobilephone;
        return this;
    }

    public RecordData withEmail(String email) {
        this.email = email;
        return this;
    }



    public int getId(){
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getHomePhone() {
        return homephone;
    }

    public String getWorkPhone() {
        return workphone;
    }

    public String getMobilePhone() {
        return mobilephone;
    }


    public String getEmail() {
        return email;
    }

    public Groups getGroups() {
        return new Groups(groups);
    }

    @Override
    public String toString() {
        return "RecordData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    public RecordData inGroup(GroupData group) {
        groups.add(group);
        return this;
    }
}
