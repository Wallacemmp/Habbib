package Habbib.dao;

import Habbib.connection.BaseDAO;
import Habbib.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class RequisitionDAO extends BaseDAO{

    public RequisitionDAO()
    {
        super();
    }

    public ArrayList<Requisition> getInstitutionRequisitions(Institution institution) throws Exception {

        PreparedStatement stmt;
        ResultSet rs;
        ArrayList<Requisition> requisitions = new ArrayList<>();

        try{

            String select = "SELECT p.*, r.*, b.*, destinationI.*, a.*\n" +
                            " FROM Requisition r\n" +
                            " JOIN Institution sourceI ON sourceI.Id = r.Id_Institution\n" +
                            " JOIN Patient p ON r.Id_Patient = p.Id\n" +
                            " JOIN Bed b ON r.Id_Bed = b.Id\n" +
                            " JOIN Institution destinationI ON b.Id_Institution = destinationI.Id\n" +
                            " JOIN Address a ON destinationI.Id_Address = a.Id\n" +
                            " WHERE sourceI.Id = ?";
            stmt = super.connection.prepareStatement(select);
            stmt.setInt(1,institution.getId());
            rs = stmt.executeQuery();

            while(rs.next()){

                Requisition requisition = new Requisition();
                requisition.setId(rs.getInt("r.Id"));
                requisition.setStatus(rs.getString("r.Status"));
                requisition.setDescription(rs.getString("Description"));

                Patient patient = new Patient();
                patient.setId(rs.getInt("p.Id"));
                patient.setFirstName(rs.getString("FirstName"));
                patient.setLastName(rs.getString("LastName"));
                patient.setCpf(rs.getString("CPF"));
                patient.setDob(rs.getDate("DOB"));
                patient.setGender(rs.getString("Gender"));
                patient.setCid(rs.getString("CID"));
                requisition.setPatient(patient);

                    Bed bed = new Bed();
                    bed.setId(rs.getInt("b.Id"));
                    bed.setType(rs.getString("Type"));
                    bed.setStatus(rs.getString("b.Status"));
                    requisition.setBed(bed);

                Institution destinationInstitution = new Institution();
                Address address = new Address();

                destinationInstitution.setId(rs.getInt("Id"));
                destinationInstitution.setName(rs.getString("Name"));
                destinationInstitution.setCnpj(rs.getString("CNPJ"));
                destinationInstitution.setPassword(rs.getString("Password"));
                destinationInstitution.setType(rs.getString("Type"));
                destinationInstitution.setContactNumber(rs.getString("ContactNumber"));

                address.setId(rs.getInt("a.Id"));
                address.setZipCode(rs.getString("ZipCode"));
                address.setAddress(rs.getString("Address"));
                address.setNumber(rs.getInt("AddressNumber"));
                address.setComplement(rs.getString("Complement"));
                address.setNeighborhood(rs.getString("Neighborhood"));
                address.setCity(rs.getString("City"));
                address.setUf(rs.getString("UF"));
                destinationInstitution.setAddress(address);

                //requisition.setRequestingInsitution(institution);
                requisition.setDestinationInstitution(destinationInstitution);

                requisitions.add(requisition);
            }

            institution.setRequisitions(requisitions);

        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
        return requisitions;
    }

    public ArrayList<Institution> getRequestingInstitutions(Institution destinationInstitution) throws Exception {
        PreparedStatement stmt;
        ResultSet rs;
        ArrayList<Institution> institutions = new ArrayList<>();
        ArrayList<Requisition> requisitions = new ArrayList<>();

        try{

            String select = "SELECT sourceI.*, sourceA.*, p.*, r.*, b.*\n" +
                            " FROM Requisition r\n" +
                            " JOIN Institution sourceI ON sourceI.Id = r.Id_Institution\n" +
                            " JOIN Patient p ON r.Id_Patient = p.Id\n" +
                            " JOIN Bed b ON r.Id_Bed = b.Id\n" +
                            " JOIN Institution destinationI ON b.Id_Institution = destinationI.Id\n" +
                            " JOIN Address sourceA ON sourceI.Id_Address = sourceA.Id\n" +
                            " WHERE destinationI.Id = ?";
            stmt = super.connection.prepareStatement(select);
            stmt.setInt(1,destinationInstitution.getId());
            rs = stmt.executeQuery();

            if(rs.next()){Institution institution = new Institution();
                Address address = new Address();

                institution.setId(rs.getInt("sourceI.Id"));
                institution.setName(rs.getString("Name"));
                institution.setCnpj(rs.getString("CNPJ"));
                institution.setPassword(rs.getString("Password"));
                institution.setType(rs.getString("Type"));
                institution.setContactNumber(rs.getString("ContactNumber"));

                address.setId(rs.getInt("sourceA.Id"));
                address.setZipCode(rs.getString("ZipCode"));
                address.setAddress(rs.getString("Address"));
                address.setNumber(rs.getInt("AddressNumber"));
                address.setComplement(rs.getString("Complement"));
                address.setNeighborhood(rs.getString("Neighborhood"));
                address.setCity(rs.getString("City"));
                address.setUf(rs.getString("UF"));
                institution.setAddress(address);

                do {
                    if(institution.getId() != rs.getInt("sourceI.Id")){
                        institution.setRequisitions(requisitions);
                        institutions.add(institution);
                        requisitions = new ArrayList<>();
                        institution = new Institution();
                        address = new Address();

                        institution.setId(rs.getInt("sourceI.Id"));
                        institution.setName(rs.getString("Name"));
                        institution.setCnpj(rs.getString("CNPJ"));
                        institution.setPassword(rs.getString("Password"));
                        institution.setType(rs.getString("Type"));
                        institution.setContactNumber(rs.getString("ContactNumber"));

                        address.setId(rs.getInt("sourceA.Id"));
                        address.setZipCode(rs.getString("ZipCode"));
                        address.setAddress(rs.getString("Address"));
                        address.setNumber(rs.getInt("AddressNumber"));
                        address.setComplement(rs.getString("Complement"));
                        address.setNeighborhood(rs.getString("Neighborhood"));
                        address.setCity(rs.getString("City"));
                        address.setUf(rs.getString("UF"));
                        institution.setAddress(address);
                    }

                    Requisition requisition = new Requisition();
                    requisition.setId(rs.getInt("r.Id"));
                    requisition.setStatus(rs.getString("r.Status"));
                    requisition.setDescription(rs.getString("Description"));

                    Patient patient = new Patient();
                    patient.setId(rs.getInt("p.Id"));
                    patient.setFirstName(rs.getString("FirstName"));
                    patient.setLastName(rs.getString("LastName"));
                    patient.setCpf(rs.getString("CPF"));
                    patient.setDob(rs.getDate("DOB"));
                    patient.setGender(rs.getString("Gender"));
                    patient.setCid(rs.getString("CID"));
                    requisition.setPatient(patient);

                Bed bed = new Bed();
                bed.setId(rs.getInt("b.Id"));
                bed.setType(rs.getString("b.Type"));
                bed.setStatus(rs.getString("b.Status"));
                requisition.setBed(bed);

                    requisition.setDestinationInstitution(destinationInstitution);

                    requisitions.add(requisition);

                }while (rs.next());

                institution.setRequisitions(requisitions);
                institutions.add(institution);
            }


        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
        return institutions;
    }

    public Requisition getRequisitionById(int id) throws Exception {

        PreparedStatement stmt;
        ResultSet rs;
        Requisition requisition = null;
        try {

            String select = "SELECT p.*, r.*, b.*, destinationI.*, a.*\n" +
                    " FROM Requisition r\n" +
                    " JOIN Institution sourceI ON sourceI.Id = Id_Institution AND r.Id = ? " +
                    " JOIN Patient p ON r.Id_Patient = p.Id\n" +
                    " JOIN Bed b ON r.Id_Bed = b.Id\n" +
                    " JOIN Institution destinationI ON b.Id_Institution = destinationI.Id\n" +
                    " JOIN Address a ON destinationI.Id_Address = a.Id";

            stmt = super.connection.prepareStatement(select);
            stmt.setInt(1,id);
            rs = stmt.executeQuery();

            if(rs.next()){

                requisition = new Requisition();
                requisition.setId(rs.getInt("r.Id"));
                requisition.setStatus(rs.getString("r.Status"));
                requisition.setDescription(rs.getString("Description"));

                Patient patient = new Patient();
                patient.setId(rs.getInt("p.Id"));
                patient.setFirstName(rs.getString("FirstName"));
                patient.setLastName(rs.getString("LastName"));
                patient.setCpf(rs.getString("CPF"));
                patient.setDob(rs.getDate("DOB"));
                patient.setGender(rs.getString("Gender"));
                patient.setCid(rs.getString("CID"));
                requisition.setPatient(patient);

                Bed bed = new Bed();
                bed.setId(rs.getInt("b.Id"));
                bed.setType(rs.getString("Type"));
                bed.setStatus(rs.getString("b.Status"));
                requisition.setBed(bed);

                Institution destinationInstitution = new Institution();
                Address address = new Address();

                destinationInstitution.setId(rs.getInt("Id"));
                destinationInstitution.setName(rs.getString("Name"));
                destinationInstitution.setCnpj(rs.getString("CNPJ"));
                destinationInstitution.setPassword(rs.getString("Password"));
                destinationInstitution.setType(rs.getString("Type"));
                destinationInstitution.setContactNumber(rs.getString("ContactNumber"));

                address.setId(rs.getInt("a.Id"));
                address.setZipCode(rs.getString("ZipCode"));
                address.setAddress(rs.getString("Address"));
                address.setNumber(rs.getInt("AddressNumber"));
                address.setComplement(rs.getString("Complement"));
                address.setNeighborhood(rs.getString("Neighborhood"));
                address.setCity(rs.getString("City"));
                address.setUf(rs.getString("UF"));
                destinationInstitution.setAddress(address);

                requisition.setDestinationInstitution(destinationInstitution);

            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
        return requisition;
    }

    public Requisition addRequisition(Requisition requisition, Institution institution) throws Exception{
        PreparedStatement stmt;
        ResultSet rs;

        try {
            String insert = "INSERT INTO Requisition\n" +
                            "    VALUE (DEFAULT,DEFAULT,?,\n" +
                            "           (SELECT bed.Id FROM Bed bed WHERE bed.Id_Institution = ? and bed.Status = 'Disponivel' and bed.Type = ? limit 1),\n" +
                            "           ?,?)";
            stmt = super.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, requisition.getDescription());
            stmt.setInt(2, requisition.getDestinationInstitution().getId());
            stmt.setString(3, requisition.getBed().getType());
            stmt.setInt(4, requisition.getPatient().getId());
            stmt.setInt(5, institution.getId());
            stmt.execute();

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
