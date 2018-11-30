package main.java.controller.filter;

import java.io.IOException;
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

import org.apache.commons.lang3.StringUtils;

import main.java.model.bean.OrderItem;
import main.java.model.bean.User;
import main.java.model.dao.OrderItemDAO;

@WebFilter("/*")
public class ForeServletFilter implements Filter {
	/**
	 * Default constructor.
	 */
	public ForeServletFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String contextPath = request.getServletContext().getContextPath();
		request.getServletContext().setAttribute("contextPath", contextPath);

		// After the user logs in, the user object is obtained from the 
		// session, and the total number of items in the shopping cart is 
		// obtained according to the user object.
		User user = (User) request.getSession().getAttribute("user");
		int cartTotalItemNumber = 0;
		if (null != user) {
			List<OrderItem> ois = null;
			try {
				ois = new OrderItemDAO().listCartByUser(user.getId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for (OrderItem oi : ois) {
				cartTotalItemNumber += oi.getQuantity();
			}
		}
		request.setAttribute("cartTotalItemNumber", cartTotalItemNumber);

		String uri = request.getRequestURI();
		uri = StringUtils.remove(uri, contextPath);
		if (uri.startsWith("/fore") && !uri.startsWith("/foreServlet")) {
			String method = StringUtils.substringAfterLast(uri, "/fore");

			request.setAttribute("method", method);
			req.getRequestDispatcher("/foreServlet").forward(request, response);
			return;
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
