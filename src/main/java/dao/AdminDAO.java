package dao;

import connection.ConnectionFactory;
import model.Administrator;
import model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

public class AdminDAO extends AbstractDAO<Administrator>{

    public String createSelect() {
        return "SELECT * FROM administrator WHERE Name = ? AND Password = ?;";
    }

    public Administrator findAdmin(String Name, String Password){
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
                int id = resultSet.getInt("idAdministrator");
                String name = resultSet.getString("Name");
                String pass = resultSet.getString("Password");
                return new Administrator(id, name, pass);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Admin" + "DAO:login " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
}
