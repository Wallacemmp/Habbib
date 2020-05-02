package Habbib.dao;

import Habbib.connection.BaseDAO;
import Habbib.model.Patient;


import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PatientDAO extends BaseDAO {
    //TODO Fazer o CRUD
    public PatientDAO()
    {
        super();
    }

    public void addPatient(Patient patient)
    {
        PreparedStatement stmt;
        ResultSet rs;
        try
        {
            String insert = "INSERT INTO Patient VALUE(DEFAULT, ?, ?, ?, ?, ?, ?)";

            stmt = super.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, patient.getFirstName());
            stmt.setString(2, patient.getLastName());
            stmt.setString(3, patient.getCpf());
            stmt.setDate(4, new java.sql.Date(patient.getDob().getDate()));
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
            JOptionPane.showMessageDialog(null,"Erro ao adicionar paciente.\n\n"+ e.getMessage(),"WARNING",JOptionPane.WARNING_MESSAGE);
        }
    }

}
