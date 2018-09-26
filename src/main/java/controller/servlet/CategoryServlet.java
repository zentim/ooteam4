package main.java.controller.servlet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import main.java.model.bean.Category;
import main.java.model.util.Page;

public class CategoryServlet extends BaseBackServlet {

	@Override
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
		
		String name = request.getParameter("name");
		Category c = new Category();
		c.setName(name);
		categoryDAO.add(c);
		
		return "@admin_category_list";
	}

	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		categoryDAO.delete(id);
		return "@admin_category_list";
	}

	@Override
	public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		Category c = categoryDAO.get(id);
		request.setAttribute("c", c);
		return "admin/editCategory.jsp";
	}

	@Override
	public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
		List<Category> cs = categoryDAO.list(page.getStart(), page.getCount());
		int total = categoryDAO.getTotal();
		page.setTotal(total);
		
		request.setAttribute("thecs", cs);
		request.setAttribute("page", page);
		
		return "admin/listCategory.jsp";
	}
	
}
