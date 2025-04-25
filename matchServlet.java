package classes.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import classes.worker.matchClientWorker;

/**
 * Servlet implementation class matchServlet
 */
@WebServlet("/match")
public class matchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	matchClientWorker matcher= new matchClientWorker();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public matchServlet() {
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
		 String phone = request.getParameter("phonenumberClient");
		 response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();
		 String data=matcher.matchClient(phone);
		 try {
	            request.setAttribute("search", data);
	            request.getRequestDispatcher("sellerDetails.jsp").forward(request, response);
	        } catch (Exception e) {
	            out.println("Database connection problem: " + e.getMessage());
	        } finally {
	            out.close();
	        }
		 
		
	}

}
