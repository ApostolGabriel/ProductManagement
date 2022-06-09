package bll;

import dao.AdminDAO;
import dao.EmployeeDAO;
import model.Administrator;
import model.Employee;

import java.util.NoSuchElementException;

public class EmployeeBLL {

    private EmployeeDAO employeeDAO;

    public EmployeeBLL(){
        this.employeeDAO = new EmployeeDAO();
    }

    public Employee isEmployee(String Name, String Password){
        Employee employee = employeeDAO.findEmployee(Name,Password);
        if (employee == null) {
            throw new NoSuchElementException("Not a admin");
        }
        return employee;
    }
}
