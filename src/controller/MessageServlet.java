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

import org.apache.commons.lang.StringUtils;

import beans.Message;
import beans.User;
import service.MessageService;

@WebServlet(urlPatterns = { "/message" })
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
		request.getRequestDispatcher("message.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<String> errormessages = new ArrayList<String>();
		HttpSession session = request.getSession();
		session.setAttribute("errorMessages", errormessages);
		String subject = request.getParameter("subject");
		String category = request.getParameter("category");
		String newText = request.getParameter("text");
		request.setAttribute("subject", subject);
		request.setAttribute("category", category);
		request.setAttribute("Text", newText);

		if (isValid(request, errormessages)) {

			Message text = new Message();
			User user = (User) session.getAttribute("loginUser");
			text.setSubject(request.getParameter("subject"));
			text.setCategory(request.getParameter("category"));
			text.setText(request.getParameter("text"));
			text.setUserId(user.getId());

			new MessageService().register(text);
			session.removeAttribute("errorMessages");
			response.sendRedirect("./");
		} else {
			session.setAttribute("errorMessages", errormessages);
			subject = request.getParameter("subject");
			category = request.getParameter("category");
			newText = request.getParameter("text");
			request.setAttribute("subject", subject);
			request.setAttribute("category", category);
			request.setAttribute("text", newText);

			request.getRequestDispatcher("message.jsp").forward(request, response);
		}
	}


	private boolean isValid(HttpServletRequest request, List<String>errormessages) {

		String subject = request.getParameter("subject");
		String category = request.getParameter("category");
		String text = request.getParameter("text");

		if (StringUtils.isEmpty(subject)) {
			errormessages.add("件名を入力してください");
		}
		if (50 < subject.length()) {
			errormessages.add("50文字以下で入力してください");
		}

		if (StringUtils.isEmpty(category)) {
			errormessages.add("カテゴリーを入力してください");
		}
		if (10 < category.length()) {
			errormessages.add("10文字以下で入力してください");
		}

		if (StringUtils.isEmpty(text)) {
			errormessages.add("本文を入力してください");
		}
		if (1000 < text.length()) {
			errormessages.add("1000文字以下で入力してください");
		}
		if (errormessages.size() == 0){
			return true;
		} else {
			return false;
		}
	}
}