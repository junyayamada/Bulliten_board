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
import service.ManagementService;
import service.UserService;

@WebServlet (urlPatterns={"/management"})
public class ManagementUserServlet  extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		List<User> management = new ManagementService().getManagement();
		request.setAttribute("managements", management);

		List<Branch> branch = new BranchService().getBranch();
		List<Department> department = new DepartmentService().getDepartment();

		request.setAttribute("branches", branch);
		request.setAttribute("departmentes", department);

		request.getRequestDispatcher("management.jsp").forward(request,  response);
	}

	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		List<String> stoped = new ArrayList<String>();

		String id = request.getParameter("id");
		String isStoped = request.getParameter("isStoped");

		if(id == null || isStoped == null){
			if (StringUtils.isEmpty(id) == true){
				stoped.add(id);
			}
			if (StringUtils.isEmpty(isStoped) == true){
				stoped.add(isStoped);
			}
		}


		HttpSession session = request.getSession();
		User userStatus = getUserStatus(request);

		session.setAttribute("userStatus", userStatus);
		new UserService().updateStatus(userStatus);

		response.sendRedirect("management");
	}

	private User getUserStatus(HttpServletRequest request)throws IOException, ServletException {

		User status = new User();

		status.setIsStoped(Integer.parseInt(request.getParameter("isStoped")));
		status.setId(Integer.parseInt(request.getParameter("id")));

		return status;
	}
}

