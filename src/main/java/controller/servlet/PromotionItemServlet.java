package main.java.controller.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.model.bean.Product;
import main.java.model.bean.Promotion;
import main.java.model.bean.PromotionItem;
import main.java.model.util.Page;

@WebServlet("/promotionItemServlet")
public class PromotionItemServlet extends BaseBackServlet {

    @Override
    public String add(HttpServletRequest request, HttpServletResponse response, Page page) throws Exception {
        int promotionId = Integer.parseInt(request.getParameter("promotionId"));
        int productId = Integer.parseInt(request.getParameter("productId"));
        int minQuantity = Integer.parseInt(request.getParameter("minQuantity"));
        int discountOf = Integer.parseInt(request.getParameter("discountOf"));

        PromotionItem pi = new PromotionItem();
        if (promotionId == -1) {
            pi.setPromotion(null);
        } else {
            pi.setPromotion((Promotion) promotionDAO.get(promotionId));
        }
        pi.setProduct((Product) productDAO.get(productId));
        pi.setMinQuantity(minQuantity);
        pi.setDiscountOf(discountOf);
        try {
            promotionItemDAO.add(pi);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "%success";
    }

    @Override
    public String delete(HttpServletRequest request, HttpServletResponse response, Page page) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String edit(HttpServletRequest request, HttpServletResponse response, Page page) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String update(HttpServletRequest request, HttpServletResponse response, Page page) throws Exception {
        int promotionId = Integer.parseInt(request.getParameter("promotionId"));
        int productId = Integer.parseInt(request.getParameter("productId"));
        int promotionItemId = Integer.parseInt(request.getParameter("promotionItemId"));
        int minQuantity = Integer.parseInt(request.getParameter("minQuantity"));
        int discountOf = Integer.parseInt(request.getParameter("discountOf"));

        PromotionItem pi = new PromotionItem();
        if (promotionId == -1) {
            pi.setPromotion(null);
        } else {
            pi.setPromotion((Promotion) promotionDAO.get(promotionId));
        }

        pi.setProduct((Product) productDAO.get(productId));
        pi.setMinQuantity(minQuantity);
        pi.setDiscountOf(discountOf);
        pi.setId(promotionItemId);
        try {
            promotionItemDAO.update(pi);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "%success";
    }

    @Override
    public String list(HttpServletRequest request, HttpServletResponse response, Page page) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
