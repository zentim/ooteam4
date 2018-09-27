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

import main.java.model.dao.CategoryDAO;
import main.java.model.dao.OrderDAO;
import main.java.model.dao.OrderItemDAO;
import main.java.model.dao.ProductDAO;
import main.java.model.dao.ProductImageDAO;
import main.java.model.dao.PropertyDAO;
import main.java.model.dao.PropertyValueDAO;
import main.java.model.dao.UserDAO;
import main.java.model.util.Page;

public abstract class BaseBackServlet extends HttpServlet {

	public abstract String add(HttpServletRequest request, HttpServletResponse response, Page page);

	public abstract String delete(HttpServletRequest request, HttpServletResponse response, Page page);

	public abstract String edit(HttpServletRequest request, HttpServletResponse response, Page page);

	public abstract String update(HttpServletRequest request, HttpServletResponse response, Page page);

	public abstract String list(HttpServletRequest request, HttpServletResponse response, Page page);
	
	protected CategoryDAO categoryDAO = new CategoryDAO();
	protected OrderDAO orderDAO = new OrderDAO();
	protected OrderItemDAO orderItemDAO = new OrderItemDAO();
	protected ProductDAO productDAO = new ProductDAO();
	protected ProductImageDAO productImageDAO = new ProductImageDAO();
	protected PropertyDAO propertyDAO = new PropertyDAO();
	protected PropertyValueDAO propertyValueDAO = new PropertyValueDAO();
	protected UserDAO userDAO = new UserDAO();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			// 獲取分頁信息
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
			
			// 借助反射調用對應的方法
			String method = (String) req.getAttribute("method");
			
			Method m = this.getClass().getMethod(method, javax.servlet.http.HttpServletRequest.class, 
					javax.servlet.http.HttpServletResponse.class, Page.class);
			String redirect = m.invoke(this, req, resp, page).toString();
			
			// 根據方法的返回值，進行相應的客戶端跳轉、服務端跳轉，或者僅僅是輸出字符串
			if (redirect.startsWith("@")) {
				// 客戶端跳轉
				resp.sendRedirect(redirect.substring(1));
			} else if (redirect.startsWith("%")) {
				// 服務端跳轉
				resp.getWriter().print(redirect.substring(1));
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
			// 設置上傳文件的大小限制為 10M
			factory.setSizeThreshold(1024 * 1024);
			
			List items = upload.parseRequest(request);
			Iterator iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (!item.isFormField()) {
					// item.getInputStream() 獲取上傳文件的輸入流
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
