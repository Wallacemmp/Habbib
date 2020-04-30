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
        InstitutionDAO institutionDAO;
        ArrayList<Bed> beds;
        Bed bed;

        try{

            institutionDAO = new InstitutionDAO();
            bed = new Bed();
            beds = new ArrayList<Bed>();
            String select = "SELECT * FROM Bed WHERE Id_Institution = (SELECT Id FROM Institution WHERE Name = ?)";
            stmt = super.connection.prepareStatement(select);
            stmt.setString(1,name);
            rs = stmt.executeQuery();

            while(rs.next()){

                bed.setId(rs.getInt("Id"));
                bed.setType(rs.getString("Type"));
                bed.setStatus(rs.getString("Status"));
                bed.setInstitution(institutionDAO.getInstitutionByName(name));

                beds.add(bed);

            }

        } catch (SQLException e){
            throw new RuntimeException("Error connecting to database", e);
        }

        return beds;
    }
    //TODO:Testar
    public void addBedByType(String type) {
        PreparedStatement stmt;
        Institution institution;

        try{
            institution = new Institution();
            String insert = "INSERT INTO Bed VALUE (DEFAULT,?,DEFAULT,?)";
            stmt = super.connection.prepareStatement(insert);
            stmt.setString(1,type);
            stmt.setInt(2,institution.getId());
            stmt.execute();

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
