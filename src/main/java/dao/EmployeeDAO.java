package dao;

import connection.ConnectionFactory;
import model.Administrator;
import model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

public class EmployeeDAO extends AbstractDAO<Employee>{

    public String createSelect() {
        return "SELECT * FROM employee WHERE Name = ? AND Password = ?;";
    }

    public Employee findEmployee(String Name, String Password){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelect();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, Name);
            statement.setString(2, Password);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                int id = resultSet.getInt("idEmployee");
                String name = resultSet.getString("Name");
                String pass = resultSet.getString("Password");
                return new Employee(id, name, pass);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Employee" + "DAO:login " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
}
