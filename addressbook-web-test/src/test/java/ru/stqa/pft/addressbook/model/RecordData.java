package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class RecordData {
    private  int id;
    private final String firstname;
    private final String lastname;
    private final String address;
    private final String hometelefon;
    private final String email;
    private final String group;

    public RecordData(String firstname, String lastname, String address, String hometelefon, String email, String group) {
        this.id = Integer.MAX_VALUE;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.hometelefon = hometelefon;
        this.email = email;
        this.group = group;
    }


    public RecordData(int id, String firstname, String lastname, String address, String hometelefon, String email, String group) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.hometelefon = hometelefon;
        this.email = email;
        this.group = group;
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
