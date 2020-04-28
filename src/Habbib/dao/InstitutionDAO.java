package Habbib.dao;

import Habbib.connection.BaseDAO;
import Habbib.model.Address;
import Habbib.model.Institution;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

        try{
            stmt = super.connection.prepareStatement("SELECT * FROM Institution i JOIN Address a ON Nome = ? AND Id_Address = a.Id");
            stmt.setString(1, name);
            rs = stmt.executeQuery();

            if(rs.next()){

                institution = new Institution();
                Address address = new Address();

                institution.setId(rs.getInt("Id"));
                institution.setNome(rs.getString("Name"));
                institution.setCnpj(rs.getString("CNPJ"));
                institution.setPassword(rs.getString("Password"));
                institution.setType(rs.getString("Type"));
                institution.setContactNumber(rs.getString("ContactNumber"));
                address.setId(rs.getInt("Id" ));
                address.setZipCode(rs.getInt("ZipCode"));
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
}