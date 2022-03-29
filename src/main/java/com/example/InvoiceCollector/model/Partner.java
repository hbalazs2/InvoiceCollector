package com.example.InvoiceCollector.model;

import java.util.Date;

public class Partner {
    private long id;
    private String name;
    private String countryCode;
    private String postalCode;
    private String city;
    private String address;
    private Date connectionDate;

    // constructor without ID
    public Partner(String name, String countryCode, String postalCode, String city, String address, Date connectionDate) {
        this.name = name;
        this.countryCode = countryCode;
        this.postalCode = postalCode;
        this.city = city;
        this.address = address;
        this.connectionDate = connectionDate;
    }

    //constructor with ID
    public Partner(long id, String name, String countryCode, String postalCode, String city, String address, Date connectionDate) {
        this.id = id;
        this.name = name;
        this.countryCode = countryCode;
        this.postalCode = postalCode;
        this.city = city;
        this.address = address;
        this.connectionDate = connectionDate;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public Date getConnectionDate() {
        return connectionDate;
    }

    @Override
    public String toString() {
        return "Partner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", connectionDate=" + connectionDate +
                '}';
    }
}
