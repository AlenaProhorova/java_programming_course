package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class RecordData {
    private  int id = Integer.MAX_VALUE;
    private String firstname;
    private String lastname;
    private String address;
    private String hometelefon;
    private String email;
    private String group;

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

    public RecordData withHometelefon(String hometelefon) {
        this.hometelefon = hometelefon;
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


    public int getId() {
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

    public String getHometelefon() {
        return hometelefon;
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
        RecordData recordData = (RecordData) o;
        return Objects.equals(firstname,  recordData.firstname) &&
                Objects.equals(lastname,  recordData.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname);
    }
}
