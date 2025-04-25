package classes.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import classes.models.Admin;
import classes.models.Client;
import classes.models.Seller;
import classes.worker.loginWorker;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/login")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private loginWorker loginworker=new loginWorker();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");
	    try {
	        Object rs = loginworker.authenticateUser(username, password);
	        
	        if (rs instanceof Admin) {
	            Admin admin = (Admin) rs;
	            request.setAttribute("admin", admin);
	            request.getRequestDispatcher("/adminDetails.jsp").forward(request, response);
	        } else if (rs instanceof Seller) {
	            Seller seller = (Seller) rs;
	            request.setAttribute("seller", seller);
	            request.getRequestDispatcher("/sellerDetails.jsp").forward(request, response);
	        } else if (rs instanceof Client) {
	            Client client = (Client) rs;
	           // System.out.println("Client object: " + client);

	           
	           request.setAttribute("client", client);
	           
	            request.getRequestDispatcher("/clientDetails.jsp").forward(request, response);
	            
	        } else {
	            // Handle the case where authentication fails or user type is unknown
	        	
	            response.sendRedirect("login.jsp?error=Invalid+credentials");
	        }
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	        response.sendRedirect("login.jsp?error=Authentication+failed");
	    }
	}

}
