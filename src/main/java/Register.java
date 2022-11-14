

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import Database.DatabaseManager;
import repo.UserRepository;
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
		
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		JsonObject myObj = new JsonObject();
		
		Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$");
		Matcher matcher = pattern.matcher(submitData.get(0));
		Boolean matchFound = matcher.find();
		
		if(submitData.get(0).length() <= 7 || matchFound == false) {
			myObj.addProperty("success", false);
			myObj.addProperty("msg", "Password must be atleast 8 characters and include 1 uppercase letter and a number");
			
			out.println(myObj.toString());
			out.close();
		} else {
			User newUser = new User(submitData.get(0), submitData.get(1), submitData.get(2), dtf.format(now), submitData.get(3), submitData.get(4), 0, false);
			UserRepository UR = new UserRepository();
			
			User results = UR.insertOne(newUser);
			
			//DatabaseManager dbm = new DatabaseManager();
			//String userResponse = dbm.createUser(newUser);
			
			//System.out.println(userResponse);
			
			
			if(results == null) {
				myObj.addProperty("success", false);
				myObj.addProperty("msg", "An account with that email already exists");
				
				out.println(myObj.toString());
				out.close();
				
				//response.sendError(500, "An account with that email already exists");
			} else {
				myObj.addProperty("success", true);
				myObj.addProperty("msg", "Your account was created successfully");
				
				out.println(myObj.toString());
				out.close();
			}
		}
		
		

		doGet(request, response);
	}

}
