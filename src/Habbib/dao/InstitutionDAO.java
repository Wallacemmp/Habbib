package Habbib.dao;

import Habbib.connection.ConnectionFactory;
import Habbib.model.Institution;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InstitutionDAO {

    private Connection con;

    public  InstitutionDAO ()
    {
        con = ConnectionFactory.getConnection();
    }

    public Institution getInstitutionByName(String name)
    {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Institution institution = null;

        try{
            stmt = con.prepareStatement("SELECT * FROM Instituicao WHERE Nome = ?");
            stmt.setString(1, name);
            rs = stmt.executeQuery();

            if(rs.next()){
                //TODO: Wallace implementar os outros campos
                institution = new Institution();
                institution.setNome(rs.getString("Nome"));
                institution.setCnpj(Integer.parseInt(rs.getString("CNPJ")));
                institution.setPassword(rs.getString("Senha"));
                institution.setContactNumber(Integer.parseInt(rs.getString("Telefone")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return institution;
    }
}
