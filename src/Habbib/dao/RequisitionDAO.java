package Habbib.dao;

import Habbib.connection.BaseDAO;
import Habbib.model.Bed;
import Habbib.model.Institution;
import Habbib.model.Patient;
import Habbib.model.Requisition;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class RequisitionDAO extends BaseDAO{

    public RequisitionDAO()
    {
        super();
    }
    //TODO (Finished) Testar
    public ArrayList<Requisition> getRequisitionsByInstitution(Institution institution) throws Exception {
        PreparedStatement stmt;
        ResultSet rs;
        ArrayList<Requisition> requisitions;
        Requisition requisition;

        try{
            requisitions = new ArrayList<>();
            String select = "SELECT * FROM Requisition WHERE Id_Institution = ?";
            stmt = super.connection.prepareStatement(select);
            stmt.setInt(1,institution.getId());
            rs = stmt.executeQuery();

            //Carrega os objetos requisition em seguida insere o OBJETO REQUISITION no ArrayList
            while(rs.next()){

                requisition = new Requisition();
                requisition.setId(rs.getInt("Id"));
                requisition.setStatus(rs.getString("Status"));
                requisition.setDescription(rs.getString("Description"));

                requisitions.add(requisition);

            }

            institution.setRequisitions(requisitions);

        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
        // retorno do ArrayList carregado.
        return requisitions;
    }
    //TODO (Finished) Testar
    public Requisition addRequisition(Bed bed, Patient patient, Requisition requisition) throws Exception{
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

        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }

        return requisition;
    }

}
