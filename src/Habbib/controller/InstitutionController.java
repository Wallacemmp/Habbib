package Habbib.controller;

import Habbib.dao.InstitutionDAO;
import Habbib.model.Address;
import Habbib.model.Institution;

import java.util.ArrayList;

public class InstitutionController {

    public void registerInstitution(Institution institution) throws Exception {

        try (InstitutionDAO institutionDAO = new InstitutionDAO()) {

            //Institution institutionName = institutionDAO.getInstitutionByName(institution.getName());
            //Institution institutionCnpj = institutionDAO.getInstitutionByCNPJ(institution.getCnpj());

            boolean institutionName = isRegistered_Institution(institutionDAO.getRegisteredInstitutions(),institution,0);
            boolean institutionCnpj = isRegistered_CNPJ(institutionDAO.getRegisteredCNPJ(),institution,0);

            if(!institutionName && !institutionCnpj) {
                institutionDAO.addInstitution(institution);
            } else if(institutionName){
                throw new Exception("Instituição com o nome " + institution.getName() + "já cadastrado");
            } else {
                throw new Exception("Instituição com o CNPJ " + institution.getCnpj() + "já cadastrado");
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Address registerAddress(Address address) throws Exception {

        try (InstitutionDAO institutionDAO = new InstitutionDAO()) {

            return institutionDAO.addAddressInstitution(address);

        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Institution getInstitutionByName(String name) throws Exception{

        try(InstitutionDAO institutionDAO = new InstitutionDAO()){
            return institutionDAO.getInstitutionByName(name);
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }

    protected boolean isRegistered_Institution(ArrayList<String> institutionList, Institution institution, int i){
        if (i >= institutionList.size())
            return false;
        if(institutionList.get(i).equals(institution.getName()))
            return true;

        return isRegistered_Institution(institutionList, institution, i+1);
    }

    protected boolean isRegistered_CNPJ(ArrayList<String> cnpjList, Institution institution, int i){
        if (i >= cnpjList.size())
            return false;
        if(cnpjList.get(i).equals(institution.getCnpj()))
            return true;

        return isRegistered_CNPJ(cnpjList, institution, i+1);
    }
}
