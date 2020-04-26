package Habbib.controller;

import Habbib.dao.InstitutionDAO;
import Habbib.model.Institution;

public class SessionController
{

    public Institution login(String user, String password) throws Exception
    {

        try(InstitutionDAO institutionDAO = new InstitutionDAO())
        {
            Institution institution = institutionDAO.getInstitutionByName(user);

            if(institution == null)
            {

                throw new Exception(" User not found! ");

            }
            else if(institution.getPassword().equals(password))
            {
                System.out.println("logou!");
                return institution;

            }
            else
            {
                throw new Exception("Password wrong !");
            }
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
}
