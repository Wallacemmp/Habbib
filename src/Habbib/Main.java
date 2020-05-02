package Habbib;

import Habbib.controller.InstitutionController;
import Habbib.dao.BedDAO;
import Habbib.dao.InstitutionDAO;
import Habbib.dao.PatientDAO;
import Habbib.dao.RequisitionDAO;
import Habbib.model.Bed;
import Habbib.model.Institution;
import Habbib.model.Patient;
import Habbib.model.Requisition;
import Habbib.view.Views;

import java.text.ParseException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Main {

    private static Object Date;

    public static void main(String[] args) {

        DateFormat formatter = new SimpleDateFormat("DD/MM/YYYY");

        Bed bed = new Bed();
        BedDAO bedDAO = new BedDAO();
        Patient patient = new Patient();
        PatientDAO patientDAO = new PatientDAO();
        Requisition requisition = new Requisition();
        RequisitionDAO requisitionDAO = new RequisitionDAO();
        InstitutionDAO institutionDAO = new InstitutionDAO();

        institutionDAO.getInstitutionByName("teste");

        bed.setType("UTI");
        bed.setInstitution(institutionDAO.getInstitutionByName("teste"));
        bedDAO.addBed(bed);

        patient.setCid("10 - F52");
        patient.setCpf("123123");
        try {
            patient.setDob((Date)formatter.parse("12/01/1999"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        patient.setGender("Masculino");
        patient.setFirstName("Arthur");
        patient.setLastName("Machado");
        patientDAO.addPatient(patient);

        requisition.setPatient(patient);
        requisition.setBed(bed);
        requisition.setDescription("O paciente reclamou de incapacidade de manter a ereção.");

        requisitionDAO.addRequisition(requisition, institutionDAO.getInstitutionByName("teste"));
        System.out.println("Foi");

    }
}
