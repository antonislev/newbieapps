package classes.worker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import classes.models.PhoneNumber;

public class phonenumberWorker {
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "pba2004ath";

    public phonenumberWorker() {
    }

    public int registerPhoneNumber(PhoneNumber phoneNumber) throws ClassNotFoundException {
        String INSERT_PHONE_SQL = "INSERT INTO phonenumber (phoneID, programID) VALUES (?, ?);";
        int result = 0;

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/clientserverexercise?serverTimezone=UTC&useSSL=false", DB_USER, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PHONE_SQL)) {

            // Set parameters for the insert statement
            preparedStatement.setString(1, phoneNumber.getNumber());
            preparedStatement.setInt(2, phoneNumber.getProgram().getId());

            // Execute the insert query
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
        return result;
    }

    /*public PhoneNumber getPhoneNumber(String phoneID) throws ClassNotFoundException {
        String SELECT_PHONE_SQL = "SELECT phoneID, programID FROM phonenumber WHERE phoneID = ?;";
        PhoneNumber phoneNumber = null;

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/clientserverexercise?serverTimezone=UTC&useSSL=false", DB_USER, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PHONE_SQL)) {

            // Set parameters for the select statement
            preparedStatement.setString(1, phoneID);

            // Execute the select query
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    String retrievedPhoneID = rs.getString("phoneID");
                    int programID = rs.getInt("programID");
                    phoneNumber = new PhoneNumber(retrievedPhoneID, programID);
                }
            }

        } catch (SQLException e) {
            printSQLException(e);
        }
        return phoneNumber;
    }*/

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
