package Habbib.model;

public class Requisition {
    private int id;
    private String status;
    private String description;
    private Bed bed;
    private Patient patient;
    private Institution destinationInstitution;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bed getBed() {
        return bed;
    }

    public void setBed(Bed bed) {
        this.bed = bed;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Institution getDestinationInstitution() {
        return destinationInstitution;
    }

    public void setDestinationInstitution(Institution destinationInstitution) {
        this.destinationInstitution = destinationInstitution;
    }
}
