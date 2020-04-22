package Habbib.controller;

import Habbib.dao.InstitutionDAO;
import Habbib.view.ViewLogin;

import javax.swing.*;

public class SessionController {

    public static void login(String user, String password){

        ViewLogin vl = new ViewLogin();

        int validator = InstitutionDAO.loginValidator(user,password);

        if(validator == 1){
            JOptionPane.showMessageDialog(null,"Voce ta logadisso mona !!");
        }else {
            if(validator == 2){
                JOptionPane.showMessageDialog(null,"Bixa, tu errou a senha :C");
            } else {
                if(validator == 3){
                    JOptionPane.showMessageDialog(null,"NÃO ENCONTRAMO ESSE USUARIO NÃO AMOR");
                }
            }
        }
    }
}
