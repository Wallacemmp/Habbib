package Habbib.controller;

import Habbib.dao.InstitutionDAO;
import Habbib.model.Address;
import Habbib.model.Institution;

//Essa classe está estranha, eu sei.
//O método
public class InstitutionController
{
        public void Register(String cnpj, String nome, String password, String contactNumber, String tipo, int address) throws Exception
        {
                try(InstitutionDAO institutionDAO = new InstitutionDAO())
                {
                        Institution institutionNome = institutionDAO.getInstitutionByName(nome);
                        Institution institutionCnpj = institutionDAO.getInstitutionByCNPJ(cnpj);

                        if(institutionNome == null)
                        {
                                if(institutionCnpj == null)
                                {
                                        institutionDAO.insertInstitution(nome, cnpj, password, tipo, contactNumber, address);
                                }
                        }
                        else
                        {
                                if(institutionNome.getNome().equals(nome))
                                {
                                        throw new Exception("Instituição com o nome " + nome + " já cadastrada.");
                                }
                                else
                                {
                                        if(institutionCnpj.getCnpj().equals(cnpj))
                                        {
                                                throw new Exception("Instituição com o CNPJ" + cnpj + " já cadastrada.");
                                        }
                                        else
                                        {
                                                institutionDAO.insertInstitution(nome, cnpj, password, tipo, contactNumber, address);
                                        }
                                }
                        }
                }
        }

        public int addAddress(int zipCode, int number, String complement, String neighborhood, String city, String UF, String street) throws Exception
        {
                try(InstitutionDAO institutionDAO = new InstitutionDAO())
                {
                        Address address = new Address();

                        address.setZipCode(zipCode);
                        address.setNumber(number);
                        address.setComplement(complement);
                        address.setNeighborhood(neighborhood);
                        address.setCity(city);
                        address.setUf(UF);
                        address.setStreet(street);

                        return institutionDAO.insertAddress(address.getZipCode(), address.getNumber(), address.getComplement(), address.getNeighborhood(), address.getCity(), address.getUf(), address.getStreet());
                }
        }
}
