package com.csd.beta.provisio;

import com.csd.beta.provisio.entity.User;
import com.csd.beta.provisio.exception.ProvisioException;
import com.csd.beta.provisio.repo.UserRepository;
import com.csd.beta.provisio.util.Logger;
import com.google.gson.JsonObject;

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
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UserRepository userRepository;
	private final Logger logger;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
		super();
		userRepository = new UserRepository();
		logger = new Logger(Register.class.getSimpleName());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		final PrintWriter out = response.getWriter();
		final JsonObject myObj = new JsonObject();
		final Map<String, String> registrationData = new HashMap<>(); // using a map to cache params
		response.setContentType("application/json"); // set content to JSON

		try {
			// sanitize input coming from client
			registrationData.put("password", request.getParameter("password").trim());
			registrationData.put("email", request.getParameter("email").toLowerCase().trim());
			registrationData.put("phone", request.getParameter("phone").trim());
			registrationData.put("fname", request.getParameter("fname").trim());
			registrationData.put("lname", request.getParameter("lname").trim());
			registrationData.put("confirmpassword", request.getParameter("confirmpassword").trim());

			// test if user email already exists
			User foundUser = userRepository.getUserByUserName(registrationData.get("email"));
			if (foundUser != null) {
				throw new ProvisioException.RegistrationException("User already exists");
			}

			// test that passwords match and are valid
			Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$");
			Matcher matcher = pattern.matcher(registrationData.get("password"));
			boolean matchFound = matcher.find();
			if (!registrationData.get("password").equals(registrationData.get("confirmpassword"))) {
				throw new ProvisioException.RegistrationException("Passwords do not match");
			} else if (registrationData.get("password").length() <= 7 || !matchFound) {
				throw new ProvisioException.RegistrationException("Password must be at least 8 characters and include "
						                                                  + "1 uppercase letter and a number");
			}

			// save the new user
			User newUser = new User();
			newUser.setEmail(registrationData.get("email"));
			newUser.setPassword(registrationData.get("password"));
			newUser.setPhone(registrationData.get("phone"));
			newUser.setFirstname(registrationData.get("fname"));
			newUser.setLastname(registrationData.get("lname"));

			User createdUser = userRepository.insertOne(newUser);
			logger.i("doPost", createdUser);

			// return success to the client
			myObj.addProperty("success", true);
			myObj.addProperty("msg", "Your account was created successfully");
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
