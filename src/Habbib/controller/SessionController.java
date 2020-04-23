package Habbib.controller;

import Habbib.dao.InstitutionDAO;
import Habbib.model.Institution;

public class SessionController {

    public Institution login(String user, String password) throws Exception{

        InstitutionDAO institutionDAO = new InstitutionDAO();

        Institution institution = institutionDAO.getInstitutionByName(user);

        if(institution == null)
            throw new Exception("User not find!");
        else if(institution.getPassword().equals(password)){
            return institution;
        }else {
            throw new Exception("Password does not match!");
        }
    }
}
