package main.java.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.java.model.bean.Category;
import main.java.model.bean.Product;
import main.java.model.bean.ProductImage;
import main.java.model.util.DBUtil;
import main.java.model.util.DateUtil;

public class ProductDAO {

    public int getTotal(int categoryId) {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select count(*) from product where categoryId = " + categoryId;

            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return total;
    }

    public int getTotalBySeller(int sellerId, int categoryId) {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select count(*) from product where categoryId = " + categoryId + " and sellerId = "
                    + sellerId;

            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return total;
    }

    public int add(Product bean) {
        String sql = "insert into product values(DEFAULT,?,?,?,?,?)";
        try (Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, bean.getName());
            ps.setInt(2, bean.getInventory());
            ps.setFloat(3, bean.getPrice());
            ps.setTimestamp(4, DateUtil.d2t(bean.getDateAdded()));
            ps.setInt(5, bean.getCategory().getId());
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

        System.out.println("Add Fail!");
        return 0;
    }

    public void update(Product bean) {

        String sql = "update product set name= ?, inventory=?, price=?, dateAdded=?, categoryId = ? where productId = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1, bean.getName());
            ps.setInt(2, bean.getInventory());
            ps.setFloat(3, bean.getPrice());
            ps.setTimestamp(4, DateUtil.d2t(bean.getDateAdded()));
            ps.setInt(5, bean.getCategory().getId());

            ps.setInt(6, bean.getId());
            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    public void delete(int id) {

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "delete from product where productId = " + id;

            s.execute(sql);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public Product get(int id) {
        Product bean = new Product();

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select * from product where productId = " + id;

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {

                String name = rs.getString("name");
                int inventory = rs.getInt("inventory");
                float price = rs.getFloat("price");
                Date dateAdded = DateUtil.t2d(rs.getTimestamp("dateAdded"));
                int categoryId = rs.getInt("categoryId");

                Category category = new CategoryDAO().get(categoryId);

                bean.setName(name);
                bean.setInventory(inventory);
                bean.setPrice(price);
                bean.setDateAdded(dateAdded);
                bean.setCategory(category);

                bean.setId(id);
                setFirstProductImage(bean);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bean;
    }

    public List<Product> list(int categoryId) {
        return list(categoryId, 0, Short.MAX_VALUE);
    }

    public List<Product> list(int categoryId, int start, int count) {
        List<Product> beans = new ArrayList<Product>();

        String sql = "select * from product where categoryId = ? order by productId desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, categoryId);
            ps.setInt(2, start);
            ps.setInt(3, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product bean = new Product();
                int id = rs.getInt(1);
                String name = rs.getString("name");
                int inventory = rs.getInt("inventory");
                float price = rs.getFloat("price");
                Date dateAdded = DateUtil.t2d(rs.getTimestamp("dateAdded"));
                Category category = new CategoryDAO().get(categoryId);

                bean.setName(name);
                bean.setInventory(inventory);
                bean.setPrice(price);
                bean.setDateAdded(dateAdded);
                bean.setCategory(category);

                bean.setId(id);
                bean.setCategory(category);
                setFirstProductImage(bean);

                beans.add(bean);
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
        List<Product> beans = new ArrayList<Product>();

        String sql = "select * from product limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, start);
            ps.setInt(2, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product bean = new Product();
                int id = rs.getInt(1);
                int categoryId = rs.getInt("categoryId");
                String name = rs.getString("name");
                int inventory = rs.getInt("inventory");
                float price = rs.getFloat("price");
                Date dateAdded = DateUtil.t2d(rs.getTimestamp("dateAdded"));
                Category category = new CategoryDAO().get(categoryId);

                bean.setName(name);
                bean.setInventory(inventory);
                bean.setPrice(price);
                bean.setDateAdded(dateAdded);
                bean.setCategory(category);

                bean.setId(id);
                bean.setCategory(category);
                setFirstProductImage(bean);

                beans.add(bean);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return beans;
    }

    public void fill(List<Category> cs) {
        for (Category c : cs) {
            fill(c);
        }
    }

    public void fill(Category c) {
        List<Product> ps = this.list(c.getId());
        c.setProducts(ps);
    }

    public void fillByRow(List<Category> cs) {
        int productNumberEachRow = 8;
        for (Category c : cs) {
            List<Product> products = c.getProducts();
            List<List<Product>> productsByRow = new ArrayList<>();
            for (int i = 0; i < products.size(); i += productNumberEachRow) {
                int size = i + productNumberEachRow;
                size = size > products.size() ? products.size() : size;
                List<Product> productsOfEachRow = products.subList(i, size);
                productsByRow.add(productsOfEachRow);
            }
            c.setProductsByRow(productsByRow);
        }
    }

    public void setFirstProductImage(Product p) {
        List<ProductImage> pis = new ProductImageDAO().list(p, ProductImageDAO.type_single);
        if (!pis.isEmpty())
            p.setFirstProductImage(pis.get(0));
    }
}
