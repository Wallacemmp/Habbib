package Habbib.controller;

import Habbib.dao.InstitutionDAO;
import Habbib.model.Address;
import Habbib.model.Institution;
import org.jetbrains.annotations.NotNull;

public class InstitutionController
{
        //TODO receber um objeto do tipo instituição
        public void Register(@NotNull Institution institution) throws Exception
        {
                try(InstitutionDAO institutionDAO = new InstitutionDAO())
                {
                        Institution institutionNome = institutionDAO.getInstitutionByName(institution.getNome());
                        Institution institutionCnpj = institutionDAO.getInstitutionByCNPJ(institution.getCnpj());

                        if(institutionNome == null)
                        {
                                if(institutionCnpj == null)
                                {
                                        institutionDAO.insertInstitution(institution);
                                }
                        }
                        else
                        {
                                if(institutionNome.getNome().equals(institution.getNome()))
                                {
                                        throw new Exception("Instituição com o nome " + institution.getNome() + " já cadastrada.");
                                }
                                else
                                {
                                        if(institutionCnpj.getCnpj().equals(institution.getCnpj()))
                                        {
                                                throw new Exception("Instituição com o CNPJ" + institution.getCnpj() + " já cadastrada.");
                                        }
                                        else
                                        {
                                                institutionDAO.insertInstitution(institution);
                                        }
                                }
                        }
                }
        }

        //TODO receber um objeto do tipo Address
        public int addAddress(@NotNull Address address) throws Exception
        {
                try(InstitutionDAO institutionDAO = new InstitutionDAO())
                {
                        address.setId(institutionDAO.insertAddress(address));
                        return address.getId();
                }
        }
}
