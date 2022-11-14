package com.csd.beta.provisio;

import com.csd.beta.provisio.entity.User;
import com.csd.beta.provisio.repo.UserRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Servlet implementation class Forgot
 */
@WebServlet("/Forgot")
public class Forgot extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Forgot() {
		super();
		// TODO Auto-generated constructor stub
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
		ArrayList<String> submitData = new ArrayList<String>();
		submitData.add(request.getParameter("username"));
		submitData.add(request.getParameter("newpassword"));
		submitData.add(request.getParameter("confirmnewpassword"));

		UserRepository UR = new UserRepository();
		User reset = UR.getUserByUserName(submitData.get(0));

		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		JsonObject myObj = new JsonObject();

		Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$");
		Matcher matcher = pattern.matcher(submitData.get(1));
		boolean matchFound = matcher.find();

		if (submitData.get(1).equals(submitData.get(2))) {
			if (submitData.get(1).length() <= 7 || !matchFound) {
				myObj.addProperty("success", false);
				myObj.addProperty("msg", "Password must be atleast 8 characters and include 1 uppercase " +
						                         "letter and a number");

				out.println(myObj);
				out.close();
			} else {
				try {
					UR.changeUserPassword(reset, submitData.get(1));
					myObj.addProperty("success", true);
					myObj.addProperty("msg", "Password has been reset");

					out.println(myObj);
					out.close();
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		} else {
			myObj.addProperty("success", false);
			myObj.addProperty("msg", "Passwords do not match");

			out.println(myObj);
			out.close();
		}
		doGet(request, response);
	}

}