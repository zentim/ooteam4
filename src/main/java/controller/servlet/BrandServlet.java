package main.java.controller.servlet;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.model.bean.Brand;
import main.java.model.bean.Category;
import main.java.model.bean.Segment;
import main.java.model.util.Page;

@WebServlet("/brandServlet")
public class BrandServlet extends BaseBackServlet {

	@Override
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("cid"));
		Category category = categoryDAO.get(id);
		
		String name = request.getParameter("name");
		Brand c = new Brand();
		c.setName(name);
		c.setCategory(category);
		brandDAO.add(c);

		return "@admin_brand_list?cid=" + id;
	}

	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		Brand brand = brandDAO.get(id);
		brandDAO.delete(id);
		
		return "@admin_brand_list?cid=" + brand.getCategory().getId();
	}

	@Override
	public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		Brand b = brandDAO.get(id);
		Category c = categoryDAO.get(b.getCategory().getId());
		Segment s = segmentDAO.get(c.getSegment().getId());
		
		request.setAttribute("b", b);
		request.setAttribute("c", c);
		request.setAttribute("s", s);
		return "admin/editBrand.jsp";
	}

	@Override
	public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
		int cid = Integer.parseInt(request.getParameter("cid"));
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		
		Brand c = new Brand();
		c.setId(id);
		c.setName(name);
		brandDAO.update(c);

		return "@admin_brand_list?cid=" + cid;
	}

	@Override
	public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("cid"));
		Category category = categoryDAO.get(id);
		List<Brand> cs = brandDAO.list(id, page.getStart(), page.getCount());
		int total = brandDAO.getTotal();
		page.setTotal(total);

		request.setAttribute("thecs", cs);
		request.setAttribute("page", page);
		request.setAttribute("c", category);

		return "admin/listBrand.jsp";
	}

}
