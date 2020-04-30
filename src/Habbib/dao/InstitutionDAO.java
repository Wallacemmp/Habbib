package Habbib.dao;

import Habbib.connection.BaseDAO;
import Habbib.model.Address;
import Habbib.model.Institution;
import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InstitutionDAO extends BaseDAO {


    public  InstitutionDAO ()
    {
        super();
    }

    public Institution getInstitutionByName(String name) {
        PreparedStatement stmt;
        ResultSet rs;
        Institution institution = null;
        Address address;

        try{
            String select = "SELECT * FROM Institution i JOIN Address a ON Name = ? AND Id_Address = a.Id";
            stmt = super.connection.prepareStatement(select);
            stmt.setString(1, name);
            rs = stmt.executeQuery();

            if(rs.next()){

                institution = new Institution();
                address = new Address();

                institution.setId(rs.getInt("Id"));
                institution.setNome(rs.getString("Name"));
                institution.setCnpj(rs.getString("CNPJ"));
                institution.setPassword(rs.getString("Password"));
                institution.setType(rs.getString("Type"));
                institution.setContactNumber(rs.getString("ContactNumber"));
                address.setId(rs.getInt("Id" ));
                address.setZipCode(rs.getString("ZipCode"));
                address.setAddress(rs.getString("Address"));
                address.setNumber(rs.getInt("AddressNumber"));
                address.setComplement(rs.getString("Complement"));
                address.setNeighborhood(rs.getString("Neighborhood"));
                address.setCity(rs.getString("City"));
                address.setUf(rs.getString("UF"));
                institution.setAddress(address);

            }
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to database", e);
        }

        return institution;
    }

    public Institution getInstitutionByCNPJ(String cnpj) {
        PreparedStatement stmt;
        ResultSet rs;

        Institution institution = null;
        try {
            stmt = super.connection.prepareStatement("SELECT * FROM Institution i JOIN Address e ON cnpj = ? AND e.Id = i.Id_Address");
            stmt.setString(1, cnpj);
            rs = stmt.executeQuery();

            if (rs.next()) {

                institution = new Institution();
                Address address = new Address();

                institution.setId(rs.getInt("Id"));
                institution.setNome(rs.getString("Name"));
                institution.setCnpj(rs.getString("CNPJ"));
                institution.setPassword(rs.getString("Password"));
                institution.setType(rs.getString("Type"));
                institution.setContactNumber(rs.getString("ContactNumber"));
                address.setId(rs.getInt("Id_Address"));
                address.setZipCode(rs.getString("ZipCode"));
                address.setAddress(rs.getString("Address"));
                address.setNumber(rs.getInt("AddressNumber"));
                address.setComplement(rs.getString("Complement"));
                address.setNeighborhood(rs.getString("Neighborhood"));
                address.setCity(rs.getString("City"));
                address.setUf(rs.getString("UF"));

            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return institution;
    }

    //TODO:Testar
    public void removeInstitutionByName(String name) {
        PreparedStatement stmt;

        try{
            String delete = "DELETE a,i FROM Address a, Institution i WHERE i.Name = ? AND a.Id = i.Id_Address";
            stmt = super.connection.prepareStatement(delete);
            stmt.setString(1, name);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to database", e);
        }
    }

    public int insertAddress(Address address)
    {
        PreparedStatement stmt;
        ResultSet rs;
        int key = 0;

        try
        {
            String insert = "INSERT INTO Address VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?)";
            stmt = super.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, address.getZipCode());
            stmt.setString(2, address.getAddress());
            stmt.setInt(3, address.getNumber());
            stmt.setString(4, address.getComplement());
            stmt.setString(5, address.getNeighborhood());
            stmt.setString(6, address.getCity());
            stmt.setString(7, address.getUf());

            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();

            //TODO testar com if
            while(rs.next())
            {
                key = rs.getInt(1);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }

        return key;
    }

    public void insertInstitution(Institution institution) {
        PreparedStatement stmt;
        try {

            String insert = "INSERT INTO Institution VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)";

            stmt = super.connection.prepareStatement(insert);

            stmt.setString(1, institution.getNome());
            stmt.setString(2, institution.getCnpj());
            stmt.setString(3, institution.getPassword());
            stmt.setString(4, institution.getType());
            stmt.setString(5, institution.getContactNumber());
            stmt.setInt(6, institution.getAddress().getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to database", e);
        }
    }
}