package com.habbib.dao;
import java.sql.*;

public class InsertQuerie {
    private final String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/db_Habbib?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT&useSSL=false";
    private final String USERNAME = "root";
    private final String PASSWORD = "password";

    private Connection connection = null;
    private PreparedStatement insertNewInstitution = null;

    public InsertQuerie()
    {
        try
        {
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

            insertNewInstitution = connection.prepareStatement(
                    "INSERT INTO Endereco" +
                    "(CEP, Endereco, Número, Complemento, Bairro, Cidade, UF)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)");
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
            System.exit(1);
        }
    }

    public int insertAddress(int zipCode, int number, String complement, String neighborhood, String city, String UF, String street)
    {
        int result = 0;

        try
        {
            insertNewInstitution.setInt(1, zipCode);
            insertNewInstitution.setString(2, street);
            insertNewInstitution.setInt(3, number);
            insertNewInstitution.setString(4, complement);
            insertNewInstitution.setString(5, neighborhood);
            insertNewInstitution.setString(6, city);
            insertNewInstitution.setString(7, UF);

            result = insertNewInstitution.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }

        return result;
    }

}
