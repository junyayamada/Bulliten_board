package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UserComment;
import beans.UserMessage;
import service.CommentService;
import service.MessageService;

@WebServlet (urlPatterns={"/index.jsp"})
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		List<UserMessage> messages = new MessageService().getMessage();
		request.setAttribute("messages", messages);

		List<UserComment> comments = new CommentService().getComment();
		request.setAttribute("comments", comments);

		request.getRequestDispatcher("/top.jsp").forward(request, response);
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<String> comments = new ArrayList<String>();

		request.setAttribute("comments", comments);
		request.setAttribute("start", request.getParameter("start"));
		request.setAttribute("end", request.getParameter("end"));

		request.getRequestDispatcher("/top.jsp").forward(request, response);
	}
}