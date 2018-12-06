package main.java.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.java.model.bean.Order;
import main.java.model.bean.Segment;
import main.java.model.util.DBUtil;
import main.java.model.util.DateUtil;
import main.java.pattern.template.DAOTemplate;

/**
 * 
 * Template Pattern - ConcreteTemplate
 *
 */
public class SegmentDAO extends DAOTemplate {
    String TABLE_NAME = "segment";

    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select count(*) from segment";

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
            sql = "insert into " + TABLE_NAME + " values(DEFAULT,?)";
            break;
        case 2:
            sql = "update " + TABLE_NAME + " set name= ? where segmentId = ?";
            break;
        case 3:
            sql = "delete from " + TABLE_NAME + " where segmentId = ?";
            break;
        case 4:
            sql = "select * from " + TABLE_NAME + " where segmentId = ?";
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
    protected Object setModelFromGet(ResultSet rs) throws SQLException {
        Segment bean = new Segment();
        if (rs.next()) {
            bean = new Segment();
            String name = rs.getString(2);
            bean.setName(name);
            bean.setId(rs.getInt("segmentId"));

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
        Segment bean = (Segment) obj;
        ps.setString(1, bean.getName());
        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        return rs;

    }

    protected void executeUpdate(PreparedStatement ps, Object obj) throws SQLException {
        Segment bean = (Segment) obj;

        ps.setString(1, bean.getName());
        ps.setInt(2, bean.getId());
        ps.execute();

    }

    protected void executeDelete(PreparedStatement ps, int id) throws SQLException {
        ps.setInt(1, id);
        ps.execute();

    }

    protected int setModel(ResultSet rs, Object obj) throws SQLException {
        Segment bean = (Segment) obj;
        if (rs.next()) {
            int id = rs.getInt(1);
            bean.setId(id);

            return id;
        }
        return 0;
    }

    

    public List<Segment> list() {
        return list(0, Short.MAX_VALUE);
    }

    public List<Segment> list(int start, int count) {
        List<Segment> beans = new ArrayList<>();

        String sql = "select * from segment order by segmentId desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, start);
            ps.setInt(2, count);

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    Segment bean = new Segment();
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    bean.setId(id);
                    bean.setName(name);
                    beans.add(bean);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return beans;
    }

}
