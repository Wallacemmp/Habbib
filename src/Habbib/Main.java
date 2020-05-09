package Habbib;

import Habbib.view.View;

public class Main {

    public static void main(String[] args) {
        try {
            View view = new View();
        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }
    }
}
