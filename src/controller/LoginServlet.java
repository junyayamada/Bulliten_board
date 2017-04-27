package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import service.LoginService;

@WebServlet(urlPatterns = { "/login"})
public class LoginServlet  extends HttpServlet{
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}


	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String account = request.getParameter("account");
		String password = request.getParameter("password");

		request.setAttribute("account", account);
		request.setAttribute("password", password);

		LoginService loginservice = new LoginService();
		User users = loginservice.login(account, password);

		HttpSession session = request.getSession();
		if (users != null && users.getIsStoped() == 0) {
			session.setAttribute("loginUser", users);
			response.sendRedirect("./");
		} else {
			List<String> errormessages = new ArrayList<String>();
			session.removeAttribute("errorMessages");
			session.setAttribute("errorMessages", errormessages);

			errormessages.add("ログインに失敗しました。");

			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
}