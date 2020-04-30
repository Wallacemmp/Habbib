package Habbib.controller;

import Habbib.dao.InstitutionDAO;
import Habbib.model.Institution;

public class SessionController {

    private Institution loggedInstitution;

    public Institution login(String user, String password) throws Exception{

        try(InstitutionDAO institutionDAO = new InstitutionDAO()) {

            this.loggedInstitution = institutionDAO.getInstitutionByName(user);

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

    public Institution getLoggedInstitution(){
        return loggedInstitution;
    }
}
