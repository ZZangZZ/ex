package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.DaoImpl;
import model.Member;

/**
 * 
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
		    DaoImpl memberDao = new DaoImpl();
		    String id = request.getParameter("id");
		    String pw = request.getParameter("pw");
		    Member member = memberDao.exist(id, pw);
		    if (member != null) {
		        HttpSession session = request.getSession();
		        session.setAttribute("member", member);
		        RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
		        rd.forward(request, response);
		    } else {
		        String alertScript = "<script>alert('없는 아이디와 비밀번호입니다.');" + "window.location.href = 'login.jsp';</script>";
		        response.getWriter().println(alertScript);
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
}