package main.java.controller.servlet;

import java.util.Date;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.model.bean.Product;
import main.java.model.bean.Promotion;
import main.java.model.util.DateUtil;
import main.java.model.util.Page;

@WebServlet("/promotionServlet")
public class PromotionServlet extends BaseBackServlet {

	@Override
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
		int discountType = Integer.parseInt(request.getParameter("discountType"));
		String discountName = request.getParameter("discountName");
		Date dateFrom = DateUtil.s2d(request.getParameter("dateFrom"));
		Date dateTo = DateUtil.s2d(request.getParameter("dateTo"));
		int state = Integer.parseInt(request.getParameter("state"));
		
		/*
		String[] selectedProducts = (String[]) request.getParameterValues("selectedProducts[]");
		for(int i=0; i < selectedProducts.length; i++) {
			PromotionItem pi = new PromotionItem();
			pi.setProduct(productDAO.get(Integer.parseInt(selectedProducts[i])));
		}
		*/
		
		Promotion p = new Promotion();
		p.setDiscountType(discountType);
		p.setName(discountName);
		p.setDateFrom(dateFrom);
		p.setDateTo(dateTo);
		p.setState(state);
		promotionDAO.add(p);

		return "%success";
	}

	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		promotionDAO.delete(id);
		
		return "@admin_promotion_list";
	}

	@Override
	public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
		int promotionId = Integer.parseInt(request.getParameter("id"));
		Promotion promotion = promotionDAO.get(promotionId);
		
		request.setAttribute("dateFrom", DateUtil.d2t(promotion.getDateFrom()));
		request.setAttribute("dateTo", DateUtil.d2t(promotion.getDateTo()));
		request.setAttribute("promotion", promotion);
		return "admin/editPromotion.jsp";
	}

	@Override
	public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
		int discountType = Integer.parseInt(request.getParameter("discountType"));
		String discountName = request.getParameter("discountName");
		Date dateFrom = DateUtil.s2d(request.getParameter("dateFrom"));
		Date dateTo = DateUtil.s2d(request.getParameter("dateTo"));
		int state = Integer.parseInt(request.getParameter("state"));
		int promotionId = Integer.parseInt(request.getParameter("promotionId"));
		
		/*
		String[] selectedProducts = (String[]) request.getParameterValues("selectedProducts[]");
		for(int i=0; i < selectedProducts.length; i++) {
			PromotionItem pi = new PromotionItem();
			pi.setProduct(productDAO.get(Integer.parseInt(selectedProducts[i])));
		}
		*/
		
		Promotion p = new Promotion();
		p.setDiscountType(discountType);
		p.setName(discountName);
		p.setDateFrom(dateFrom);
		p.setDateTo(dateTo);
		p.setState(state);
		p.setId(promotionId);
		promotionDAO.update(p);

		return "%success";
	}

	@Override
	public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
		List<Promotion> ps = promotionDAO.list(page.getStart(), page.getCount());
		List<Product> products = productDAO.list();
		promotionItemDAO.fill(ps);
		int total = promotionDAO.getTotal();
		page.setTotal(total);

		request.setAttribute("ps", ps);
		request.setAttribute("products", products);
		request.setAttribute("page", page);

		return "admin/listPromotion.jsp";
	}

}
