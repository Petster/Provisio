package com.csd.beta.provisio;

import com.csd.beta.provisio.entity.User;
import com.csd.beta.provisio.repo.UserRepository;
import com.csd.beta.provisio.util.Logger;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private final UserRepository userRepository;
	private static final long serialVersionUID = 1L;
	private final Logger logger;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
		userRepository = new UserRepository();
		logger = new Logger(Login.class.getSimpleName());
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ArrayList<String> submitData = new ArrayList<String>();
		String userName = request.getParameter("username");
		String password = request.getParameter("username");
		logger.i("doPost username", userName);
		logger.i("doPost password", password);

		submitData.add(userName);
		submitData.add(password);
		
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		JsonObject myObj = new JsonObject();
		
		UserRepository UR = new UserRepository();
		HttpSession session = request.getSession();

		try {
			User loggedIn = UR.getUserByUserNameAndPassword(submitData.get(0), submitData.get(1));
			if(loggedIn == null || Objects.equals(loggedIn.getEmail(), "")) {
				myObj.addProperty("success", false);
				myObj.addProperty("msg", "No account exists for this email or the password was incorrect");
			} else {
				session.setAttribute("LoggedIn", loggedIn);
				myObj.addProperty("success", true);
			}

			out.println(myObj);
			out.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		doGet(request, response);
	}

}
