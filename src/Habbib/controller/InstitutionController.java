package Habbib.controller;

import Habbib.dao.InstitutionDAO;
import Habbib.model.Address;
import Habbib.model.Institution;

public class InstitutionController {
    public void register(Institution institution) throws Exception {

        //TODO corrigir lógica de validação da instituição já cadastrada
        try (InstitutionDAO institutionDAO = new InstitutionDAO()) {

            Institution institutionNome = institutionDAO.getInstitutionByName(institution.getNome());
            Institution institutionCnpj = institutionDAO.getInstitutionByCNPJ(institution.getCnpj());

            if (institutionNome == null) {
                if (institutionCnpj == null) {
                    institutionDAO.addInstitution(institution);
                }
            } else {
                if (institutionNome.getNome().equals(institution.getNome())) {
                    throw new Exception("Instituição com o nome " + institution.getNome() + " já cadastrada.");
                } else {
                    if (institutionCnpj.getCnpj().equals(institution.getCnpj())) {
                        throw new Exception("Instituição com o CNPJ" + institution.getCnpj() + " já cadastrada.");
                    } else {
                        institutionDAO.addInstitution(institution);
                    }
                }
            }
        }
    }

    public int addAddress(Address address) throws Exception {
        try (InstitutionDAO institutionDAO = new InstitutionDAO()) {
            address.setId(institutionDAO.addAddress(address));
            return address.getId();
        }
    }
}
