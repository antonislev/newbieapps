package classes.worker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowPacketsWorker {
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "pba2004ath";

    public ShowPacketsWorker() {
    }

    public String fetchPackets() {
        String sql = "SELECT * FROM programms";
        StringBuilder packetsHtml = new StringBuilder("<table  class=\"inner-border\" >");
        packetsHtml.append("<tr><th>Packet ID</th><th>Packet Name</th><th>Minutes</th><th>SMS</th><th>GB</th><th>Base Charge</th></tr>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Ensure correct driver class
            try (Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/clientserverexercise?serverTimezone=UTC&useSSL=false", 
                    DB_USER, DB_PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    packetsHtml.append("<tr >");

                    int id = resultSet.getInt("programID");
                    packetsHtml.append("<td >").append(id).append("</td>");
                    String name = resultSet.getString("name");
                    packetsHtml.append("<td>").append(name).append("</td>");
                    int minutes = resultSet.getInt("minutes");
                    packetsHtml.append("<td>").append(minutes).append("</td>");
                    int sms = resultSet.getInt("sms");
                    packetsHtml.append("<td>").append(sms).append("</td>");
                    int gb = resultSet.getInt("gb");
                    packetsHtml.append("<td>").append(gb).append("</td>");
                    double basecharge = resultSet.getDouble("basecharge");
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

    private void printSQLException(SQLException e2) {
        for (Throwable e : e2) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = e2.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
