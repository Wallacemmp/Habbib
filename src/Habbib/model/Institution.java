package Habbib.model;

import java.util.ArrayList;

public class Institution {
    private int id;
    private String name;
    private String cnpj;
    private String password;
    private String type;
    private String contactNumber;
    private Address address;
    private ArrayList<Requisition> requisitions;
    private ArrayList<Bed> beds;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ArrayList<Requisition> getRequisitions() {
        return requisitions;
    }

    public void setRequisitions(ArrayList<Requisition> requisitions) {
        this.requisitions = requisitions;
    }

    public ArrayList<Bed> getBeds() {
        return beds;
    }

    public void setBeds(ArrayList<Bed> beds) {
        this.beds = beds;
    }
}
