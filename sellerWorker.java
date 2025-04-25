package classes.worker;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import classes.models.Seller;
import java.util.regex.Pattern;

public class sellerWorker {
	private static final Pattern PASSWORD_PATTERN = Pattern.compile(".*\\d.*");
	private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "pba2004ath";
    private PasswordEncryptorDecryptor pass= new PasswordEncryptorDecryptor();
	
	public sellerWorker() {
		
	}

    public int registerSeller(Seller seller) throws ClassNotFoundException {
        String INSERT_USERS_SQL = "INSERT INTO sellers" +
            "  (username, password, name, surname, role,company) VALUES " +
            " (?, ?, ?, ?, ?,?);";

        int result = 0;

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/clientserverexercise?serverTimezone=UTC&useSSL=false", DB_USER, DB_PASSWORD);

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(3, seller.getName());
            preparedStatement.setString(4, seller.getSurname());
            preparedStatement.setString(1, seller.getUsername());
            preparedStatement.setString(6, seller.getCompany());
        //    preparedStatement.setString(2, admin.getPassword());
            preparedStatement.setString(5, seller.getCharacteristic());
            if (!PASSWORD_PATTERN.matcher(seller.getPassword()).matches()) {
            	System.err.println("Password must contain at least one number.");
                return 0;
            }

            // Check if fields exceed maximum length
            if ( seller.getUsername().length() > 30 || seller.getPassword().length() > 30 || seller.getName().length() > 30 || seller.getSurname().length() > 30 ||  seller.getCharacteristic().length() > 30) {
            	System.err.println("Fields must not exceed 30 characters in length.");
                return 0;
            }
            

            preparedStatement.setString(2,pass.encrypt(seller.getPassword()));
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