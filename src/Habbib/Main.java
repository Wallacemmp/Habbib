package Habbib;

import Habbib.controller.SessionController;
import Habbib.model.Institution;

public class Main {

    public static void main(String[] args) {

        try
        {
            SessionController sessionController = new SessionController();
            Institution institution = sessionController.login("Wallace", "cocacola");
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
