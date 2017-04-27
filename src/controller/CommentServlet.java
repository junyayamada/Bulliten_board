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

import beans.Comment;
import beans.User;
import service.CommentService;

@WebServlet(urlPatterns = { "/comment" })
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("/top.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8"); //リクエストボディ(POSTメソッドはこの中)に含まれるデータの文字コードを指定した値に書き換える
		List<String> errorMessages = new ArrayList<String>();
		HttpSession session = request.getSession(); //セッションを取得

		if (isValid(request, errorMessages)) {

			Comment text = new Comment();
			User user = (User) session.getAttribute("loginUser");
			text.setText(request.getParameter("text"));
			text.setMessageId(Integer.parseInt(request.getParameter("message_id")));
			text.setUserId(user.getId());

			new CommentService().register(text);

			response.sendRedirect("./");
		} else {
			session.setAttribute("errorMessages", errorMessages);
			response.sendRedirect("./");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String>errormessages) { //入力値チェック

		String text = request.getParameter("text");

		if (StringUtils.isEmpty(text)) {
			errormessages.add("コメントを入力してください");
		}
		if (500 < text.length()) {
			errormessages.add("500文字以下で入力してください");
		}
		if (errormessages.size() == 0){
			return true;
		} else {
			return false;
		}
	}
}