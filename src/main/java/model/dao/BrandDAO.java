package main.java.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.java.model.bean.Brand;
import main.java.model.bean.Category;
import main.java.model.util.DBUtil;
import main.java.pattern.template.DAOTemplate;

/**
 * 
 * Template Method Pattern - ConcreteTemplate
 * Factory Method Pattern - ConcreteCreator
 *
 */
public class BrandDAO extends DAOTemplate {
    String TABLE_NAME = "brand";

    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select count(*) from brand";

            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
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
            sql = "insert into " + TABLE_NAME + " values(DEFAULT,?, ?)";
            break;
        case 2:
            sql = "update " + TABLE_NAME + " set name = ? where brandId = ?";
            break;
        case 3:
            sql = "delete from " + TABLE_NAME + " where brandId = ?";
            break;
        case 4:
            sql = "select * from " + TABLE_NAME + " where brandId = ?";
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
        Brand bean = new Brand();
        while (rs.next()) {
            String name = rs.getString("name");
            int categoryId = rs.getInt("categoryId");
            Category category = (Category) new CategoryDAO().get(categoryId);
            bean.setName(name);
            bean.setCategory(category);
            bean.setId(rs.getInt("brandId"));
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
        Brand bean = (Brand) obj;
        ps.setString(1, bean.getName());
        ps.setInt(2, bean.getCategory().getId());

        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        return rs;
    }

    protected void executeUpdate(PreparedStatement ps, Object obj) throws SQLException {
        Brand bean = (Brand) obj;

        ps.setString(1, bean.getName());
        ps.setInt(2, bean.getId());

        ps.execute();
    }

    protected void executeDelete(PreparedStatement ps, int id) throws SQLException {
        ps.setInt(1, id);
        ps.execute();
    }

    protected int setModel(ResultSet rs, Object obj) throws SQLException {
        Brand bean = (Brand) obj;
        if (rs.next()) {
            int id = rs.getInt(1);
            bean.setId(id);

            return id;
        }
        return 0;
    }

    

    public List<Brand> list() throws Exception {
        return list(0, Short.MAX_VALUE);
    }

    public List<Brand> list(int start, int count) throws Exception {
        List<Brand> beans = new ArrayList<>();

        String sql = "select * from brand order by brandId desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, start);
            ps.setInt(2, count);

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    Brand bean = new Brand();
                    int id = rs.getInt(1);
                    String name = rs.getString("name");
                    int categoryId = rs.getInt("categoryId");

                    Category category = (Category) new CategoryDAO().get(categoryId);

                    bean.setName(name);
                    bean.setCategory(category);
                    bean.setId(id);
                    beans.add(bean);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return beans;
    }

    public List<Brand> list(int categoryId) throws Exception {
        return list(categoryId, 0, Short.MAX_VALUE);
    }

    public List<Brand> list(int categoryId, int start, int count) throws Exception {
        List<Brand> beans = new ArrayList<>();

        String sql = "select * from brand where categoryId = ? order by brandId desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, categoryId);
            ps.setInt(2, start);
            ps.setInt(3, count);

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    Brand bean = new Brand();
                    int id = rs.getInt(1);
                    String name = rs.getString("name");

                    Category category = (Category) new CategoryDAO().get(categoryId);

                    bean.setName(name);
                    bean.setCategory(category);
                    bean.setId(id);
                    beans.add(bean);
                }
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }

        return beans;
    }

    public void fill(List<Category> cs) throws Exception {
        for (Category c : cs) {
            fill(c);
        }
    }

    public void fill(Category c) throws Exception {
        List<Brand> bs = this.list(c.getId());
        
        /**
         * 
         * Use Composite Pattern
         *  
         */
        for (Brand b : bs) {
            c.add(b);
        }
    }

}
