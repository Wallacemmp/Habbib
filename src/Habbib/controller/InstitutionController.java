package Habbib.controller;

import Habbib.dao.InstitutionDAO;
import Habbib.model.Address;
import Habbib.model.Institution;

public class InstitutionController {

    public void registerInstitution(Institution institution) throws Exception {

        try (InstitutionDAO institutionDAO = new InstitutionDAO()) {

            Institution institutionNome = institutionDAO.getInstitutionByName(institution.getName());
            Institution institutionCnpj = institutionDAO.getInstitutionByCNPJ(institution.getCnpj());

            if (institutionNome.getName().equals(institution.getName())) {
              throw new Exception("Instituição com o nome " + institution.getName() + " já cadastrada.");
            } else if (institutionCnpj.getCnpj().equals(institution.getCnpj())) {
              throw new Exception("Instituição com o CNPJ" + institution.getCnpj() + " já cadastrada.");
            } else {
              institutionDAO.addInstitution(institution);
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

}
