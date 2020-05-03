package Habbib.controller;

import Habbib.dao.RequisitionDAO;
import Habbib.model.*;
import java.util.ArrayList;

public class RequisitionController {

    public Requisition createRequisition(Patient patient, Bed bed, Institution requester, String description) throws Exception {

        Requisition requisition = new Requisition();
        try(RequisitionDAO requisitionDAO = new RequisitionDAO())
        {
            requisition.setPatient(patient);
            requisition.setBed(bed);
            requisition.setInstitution(requester);
            requisition.setDescription(description);

            requisitionDAO.addRequisition(requisition, requester);

        }catch (Exception e){
            System.out.println(e);
            throw e;
        }
        return requisition;
    }

    public Requisition updateRequisitionStatus(Requisition requisition) throws Exception{

        try(RequisitionDAO requisitionDAO = new RequisitionDAO()){

            requisitionDAO.updateRequisition(requisition);

        }catch (Exception e){
            System.out.println(e);
            throw e;
        }
        return requisition;
    }

    public ArrayList<Requisition> listRequisitions(Institution institution) throws Exception{

        ArrayList<Requisition> content;

        try(RequisitionDAO requisitionDAO = new RequisitionDAO()){

            content = requisitionDAO.getRequisitionsByInstitution(institution);
        }
        catch (Exception e){
            System.out.println(e);
            throw e;
        }

        return content;
    }

}
