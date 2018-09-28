package main.java.controller.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.model.bean.Category;
import main.java.model.dao.CategoryDAO;
import main.java.model.dao.ProductDAO;
import main.java.model.util.Page;

@WebServlet("/foreServlet")
public class ForeServlet extends BaseForeServlet {
	
	public String home(HttpServletRequest request, HttpServletResponse response, Page page) {
		List<Category> cs = new CategoryDAO().list();
		new ProductDAO().fill(cs);
		new ProductDAO().fillByRow(cs);
		request.setAttribute("cs", cs);
		
		return "home.jsp";
	}

}
