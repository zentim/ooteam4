package main.java.controller.servlet;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.model.bean.Category;
import main.java.model.bean.PromotionItem;
import main.java.model.util.Page;

@WebServlet("/promotionItemServlet")
public class PromotionItemServlet extends BaseBackServlet {

	@Override
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		int promotionId = Integer.parseInt(request.getParameter("promotionId"));
		int productId = Integer.parseInt(request.getParameter("productId"));
		int minQuantity = Integer.parseInt(request.getParameter("minQuantity"));
		int discountOf = Integer.parseInt(request.getParameter("discountOf"));
		
		PromotionItem pi = new PromotionItem();
		if (promotionId == -1) {
			pi.setPromotion(null);
		} else {
			pi.setPromotion(promotionDAO.get(promotionId));
		}
		pi.setProduct(productDAO.get(productId));
		pi.setMinQuantity(minQuantity);
		pi.setDiscountOf(discountOf);
		promotionItemDAO.add(pi);

		return "%success";
	}

	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		int promotionId = Integer.parseInt(request.getParameter("promotionId"));
		int productId = Integer.parseInt(request.getParameter("productId"));
		int promotionItemId = Integer.parseInt(request.getParameter("promotionItemId"));
		int minQuantity = Integer.parseInt(request.getParameter("minQuantity"));
		int discountOf = Integer.parseInt(request.getParameter("discountOf"));
		
		PromotionItem pi = new PromotionItem();
		if (promotionId == -1) {
			pi.setPromotion(null);
		} else {
			pi.setPromotion(promotionDAO.get(promotionId));
		}
		
		pi.setProduct(productDAO.get(productId));
		pi.setMinQuantity(minQuantity);
		pi.setDiscountOf(discountOf);
		pi.setId(promotionItemId);
		promotionItemDAO.update(pi);

		return "%success";
	}

	@Override
	public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

}
