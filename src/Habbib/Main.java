package Habbib;

import Habbib.controller.InstitutionController;
import Habbib.controller.RequisitionController;
import Habbib.dao.BedDAO;
import Habbib.dao.PatientDAO;
import Habbib.model.Bed;
import Habbib.model.Institution;
import Habbib.model.Patient;
import Habbib.model.Requisition;
import Habbib.view.View;

public class Main {

    public static void main(String[] args) throws Exception {
//        try
//        {
//            View view = new View();
//        }
//        catch (Exception ex)
//        {
//            System.out.println(ex.getMessage());
//        }

        Institution institution = new Institution();
        InstitutionController institutionController = new InstitutionController();
        Requisition requisition = new Requisition();
        RequisitionController requisitionController = new RequisitionController();
        Bed bed = new Bed();
        BedDAO bedDAO = new BedDAO();
        Patient patient = new Patient();
        PatientDAO patientDAO = new PatientDAO();
        institution = institutionController.searchInstitutionById(7);
        bed = bedDAO.getBedByInstitution(institution).get(0);
        patient.setFirstName("Roland");
        patient.setLastName("Deschain");

        patient = patientDAO.addPatient();
        String teste = "Descrição de teste";
        requisition.setIdInstitution(institution.getId());
        requisition.setBed(bed);
        requisition.setDescription(teste);
        requisition.setPatient();
        requisitionController.createRequisition(requisition);
        System.out.println("Teste");

    }
}
