package Habbib.dao;

import Habbib.connection.BaseDAO;
import Habbib.model.Address;
import Habbib.model.Bed;
import Habbib.model.Institution;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BedDAO extends BaseDAO {

    public BedDAO() throws Exception {
        super();
    }

    public ArrayList<Bed> getBedByInstitution(Institution institution) throws Exception{
        PreparedStatement stmt;
        ResultSet rs;
        ArrayList<Bed> beds = new ArrayList<>();
        Bed bed;

        try{

            String select = "SELECT * FROM Bed WHERE Id_Institution = ?";
            stmt = super.connection.prepareStatement(select);
            stmt.setInt(1,institution.getId());
            rs = stmt.executeQuery();

            while(rs.next()){

                bed = new Bed();
                bed.setId(rs.getInt("Id"));
                bed.setType(rs.getString("Type"));
                bed.setStatus(rs.getString("Status"));

                beds.add(bed);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
        return beds;
    }

    public ArrayList<Institution> getAvailableBedsFromInstitutions(Institution loggedInsitution) throws Exception{

        PreparedStatement stmt;
        ResultSet rs;
        ArrayList<Institution> institutionList = new ArrayList<>();

        try{
            String select = "SELECT i.*, a.*, \n" +
                    "       (SELECT count(bed.Id) FROM Bed bed WHERE bed.Id_Institution = i.Id and bed.Status = 'Disponivel' and bed.Type = 'UTI') as UTICount,\n" +
                    "       (SELECT count(bed.Id) FROM Bed bed WHERE bed.Id_Institution = i.Id and bed.Status = 'Disponivel' and bed.Type = 'Semi-intensivo') as SemiIntensiveCount,\n" +
                    "       (SELECT count(bed.Id) FROM Bed bed WHERE bed.Id_Institution = i.Id and bed.Status = 'Disponivel' and bed.Type = 'Baixa complexidade') as LowComplexityCount\n" +
                    "FROM Institution i\n" +
                    "join Bed b on i.Id = b.Id_Institution\n" +
                    "join Address a on i.Id_Address = a.Id\n" +
                    "where b.Status = 'Disponivel' AND i.Id != ? \n" +
                    "GROUP BY i.Id";
            stmt = super.connection.prepareStatement(select);
            stmt.setInt(1, loggedInsitution.getId());
            rs = stmt.executeQuery();

            while(rs.next()) {
                Institution institution = new Institution();

                institution.setId(rs.getInt("i.Id"));
                institution.setName(rs.getString("Name"));
                institution.setCnpj(rs.getString("CNPJ"));
                institution.setPassword(rs.getString("Password"));
                institution.setType(rs.getString("Type"));
                institution.setContactNumber(rs.getString("ContactNumber"));

                Address address = new Address();
                address.setId(rs.getInt("a.Id"));
                address.setZipCode(rs.getString("ZipCode"));
                address.setAddress(rs.getString("Address"));
                address.setNumber(rs.getInt("AddressNumber"));
                address.setComplement(rs.getString("Complement"));
                address.setNeighborhood(rs.getString("Neighborhood"));
                address.setCity(rs.getString("City"));
                address.setUf(rs.getString("UF"));

                institution.setAddress(address);

                ArrayList<Bed> beds = new ArrayList<>();

                if(rs.getInt("UTICount") > 0)
                    for (int i = 0; i < rs.getInt("UTICount"); i++)
                        beds.add(new Bed("UTI", "Disponivel"));

                if(rs.getInt("SemiIntensiveCount") > 0)
                    for (int i = 0; i < rs.getInt("SemiIntensiveCount"); i++)
                        beds.add(new Bed("Semi-intensivo", "Disponivel"));

                if(rs.getInt("LowComplexityCount") > 0)
                    for (int i = 0; i < rs.getInt("LowComplexityCount"); i++)
                        beds.add(new Bed("Baixa complexidade", "Disponivel"));

                institution.setBeds(beds);
                institutionList.add(institution);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }

        return institutionList;
    }

    public Bed addBed(Bed bed, Institution institution) throws Exception {
        PreparedStatement stmt;
        ResultSet rs;

        try {
            String insert = "INSERT INTO Bed VALUE (DEFAULT,?,DEFAULT,?)";
            stmt = super.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, bed.getType());
            stmt.setInt(2, institution.getId());
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

    public void updateBedStatus(Bed bed) throws Exception {

        PreparedStatement stmt;

        try {
            String updateBed = "UPDATE Bed SET Status = ? WHERE Id = ?";
            stmt = super.connection.prepareStatement(updateBed);
            stmt.setString(1, bed.getStatus());
            stmt.setInt(2, bed.getId());
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}