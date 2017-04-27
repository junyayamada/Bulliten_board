package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;
import service.UserService;

@WebServlet (urlPatterns={"/deleteuser"})
public class DeleteUserServlet  extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		User user = getDeleteUserStatus(request);
		new UserService().Delete(user);
		response.sendRedirect("management");
	}

	private User getDeleteUserStatus(HttpServletRequest request)throws IOException, ServletException {
		User status = new User();

		status.setId(Integer.parseInt(request.getParameter("id")));

		System.out.println("hhh");

		return status;
	}
}