package com.habbib.view;

import com.habbib.controller.SessionManager;

import javax.swing.*;

public class ViewLogin {

    public void checklogin(){
        String name = JOptionPane.showInputDialog("User: ");
        String password = JOptionPane.showInputDialog("Senha: ");

        SessionManager sm = new SessionManager();

        if(sm.login(name,password)){
            JOptionPane.showMessageDialog(null,"Logado !");
        } else{
            JOptionPane.showMessageDialog(null,"Usuário ou senha inválido!");
        }
    }
}
