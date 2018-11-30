package main.java.controller.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import main.java.model.dao.SegmentDAO;
import main.java.model.dao.CategoryDAO;
import main.java.model.dao.BrandDAO;
import main.java.model.dao.OrderDAO;
import main.java.model.dao.OrderItemDAO;
import main.java.model.dao.ProductDAO;
import main.java.model.dao.ProductImageDAO;
import main.java.model.dao.PromotionDAO;
import main.java.model.dao.PromotionItemDAO;
import main.java.model.dao.SubscriptionDAO;
import main.java.model.dao.UserDAO;
import main.java.model.util.Page;

public abstract class BaseBackServlet extends HttpServlet {

    public abstract String add(HttpServletRequest request, HttpServletResponse response, Page page) throws Exception;

    public abstract String delete(HttpServletRequest request, HttpServletResponse response, Page page) throws Exception;

    public abstract String edit(HttpServletRequest request, HttpServletResponse response, Page page) throws Exception;

    public abstract String update(HttpServletRequest request, HttpServletResponse response, Page page) throws Exception;

    public abstract String list(HttpServletRequest request, HttpServletResponse response, Page page) throws Exception;

    protected SegmentDAO segmentDAO = new SegmentDAO();
    protected CategoryDAO categoryDAO = new CategoryDAO();
    protected BrandDAO brandDAO = new BrandDAO();
    protected OrderDAO orderDAO = new OrderDAO();
    protected OrderItemDAO orderItemDAO = new OrderItemDAO();
    protected ProductDAO productDAO = new ProductDAO();
    protected ProductImageDAO productImageDAO = new ProductImageDAO();
    protected PromotionDAO promotionDAO = new PromotionDAO();
    protected PromotionItemDAO promotionItemDAO = new PromotionItemDAO();
    protected SubscriptionDAO subscriptionDao = new SubscriptionDAO();
    protected UserDAO userDAO = new UserDAO();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            // get pagination info
            int start = 0;
            int count = 5;

            try {
                start = Integer.parseInt(req.getParameter("page.start"));
            } catch (Exception e) {
                // TODO: handle exception
            }
            try {
                count = Integer.parseInt(req.getParameter("page.count"));
            } catch (Exception e) {
                // TODO: handle exception
            }

            Page page = new Page(start, count);

            // Call the corresponding method with reflection
            String method = (String) req.getAttribute("method");

            Method m = this.getClass().getMethod(method, javax.servlet.http.HttpServletRequest.class,
                    javax.servlet.http.HttpServletResponse.class, Page.class);

            String redirect = m.invoke(this, req, resp, page).toString();

            // According to the return value of the method, the corresponding
            // client redirect, server redirect, or just the output string.
            if (redirect.startsWith("@")) {
                resp.sendRedirect(redirect.substring(1)); // client redirect
            } else if (redirect.startsWith("%")) {
                resp.getWriter().print(redirect.substring(1)); // server redirect
            } else {
                req.getRequestDispatcher(redirect).forward(req, resp);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public InputStream parseUpload(HttpServletRequest request, Map<String, String> params) {
        InputStream is = null;
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            // Set the size of the uploaded file to 10M
            factory.setSizeThreshold(1024 * 1024);

            List items = upload.parseRequest(request);
            Iterator iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                if (!item.isFormField()) {
                    // item.getInputStream() //Get the input stream of the uploaded file
                    is = item.getInputStream();
                } else {
                    String paramName = item.getFieldName();
                    String paramValue = item.getString();
                    paramValue = new String(paramValue.getBytes("ISO-8859-1"), "UTF-8");
                    params.put(paramName, paramValue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return is;
    }

}
