package filter;

import java.io.IOException;

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

/**
 * Servlet Filter implementation class LoginFilter
 */

@WebFilter("/*")
public class LoginFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = (HttpSession) req.getSession();

		if (req.getServletPath().equals("/login")) {
				chain.doFilter(request, response);
		} else {
				if(req.getServletPath().equals("/css/style.css")) {
					chain.doFilter(request, response);
				} else {
					if (session.getAttribute("loginUser") == null) {
						res.sendRedirect("./login");
					} else {
						chain.doFilter(request, response);
					}
				}
			}
		}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void destroy() {
	}
}