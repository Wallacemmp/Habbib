package Habbib.controller;

import Habbib.dao.BedDAO;
import Habbib.model.Bed;

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


}