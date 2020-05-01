package Habbib.dao;

import Habbib.connection.BaseDAO;
import Habbib.model.Bed;
import Habbib.model.Institution;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BedDAO extends BaseDAO {

    public BedDAO()
    {
        super();
    }
    //TODO:Testar
    public ArrayList<Bed> getBedByInstitution(Institution institution) {
        PreparedStatement stmt;
        ResultSet rs;
        ArrayList<Bed> beds = null;
        Bed bed;

        try{
            bed = new Bed();
            beds = new ArrayList<Bed>();
            String select = "SELECT * FROM Bed WHERE Id_Institution = ?";
            stmt = super.connection.prepareStatement(select);
            stmt.setInt(1,institution.getId());
            rs = stmt.executeQuery();

            while(rs.next()){

                bed.setId(rs.getInt("Id"));
                bed.setType(rs.getString("Type"));
                bed.setStatus(rs.getString("Status"));
                bed.setInstitution(institution);

                beds.add(bed);

            }

        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Erro ao buscar leitos.\n\n"+ e.getMessage(),"WARNING",JOptionPane.WARNING_MESSAGE);
        }

        return beds;
    }
    //TODO:Testar
    public Bed addBed(String type, Institution institution) {
        PreparedStatement stmt;
        Bed bed = new Bed();
        bed.setType(type);
        bed.setStatus("Disponível");
        bed.setInstitution(institution);
        ResultSet rs;

        try{
            String insert = "INSERT INTO Bed VALUE (DEFAULT,?,DEFAULT,?)";
            stmt = super.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,type);
            stmt.setInt(2,institution.getId());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();

            bed.setId(rs.getInt(1));

        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Erro ao adicionar leito.\n\n"+ e.getMessage(),"WARNING",JOptionPane.WARNING_MESSAGE);
        }
        return bed;
    }
    //TODO: Criar método para excluir leito.
    public void removeBed(String type) {

    }
}
