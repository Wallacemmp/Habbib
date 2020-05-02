package Habbib.dao;

import Habbib.connection.BaseDAO;
import Habbib.model.Address;
import Habbib.model.Bed;
import Habbib.model.Institution;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BedDAO extends BaseDAO {

    public BedDAO() {
        super();
    }
    // Método que retorna um ArrayList com todos os leitos presentes em uma instituição.
    public ArrayList<Bed> getBedByInstitution(Institution institution) throws Exception{
        PreparedStatement stmt;
        ResultSet rs;
        ArrayList<Bed> beds;
        Bed bed;

        try{
            beds = new ArrayList<>();
            String select = "SELECT * FROM Bed WHERE Id_Institution = ?";
            stmt = super.connection.prepareStatement(select);
            stmt.setInt(1,institution.getId());
            rs = stmt.executeQuery();

            //Carrega objetos bed em seguida insere o OBJETO BED no ArrayList
            while(rs.next()){

                bed = new Bed();
                bed.setId(rs.getInt("Id"));
                bed.setType(rs.getString("Type"));
                bed.setStatus(rs.getString("Status"));
                bed.setInstitution(institution);

                beds.add(bed);

            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
        // retorno do ArrayList carregado.
        return beds;
    }

    public ArrayList<Bed> getAvailableBeds() throws Exception{

        PreparedStatement stmt;
        ResultSet rs;
        ArrayList<Bed> beds;
        Bed bed;
        Institution institution;
        Address address;

        try{

            beds = new ArrayList<>();
            String select = "SELECT b.*,i.* FROM Bed b JOIN Institution i ON b.Id_Institution = i.Id";
            stmt = super.connection.prepareStatement(select);
            rs = stmt.executeQuery();

            //Carrega objetos bed em seguida insere o OBJETO BED no ArrayList
            while(rs.next()){

                bed = new Bed();
                institution = new Institution();
                address = new Address();
                bed.setId(rs.getInt("b.Id"));
                bed.setType(rs.getString("b.Type"));
                bed.setStatus(rs.getString("Status"));
                institution.setId(rs.getInt("i.Id"));
                institution.setName(rs.getString("Name"));
                institution.setCnpj(rs.getString("CNPJ"));
                institution.setPassword(rs.getString("Password"));
                institution.setType(rs.getString("i.Type"));
                institution.setContactNumber(rs.getString("ContactNumber"));
                address.setId(rs.getInt("a.Id"));
                address.setZipCode(rs.getString("ZipCode"));
                address.setAddress(rs.getString("Address"));
                address.setNumber(rs.getInt("AddressNumber"));
                address.setComplement(rs.getString("Complement"));
                address.setNeighborhood(rs.getString("Neighborhood"));
                address.setCity(rs.getString("City"));
                address.setUf(rs.getString("UF"));
                institution.setAddress(address);
                bed.setInstitution(institution);

                beds.add(bed);

            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
        // retorno do ArrayList carregado.
        return beds;
    }
    // Adiciona um novo leito por instituição, retornando o leito criado.
    public Bed addBed(Bed bed) throws Exception {
        PreparedStatement stmt;
        ResultSet rs;

        //Statement.RETURN_GENERATED_KEYS e getGeneratedKeys() são responsáveis por retornar a pk gerada para o registro.
        try {
            String insert = "INSERT INTO Bed VALUE (DEFAULT,?,DEFAULT,?)";
            stmt = super.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, bed.getType());
            stmt.setInt(2, bed.getInstitution().getId());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();

            if(rs.next()){
                bed.setId(rs.getInt(1));
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
        return bed;
    }
    // Método para remover leito de uma instituição.
    public void removeBed(Bed bed) throws Exception {
        PreparedStatement stmt;

        try {
            String delete = "DELETE FROM Bed WHERE Id = ?";
            stmt = super.connection.prepareStatement(delete);
            stmt.setInt(1,bed.getId());
            stmt.executeUpdate();
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
