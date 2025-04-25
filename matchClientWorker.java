package classes.worker;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class matchClientWorker {
	 private static final String DB_USER = "root";
	    private static final String DB_PASSWORD = "pba2004ath";
	    
	    public matchClientWorker() {
	    }
	    public String matchClient(String phone) {
	    	 String sql = "SELECT cl.username,cl.name,cl.surname,cl.phoneID,cl.taxId,pr.name as 'Program' ,pr.baseCharge as 'Total Charge'"
	    	 		+ " FROM phonenumber as ph  Join clients as cl  on cl.phoneID=ph.phoneID Join programms as pr on pr.programID=ph.programID "
	    	 		+ "WHERE ph.phoneID="+ phone + " ; ";
	    	 
	         StringBuilder packetsHtml = new StringBuilder("<table  class=\"inner-search\" >");
	         packetsHtml.append("<tr><th>Username</th><th>Customer Name</th><th>Customer Surname</th><th>PhoneNumber</th><th>TaxID</th><th>Program Name</th><th>Total Charge</th></tr>");
	         try {
	             Class.forName("com.mysql.cj.jdbc.Driver"); // Ensure correct driver class
	             try (Connection connection = DriverManager.getConnection(
	                     "jdbc:mysql://localhost:3306/clientserverexercise?serverTimezone=UTC&useSSL=false", 
	                     DB_USER, DB_PASSWORD);
	            		 PreparedStatement preparedStatement = connection.prepareStatement(sql);
	            		 ResultSet resultSet = preparedStatement.executeQuery()) {

	                 while (resultSet.next()) {
	                     packetsHtml.append("<tr >");

	                     String username = resultSet.getString("username");
	                     packetsHtml.append("<td >").append(username).append("</td>");
	                     String name = resultSet.getString("name");
	                     packetsHtml.append("<td>").append(name).append("</td>");
	                     String surname = resultSet.getString("surname");
	                     packetsHtml.append("<td>").append(surname).append("</td>");
	                     String phonenumber = resultSet.getString("phoneID");
	                     packetsHtml.append("<td>").append(phonenumber).append("</td>");
	                     String taxid = resultSet.getString("taxId");
	                     packetsHtml.append("<td>").append(taxid).append("</td>");
	                     String program = resultSet.getString("Program");
	                     packetsHtml.append("<td>").append(program).append("</td>");
	                     double basecharge = resultSet.getDouble("Total Charge");
	                     packetsHtml.append("<td>").append(basecharge).append("</td>");

	                     packetsHtml.append("</tr>");
	                 }
	                 packetsHtml.append("</table>");
	             }
	         } catch (SQLException | ClassNotFoundException e) {
	             System.out.println("Error in SQL: " + e.getMessage());
	         }
	         return packetsHtml.toString();
	    	
	    }
}
