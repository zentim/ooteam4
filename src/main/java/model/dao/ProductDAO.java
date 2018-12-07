package main.java.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.java.model.bean.Brand;
import main.java.model.bean.OrderItem;
import main.java.model.bean.Product;
import main.java.model.bean.ProductImage;
import main.java.model.bean.Promotion;
import main.java.model.bean.PromotionItem;
import main.java.model.bean.User;
import main.java.model.util.DBUtil;
import main.java.model.util.DateUtil;
import main.java.pattern.chainOfResponsibility.DiscountPolicy;
import main.java.pattern.template.DAOTemplate;

/**
 * 
 * Template Method Pattern - ConcreteTemplate
 * Factory Method Pattern - ConcreteCreator
 *
 */
public class ProductDAO extends DAOTemplate {
    String TABLE_NAME = "product";

    public int getTotal(int brandId) {
        int total = 0;
        String sql = "select count(*) from product where brandId = ?";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, brandId);

            try (ResultSet rs = ps.executeQuery();) {
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
            sql = "insert into " + TABLE_NAME + " values(DEFAULT,?,?,?,?,?)";
            break;
        case 2:
            sql = "update " + TABLE_NAME + " set " + "name= ?, inventory=?, price=?, dateAdded=?, brandId = ? "
                    + "where productId = ?";
            break;
        case 3:
            sql = "delete from " + TABLE_NAME + "  where productId = ?";
            break;
        case 4:
            sql = "select * from " + TABLE_NAME + "  where productId = ?";
            break;
        default:
            break;
        }
        return sql;

    }
    
    /**
     * 
     * factory method
     * 
     */
    @Override
    protected Object setModelFromGet(ResultSet rs) throws Exception {
        Product bean = new Product();
        if (rs.next()) {
            String name = rs.getString("name");
            int inventory = rs.getInt("inventory");
            float price = rs.getFloat("price");
            Date dateAdded = DateUtil.t2d(rs.getTimestamp("dateAdded"));
            int brandId = rs.getInt("brandId");

            Brand brand = null;
            brand = (Brand) new BrandDAO().get(brandId);
            bean.setName(name);
            bean.setInventory(inventory);
            bean.setPrice(price);
            bean.setDateAdded(dateAdded);
            bean.setBrand(brand);

            bean.setId(rs.getInt("productID"));
            setFirstProductImage(bean);
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
        Product bean = (Product) obj;
        ps.setString(1, bean.getName());
        ps.setInt(2, bean.getInventory());
        ps.setFloat(3, bean.getPrice());
        ps.setTimestamp(4, DateUtil.d2t(bean.getDateAdded()));
        ps.setInt(5, bean.getBrand().getId());

        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        return rs;

    }

    protected void executeUpdate(PreparedStatement ps, Object obj) throws SQLException {
        Product bean = (Product) obj;

        ps.setString(1, bean.getName());
        ps.setInt(2, bean.getInventory());
        ps.setFloat(3, bean.getPrice());
        ps.setTimestamp(4, DateUtil.d2t(bean.getDateAdded()));
        ps.setInt(5, bean.getBrand().getId());

        ps.setInt(6, bean.getId());
        ps.execute();

    }

    protected void executeDelete(PreparedStatement ps, int id) throws SQLException {
        ps.setInt(1, id);
        ps.execute();

    }

    protected int setModel(ResultSet rs, Object obj) throws SQLException {
        Product bean = (Product) obj;
        if (rs.next()) {
            int id = rs.getInt(1);
            bean.setId(id);

            return id;
        }
        return 0;
    }

    

    public List<Product> list(int brandId) {
        return list(brandId, 0, Short.MAX_VALUE);
    }

    public List<Product> list(int brandId, int start, int count) {
        List<Product> beans = new ArrayList<>();

        String sql = "select * from product " + "where brandId = ? " + "order by productId desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, brandId);
            ps.setInt(2, start);
            ps.setInt(3, count);

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    Product bean = new Product();
                    int id = rs.getInt(1);
                    String name = rs.getString("name");
                    int inventory = rs.getInt("inventory");
                    float price = rs.getFloat("price");
                    Date dateAdded = DateUtil.t2d(rs.getTimestamp("dateAdded"));
                    Brand brand = null;
                    try {
                        brand = (Brand) new BrandDAO().get(brandId);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    bean.setName(name);
                    bean.setInventory(inventory);
                    bean.setPrice(price);
                    bean.setDateAdded(dateAdded);
                    bean.setBrand(brand);

                    bean.setId(id);
                    bean.setBrand(brand);
                    setFirstProductImage(bean);

                    beans.add(bean);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return beans;
    }

    public List<Product> list() {
        return list(0, Short.MAX_VALUE);
    }

    public List<Product> list(int start, int count) {
        List<Product> beans = new ArrayList<>();

        String sql = "select * from product limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, start);
            ps.setInt(2, count);

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    Product bean = new Product();
                    int id = rs.getInt(1);
                    int brandId = rs.getInt("brandId");
                    String name = rs.getString("name");
                    int inventory = rs.getInt("inventory");
                    float price = rs.getFloat("price");
                    Date dateAdded = DateUtil.t2d(rs.getTimestamp("dateAdded"));
                    Brand brand = null;
                    try {
                        brand = (Brand) new BrandDAO().get(brandId);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    bean.setName(name);
                    bean.setInventory(inventory);
                    bean.setPrice(price);
                    bean.setDateAdded(dateAdded);
                    bean.setBrand(brand);

                    bean.setId(id);
                    bean.setBrand(brand);
                    setFirstProductImage(bean);

                    beans.add(bean);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return beans;
    }

    public void fill(List<Brand> bs) {
        System.out.println("\n=== Show Brand ===");
        for (Brand b : bs) {
            fill(b);
        }
    }

    public void fill(Brand b) {
        List<Product> ps = this.list(b.getId());

        /**
         * 
         * Use Composite Pattern
         *  
         */
        for (Product p : ps) {
            b.add(p);
        }

        b.operation();
    }

    public void setFirstProductImage(Product p) {
        List<ProductImage> pis = new ProductImageDAO().list(p, ProductImageDAO.type_single);
        if (!pis.isEmpty())
            p.setFirstProductImage(pis.get(0));
    }

}
