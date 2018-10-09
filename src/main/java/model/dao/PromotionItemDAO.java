package main.java.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.java.model.bean.PromotionItem;
import main.java.model.bean.Product;
import main.java.model.bean.Promotion;
import main.java.model.util.DBUtil;

public class PromotionItemDAO {

    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select count(*) from promotion_item";

            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return total;
    }

    public int add(PromotionItem bean) {

        String sql = "insert into promotion_item values(DEFAULT,?, ?, ?, ?)";
        try (Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setInt(1, bean.getPromotion().getId());
            ps.setInt(2, bean.getProduct().getId());
            ps.setInt(3, bean.getMinQuantity());
            ps.setInt(4, bean.getDiscountOf());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                bean.setId(id);

                return id;
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }

        return 0;
    }

    public void update(PromotionItem bean) {

        String sql = "update promotion_item set promotionId=?, productId=?, minQuantity=?, discountOf=?  where promotionId = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, bean.getPromotion().getId());
            ps.setInt(2, bean.getProduct().getId());
            ps.setInt(3, bean.getMinQuantity());
            ps.setInt(4, bean.getDiscountOf());

            ps.setInt(5, bean.getId());
            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    public void delete(int id) {

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "delete from promotion_item where promotionId = " + id;

            s.execute(sql);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public PromotionItem get(int id) {
        PromotionItem bean = new PromotionItem();

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select * from promotion_item where promotionId = " + id;

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                int promotionId = rs.getInt("promotionId");
                int productId = rs.getInt("productId");
                int minQuantity = rs.getInt("minQuantity");
                int discountOf = rs.getInt("discountOf");

                Promotion promotion = new PromotionDAO().get(promotionId);
                Product product = new ProductDAO().get(productId);

                bean.setPromotion(promotion);
                bean.setProduct(product);
                bean.setMinQuantity(minQuantity);
                bean.setDiscountOf(discountOf);

                bean.setId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bean;
    }

    public List<PromotionItem> listByUser(int userId) {
        return listByUser(userId, 0, Short.MAX_VALUE);
    }

    public List<PromotionItem> listByUser(int userId, int start, int count) {
        List<PromotionItem> beans = new ArrayList<PromotionItem>();

        String sql = "select * from promotion_item where userId = ? and promotionId = -1 order by promotionItemId desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, userId);
            ps.setInt(2, start);
            ps.setInt(3, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PromotionItem bean = new PromotionItem();
                int id = rs.getInt(1);

                int promotionId = rs.getInt("promotionId");
                int productId = rs.getInt("productId");
                int minQuantity = rs.getInt("minQuantity");
                int discountOf = rs.getInt("discountOf");

                Promotion promotion = new PromotionDAO().get(promotionId);
                Product product = new ProductDAO().get(productId);

                bean.setPromotion(promotion);
                bean.setProduct(product);
                bean.setMinQuantity(minQuantity);
                bean.setDiscountOf(discountOf);

                bean.setId(id);
                beans.add(bean);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return beans;
    }

    public List<PromotionItem> listByPromotion(int promotionId) {
        return listByPromotion(promotionId, 0, Short.MAX_VALUE);
    }

    public List<PromotionItem> listByPromotion(int promotionId, int start, int count) {
        List<PromotionItem> beans = new ArrayList<PromotionItem>();

        String sql = "select * from promotion_item where promotionId = ? order by promotionItemId desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, promotionId);
            ps.setInt(2, start);
            ps.setInt(3, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PromotionItem bean = new PromotionItem();
                int id = rs.getInt(1);

                int productId = rs.getInt("productId");
                int minQuantity = rs.getInt("minQuantity");
                int discountOf = rs.getInt("discountOf");

                Promotion promotion = new PromotionDAO().get(promotionId);
                Product product = new ProductDAO().get(productId);

                bean.setPromotion(promotion);
                bean.setProduct(product);
                bean.setMinQuantity(minQuantity);
                bean.setDiscountOf(discountOf);

                bean.setId(id);
                beans.add(bean);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return beans;
    }

    public List<PromotionItem> listByProduct(int productId) {
        return listByProduct(productId, 0, Short.MAX_VALUE);
    }

    public List<PromotionItem> listByProduct(int productId, int start, int count) {
        List<PromotionItem> beans = new ArrayList<PromotionItem>();

        String sql = "select * from promotion_item where productId = ? order by promotionItemId desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, productId);
            ps.setInt(2, start);
            ps.setInt(3, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PromotionItem bean = new PromotionItem();
                int id = rs.getInt(1);

                int promotionId = rs.getInt("promotionId");
                int minQuantity = rs.getInt("minQuantity");
                int discountOf = rs.getInt("discountOf");

                Promotion promotion = new PromotionDAO().get(promotionId);
                Product product = new ProductDAO().get(productId);

                bean.setPromotion(promotion);
                bean.setProduct(product);
                bean.setMinQuantity(minQuantity);
                bean.setDiscountOf(discountOf);

                bean.setId(id);
                beans.add(bean);

            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return beans;
    }

}
