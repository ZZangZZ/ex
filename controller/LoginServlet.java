package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.DaoImpl;
import model.Member;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			DaoImpl memberinfoDao = new DaoImpl();
			Member member = memberinfoDao.exist(request.getParameter("id"), request.getParameter("pw"));
			
			if(request.getParameter("id").equals(member)) {
				response.sendRedirect("ListServlet");
			} else {
				// 로그인 실패 처리
				// 로그인 페이지를 다시 보여주는 등의 처리를 수행합니다.
				response.sendRedirect("login.jsp");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
