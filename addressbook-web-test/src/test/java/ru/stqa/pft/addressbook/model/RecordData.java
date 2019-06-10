package ru.stqa.pft.addressbook.model;

public class RecordData {
    private final String firstname;
    private final String lastname;
    private final String address;
    private final String hometelefon;
    private final String email;
    private final String group;

    public RecordData(String firstname, String lastname, String address, String hometelefon, String email, String group) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.hometelefon = hometelefon;
        this.email = email;
        this.group = group;
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
}
