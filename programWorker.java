package classes.worker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classes.models.Program;

public class programWorker {
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "pba2004ath";

    public programWorker() {
    }

    public int registerProgram(Program program) throws ClassNotFoundException {
        String INSERT_PROGRAM_SQL = "INSERT INTO program (name, minutes, sms, gb, minutesleft, smsleft, gbleft, baseCharge, minutesextra) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        int result = 0;

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/clientserverexercise?serverTimezone=UTC&useSSL=false", DB_USER, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROGRAM_SQL)) {

            // Set parameters for the insert statement
            preparedStatement.setString(1, program.getName());
            preparedStatement.setInt(2, program.getMinutes());
            preparedStatement.setInt(3, program.getSms());
            preparedStatement.setInt(4, program.getGb());
            preparedStatement.setInt(5, program.getMinutesLeft());
            preparedStatement.setInt(6, program.getSmsLeft());
            preparedStatement.setInt(7, program.getGbLeft());
            preparedStatement.setDouble(8, program.getBaseCharge());
            preparedStatement.setInt(9, program.getMinutesExtra());

            // Execute the insert query
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
        return result;
    }

    public static Program getProgram(String name) throws ClassNotFoundException {
        String SELECT_PROGRAM_SQL = "SELECT programID,name, minutes, sms, gb, minutesleft, smsleft, gbleft, baseCharge, minutesextra FROM programms WHERE name = ?;";
        Program program = null;

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/clientserverexercise?serverTimezone=UTC&useSSL=false", DB_USER, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROGRAM_SQL)) {

            // Set parameters for the select statement
            preparedStatement.setString(1, name);

            // Execute the select query
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                //	System.out.println("I AM IN THE DATABASE");//test
                	int id=rs.getInt("programID");;
                //	System.out.println("Id is =" + id);
                    String retrievedName = rs.getString("name");
                 //   System.out.println("Name is =" + name);
                    int minutes = rs.getInt("minutes");
                    int sms = rs.getInt("sms");
                    int gb = rs.getInt("gb");
                    int minutesLeft = rs.getInt("minutesleft");
                    int smsLeft = rs.getInt("smsleft");
                    int gbLeft = rs.getInt("gbleft");
                    double baseCharge = rs.getDouble("baseCharge");
                    int minutesExtra = rs.getInt("minutesextra");
                    List<Integer> services= new ArrayList<>();
                    services.add(0,minutes);
                    services.add(1,gb);
                    services.add(2,sms);

                    program = new Program(retrievedName,services,baseCharge,id);
                }
            }

        } catch (SQLException e) {
            printSQLException(e);
        }
        return program;
    }

    private static void printSQLException(SQLException ex) {
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
