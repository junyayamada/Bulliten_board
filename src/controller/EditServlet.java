package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import beans.Branch;
import beans.Department;
import beans.User;
import service.BranchService;
import service.DepartmentService;
import service.UserService;

@WebServlet(urlPatterns = { "/edit" })
@MultipartConfig(maxFileSize = 100000)
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		User loginUser = (User) request.getSession().getAttribute("loginUser");
		List<Branch> branch = new BranchService().getBranch();
		List<Department> department = new DepartmentService().getDepartment();
		List<String> errormessages = new ArrayList<String>();
		HttpSession session = request.getSession();
		request.setAttribute("branch", branch);
		request.setAttribute("department", department);
		if (request.getParameter("id") == null || request.getParameter("id")  == "") {
			session.setAttribute("errorMessages", errormessages);
			errormessages.add("不正なアクセスです");
			response.sendRedirect("./management");
			return;
		} else {
			if (!request.getParameter("id").matches("[0-9]+$")){
				session.setAttribute("errorMessages", errormessages);
				response.sendRedirect("./management");
				return;
			} else {
				if (session.getAttribute("edit") == null) {
					User editUser = new UserService().getUser(Integer.parseInt(request.getParameter("id")));
					request.setAttribute("edit", editUser);
					request.getRequestDispatcher("edit.jsp").forward(request, response);
				}
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		List<String>errormessages = new ArrayList<String>();
		HttpSession session = request.getSession();
		User editUser = getEditUser(request);


		if (isValid(request, errormessages)) {
			new UserService().update(editUser);
			request.setAttribute("edit", editUser);
			response.sendRedirect("management");
		} else {

			List<Branch> branch = new BranchService().getBranch();
			List<Department> department = new DepartmentService().getDepartment();

			request.setAttribute("branch", branch);
			request.setAttribute("department", department);
			request.setAttribute("edit", editUser);

			request.setAttribute("errorMessages", errormessages);
			request.getRequestDispatcher("edit.jsp").forward(request, response);
		}
	}

	private User getEditUser(HttpServletRequest request)throws IOException, ServletException {

		User editUser = new User();
		editUser.setId(Integer.parseInt(request.getParameter("id")));
		editUser.setName(request.getParameter("name"));
		editUser.setAccount(request.getParameter("account"));
		editUser.setPassword(request.getParameter("password"));
		editUser.setConfirmPassword(request.getParameter("confirmPassword"));
		editUser.setBranchId(Integer.parseInt(request.getParameter("branch_id")));
		editUser.setDepartmentId(Integer.parseInt(request.getParameter("department_id")));
		return editUser;
	}

	private boolean isValid (HttpServletRequest request, List<String>errormessages) throws IOException, ServletException {
		String name = request.getParameter("name");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		User editUser = getEditUser(request);

		if (StringUtils.isEmpty(name)) {
			errormessages.add("名前を入力してください");
		} else {
			if (10 <= name.length()) {
				errormessages.add("名前を10文字以下で入力してください");
			}
		}

		if (StringUtils.isEmpty(account)) {
			errormessages.add("ログインIDを入力してください");
		} else {
			if (!account.matches("[a-zA-Z0-9]{6,20}$")) {
				errormessages.add("ログインIDを半角英数字6文字以上20文字以下で入力してください");
			} else {
				User user = new UserService().getSuffer(account) ;
				if (user != null && user.getId() != editUser.getId()) {
					errormessages.add("そのログインIDは使用できません");
				}
			}
		}

		if (!StringUtils.isEmpty(password)) {
			if (6 > password.length()) {
				errormessages.add("パスワードを6文字以上で入力してください");
			} else {
				if (!password.equals(confirmPassword)) {
					errormessages.add("パスワードが違います");
				}
			}
		}

		if (errormessages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}