package main.java.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.java.model.bean.PromotionItem;
import main.java.model.bean.Order;
import main.java.model.bean.Product;
import main.java.model.bean.Promotion;
import main.java.model.util.DBUtil;
import main.java.model.util.DateUtil;
import main.java.pattern.template.DAOTemplate;

/**
 * 
 * Template Method Pattern - ConcreteTemplate
 *
 */
public class PromotionItemDAO extends DAOTemplate {
    String TABLE_NAME = "promotion_item";

    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select count(*) from promotion_item";

            try (ResultSet rs = s.executeQuery(sql);) {
                while (rs.next()) {
                    total = rs.getInt(1);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return total;
    }

    /**
     * 
     * primitive methods
     * 
     */
    protected String getMainSql(int type) {
        String sql = "";
        switch (type) {
        case 1:
            sql = "insert into " + TABLE_NAME + " values(DEFAULT,?, ?, ?, ?)";
            break;
        case 2:
            sql = "update " + TABLE_NAME
                    + " set promotionId=?, productId=?, minQuantity=?, discountOf=? where promotionItemId = ?";
            break;
        case 3:
            sql = "delete from " + TABLE_NAME + " where promotionItemId = ?";
            break;
        case 4:
            sql = "select * from " + TABLE_NAME + " where promotionItemId = ?";
            break;
        default:
            break;
        }
        return sql;

    }
    
    /**
     * 
     * hook methods
     * 
     */
    @Override
    protected Object setModelFromGet(ResultSet rs) throws Exception {
        PromotionItem bean = new PromotionItem();
        if (rs.next()) {

            int promotionId = rs.getInt("promotionId");
            int productId = rs.getInt("productId");
            int minQuantity = rs.getInt("minQuantity");
            int discountOf = rs.getInt("discountOf");

            Promotion promotion = (Promotion) new PromotionDAO().get(promotionId);
            Product product = (Product) new ProductDAO().get(productId);

            bean.setPromotion(promotion);
            bean.setProduct(product);
            bean.setMinQuantity(minQuantity);
            bean.setDiscountOf(discountOf);

            bean.setId(rs.getInt("promotionItemId"));
            return bean;
        }
        return null;
    }

    /**
     * 
     * primitive methods
     * 
     */
    protected ResultSet executeAdd(PreparedStatement ps, Object obj) throws SQLException {
        PromotionItem bean = (PromotionItem) obj;
        if (bean.getPromotion() == null) {
            ps.setInt(1, -1);
        } else {
            ps.setInt(1, bean.getPromotion().getId());
        }

        ps.setInt(2, bean.getProduct().getId());
        ps.setInt(3, bean.getMinQuantity());
        ps.setInt(4, bean.getDiscountOf());

        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        return rs;

    }

    protected void executeUpdate(PreparedStatement ps, Object obj) throws SQLException {
        PromotionItem bean = (PromotionItem) obj;
        if (bean.getPromotion() == null) {
            ps.setInt(1, -1);
        } else {
            ps.setInt(1, bean.getPromotion().getId());
        }

        ps.setInt(2, bean.getProduct().getId());
        ps.setInt(3, bean.getMinQuantity());
        ps.setInt(4, bean.getDiscountOf());
        ps.setInt(5, bean.getId());
        ps.execute();

    }

    protected void executeDelete(PreparedStatement ps, int id) throws SQLException {
        ps.setInt(1, id);
        ps.execute();

    }

    protected int setModel(ResultSet rs, Object obj) throws SQLException {
        PromotionItem bean = (PromotionItem) obj;
        if (rs.next()) {
            int id = rs.getInt(1);
            bean.setId(id);

            return id;
        }
        return 0;
    }

    

    public PromotionItem getByProduct(int productId) throws Exception {
        PromotionItem bean = new PromotionItem();
        String sql = "select * from promotion_item where productId = ?";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, productId);

            try (ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    int promotionItemId = rs.getInt("promotionItemId");
                    int promotionId = rs.getInt("promotionId");
                    int minQuantity = rs.getInt("minQuantity");
                    int discountOf = rs.getInt("discountOf");

                    Promotion promotion = (Promotion) new PromotionDAO().get(promotionId);
                    Product product = (Product) new ProductDAO().get(productId);

                    bean.setPromotion(promotion);
                    bean.setProduct(product);
                    bean.setMinQuantity(minQuantity);
                    bean.setDiscountOf(discountOf);

                    bean.setId(promotionItemId);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bean;
    }

    public List<PromotionItem> listByUser(int userId) throws Exception {
        return listByUser(userId, 0, Short.MAX_VALUE);
    }

    public List<PromotionItem> listByUser(int userId, int start, int count) throws Exception {
        List<PromotionItem> beans = new ArrayList<>();

        String sql = "select * from promotion_item " + "where userId = ? and promotionId = -1 "
                + "order by promotionItemId desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, userId);
            ps.setInt(2, start);
            ps.setInt(3, count);

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    PromotionItem bean = new PromotionItem();
                    int id = rs.getInt(1);

                    int promotionId = rs.getInt("promotionId");
                    int productId = rs.getInt("productId");
                    int minQuantity = rs.getInt("minQuantity");
                    int discountOf = rs.getInt("discountOf");

                    Promotion promotion = (Promotion) new PromotionDAO().get(promotionId);
                    Product product = (Product) new ProductDAO().get(productId);

                    bean.setPromotion(promotion);
                    bean.setProduct(product);
                    bean.setMinQuantity(minQuantity);
                    bean.setDiscountOf(discountOf);

                    bean.setId(id);
                    beans.add(bean);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return beans;
    }

    public List<PromotionItem> listByPromotion(int promotionId) throws Exception {
        return listByPromotion(promotionId, 0, Short.MAX_VALUE);
    }

    public List<PromotionItem> listByPromotion(int promotionId, int start, int count) throws Exception {
        List<PromotionItem> beans = new ArrayList<>();

        String sql = "select * from promotion_item " + "where promotionId = ? "
                + "order by promotionItemId desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, promotionId);
            ps.setInt(2, start);
            ps.setInt(3, count);

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    PromotionItem bean = new PromotionItem();
                    int id = rs.getInt(1);

                    int productId = rs.getInt("productId");
                    int minQuantity = rs.getInt("minQuantity");
                    int discountOf = rs.getInt("discountOf");

                    Promotion promotion = (Promotion) new PromotionDAO().get(promotionId);
                    Product product = (Product) new ProductDAO().get(productId);

                    bean.setPromotion(promotion);
                    bean.setProduct(product);
                    bean.setMinQuantity(minQuantity);
                    bean.setDiscountOf(discountOf);

                    bean.setId(id);
                    beans.add(bean);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return beans;
    }

    public List<PromotionItem> listByProduct(int productId) throws Exception {
        return listByProduct(productId, 0, Short.MAX_VALUE);
    }

    public List<PromotionItem> listByProduct(int productId, int start, int count) throws Exception {
        List<PromotionItem> beans = new ArrayList<>();

        String sql = "select * from promotion_item " + "where productId = ? "
                + "order by promotionItemId desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, productId);
            ps.setInt(2, start);
            ps.setInt(3, count);

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    PromotionItem bean = new PromotionItem();
                    int id = rs.getInt(1);

                    int promotionId = rs.getInt("promotionId");
                    int minQuantity = rs.getInt("minQuantity");
                    int discountOf = rs.getInt("discountOf");

                    Promotion promotion = (Promotion) new PromotionDAO().get(promotionId);
                    Product product = (Product) new ProductDAO().get(productId);

                    bean.setPromotion(promotion);
                    bean.setProduct(product);
                    bean.setMinQuantity(minQuantity);
                    bean.setDiscountOf(discountOf);

                    bean.setId(id);
                    beans.add(bean);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return beans;
    }

    public void fill(List<Promotion> ps) throws Exception {
        for (Promotion p : ps) {
            List<PromotionItem> pis = listByPromotion(p.getId());
            p.setPromotionItems(pis);
        }
    }

    public void fill(Promotion p) throws Exception {
        List<PromotionItem> pis = listByPromotion(p.getId());
        p.setPromotionItems(pis);
    }

}
