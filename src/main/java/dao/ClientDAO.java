package dao;

import connection.ConnectionFactory;
import model.Administrator;
import model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

public class ClientDAO extends AbstractDAO<Client>{

    public String createSelect() {
        return "SELECT * FROM client WHERE Name = ? AND Password = ?;";
    }

    public Client findClient(String Name, String Password){
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

            if(resultSet.next()){
                int id = resultSet.getInt("idClient");
                String name = resultSet.getString("Name");
                String pass = resultSet.getString("Password");
                return new Client(id,name,pass);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Client" + "DAO:login " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }


    public Client findClientById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectWhereQuery("idClient");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while(resultSet.next()){
                int idCl = resultSet.getInt("idClient");
                String name = resultSet.getString("Name");
                String pass = resultSet.getString("Password");
                return new Client(idCl,name,pass);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:findClientById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public Client findClientByName(String nameSearch) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectWhereQuery("Name");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, nameSearch);
            resultSet = statement.executeQuery();

            while(resultSet.next()){
                int idCl = resultSet.getInt("idClient");
                String name = resultSet.getString("Name");
                String pass = resultSet.getString("Password");
                return new Client(idCl,name,pass);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:findClientById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
}
