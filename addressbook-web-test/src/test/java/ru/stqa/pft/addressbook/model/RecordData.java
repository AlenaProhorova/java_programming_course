package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;
import java.util.Objects;

@XStreamAlias("record")

public class RecordData {
    @XStreamOmitField
    private  int id = Integer.MAX_VALUE;
    @Expose
    private String firstname;
    @Expose
    private String lastname;
    @Expose
    private String address;
    private String address2;
    @Expose
    private String homephone;
    private String workphone;
    private String mobilephone;
    @Expose
    private String email;
    private String email2;
    private String email3;
    @Expose
    private String group;
    private String allphones;
    private String allemails;

    public File getPhoto() {
        return photo;
    }

    public RecordData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }
    @Expose
    private File photo;

    public String getAlladdress() {
        return alladdress;
    }

    public RecordData withAlladdress(String alladdress) {
        this.alladdress = alladdress;
        return this;
    }

    private String alladdress;

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

    public RecordData withGroup(String group) {
        this.group = group;
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

    public String getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "RecordData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

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
        return Objects.hash(id, firstname, lastname);
    }
}
