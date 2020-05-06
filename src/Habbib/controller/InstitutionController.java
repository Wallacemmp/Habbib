package Habbib.controller;

import Habbib.dao.InstitutionDAO;
import Habbib.model.Address;
import Habbib.model.Institution;

public class InstitutionController {

    public void registerInstitution(Institution institution) throws Exception {

        try (InstitutionDAO institutionDAO = new InstitutionDAO()) {

            Institution institutionName = institutionDAO.getInstitutionByName(institution.getName());
            Institution institutionCnpj = institutionDAO.getInstitutionByCNPJ(institution.getCnpj());

            if(institutionName == null && institutionCnpj == null) {
                institutionDAO.addInstitution(institution);
            } else if(institutionName != null){
                throw new Exception("Instituição com o nome " + institutionName.getName() + "já cadastrado");
            } else {
                throw new Exception("Instituição com o CNPJ " + institutionCnpj.getCnpj() + "já cadastrado");
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

    public Institution searchInstitutionByName(String name) throws Exception {

        try(InstitutionDAO institutionDAO = new InstitutionDAO())
        {
            return institutionDAO.getInstitutionByName(name);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Institution searchInstitutionById(int Id) throws Exception {

        try(InstitutionDAO institutionDAO = new InstitutionDAO())
        {
            return institutionDAO.getInstitutionById(Id);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }

}
