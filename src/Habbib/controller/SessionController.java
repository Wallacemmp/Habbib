package Habbib.controller;

import Habbib.dao.InstitutionDAO;
import Habbib.model.Institution;

public class SessionController {

    public Institution login(String user, String password) throws Exception{

        try(InstitutionDAO institutionDAO = new InstitutionDAO()) {

            Institution loggedInstitution = institutionDAO.getInstitutionByName(user);

            if(loggedInstitution == null) {
                throw new Exception(" User not found! ");
            } else if(loggedInstitution.getPassword().equals(password)){
                return loggedInstitution;
            }else {
                throw new Exception("Password wrong !");
            }
        }catch (Exception ex){
            throw ex;
        }
    }
}