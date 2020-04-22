package Habbib.dao;

import Habbib.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InstitutionDAO {

    public static int loginValidator(String user, String password){

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int validation = 0;

        try{
            stmt = con.prepareStatement("SELECT Nome FROM Instituicao WHERE Nome = ?");
            stmt.setString(1, user);
            rs = stmt.executeQuery();

            if(rs.next()){

                try {
                    stmt = con.prepareStatement("SELECT Nome,Senha FROM Instituicao WHERE Nome = ? AND Senha = ?");
                    stmt.setString(1, user);
                    stmt.setString(2, password);
                    rs = stmt.executeQuery();

                    if(rs.next()){
                        validation = 1;
                    } else{
                        validation = 2;
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else {
                validation = 3;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return validation;
    }
}
