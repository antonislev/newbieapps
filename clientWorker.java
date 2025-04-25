package classes.worker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern;

import classes.models.Client;

public class clientWorker {
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(".*\\d.*");
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "pba2004ath";
   // private PasswordEncryptorDecryptor pass = new PasswordEncryptorDecryptor();

    public clientWorker() {
    }

    public int registerClient(Client client) throws ClassNotFoundException {
        String INSERT_CLIENT_SQL = "INSERT INTO clients" +
            " (username, password, name, surname, role, taxId, phoneID, money) VALUES " +
            " (?, ?, ?, ?, ?, ?, ?, ?);";

        int result = 0;

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/clientserverexercise?serverTimezone=UTC&useSSL=false", DB_USER, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLIENT_SQL)) {

            preparedStatement.setString(1, client.getUsername());
            preparedStatement.setString(3, client.getName());
            preparedStatement.setString(4, client.getSurname());
            preparedStatement.setString(5, client.getCharacteristic());
            preparedStatement.setString(6, client.getAFM());
            preparedStatement.setString(7, client. getPhoneNumber().getNumber());
            preparedStatement.setFloat(8, (float) client.getFunds());

            if (!PASSWORD_PATTERN.matcher(client.getPassword()).matches()) {
                System.err.println("Password must contain at least one number.");
                return 0;
            }

            // Check if fields exceed maximum length
            if (client.getUsername().length() > 30 || client.getPassword().length() > 30 || 
                client.getName().length() > 50 || client.getSurname().length() > 50 || 
                client.getCharacteristic().length() > 50 || client.getAFM().length() > 15 || 
                client. getPhoneNumber().getNumber().length() > 10) {
                System.err.println("Fields must not exceed their maximum allowed lengths.");
                return 0;
            }

            preparedStatement.setString(2, client.getPassword());
            System.out.println(preparedStatement);
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
        return result;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
