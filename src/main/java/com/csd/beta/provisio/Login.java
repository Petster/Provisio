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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UserRepository userRepository;
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
		final HttpSession session = request.getSession();
		final PrintWriter out = response.getWriter();
		final JsonObject myObj = new JsonObject();
		final Map<String, String> submitData = new HashMap<>(2);
		response.setContentType("application/json");

		try {
			// sanitize the input coming from client
			submitData.put("username", request.getParameter("username").toLowerCase().trim());
			submitData.put("password", request.getParameter("password").trim());

			User foundUser = userRepository.getUserByUserNameAndPassword(submitData.get("username"), submitData.get(
					"password"));
			if (foundUser == null || Objects.equals(foundUser.getEmail(), "")) { // no auth
				throw new ProvisioException.LoginException("No account exists for this email or the password was " +
						                                           "incorrect");
			}

			// success
			session.setAttribute("LoggedIn", foundUser);
			myObj.addProperty("success", true);
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
