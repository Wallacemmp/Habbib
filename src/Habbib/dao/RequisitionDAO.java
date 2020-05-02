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
        Patient patient;
        Bed bed;
        Institution bedOwner;

        try{
            requisitions = new ArrayList<>();
            //TODO fazer o join para retornar o paciente com a requisition
            String select = "SELECT DISTINCT p.*, r.*, i.name AS Nome_Fornecedor, b.*" +
                    " FROM Requisition r" +
                    " JOIN Patient p ON r.Id_Patient = p.Id" +
                    " JOIN Bed b ON r.Id_Bed = b.Id" +
                    " JOIN Institution i ON i.Id = ?";
            stmt = super.connection.prepareStatement(select);
            stmt.setInt(1,institution.getId());
            rs = stmt.executeQuery();

            //Carrega os objetos requisition em seguida insere o OBJETO REQUISITION no ArrayList
            while(rs.next()){

                //TODO Montar objeto paciente
                patient = new Patient();
                patient.setId(rs.getInt("p.Id"));
                patient.setFirstName(rs.getString("FirstName"));
                patient.setLastName(rs.getString("LastName"));
                patient.setCpf(rs.getString("CPF"));
                patient.setDob(rs.getDate("DOB"));
                patient.setGender(rs.getString("DOB"));
                patient.setCid(rs.getString("CID"));

                //TODO Montar objeto bed
                bed = new Bed();
                bedOwner = new Institution();
                bed.setId(rs.getInt("b.Id"));
                bed.setType(rs.getString("Type"));
                bed.setStatus(rs.getString("b.Status"));
                bedOwner.setName(rs.getString("Nome_Fornecedor"));
                bedOwner.setId(rs.getInt("b.Id_Institution"));
                bed.setInstitution(bedOwner);
                bed.setInstitution(institution);

                //TODO Montar o objeto Requisition
                requisition = new Requisition();
                requisition.setId(rs.getInt("r.Id"));
                requisition.setStatus(rs.getString("r.Status"));
                requisition.setDescription(rs.getString("Description"));

                //Insere paciente, leito e a institução solicitante
                requisition.setPatient(patient);
                requisition.setBed(bed);
                requisition.setInstitution(institution);
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
    public Requisition addRequisition(Requisition requisition, Institution institution) throws Exception{
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

    public void updateRequisition(Requisition requisition) throws Exception {
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
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
