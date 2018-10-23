package main.java.controller.servlet;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.model.bean.Brand;
import main.java.model.bean.Product;
import main.java.model.bean.Promotion;
import main.java.model.bean.PromotionItem;
import main.java.model.util.Page;

@WebServlet("/productServlet")
public class ProductServlet extends BaseBackServlet {

	@Override
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
		int cid = Integer.parseInt(request.getParameter("cid"));
		Brand c = brandDAO.get(cid);
		
		String name = request.getParameter("name");
		float price = Float.parseFloat(request.getParameter("price"));
		int inventory = Integer.parseInt(request.getParameter("inventory"));
		
		Product p = new Product();

		p.setBrand(c);
		p.setName(name);
		p.setPrice(price);
		p.setInventory(inventory);
		System.out.println(p.toString());
		productDAO.add(p);

		return "@admin_product_list?cid=" + cid;
	}

	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		Product p = productDAO.get(id);
		productDAO.delete(id);

		return "@admin_product_list?cid=" + p.getBrand().getId();
	}

	@Override
	public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		Product p = productDAO.get(id);
		request.setAttribute("p", p);

		return "admin/editProduct.jsp";
	}

	@Override
	public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
		int cid = Integer.parseInt(request.getParameter("cid"));
		Brand c = brandDAO.get(cid);

		int id = Integer.parseInt(request.getParameter("id"));
		int inventory = Integer.parseInt(request.getParameter("inventory"));
		float price = Float.parseFloat(request.getParameter("price"));
		String name = request.getParameter("name");

		Product p = new Product();

		p.setName(name);
		p.setPrice(price);
		p.setInventory(inventory);
		p.setId(id);
		p.setBrand(c);

		productDAO.update(p);

		return "@admin_product_list?cid=" + p.getBrand().getId();
	}

	@Override
	public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
		int cid = Integer.parseInt(request.getParameter("cid"));
		Brand c = brandDAO.get(cid);

		List<Product> ps = productDAO.list(cid, page.getStart(), page.getCount());

		int total = productDAO.getTotal(cid);
		page.setTotal(total);
		page.setParam("&cid=" + c.getId());

		request.setAttribute("ps", ps);
		request.setAttribute("c", c);
		request.setAttribute("page", page);

		return "admin/listProduct.jsp";
	}

	// public String editPropertyValue(HttpServletRequest request,
	// HttpServletResponse response, Page page) {
	// int id = Integer.parseInt(request.getParameter("id"));
	// Product p = productDAO.get(id);
	// request.setAttribute("p", p);
	// propertyValueDAO.init(p);
	//
	// List<PropertyValue> pvs = propertyValueDAO.list(p.getId());
	//
	// request.setAttribute("pvs", pvs);
	//
	// return "admin/editPropertyValue.jsp";
	// }

	// public String updatePropertyValue(HttpServletRequest request,
	// HttpServletResponse response, Page page) {
	// int pvid = Integer.parseInt(request.getParameter("pvid"));
	// String value = request.getParameter("value");
	//
	// PropertyValue pv = propertyValueDAO.get(pvid);
	// pv.setValue(value);
	// propertyValueDAO.update(pv);
	//
	// return "%success";
	// }

	public String editPromotionItem(HttpServletRequest request, HttpServletResponse response, Page page) {
		int productId = Integer.parseInt(request.getParameter("id"));
		Product p = productDAO.get(productId);
		List<Promotion> ps = promotionDAO.list();
		PromotionItem pi = promotionItemDAO.getByProduct(productId);
		
		request.setAttribute("p", p);
		request.setAttribute("ps", ps);
		request.setAttribute("pi", pi);
		
		int promotionItemId = promotionItemDAO.getByProduct(productId).getId();
		if (promotionItemId > 0) {
			return "admin/editPromotionItem.jsp";
		}
		
		return "admin/listPromotionItem.jsp";
	}
	
}
