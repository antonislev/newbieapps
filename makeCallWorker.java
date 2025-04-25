package classes.worker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class makeCallWorker {
	private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "pba2004ath";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/clientserverexercise?serverTimezone=UTC&useSSL=false";

    public makeCallWorker() {
        // Constructor
    }
    
    public String phoneExists(String phone ) {
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
    	    // Check clients table
    	    String clientQuery = "SELECT * FROM clients WHERE phoneID = ? ";
    	    PreparedStatement clientStatement = connection.prepareStatement(clientQuery);
    	    clientStatement.setString(1, phone);
    	    ResultSet clientResultSet = clientStatement.executeQuery();
    	    if (clientResultSet.next()) {
    	    	 String name = clientResultSet.getString("name");
    	    	 return name;
    	    }
    	    
    } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    	return "";
}
    
    
    public void saveCall(boolean exists, String name, String phone) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String query;
                if (exists) {
                    query = "INSERT INTO calls (duration, callRecipient, status,RecipientName) VALUES (10, ?, 1,?)";
                   
                } else {
                    query = "INSERT INTO calls (duration, callRecipient, status,RecipientName) VALUES (0, ?, 0,?)";
                    
                }
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, phone);
                    statement.setString(2, name);
                   // System.out.println(query);
                    statement.executeUpdate();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
