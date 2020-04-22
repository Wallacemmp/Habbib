package Habbib.model;

public class Address {
    private int id;
    private int zipCode;
    private int number;
    private String complement;
    private String neighborhood;
    private String city;
    private String uf;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUF() {
        return uf;
    }

    public void setUF(String UF) {
        this.uf = UF;
    }
}
