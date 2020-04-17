package com.habbib.controller;
import com.habbib.dao.InsertQuerie;
import com.habbib.enumerator.InstitutionType;
import com.habbib.model.Address;
import com.habbib.model.Institution;

import javax.swing.*;

public class InstitutionService
{
//        public void InstitutionRegister()
//        {
//                InsertQuerie intitutionAddress = new InsertQuerie();
//                Institution newInstitution = new Institution();
//
//                //TODO
//                int cnpj = Integer.parseInt(JOptionPane.showInputDialog("Informe o CNPJ: "));
//                String nome = JOptionPane.showInputDialog("Informe o Nome da Instituição: ");
//                String password = JOptionPane.showInputDialog("Informe a senha: ");
//                String contactNumber = JOptionPane.showInputDialog("Informe o número de contato: ");
//
//
//
//
//
//        }

        public void insertAddress()
        {
                InsertQuerie intitutionAddress = new InsertQuerie();
                Address address1 = new Address();

                int zipCode = Integer.parseInt(JOptionPane.showInputDialog("Informe o CEP: "));
                int number = Integer.parseInt(JOptionPane.showInputDialog("Informe o Número: "));
                String complement = JOptionPane.showInputDialog("Informe o Complemento: ");
                String neighborhood = JOptionPane.showInputDialog("Informe o Bairro: ");
                String city = JOptionPane.showInputDialog("Informe a Cidade: ");
                String UF = JOptionPane.showInputDialog("Informe o UF: ");
                String street = JOptionPane.showInputDialog("Informe a Rua: ");

                address1.setZipCode(zipCode);
                address1.setNumber(number);
                address1.setComplement(complement);
                address1.setNeighborhood(neighborhood);
                address1.setCity(city);
                address1.setUF(UF);
                address1.setStreet(street);

                intitutionAddress.insertAddress(address1.getZipCode(), address1.getNumber(), address1.getComplement(), address1.getNeighborhood(), address1.getCity(), address1.getUF(), address1.getStreet());
        }
}
