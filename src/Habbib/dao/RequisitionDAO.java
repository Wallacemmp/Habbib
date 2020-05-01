package Habbib.dao;

import Habbib.connection.BaseDAO;
import Habbib.model.Bed;
import Habbib.model.Patient;
import Habbib.model.Requisition;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RequisitionDAO extends BaseDAO{

    public RequisitionDAO()
    {
        super();
    }
    //TODO (Finished) Testar
    public Requisition addRequisition(Bed bed, Patient patient, Requisition requisition){
        PreparedStatement stmt;
        ResultSet rs;
        requisition.setBed(bed);
        requisition.setPatient(patient);

        try {
            String insert = "INSERT INTO Requisition VALUE (DEFAULT,?,?,?,?)";
            stmt = super.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,requisition.getStatus());
            stmt.setString(2,requisition.getDescription());
            stmt.setInt(3,bed.getId());
            stmt.setInt(4,patient.getId());
            rs = stmt.getGeneratedKeys();

            requisition.setId(rs.getInt(1));

        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Erro ao criar solicitação.\n\n"+ e.getMessage(),"WARNING",JOptionPane.WARNING_MESSAGE);
        }

        return requisition;
    }

}
