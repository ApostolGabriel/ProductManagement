package model;

public class Employee {

    int idEmployee;
    String Name;
    String Password;

    Employee(){ }

    public Employee(String Name, String Password){
        this.Name = Name;
        this.Password = Password;
    }

    public Employee(int idEmployee, String name, String password) {
        this.idEmployee = idEmployee;
        Name = name;
        Password = password;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
