package classes.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import classes.models.Client;
import classes.models.PhoneNumber;
import classes.models.Program;
import classes.worker.clientWorker;
import classes.worker.phonenumberWorker;
import classes.worker.programWorker;

/**
 * Servlet implementation class ClientServlet
 */
@WebServlet("/registerClient")
public class clientServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private clientWorker clientWorker = new clientWorker();
    private phonenumberWorker phoneWorker=new phonenumberWorker();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public clientServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String role = request.getParameter("role");
        String taxId = request.getParameter("taxId");
        String phoneID = request.getParameter("phoneID");
        String program = request.getParameter("program");
        
			Program programchosen;
			try {
			//	System.out.println("I am in fro creating phone");
				programchosen = programWorker.getProgram(program);
				 PhoneNumber phoneString= new PhoneNumber(phoneID,programchosen);
				 System.out.println("Creation ok");
			       // String phonestr = phoneString.getNumber();
			        		
			        
			        Client client = new Client(username, name, surname, role, taxId, phoneString, password);

			        try {
			        	phoneWorker.registerPhoneNumber(phoneString);
			            clientWorker.registerClient(client);
			        } catch (Exception e) {
			            e.printStackTrace();
			        }
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Program not found");
			}
		
        
       

        response.sendRedirect("login.jsp");
    }
}
