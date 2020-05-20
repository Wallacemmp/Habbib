package Habbib.dao;

import Habbib.connection.BaseDAO;
import Habbib.model.Patient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PatientDAO extends BaseDAO {

    public PatientDAO() throws Exception {
        super();
    }

    public Patient addPatient(Patient patient) throws Exception {
        PreparedStatement stmt;
        ResultSet rs;
        try {

            String insert = "INSERT INTO Patient VALUE (DEFAULT, ?, ?, ?, ?, ?, ?)";

            stmt = super.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, patient.getFirstName());
            stmt.setString(2, patient.getLastName());
            stmt.setString(3, patient.getCpf());
            stmt.setDate(4, patient.getDob());
            stmt.setString(5, patient.getGender());
            stmt.setString(6, patient.getCid());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();

            if(rs.next())
            {
                patient.setId(rs.getInt(1));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw e;
        }
        return patient;
    }

}
