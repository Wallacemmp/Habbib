package Habbib.dao;

import Habbib.connection.BaseDAO;
import Habbib.model.Bed;
import Habbib.model.Institution;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BedDAO extends BaseDAO {

    public BedDAO()
    {
        super();
    }
    //TODO:Testar
    public ArrayList<Bed> getBedByInstitution(String name) {
        PreparedStatement stmt;
        ResultSet rs;
        Institution institution;

        try{
            institution = new Institution();
            String insert = "SELECT * FROM Bed WHERE Id = ?";
            stmt = super.connection.prepareStatement(insert);
            stmt.setInt(1,institution.getId());

            return stmt.execute();

        } catch (SQLException e){
            throw new RuntimeException("Error connecting to database", e);
        }
    }
    //TODO:Testar
    public boolean AddBed(String type) {
        PreparedStatement stmt;
        Institution institution;

        try{
            institution = new Institution();
            String insert = "INSERT INTO Bed VALUE (DEFAULT,?,DEFAULT,?)";
            stmt = super.connection.prepareStatement(insert);
            stmt.setString(1,type);
            stmt.setInt(2,institution.getId());

            return stmt.execute();

        } catch (SQLException e){
            throw new RuntimeException("Error connecting to database", e);
        }
    }

    public boolean RemoveBed(String type) {
        PreparedStatement stmt;
        Institution institution;

        try{
            institution = new Institution();
            //TODO: Não consigo deletar uma linha aleatória que tenha determinado tipo.
            String insert = "DELETE FROM Bed WHERE(SELECT Id FROM Bed WHERE Type = ? AND Id_Institution = ? LIMIT 1)";
            stmt = super.connection.prepareStatement(insert);
            stmt.setString(1,type);
            stmt.setInt(2,institution.getId());

            return stmt.execute();

        } catch (SQLException e){
            throw new RuntimeException("Error connecting to database", e);
        }
    }

}
