package Habbib;

import Habbib.controller.InstitutionController;
import Habbib.dao.BedDAO;
import Habbib.dao.InstitutionDAO;
import Habbib.model.Bed;
import Habbib.model.Institution;
import Habbib.view.Views;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        
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
