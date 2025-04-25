package classes.worker;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import classes.models.Admin;
import java.util.regex.Pattern;

public class adminWorker {
	private static final Pattern PASSWORD_PATTERN = Pattern.compile(".*\\d.*");
	private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "pba2004ath";
    private PasswordEncryptorDecryptor pass= new PasswordEncryptorDecryptor();
	
	public adminWorker() {
		
	}

    public int registerAdmin(Admin admin) throws ClassNotFoundException {
        String INSERT_USERS_SQL = "INSERT INTO admin" +
            "  (username, password, name, surname, role) VALUES " +
            " (?, ?, ?, ?, ?);";

        int result = 0;

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/clientserverexercise?serverTimezone=UTC&useSSL=false", DB_USER, DB_PASSWORD);

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(3, admin.getName());
            preparedStatement.setString(4, admin.getSurname());
            preparedStatement.setString(1, admin.getUsername());
        //    preparedStatement.setString(2, admin.getPassword());
            preparedStatement.setString(5, admin.getCharacteristic());
            if (!PASSWORD_PATTERN.matcher(admin.getPassword()).matches()) {
            	System.err.println("Password must contain at least one number.");
                return 0;
            }

            // Check if fields exceed maximum length
            if ( admin.getUsername().length() > 30 || admin.getPassword().length() > 30 || admin.getName().length() > 30 || admin.getSurname().length() > 30 ||  admin.getCharacteristic().length() > 30) {
            	System.err.println("Fields must not exceed 30 characters in length.");
                return 0;
            }
            

            preparedStatement.setString(2,admin.getPassword());
            // Step 3: Execute the query or update query
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return result;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
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