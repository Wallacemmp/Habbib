package com.habbib;

import com.habbib.view.ViewLogin;
import com.habbib.controller.InstitutionService;
import com.habbib.view.Cadastrar2;

public class Main {

    public static void main(String[] args) {

        //ViewLogin vl = new ViewLogin();
        //vl.checklogin();

//        InstitutionService addInstitution = new InstitutionService();
//
//        addInstitution.InstitutionRegister();

        Cadastrar2 dialog = new Cadastrar2();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);



    }
}
