package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Message;
import service.MessageService;

@WebServlet (urlPatterns={"/deletemessage"})
public class DeleteMessageServlet  extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Message messages = getDeleteMessage(request);
		new MessageService().delete(messages);
		response.sendRedirect("./");
	}

	private Message getDeleteMessage(HttpServletRequest request)throws IOException, ServletException {
		Message status = new Message();
		status.setUserId(Integer.parseInt(request.getParameter("message.id")));
		return status;
	}
}