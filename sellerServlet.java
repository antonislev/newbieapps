package classes.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import classes.models.Admin;
import classes.models.Seller;
import classes.worker.adminWorker;
import classes.worker.sellerWorker;

/**
 * Servlet implementation class sellerServlet
 */
@WebServlet("/registerSeller")
public class sellerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private sellerWorker sellerworker=new sellerWorker();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sellerServlet() {
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
		String firstName = request.getParameter("name");
        String lastName = request.getParameter("surname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String company = request.getParameter("company");
        String role = request.getParameter("role");
        
        System.out.println(firstName);
        Seller seller = new Seller(username,firstName,lastName,role,company,password);
     

        try {
        	sellerworker.registerSeller(seller);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        response.sendRedirect("login.jsp");
	}

}
