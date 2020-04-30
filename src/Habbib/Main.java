package Habbib;

import Habbib.controller.InstitutionController;
import Habbib.view.Views;

public class Main {

    public static void main(String[] args) {
        InstitutionController newInstitution = new InstitutionController();
        try
        {
            Views views = new Views();

            views.showWindow();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
