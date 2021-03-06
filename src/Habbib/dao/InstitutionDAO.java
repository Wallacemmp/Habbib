package Habbib.dao;

import Habbib.connection.BaseDAO;
import Habbib.model.Address;
import Habbib.model.Institution;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class InstitutionDAO extends BaseDAO {

    public InstitutionDAO () throws Exception {
        super();
    }

    public Institution getInstitutionByName(String name) throws Exception{
        PreparedStatement stmt;
        ResultSet rs;
        Institution institution = null;
        Address address;

        try{
            String select = "SELECT * FROM Institution i JOIN Address a ON i.Name = ? AND Id_Address = a.Id";
            stmt = super.connection.prepareStatement(select);
            stmt.setString(1,name);
            rs = stmt.executeQuery();

            if(rs.next()){

                institution = new Institution();
                address = new Address();

                institution.setId(rs.getInt("Id"));
                institution.setName(rs.getString("Name"));
                institution.setCnpj(rs.getString("CNPJ"));
                institution.setPassword(rs.getString("Password"));
                institution.setType(rs.getString("Type"));
                institution.setContactNumber(rs.getString("ContactNumber"));
                address.setId(rs.getInt("Id"));
                address.setZipCode(rs.getString("ZipCode"));
                address.setAddress(rs.getString("Address"));
                address.setNumber(rs.getInt("AddressNumber"));
                address.setComplement(rs.getString("Complement"));
                address.setNeighborhood(rs.getString("Neighborhood"));
                address.setCity(rs.getString("City"));
                address.setUf(rs.getString("UF"));
                institution.setAddress(address);

            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }

        return institution;
    }

    public Institution getInstitutionByCNPJ(String cnpj) throws Exception{
        PreparedStatement stmt;
        ResultSet rs;
        Institution institution = null;

        try {
            String select = "SELECT * FROM Institution i JOIN Address a ON i.cnpj = ? AND i.Id_Address = a.Id";
            stmt = super.connection.prepareStatement(select);
            stmt.setString(1, cnpj);
            rs = stmt.executeQuery();

            if (rs.next()) {

                institution = new Institution();
                Address address = new Address();

                institution.setId(rs.getInt("Id"));
                institution.setName(rs.getString("Name"));
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
                institution.setAddress(address);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
        return institution;
    }

    public void removeInstitutionByName(String name) throws Exception{
        PreparedStatement stmt;
        // A query deleta tando a institui????o quanto o endere??o atrelado a ela.
        try{
            String delete = "DELETE a,i FROM Address a, Institution i WHERE i.Name = ? AND a.Id = i.Id_Address";
            stmt = super.connection.prepareStatement(delete);
            stmt.setString(1, name);
            stmt.executeUpdate();

        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Address addAddressInstitution(Address address) throws Exception{
        PreparedStatement stmt;
        ResultSet rs;

        // O Statement.RETURN_GENERATED_KEYS e .getGeneratedKeys() s??o responsav??is por retornar a pk criada para o registro.
        try {
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
            //  Insere a pk na variav??l key.
            if (rs.next()) {
                address.setId(rs.getInt(1));
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
        return address;
    }

    public void addInstitution(Institution institution) throws Exception{
        PreparedStatement stmt;
        try {

            String insert = "INSERT INTO Institution VALUE (DEFAULT, ?, ?, ?, ?, ?, ?)";

            stmt = super.connection.prepareStatement(insert);

            stmt.setString(1, institution.getName());
            stmt.setString(2, institution.getCnpj());
            stmt.setString(3, institution.getPassword());
            stmt.setString(4, institution.getType());
            stmt.setString(5, institution.getContactNumber());
            stmt.setInt(6, institution.getAddress().getId());
            stmt.executeUpdate();

        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public ArrayList<String> getRegisteredInstitutions() throws Exception{
        PreparedStatement stmt;
        ResultSet rs;
        ArrayList<String> institutions = new ArrayList<>();
        try {
            String select = "SELECT Name FROM Institution";
            stmt = super.connection.prepareStatement(select);
            rs = stmt.executeQuery();

            while (rs.next()){

                String institution = rs.getString("Name");

                institutions.add(institution);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
        return institutions;
    }

    public ArrayList<String> getRegisteredCNPJ() throws Exception{
        PreparedStatement stmt;
        ResultSet rs;
        ArrayList<String> institutionsCnpj = new ArrayList<>();
        try {
            String select = "SELECT CNPJ FROM Institution";
            stmt = super.connection.prepareStatement(select);
            rs = stmt.executeQuery();

            while (rs.next()){

                String cnpj = rs.getString("CNPJ");

                institutionsCnpj.add(cnpj);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
        return institutionsCnpj;
    }
}