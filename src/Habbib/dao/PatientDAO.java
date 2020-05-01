package Habbib.dao;

import Habbib.connection.BaseDAO;
import Habbib.controller.SessionController;
import Habbib.model.Institution;
import Habbib.model.Patient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PatientDAO extends BaseDAO {

    public PatientDAO()
    {
        super();
    }

    public ArrayList<Patient> getPatientByInstitution(Institution institution){
        Patient patient;
        ArrayList<Patient> patients;
        PreparedStatement stmt;
        ResultSet rs;

        try{

            patient = new Patient();
            patients = new ArrayList<Patient>();

            String select = "SELECT * FROM Patient WHERE Id_Institution = ?";
            stmt = super.connection.prepareStatement(select);
            stmt.setInt(1,institution.getId());
            rs = stmt.executeQuery();

            while (rs.next()){
                patient.setId(rs.getInt("Id"));
                patient.setFirstName(rs.getString("FirstName"));
                patient.setLastName(rs.getString("LastName"));
                patient.setCpf(rs.getString("CPF"));
                patient.setDob(rs.getDate("DOB"));
                patient.setGender(rs.getString("Gender"));
                patient.setCid(rs.getString("CID"));
                patient.setInstitution(institution);

                patients.add(patient);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to database", e);
        }

        return patients;
    }
}
