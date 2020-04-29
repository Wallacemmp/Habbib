package Habbib.dao;

import Habbib.connection.BaseDAO;
import Habbib.model.Address;
import Habbib.model.Institution;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InstitutionDAO extends BaseDAO {


    public  InstitutionDAO ()
    {
        super();
    }

    public Institution getInstitutionByName(String name)
    {
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
    //TODO:Testar
    public boolean RemoveInstitutionByName(String name)
    {
        PreparedStatement stmt;

        try{
            stmt = super.connection.prepareStatement("DELETE a,i FROM Address a, Institution i WHERE i.Name = ? AND a.Id = i.Id_Address");
            stmt.setString(1, name);

            return stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to database", e);
        }

    }
}