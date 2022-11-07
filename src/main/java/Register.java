

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DatabaseManager;
import Database.User;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
		ArrayList<String> submitData = new ArrayList<String>();
		submitData.add(request.getParameter("password"));
		submitData.add(request.getParameter("email"));
		submitData.add(request.getParameter("phone"));
		submitData.add(request.getParameter("fname"));
		submitData.add(request.getParameter("lname"));
		submitData.add(request.getParameter("confirmpassword"));
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		
		User newUser = new User(submitData.get(0), submitData.get(1), submitData.get(2), dtf.format(now), submitData.get(3), submitData.get(4), 0, false);
		
		DatabaseManager dbm = new DatabaseManager();
		String userResponse = dbm.createUser(newUser);
		
		System.out.println(userResponse);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/");
		dispatcher.forward(request, response);
		
		doGet(request, response);
	}

}
