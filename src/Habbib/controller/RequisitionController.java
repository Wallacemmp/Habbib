package Habbib.controller;

import Habbib.dao.BedDAO;
import Habbib.dao.RequisitionDAO;
import Habbib.model.*;
import java.util.ArrayList;

public class RequisitionController {

    public void createRequisition(Requisition requisition, Institution institution) throws Exception {

        try(RequisitionDAO requisitionDAO = new RequisitionDAO())
        {
            requisitionDAO.addRequisition(requisition, institution);

        }catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Requisition updateRequisitionStatus(Requisition requisition) throws Exception{
        //TODO implementar a atualização do leito
        BedDAO bedDAO = new BedDAO();
        Bed bed = new Bed();

        try(RequisitionDAO requisitionDAO = new RequisitionDAO()){

            requisitionDAO.updateRequisition(requisition);

            if(requisition.getStatus().equals("Aprovado"))
            {
                bed.setId(requisition.getBed().getId());
                bed.setStatus("Aprovado");
                bedDAO.updateBedStatus(bed);
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
        return requisition;
    }

    public ArrayList<Requisition> listRequisitions(Institution institution) throws Exception{

        try(RequisitionDAO requisitionDAO = new RequisitionDAO()){

            return requisitionDAO.getRequisitionsByInstitution(institution);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }

}
