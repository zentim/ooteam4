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
import main.java.model.bean.Product;
import main.java.model.bean.ProductImage;
import main.java.model.bean.Promotion;
import main.java.model.bean.PromotionItem;
import main.java.model.util.DBUtil;
import main.java.model.util.DateUtil;
import main.java.pattern.chainOfResponsibility.DiscountPolicy;

public class ProductDAO {

    public int getTotal(int brandId) {
        int total = 0;
        String sql = "select count(*) from product where brandId = ?";
        
        try (Connection c = DBUtil.getConnection(); 
                PreparedStatement ps = c.prepareStatement(sql);) {

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

    public int getTotalBySeller(int sellerId, int brandId) {
        int total = 0;
        String sql = "select count(*) from product where brandId = ? and sellerId = ?";
        
        try (Connection c = DBUtil.getConnection(); 
                PreparedStatement ps = c.prepareStatement(sql);) {
            
            ps.setInt(1, brandId);
            ps.setInt(2, sellerId);

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

    public int add(Product bean) {
        String sql = "insert into product values(DEFAULT,?,?,?,?,?)";
        try (Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, bean.getName());
            ps.setInt(2, bean.getInventory());
            ps.setFloat(3, bean.getPrice());
            ps.setTimestamp(4, DateUtil.d2t(bean.getDateAdded()));
            ps.setInt(5, bean.getBrand().getId());
            ps.execute();

            try (ResultSet rs = ps.getGeneratedKeys();) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    bean.setId(id);

                    return id;
                }
            }
            
        } catch (SQLException e) {

            e.printStackTrace();
        }

        System.out.println("Add Fail!");
        return 0;
    }

    public void update(Product bean) {

        String sql = "update product set "
                + "name= ?, inventory=?, price=?, dateAdded=?, brandId = ? "
                + "where productId = ?";
        try (Connection c = DBUtil.getConnection(); 
                PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1, bean.getName());
            ps.setInt(2, bean.getInventory());
            ps.setFloat(3, bean.getPrice());
            ps.setTimestamp(4, DateUtil.d2t(bean.getDateAdded()));
            ps.setInt(5, bean.getBrand().getId());

            ps.setInt(6, bean.getId());
            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    public void delete(int id) {
        String sql = "delete from product where productId = ?";
        
        try (Connection c = DBUtil.getConnection(); 
                PreparedStatement ps = c.prepareStatement(sql);) {
            
            ps.setInt(1, id);
            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public Product get(int id) {
        Product bean = new Product();

        String sql = "select * from product where productId = ?";
        try (Connection c = DBUtil.getConnection(); 
                PreparedStatement ps = c.prepareStatement(sql);) {
            
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {

                    String name = rs.getString("name");
                    int inventory = rs.getInt("inventory");
                    float price = rs.getFloat("price");
                    Date dateAdded = DateUtil.t2d(rs.getTimestamp("dateAdded"));
                    int brandId = rs.getInt("brandId");

                    Brand brand = new BrandDAO().get(brandId);

                    bean.setName(name);
                    bean.setInventory(inventory);
                    bean.setPrice(price);
                    bean.setDateAdded(dateAdded);
                    bean.setBrand(brand);

                    bean.setId(id);
                    setFirstProductImage(bean);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bean;
    }

    public List<Product> list(int brandId) {
        return list(brandId, 0, Short.MAX_VALUE);
    }

    public List<Product> list(int brandId, int start, int count) {
        List<Product> beans = new ArrayList<>();

        String sql = "select * from product "
                + "where brandId = ? "
                + "order by productId desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); 
                PreparedStatement ps = c.prepareStatement(sql);) {
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
                    Brand brand = new BrandDAO().get(brandId);

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

        try (Connection c = DBUtil.getConnection(); 
                PreparedStatement ps = c.prepareStatement(sql);) {

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
                    Brand brand = new BrandDAO().get(brandId);

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
        b.setProducts(ps);
        
        // Use Composite Pattern
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
    
    public void fillPromotion(Brand brand) {
    	PromotionItemDAO promotionItemDAO = new PromotionItemDAO();
    	
        for (Product p : brand.getProducts()) {
          PromotionItem promotionItem = promotionItemDAO.getByProduct(p.getId()); 
          Promotion promotionByProduct = promotionItem.getPromotion();
          
          if (promotionByProduct != null 
                  && !(promotionItem.getDiscountOf() == 100 
                  && promotionByProduct.getDiscountType() == DiscountPolicy.BUY_X_GET_Y_FREE)) {
              
                String promotionName = "";
                if (promotionByProduct.getState() == 1) {
                    promotionName = promotionByProduct.getName();
                }
                
                String discountTypeName = promotionByProduct.getDiscountTypeDescription();
                p.setPromotionName(promotionName);
                p.setDiscountTypeName(discountTypeName);
          }
        }
          
    }
    
    public void fillPromotion(List<Brand> brands) {
    	PromotionItemDAO promotionItemDAO = new PromotionItemDAO();
    	
    	for (Brand c : brands) {
    	    for (Product p : c.getProducts()) {
    	        PromotionItem promotionItem = promotionItemDAO.getByProduct(p.getId()); 
    	        Promotion promotionByProduct = promotionItem.getPromotion();
    	        
    	        if (promotionByProduct != null 
    	                && !(promotionItem.getDiscountOf() == 100 
    	                && promotionByProduct.getDiscountType() == DiscountPolicy.BUY_X_GET_Y_FREE)) {
                  
    	            String promotionName = "";
    	            if (promotionByProduct.getState() == 1) {
    	                promotionName = promotionByProduct.getName();
    	            }
    	            
    	            String discountTypeName = promotionByProduct.getDiscountTypeDescription();
    	            p.setPromotionName(promotionName);
    	            p.setDiscountTypeName(discountTypeName);
    	        }
            }
    	}
    }
}
