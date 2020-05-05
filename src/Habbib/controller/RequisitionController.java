package Habbib.controller;

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

        try(RequisitionDAO requisitionDAO = new RequisitionDAO()){

            requisitionDAO.updateRequisition(requisition);

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
