package classes.worker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import classes.models.Admin;
import classes.models.Client;
import classes.models.PhoneNumber;
import classes.models.Program;
import classes.models.Seller;
import classes.models.User;

public class loginWorker {
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "pba2004ath";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/clientserverexercise?serverTimezone=UTC&useSSL=false";

    public loginWorker() {
        // Constructor
    }

    public User authenticateUser(String username, String password) throws ClassNotFoundException {
    	Class.forName("com.mysql.cj.jdbc.Driver");
    	try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
    	    // Check clients table
    	    String clientQuery = "SELECT * FROM clients WHERE username = ? AND password = ?";
    	    PreparedStatement clientStatement = connection.prepareStatement(clientQuery);
    	    clientStatement.setString(1, username);
    	    clientStatement.setString(2, password);
    	    
    	    ResultSet clientResultSet = clientStatement.executeQuery();
    	    
    	    if (clientResultSet.next()) {
    	        // Fetch data from the first query
    	        String username1 = clientResultSet.getString("username");
    	        String password1 = clientResultSet.getString("password");
    	        String name = clientResultSet.getString("name");
    	        String surname = clientResultSet.getString("surname");
    	        String role = clientResultSet.getString("role");
    	        String taxId = clientResultSet.getString("taxId");
    	        String phonenumber = clientResultSet.getString("phoneID");
    	        
    	        // Execute the second query
    	        String clientQuery2 = "SELECT cl.id, cl.username, cl.name, cl.phoneID, pr.name as 'program name', pr.programID "
    	                            + "FROM clients AS cl "
    	                            + "JOIN phonenumber as ph ON cl.phoneID=ph.phoneID "
    	                            + "JOIN programms as pr ON pr.programID=ph.programID "
    	                            + "WHERE cl.username = ? AND cl.password = ?";
    	        PreparedStatement clientStatement2 = connection.prepareStatement(clientQuery2);
    	        clientStatement2.setString(1, username);
    	        clientStatement2.setString(2, password);
    	        ResultSet clientResultSet2 = clientStatement2.executeQuery();
    	        
    	        if (clientResultSet2.next()) {  // Move the cursor to the first row
    	            String programName = clientResultSet2.getString("program name");
    	            
    	            Program programchosen = programWorker.getProgram(programName);
    	            PhoneNumber phoneString = new PhoneNumber(phonenumber, programchosen);
    	            
    	            Client client = new Client(username1, name, surname, role, taxId, phoneString, password1);
    	            // Set other properties of the Admin object
    	            return client;
    	        }
    	    }
            // Check admin table
            String adminQuery = "SELECT * FROM admin WHERE username = ? AND password = ?";
            PreparedStatement adminStatement = connection.prepareStatement(adminQuery);
            adminStatement.setString(1, username);
            adminStatement.setString(2, password);
            ResultSet adminResultSet = adminStatement.executeQuery();
            if (adminResultSet.next()) {
            	//System.out.println("FOUND ADMIN");
            	String username1=adminResultSet.getString("username");
            	String password1=adminResultSet.getString("password");
            	String name=adminResultSet.getString("name");
            	String surname=adminResultSet.getString("surname");
            	String role=adminResultSet.getString("role");
            	
            	 Admin admin=new Admin(username1,name,surname,role,password1);
                // Set other properties of the Admin object
                return admin;
            }

            // Check sellers table
            String sellerQuery = "SELECT * FROM sellers WHERE username = ? AND password = ?";
            PreparedStatement sellerStatement = connection.prepareStatement(sellerQuery);
            sellerStatement.setString(1, username);
            sellerStatement.setString(2, password);
            ResultSet sellerResultSet = sellerStatement.executeQuery();
            if (sellerResultSet.next()) {
            	//System.out.println("FOUND ADMIN");
            	String username1=sellerResultSet.getString("username");
            	String password1=sellerResultSet.getString("password");
            	String name=sellerResultSet.getString("name");
            	String surname=sellerResultSet.getString("surname");
            	String role=sellerResultSet.getString("role");
            	String company=sellerResultSet.getString("company");
            	
            	 Seller seller=new Seller(username1,name,surname,role,company,password1);
                // Set other properties of the Admin object
                return seller;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

