package main.java.controller.servlet;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.model.bean.Brand;
import main.java.model.util.Page;

@WebServlet("/brandServlet")
public class BrandServlet extends BaseBackServlet {

	@Override
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) {

		String name = request.getParameter("name");
		Brand c = new Brand();
		c.setName(name);
		brandDAO.add(c);

		return "@admin_brand_list";
	}

	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		brandDAO.delete(id);
		return "@admin_brand_list";
	}

	@Override
	public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		Brand c = brandDAO.get(id);
		request.setAttribute("c", c);
		return "admin/editBrand.jsp";
	}

	@Override
	public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		Brand c = new Brand();
		c.setId(id);
		c.setName(name);
		brandDAO.update(c);

		return "@admin_brand_list";
	}

	@Override
	public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
		List<Brand> cs = brandDAO.list(page.getStart(), page.getCount());
		int total = brandDAO.getTotal();
		page.setTotal(total);

		request.setAttribute("thecs", cs);
		request.setAttribute("page", page);

		return "admin/listBrand.jsp";
	}

}
