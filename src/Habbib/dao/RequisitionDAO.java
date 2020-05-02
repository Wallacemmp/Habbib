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
    //TODO verificar onde adicionar a instituição
    public Requisition addRequisition(Requisition requisition, Institution institution) {
        PreparedStatement stmt;
        ResultSet rs;

        try {
            String insert = "INSERT INTO Requisition VALUE (DEFAULT,DEFAULT,?,?,?,?)";
            stmt = super.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, requisition.getDescription());
            stmt.setInt(2,requisition.getBed().getId());
            stmt.setInt(3,requisition.getPatient().getId());
            //Instituição que faz a solicitação
            stmt.setInt(4, institution.getId());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();

            if(rs.next())
            {
                requisition.setId(rs.getInt(1));
            }


        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }

        return requisition;
    }

    public void updateRequisition(Requisition requisition)
    {
        PreparedStatement stmt;
        ResultSet rs;

        try
        {
            String update = "UPDATE Requisition SET Status = ? WHERE Id = ?";
            stmt = super.connection.prepareStatement(update);
            stmt.setString(1, requisition.getStatus());
            stmt.setInt(2, requisition.getId());
            stmt.executeUpdate();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Erro ao atualizar a solicitação.\n\n"+ e.getMessage(),"WARNING",JOptionPane.WARNING_MESSAGE);
        }
    }

}
