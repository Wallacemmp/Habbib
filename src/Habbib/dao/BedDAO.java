package Habbib.dao;

import Habbib.connection.BaseDAO;
import Habbib.model.Bed;
import Habbib.model.Institution;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BedDAO extends BaseDAO {

    public BedDAO() {
        super();
    }
    // Método que retorna um ArrayList com todos os leitos presentes em uma instituição.
    public ArrayList<Bed> getBedByInstitution(Institution institution) throws Exception{
        PreparedStatement stmt;
        ResultSet rs;
        ArrayList<Bed> beds;
        Bed bed;

        try{
            beds = new ArrayList<>();
            String select = "SELECT * FROM Bed WHERE Id_Institution = ?";
            stmt = super.connection.prepareStatement(select);
            stmt.setInt(1,institution.getId());
            rs = stmt.executeQuery();

            //Carrega objetos bed em seguida insere o OBJETO BED no ArrayList
            while(rs.next()){

                bed = new Bed();
                bed.setId(rs.getInt("Id"));
                bed.setType(rs.getString("Type"));
                bed.setStatus(rs.getString("Status"));
                bed.setInstitution(institution);

                beds.add(bed);

            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
        // retorno do ArrayList carregado.
        return beds;
    }
    // Adiciona um novo leito por instituição, retornando o leito criado.
    public Bed addBed(Bed bed) throws Exception {
        PreparedStatement stmt;
        ResultSet rs;

        //Statement.RETURN_GENERATED_KEYS e getGeneratedKeys() são responsáveis por retornar a pk gerada para o registro.
        try {
            String insert = "INSERT INTO Bed VALUE (DEFAULT,?,DEFAULT,?)";
            stmt = super.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, bed.getType());
            stmt.setInt(2, bed.getInstitution().getId());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();

            if(rs.next()){
                bed.setId(rs.getInt(1));
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
        return bed;
    }
    // Método para remover leito de uma instituição.
    public void removeBed(Bed bed) throws Exception {
        PreparedStatement stmt;

        try {
            String delete = "DELETE FROM Bed WHERE Id = ?";
            stmt = super.connection.prepareStatement(delete);
            stmt.setInt(1,bed.getId());
            stmt.executeUpdate();
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
