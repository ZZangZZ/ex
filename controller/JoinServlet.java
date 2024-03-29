package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.DaoImpl;
import model.Member;

/**
 * Servlet implementation class JoinServlet
 */
@WebServlet("/JoinServlet")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JoinServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			DaoImpl memberinfoDao = new DaoImpl();
			memberinfoDao.insert(new Member().setId(request.getParameter("id")).setPw(request.getParameter("pw"))
					.setName(request.getParameter("name")).setEmail(request.getParameter("email"))
					.setPhone(request.getParameter("phone")));

			//String message = "회원 가입이 완료되었습니다!";
			//String alertScript = "<script>alert('" + message + "');</script>";
			//response.getWriter().println(alertScript);

			// response.sendRedirect("ListServlet");
			//RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			//rd.forward(request, response);
			String alertScript = "<script>alert('회원 가입이 완료되었습니다!');"
					+ "window.location.href = 'login.jsp';</script>";
				response.getWriter().println(alertScript);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
