package filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;


@WebFilter(urlPatterns = {"/management", "/signup", "/edit"})
public class AuthorityFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		List<String>errormessages = new ArrayList<String>();

		if (session.getAttribute("loginUser") == null) {
			res.sendRedirect("./login");
		} else {
			if (loginUser.getBranchId() == 1 && loginUser.getDepartmentId() == 1) {
				chain.doFilter(request, response);
			} else {
				session.setAttribute("errorMessages", errormessages);
				errormessages.add("権限がありません。");
				res.sendRedirect("./");
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
	public void destroy() {
	}
}