package Habbib.controller;

import Habbib.dao.RequisitionDAO;
import Habbib.model.*;
import java.util.ArrayList;

public class RequisitionController {

    public Requisition createRequisition(Requisition requisition) throws Exception {

        try(RequisitionDAO requisitionDAO = new RequisitionDAO())
        {
            requisitionDAO.addRequisition(requisition);

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
