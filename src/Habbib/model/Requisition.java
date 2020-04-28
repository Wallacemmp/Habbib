package Habbib.model;

public class Requisition {
    private int id;
    private String status;
    private Patient patient;
    private String Description;
    private Institution sourceInsitituion;
    private Bed bed;

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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Bed getBed() {
        return bed;
    }

    public void setBed(Bed bed) {
        this.bed = bed;
    }

    public Institution getSourceInsitituion() {
        return sourceInsitituion;
    }

    public void setSourceInsitituion(Institution sourceInsitituion) {
        this.sourceInsitituion = sourceInsitituion;
    }
}
