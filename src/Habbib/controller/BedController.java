package Habbib.controller;

import Habbib.dao.BedDAO;
import Habbib.model.Bed;
import Habbib.model.Institution;

import java.util.ArrayList;

public class BedController {

    public void registerBeds(Bed bed, int count, Institution institution) throws Exception{

        try(BedDAO bedDAO = new BedDAO()) {

            for(int i = 0; i < count; i++){
                bedDAO.addBed(bed, institution);
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public ArrayList<Institution> searchInstitutionsWithAvailableBeds(Institution loggedInstitution) throws Exception{

        ArrayList<Institution> institution;

        try(BedDAO bedDAO = new BedDAO()){

            institution = bedDAO.getAvailableBedsFromInstitutions(loggedInstitution);

        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
        return institution;
    }

    public ArrayList<Bed> getInstitutionBeds(Institution institution) throws Exception{

        try(BedDAO bedDAO = new BedDAO()){
            return bedDAO.getBedByInstitution(institution);
        } catch (Exception e){
            throw e;
        }
    }
}