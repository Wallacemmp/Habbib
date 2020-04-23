package com.habbib.dao;
import com.habbib.connection.ConnectionFactory;
import java.sql.*;

public class InsertQuerie {

    Connection connection = ConnectionFactory.getConnection();
    private PreparedStatement insertNewInstitution = null;
    private PreparedStatement insertNewAddress = null;

    public InsertQuerie()
    {

        try
        {
            //connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            insertNewAddress = connection.prepareStatement(
                    "INSERT INTO Endereco" +
                    "(CEP, Endereco, Número, Complemento, Bairro, Cidade, UF)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            insertNewInstitution = connection.prepareStatement(
                    "INSERT INTO Instituicao" +
                    "(Nome, CNPJ, Senha, Tipo, Telefone, Endereco_ID_Endereco)" +
                    "VALUES(?, ?, ?, ?, ?, ?)");
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
            System.exit(1);
        }
    }

    public int insertAddress(int zipCode, int number, String complement, String neighborhood, String city, String UF, String street)
    {
        ResultSet result = null;
        int chave = 0;
        try
        {
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
                chave = result.getInt(1);
            }

        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }

        return chave;
    }

    public int insertInstitution(String Nome, String CNPJ, String Senha, String Tipo, String Telefone, int Endereco_ID_Endereco)
    {
        int result = 0;
        ResultSet resultSet = null;
        try
        {
            insertNewInstitution.setString(1, Nome);
            insertNewInstitution.setString(2, CNPJ);
            insertNewInstitution.setString(3, Senha);
            insertNewInstitution.setString(4, Tipo);
            insertNewInstitution.setString(5, Telefone);
            insertNewInstitution.setInt(6, Endereco_ID_Endereco);
            insertNewInstitution.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }

        return result;
    }

}
