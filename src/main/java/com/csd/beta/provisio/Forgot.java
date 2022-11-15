package com.csd.beta.provisio;

import com.csd.beta.provisio.entity.User;
import com.csd.beta.provisio.exception.ProvisioException;
import com.csd.beta.provisio.repo.UserRepository;
import com.csd.beta.provisio.util.Logger;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Servlet implementation class Forgot
 */
@WebServlet("/Forgot")
public class Forgot extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UserRepository userRepository;
	private final Logger logger;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Forgot() {
		super();
		userRepository = new UserRepository();
		logger = new Logger(Forgot.class.getSimpleName());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			                                                                                      IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			                                                                                       IOException {
		final PrintWriter out = response.getWriter();
		final JsonObject  myObj = new JsonObject();
		final Map<String, String> newData = new HashMap<>();
		response.setContentType("application/json");

		try {
			newData.put("username", request.getParameter("username").trim());
			newData.put("newpassword", request.getParameter("newpassword").trim());
			newData.put("confirmnewpassword", request.getParameter("confirmnewpassword").trim());

			User foundUser = userRepository.getUserByUserName(newData.get("username"));
			if(foundUser == null) {
				throw new ProvisioException.ForgotException("No account was found with that email");
			}

			Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$");
			Matcher matcher = pattern.matcher(newData.get("newpassword"));
			boolean matchFound = matcher.find();
			if (!newData.get("newpassword").equals(newData.get("confirmnewpassword"))) {
				throw new ProvisioException.ForgotException("Passwords do not match");
			} else if(newData.get("newpassword").length() <= 7 || !matchFound) {
				throw new ProvisioException.RegistrationException("Password must be at least 8 characters and include 1 uppercase letter and a number");
			}

			userRepository.changeUserPassword(foundUser, newData.get("newpassword"));
			logger.i("doPost", foundUser);

			myObj.addProperty("success", true);
			myObj.addProperty("msg", "Password has been reset");
		} catch (Exception e) {
			logger.e("doPost", e);
			myObj.addProperty("success", false);
			myObj.addProperty("msg", e.getMessage());
		}

		out.println(myObj);
		out.close();
		doGet(request, response);
	}

}
