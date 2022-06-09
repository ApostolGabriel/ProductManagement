package bll;

import dao.AdminDAO;
import model.Administrator;
import model.Client;

import java.util.NoSuchElementException;

public class AdminBLL {

    private AdminDAO adminDAO;

    public AdminBLL(){
        this.adminDAO = new AdminDAO();
    }

    public Administrator isAdmin(String Name, String Password){
        Administrator administrator = adminDAO.findAdmin(Name,Password);
        if (administrator == null) {
            throw new NoSuchElementException("Not a admin");
        }
        return administrator;
    }

    public void insertAdmin(String Name, String Password) {
        Administrator ad = new Administrator(Name, Password);
        adminDAO.insert(ad);
    }

}
