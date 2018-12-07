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
import main.java.model.bean.Product;
import main.java.model.bean.Segment;
import main.java.model.util.DBUtil;
import main.java.pattern.template.DAOTemplate;

/**
 * 
 * Template Method Pattern - ConcreteTemplate
 * Factory Method Pattern - ConcreteCreator
 *
 */
public class CategoryDAO extends DAOTemplate {
    String TABLE_NAME = "category";

    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select count(*) from category";

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
            sql = "insert into " + TABLE_NAME + " values(DEFAULT,?, ?)";
            break;
        case 2:
            sql = "update " + TABLE_NAME + " set name= ? where categoryId = ?";
            break;
        case 3:
            sql = "delete from " + TABLE_NAME + " where categoryId = ?";
            break;
        case 4:
            sql = "select * from " + TABLE_NAME + " where categoryId = ?";
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
        Category bean = new Category();
        if (rs.next()) {
            String name = rs.getString("name");
            int segmentId = rs.getInt("segmentId");

            Segment segment = (Segment) new SegmentDAO().get(segmentId);

            bean.setName(name);
            bean.setSegment(segment);
            bean.setId(rs.getInt("categoryId"));
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
        Category bean = (Category) obj;
        ps.setString(1, bean.getName());
        ps.setInt(2, bean.getSegment().getId());

        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        return rs;

    }

    protected void executeUpdate(PreparedStatement ps, Object obj) throws SQLException {
        Category bean = (Category) obj;

        ps.setString(1, bean.getName());
        ps.setInt(2, bean.getId());

        ps.execute();

    }

    protected void executeDelete(PreparedStatement ps, int id) throws SQLException {
        ps.setInt(1, id);
        ps.execute();

    }

    protected int setModel(ResultSet rs, Object obj) throws SQLException {
        Category bean = (Category) obj;
        if (rs.next()) {
            int id = rs.getInt(1);
            bean.setId(id);

            return id;
        }
        return 0;
    }
    
    

    public List<Category> list() throws Exception {
        return list(0, Short.MAX_VALUE);
    }

    public List<Category> list(int start, int count) throws Exception {
        List<Category> beans = new ArrayList<>();

        String sql = "select * from category order by categoryId desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, start);
            ps.setInt(2, count);

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    Category bean = new Category();
                    int id = rs.getInt(1);
                    String name = rs.getString("name");
                    int segmentId = rs.getInt("segmentId");

                    Segment segment = (Segment) new SegmentDAO().get(segmentId);

                    bean.setName(name);
                    bean.setSegment(segment);
                    bean.setId(id);
                    beans.add(bean);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return beans;
    }

    public List<Category> list(int segmentId) throws Exception {
        return list(segmentId, 0, Short.MAX_VALUE);
    }

    public List<Category> list(int segmentId, int start, int count) throws Exception {
        List<Category> beans = new ArrayList<>();

        String sql = "select * from category " + "where segmentId = ? " + "order by categoryId desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, segmentId);
            ps.setInt(2, start);
            ps.setInt(3, count);

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    Category bean = new Category();
                    int id = rs.getInt(1);
                    String name = rs.getString("name");

                    Segment segment = (Segment) new SegmentDAO().get(segmentId);

                    bean.setName(name);
                    bean.setSegment(segment);
                    bean.setId(id);
                    beans.add(bean);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return beans;
    }

    public void fill(List<Segment> ss) throws Exception {
        System.out.println("\n=== Show Segment ===");
        for (Segment s : ss) {
            fill(s);
        }
    }

    public void fill(Segment s) throws Exception {
        List<Category> cs = this.list(s.getId());

        /**
         * 
         * Use Composite Pattern
         *  
         */
        for (Category c : cs) {
            s.add(c);
        }

        s.operation();
    }

}
