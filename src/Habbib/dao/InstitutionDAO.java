package Habbib.dao;

import Habbib.connection.BaseDAO;
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
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int validation = 0;

        Institution institution = null;

        try{
            stmt = connection.prepareStatement("SELECT Nome FROM Instituicao WHERE Nome = ?");
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
        }

        return institution;
    }
}
