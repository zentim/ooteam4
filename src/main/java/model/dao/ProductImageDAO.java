package main.java.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.java.model.bean.Order;
import main.java.model.bean.Product;
import main.java.model.bean.ProductImage;
import main.java.model.util.DBUtil;
import main.java.model.util.DateUtil;
import main.java.pattern.template.DAOTemplate;


/**
 * 
 * Template Method Pattern - ConcreteTemplate
 *
 */
public class ProductImageDAO extends DAOTemplate {
    String TABLE_NAME = "product_image";
    public static final String type_single = "type_single";
    public static final String type_detail = "type_detail";

    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select count(*) from product_image";

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
            sql = "insert into " + TABLE_NAME + " values(DEFAULT,?,?)";
            break;
        case 2:
            sql = "update " + TABLE_NAME + " set " + "productId= ?, type=? where productImageId = ?";
            break;
        case 3:
            sql = "delete from " + TABLE_NAME + "  where productImageId = ?";
            break;
        case 4:
            sql = "select * from " + TABLE_NAME + "  where productImageId = ?";
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
    protected Object setModelFromGet(ResultSet rs) throws Exception {
        ProductImage bean = new ProductImage();
        if (rs.next()) {
            int productId = rs.getInt("productId");
            String type = rs.getString("type");
            Product product = (Product) new ProductDAO().get(productId);
            bean.setProduct(product);
            bean.setType(type);
            bean.setId(rs.getInt("productImageId"));
        }
        return null;
    }

    /**
     * 
     * primitive methods
     * 
     */
    protected ResultSet executeAdd(PreparedStatement ps, Object obj) throws SQLException {
        ProductImage bean = (ProductImage) obj;
        ps.setInt(1, bean.getProduct().getId());
        ps.setString(2, bean.getType());
        ps.execute();

        ResultSet rs = ps.getGeneratedKeys();
        return rs;

    }

    protected void executeUpdate(PreparedStatement ps, Object obj) throws SQLException {
        ProductImage bean = (ProductImage) obj;

        ps.setInt(1, bean.getProduct().getId());
        ps.setString(2, bean.getType());

        ps.execute();

    }

    protected void executeDelete(PreparedStatement ps, int id) throws SQLException {
        ps.setInt(1, id);
        ps.execute();

    }

    protected int setModel(ResultSet rs, Object obj) throws SQLException {
        ProductImage bean = (ProductImage) obj;
        if (rs.next()) {
            int id = rs.getInt(1);
            bean.setId(id);

            return id;
        }
        return 0;
    }

    

    public List<ProductImage> list(Product p, String type) {
        return list(p, type, 0, Short.MAX_VALUE);
    }

    public List<ProductImage> list(Product p, String type, int start, int count) {
        List<ProductImage> beans = new ArrayList<>();

        String sql = "select * from product_image " + "where productId =? and type =? "
                + "order by productImageId desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, p.getId());
            ps.setString(2, type);

            ps.setInt(3, start);
            ps.setInt(4, count);

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {

                    ProductImage bean = new ProductImage();
                    int id = rs.getInt(1);

                    bean.setProduct(p);
                    bean.setType(type);
                    bean.setId(id);

                    beans.add(bean);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return beans;
    }

}
