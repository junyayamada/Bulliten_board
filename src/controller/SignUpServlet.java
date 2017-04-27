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

import beans.Branch;
import beans.Department;
import beans.User;
import service.BranchService;
import service.DepartmentService;
import service.UserService;

@WebServlet (urlPatterns={"/signup"})
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		List<Branch> branch = new BranchService().getBranch();
		List<Department> department = new DepartmentService().getDepartment();

		request.setAttribute("branch", branch);
		request.setAttribute("department", department);

		request.getRequestDispatcher("signup.jsp").forward(request,  response);
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<String> errormessages = new ArrayList<String>();
		HttpSession session = request.getSession();
		session.setAttribute("errorMessages", errormessages);
		int branch_id = Integer.parseInt(request.getParameter("branchId"));
		int department_id = Integer.parseInt(request.getParameter("departmentId"));
		request.setAttribute("name", request.getParameter("name"));
		request.setAttribute("account", request.getParameter("account"));
		request.setAttribute("branchId", branch_id);
		request.setAttribute("departmentId", department_id);

		if(isValid(request, errormessages)) {

			User users = new User();
			users = new User();
			users.setName(request.getParameter("name"));
			users.setAccount(request.getParameter("account"));
			users.setPassword(request.getParameter("password"));
			users.setConfirmPassword(request.getParameter("confirmpassword"));
			users.setBranchId(Integer.parseInt(request.getParameter("branchId")));
			users.setDepartmentId(Integer.parseInt(request.getParameter("departmentId")));

			new UserService().register(users);
			session.removeAttribute("errorMessages");
			response.sendRedirect("management");
		} else {
			session.setAttribute("errorMessages", errormessages);
			List<Branch> branch = new BranchService().getBranch();
			List<Department> department = new DepartmentService().getDepartment();
			request.setAttribute("branch", branch);
			request.setAttribute("department", department);

			request.getRequestDispatcher("signup.jsp").forward(request, response);
		}
	}


	private boolean isValid (HttpServletRequest request, List<String>errormessages) {
		String name = request.getParameter("name");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");

		//バリデーションメッセージ
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
				if(new UserService().getSuffer(account) != null) {
					errormessages.add("そのログインIDは使用できません");
				}
			}
		}

		if (StringUtils.isEmpty(password)) {
			errormessages.add("パスワードを入力してください");
		} else {
			if (6 > password.length()) {
				errormessages.add("パスワードを6文字以上で入力してください");
		} else {
			if (!password.equals(confirmPassword)) {
				errormessages.add("パスワードが違います");
			}
		}
	}

		if (errormessages.size() == 0){
			return true;
		} else {
			return false;
		}
	}
}