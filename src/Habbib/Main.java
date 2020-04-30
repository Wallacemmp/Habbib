package Habbib;

import Habbib.controller.InstitutionController;
import Habbib.view.LoginView;

public class Main {

    public static void main(String[] args) {
        InstitutionController newInstitution = new InstitutionController();
        try
        {
            LoginView loginView = new LoginView();

            loginView.showWindow();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
