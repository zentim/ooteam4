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

    public int getTotal(int cid) {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select count(*) from Product where cid = " + cid;

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

        String sql = "insert into Product values(DEFAULT,?,?,?,?,?,?)";
        try (
        		Connection c = DBUtil.getConnection();
        		PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {

            ps.setString(1, bean.getName());
            ps.setFloat(2, bean.getOriginalPrice());
            ps.setFloat(3, bean.getPromotePrice());
            ps.setInt(4, bean.getStock());
            ps.setInt(5, bean.getCategory().getId());
            ps.setTimestamp(6, DateUtil.d2t(bean.getCreateDate()));
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

        String sql = "update Product set name= ?, originalPrice=?,promotePrice=?,stock=?, cid = ?, createDate=? where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1, bean.getName());
            ps.setFloat(2, bean.getOriginalPrice());
            ps.setFloat(3, bean.getPromotePrice());
            ps.setInt(4, bean.getStock());
            ps.setInt(5, bean.getCategory().getId());
            ps.setTimestamp(6, DateUtil.d2t(bean.getCreateDate()));
            ps.setInt(7, bean.getId());
            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    public void delete(int id) {

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "delete from Product where id = " + id;

            s.execute(sql);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public Product get(int id) {
        Product bean = new Product();

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select * from Product where id = " + id;

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {

                String name = rs.getString("name");
                float originalPrice = rs.getFloat("originalPrice");
                float promotePrice = rs.getFloat("promotePrice");
                int stock = rs.getInt("stock");
                int cid = rs.getInt("cid");
                Date createDate = DateUtil.t2d( rs.getTimestamp("createDate"));

                bean.setName(name);
                bean.setOriginalPrice(originalPrice);
                bean.setPromotePrice(promotePrice);
                bean.setStock(stock);
                Category category = new CategoryDAO().get(cid);
                bean.setCategory(category);
                bean.setCreateDate(createDate);
                bean.setId(id);
                setFirstProductImage(bean);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return bean;
    }

    public List<Product> list(int cid) {
        return list(cid,0, Short.MAX_VALUE);
    }

    // slow
    public List<Product> list(int cid, int start, int count) {
        List<Product> beans = new ArrayList<Product>();
        Category category = new CategoryDAO().get(cid);

        String sql = "select * from Product where cid = ? order by id desc limit ?,? ";

        // for postgresql
 		if (DBUtil.DBMS.equals("postgresql")) {
 			sql = "select * from Product where cid = ? order by id desc LIMIT ? OFFSET ? ";
 		}

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, cid);
            ps.setInt(2, start);
            ps.setInt(3, count);

            // for postgresql
 			if (DBUtil.DBMS.equals("postgresql")) {
 				ps.setInt(3, start);
 				ps.setInt(2, count);
 			}

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product bean = new Product();
                int id = rs.getInt(1);

                String name = rs.getString("name");
                float originalPrice = rs.getFloat("originalPrice");
                float promotePrice = rs.getFloat("promotePrice");
                int stock = rs.getInt("stock");
                Date createDate = DateUtil.t2d( rs.getTimestamp("createDate"));

                bean.setName(name);
                bean.setOriginalPrice(originalPrice);
                bean.setPromotePrice(promotePrice);
                bean.setStock(stock);
                bean.setCreateDate(createDate);
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
        return list(0,Short.MAX_VALUE);
    }

    public List<Product> list(int start, int count) {
        List<Product> beans = new ArrayList<Product>();

        String sql = "select * from Product limit ?,? ";

        // for postgresql
  		if (DBUtil.DBMS.equals("postgresql")) {
  			sql = "select * from Product limit LIMIT ? OFFSET ? ";
  		}

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, start);
            ps.setInt(2, count);

            // for postgresql
 			if (DBUtil.DBMS.equals("postgresql")) {
 				ps.setInt(2, start);
 				ps.setInt(1, count);
 			}

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product bean = new Product();
                int id = rs.getInt(1);
                int cid = rs.getInt("cid");
                String name = rs.getString("name");
                float originalPrice = rs.getFloat("originalPrice");
                float promotePrice = rs.getFloat("promotePrice");
                int stock = rs.getInt("stock");
                Date createDate = DateUtil.t2d( rs.getTimestamp("createDate"));

                bean.setName(name);
                bean.setOriginalPrice(originalPrice);
                bean.setPromotePrice(promotePrice);
                bean.setStock(stock);
                bean.setCreateDate(createDate);
                bean.setId(id);

                Category category = new CategoryDAO().get(cid);
                bean.setCategory(category);
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
            List<Product> products =  c.getProducts();
            List<List<Product>> productsByRow =  new ArrayList<>();
            for (int i = 0; i < products.size(); i+=productNumberEachRow) {
                int size = i+productNumberEachRow;
                size= size>products.size()?products.size():size;
                List<Product> productsOfEachRow =products.subList(i, size);
                productsByRow.add(productsOfEachRow);
            }
            c.setProductsByRow(productsByRow);
        }
    }

    public void setFirstProductImage(Product p) {
        List<ProductImage> pis= new ProductImageDAO().list(p, ProductImageDAO.type_single);
        if(!pis.isEmpty())
            p.setFirstProductImage(pis.get(0));
    }

    public void setSaleNumber(Product p) {
        int saleCount = new OrderItemDAO().getSaleCount(p.getId());
        p.setSaleCount(saleCount);

    }

    public void setSaleNumber(List<Product> products) {
        for (Product p : products) {
            setSaleNumber(p);
        }
    }

    public List<Product> search(String keyword, int start, int count) {
         List<Product> beans = new ArrayList<Product>();

         if(null==keyword||0==keyword.trim().length())
             return beans;
            String sql = "select * from Product where name like ? limit ?,? ";

            try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
                ps.setString(1, "%"+keyword.trim()+"%");
                ps.setInt(2, start);
                ps.setInt(3, count);

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Product bean = new Product();
                    int id = rs.getInt(1);
                    int cid = rs.getInt("cid");
                    String name = rs.getString("name");
                    float originalPrice = rs.getFloat("originalPrice");
                    float promotePrice = rs.getFloat("promotePrice");
                    int stock = rs.getInt("stock");
                    Date createDate = DateUtil.t2d( rs.getTimestamp("createDate"));

                    bean.setName(name);
                    bean.setOriginalPrice(originalPrice);
                    bean.setPromotePrice(promotePrice);
                    bean.setStock(stock);
                    bean.setCreateDate(createDate);
                    bean.setId(id);

                    Category category = new CategoryDAO().get(cid);
                    bean.setCategory(category);
                    setFirstProductImage(bean);
                    beans.add(bean);
                }
            } catch (SQLException e) {

                e.printStackTrace();
            }
            return beans;
    }
}
