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

    public Object[][] rows (ArrayList<Requisition> institutionsRequisitions){

        Object[][] rows = new Object[institutionsRequisitions.size()][3];

        for(int i=0; i < institutionsRequisitions.size(); i++){
            rows[i] = new Object[]{institutionsRequisitions.get(i).getBed().getInstitution().getName(), institutionsRequisitions.get(i).getPatient().getFirstName() + " " + institutionsRequisitions.get(i).getPatient().getLastName(), institutionsRequisitions.get(i).getBed().getType(), institutionsRequisitions.get(i).getStatus()};
        }
        return rows;
    }

}
