package Habbib.dao;

import Habbib.connection.BaseDAO;
import Habbib.model.Address;
import Habbib.model.Institution;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InstitutionDAO extends BaseDAO {

    private PreparedStatement insertNewInstitution = null;
    private PreparedStatement insertNewAddress = null;

    public  InstitutionDAO ()
    {
        super();
    }

    public Institution getInstitutionByName(String name) {
        PreparedStatement stmt;
        ResultSet rs;

        Institution institution = null;

        try {
            stmt = super.connection.prepareStatement("SELECT * FROM Instituicao i JOIN Endereco e ON Nome = ? AND i.Endereco_ID_Endereco = e.ID_Endereco");
            stmt.setString(1, name);
            rs = stmt.executeQuery();

            if (rs.next()) {

                institution = new Institution();
                Address address = new Address();

                institution.setId(rs.getInt("idInstituicao"));
                institution.setNome(rs.getString("Nome"));
                institution.setCnpj(rs.getString("CNPJ"));
                institution.setPassword(rs.getString("Senha"));
                institution.setType(rs.getString("Tipo"));
                institution.setContactNumber(rs.getString("Telefone"));
                address.setId(rs.getInt("ID_Endereco"));
                address.setZipCode(rs.getInt("CEP"));
                address.setAddress(rs.getString("Endereco"));
                address.setNumber(rs.getInt("Número"));
                address.setComplement(rs.getString("Complemento"));
                address.setNeighborhood(rs.getString("Bairro"));
                address.setCity(rs.getString("Cidade"));
                address.setUf(rs.getString("UF"));

            }



        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return institution;
    }

    public Institution getInstitutionByCNPJ(String cnpj) {
        PreparedStatement stmt;
        ResultSet rs;

        Institution institution = null;

        try {
            stmt = super.connection.prepareStatement("SELECT * FROM Instituicao i JOIN Endereco e ON CNPJ = ? AND i.Endereco_ID_Endereco = e.ID_Endereco");
            stmt.setString(1, cnpj);
            rs = stmt.executeQuery();

            if (rs.next()) {

                institution = new Institution();
                Address address = new Address();

                institution.setId(rs.getInt("idInstituicao"));
                institution.setNome(rs.getString("Nome"));
                institution.setCnpj(rs.getString("CNPJ"));
                institution.setPassword(rs.getString("Senha"));
                institution.setType(rs.getString("Tipo"));
                institution.setContactNumber(rs.getString("Telefone"));
                address.setId(rs.getInt("ID_Endereco"));
                address.setZipCode(rs.getInt("CEP"));
                address.setAddress(rs.getString("Endereco"));
                address.setNumber(rs.getInt("Número"));
                address.setComplement(rs.getString("Complemento"));
                address.setNeighborhood(rs.getString("Bairro"));
                address.setCity(rs.getString("Cidade"));
                address.setUf(rs.getString("UF"));

            }



        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return institution;
    }

    public int insertAddress(int zipCode, int number, String complement, String neighborhood, String city, String UF, String street)
    {

        ResultSet result = null;
        int key = 0;
        try
        {
            insertNewAddress = super.connection.prepareStatement(
                    "INSERT INTO Endereco" +
                            "(CEP, Endereco, Número, Complemento, Bairro, Cidade, UF)" +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            insertNewAddress.setInt(1, zipCode);
            insertNewAddress.setString(2, street);
            insertNewAddress.setInt(3, number);
            insertNewAddress.setString(4, complement);
            insertNewAddress.setString(5, neighborhood);
            insertNewAddress.setString(6, city);
            insertNewAddress.setString(7, UF);

            insertNewAddress.executeUpdate();
            result = insertNewAddress.getGeneratedKeys();

            while (result.next())
            {
                key = result.getInt(1);
            }

        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }

        return key;
    }

    public int insertInstitution(String Nome, String CNPJ, String Senha, String Tipo, String Telefone, int Endereco_ID_Endereco)
    {

        int result = 0;
        ResultSet resultSet = null;
        try
        {
            insertNewInstitution = super.connection.prepareStatement(
                    "INSERT INTO Instituicao" +
                            "(Nome, CNPJ, Senha, Tipo, Telefone, Endereco_ID_Endereco)" +
                            "VALUES(?, ?, ?, ?, ?, ?)");

            insertNewInstitution.setString(1, Nome);
            insertNewInstitution.setString(2, CNPJ);
            insertNewInstitution.setString(3, Senha);
            insertNewInstitution.setString(4, Tipo);
            insertNewInstitution.setString(5, Telefone);
            insertNewInstitution.setInt(6, Endereco_ID_Endereco);
            insertNewInstitution.executeUpdate();
        }
        catch(SQLException e)
        {
            throw new RuntimeException("Error connecting to database", e);
        }

        return result;
    }


}
