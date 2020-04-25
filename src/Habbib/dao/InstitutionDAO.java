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
            stmt = super.connection.prepareStatement("SELECT * FROM Instituicao i JOIN Endereco e ON Nome = ? AND i.Endereco_ID_Endereco = e.ID_Endereco");
            stmt.setString(1, name);
            rs = stmt.executeQuery();

            if(rs.next()){

                institution = new Institution();
                Address address = new Address();

                institution.setId(rs.getInt("idInstituicao"));
                institution.setNome(rs.getString("Nome"));
                institution.setCnpj(rs.getString("CNPJ"));
                institution.setPassword(rs.getString("Senha"));
                institution.setType(rs.getString("Tipo"));
                institution.setContactNumber(rs.getString("Telefone"));
                address.setId(rs.getInt("ID_Endereco" ));
                address.setZipCode(rs.getInt("CEP"));
                address.setAddress(rs.getString("Endereco"));
                address.setNumber(rs.getInt("Número"));
                address.setComplement(rs.getString("Complemento"));
                address.setNeighborhood(rs.getString("Bairro"));
                address.setCity(rs.getString("Cidade"));
                address.setUf(rs.getString("UF"));

            }
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to database", e);
        }

        return institution;
    }
}