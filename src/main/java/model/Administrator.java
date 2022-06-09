package model;

public class Administrator {

    private int idAdministrator;
    private String Name;
    private String Password;

    Administrator(){ }

    public Administrator(String Name, String Password){
        this.Name = Name;
        this.Password = Password;
    }

    public Administrator(int idAdministrator, String name, String password) {
        this.idAdministrator = idAdministrator;
        Name = name;
        Password = password;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public String getPassword() {
        return Password;
    }
}
