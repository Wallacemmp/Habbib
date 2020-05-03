package Habbib.controller;

import Habbib.dao.BedDAO;
import Habbib.model.Bed;

import java.util.ArrayList;

public class BedController {

    public void registerBeds(Bed bed, int count) throws Exception{

        try(BedDAO bedDAO = new BedDAO()) {

            for(int i = 0; i < count; i++){
                bedDAO.addBed(bed);
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public ArrayList<Bed> searchAvailableBeds() throws Exception{

        ArrayList<Bed> beds;

        try(BedDAO bedDAO = new BedDAO()){

            beds = bedDAO.getAvailableBeds();

        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
        return beds;
    }
}