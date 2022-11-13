

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Database.DatabaseManager;
import entity.User;
import repo.UserRepository;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		submitData.add(request.getParameter("username"));
		submitData.add(request.getParameter("password"));
		
		//System.out.println(submitData.get(0) + " " + submitData.get(1));
		
		//DatabaseManager dbm = new DatabaseManager();
		UserRepository UR = new UserRepository();
		HttpSession session = request.getSession();

		try {
			//User loggedIn = dbm.loginUser(submitData.get(0), submitData.get(1));
			User loggedIn = UR.selectOne(submitData.get(0), submitData.get(1));
			if(loggedIn != null) {
				session.setAttribute("LoggedIn", loggedIn);
			} else {
				String message = "Could not Login to Account";
				response.sendError(500, message);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		doGet(request, response);
	}

}
