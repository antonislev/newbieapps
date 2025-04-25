package classes.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import classes.worker.makeCallWorker;

/**
 * Servlet implementation class createCallServlet
 */
@WebServlet("/createCall")
public class createCallServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private makeCallWorker worker=new makeCallWorker();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public createCallServlet() {
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
		//
	//	System.out.println("This is a test");
		String phone = request.getParameter("number");
	//	System.out.println("SEND ok");
		String reciepient = request.getParameter("name");
		String name=worker.phoneExists(phone);
		if (name!="") {
			worker.saveCall(true, name, phone);
			// System.out.println("Creation ok");
		}
		else {
			worker.saveCall(false, reciepient, phone);
		}
		 response.sendRedirect("call.jsp");
	}
	}


