package main.java.controller.servlet;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.model.dao.CategoryDAO;
import main.java.model.dao.DiscountTypeDAO;
import main.java.model.dao.OrderDAO;
import main.java.model.dao.OrderItemDAO;
import main.java.model.dao.ProductDAO;
import main.java.model.dao.ProductDetailDAO;
import main.java.model.dao.ProductImageDAO;
import main.java.model.dao.PromotionDAO;
import main.java.model.dao.PromotionItemDAO;
import main.java.model.dao.SubscriptionDAO;
import main.java.model.dao.UserDAO;
import main.java.model.util.Page;

public class BaseForeServlet extends HttpServlet {
	
	protected CategoryDAO categoryDAO = new CategoryDAO();
	protected DiscountTypeDAO discountTypeDAO = new DiscountTypeDAO();
	protected OrderDAO orderDAO = new OrderDAO();
	protected OrderItemDAO orderItemDAO = new OrderItemDAO();
	protected ProductDAO productDAO = new ProductDAO();
	protected ProductDetailDAO productDetailDAO = new ProductDetailDAO();
	protected ProductImageDAO productImageDAO = new ProductImageDAO();
	protected PromotionDAO promotionDAO = new PromotionDAO();
	protected PromotionItemDAO promotionItemDAO = new PromotionItemDAO();
	protected SubscriptionDAO subscriptionDao = new SubscriptionDAO();
	protected UserDAO userDAO = new UserDAO();
     
    public void service(HttpServletRequest request, HttpServletResponse response) {
        try {
             
            int start= 0;
            int count = 10;
            try {
                start = Integer.parseInt(request.getParameter("page.start"));
            } catch (Exception e) {
                 
            }
             
            try {
                count = Integer.parseInt(request.getParameter("page.count"));
            } catch (Exception e) {
            }
             
            Page page = new Page(start,count);
             
            String method = (String) request.getAttribute("method");
 
            Method m = this.getClass().getMethod(method, javax.servlet.http.HttpServletRequest.class,
                    javax.servlet.http.HttpServletResponse.class,Page.class);
            String redirect = m.invoke(this,request, response,page).toString();
             
            if(redirect.startsWith("@"))
                response.sendRedirect(redirect.substring(1));
            else if(redirect.startsWith("%"))
                response.getWriter().print(redirect.substring(1));
            else
                request.getRequestDispatcher(redirect).forward(request, response);
             
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
