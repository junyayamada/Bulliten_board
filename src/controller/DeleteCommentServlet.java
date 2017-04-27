package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Comment;
import service.CommentService;

@WebServlet (urlPatterns={"/deletecomment"})
public class DeleteCommentServlet  extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Comment comments = getDeleteComment(request);
		new CommentService().delete(comments);
		response.sendRedirect("./");
	}

	private Comment getDeleteComment(HttpServletRequest request)throws IOException, ServletException {
		Comment status = new Comment();
		status.setUserId(Integer.parseInt(request.getParameter("comment.id")));
		return status;
	}
}