package com.csd.beta.provisio;

import com.csd.beta.provisio.Database.News;
import com.csd.beta.provisio.repo.NewsRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;


/**
 * Servlet implementation class Index
 */
@WebServlet("/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Index() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			                                                                                      IOException {
		NewsRepository NR = new NewsRepository();

		List<News> allNews = NR.getAll();
		Collections.reverse(allNews);

		//for(int i = 0; i< allNews.size(); i++) {
		//	System.out.println(allNews.get(i).getTitle());
		//}

		request.setAttribute("allNews", allNews);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			                                                                                       IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
