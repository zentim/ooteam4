package main.java.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.java.model.bean.Promotion;
import main.java.model.util.DBUtil;
import main.java.model.util.DateUtil;
import main.java.pattern.template.DAOTemplate;

/**
 * 
 * Template Pattern - ConcreteTemplate
 *
 */
public class PromotionDAO extends DAOTemplate {
    String TABLE_NAME = "promotion";

    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select count(*) from promotion";

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

    protected String getMainSql(int type) {
        String sql = "";
        switch (type) {
        case 1:
            sql = "insert into " + TABLE_NAME + " values(DEFAULT,?, ?, ?, ?, ?)";
            break;
        case 2:
            sql = "update " + TABLE_NAME
                    + " set discountType=?, name=?, dateFrom=?, dateTo=?, state=? where promotionId = ?";
            break;
        case 3:
            sql = "delete from " + TABLE_NAME + " where promotionId = ?";
            break;
        case 4:
            sql = "select * from " + TABLE_NAME + " where promotionId = ?";
            break;
        default:
            break;
        }
        return sql;

    }

    protected ResultSet executeAdd(PreparedStatement ps, Object obj) throws SQLException {
        Promotion bean = (Promotion) obj;
        ps.setInt(1, bean.getDiscountType());
        ps.setString(2, bean.getName());
        ps.setTimestamp(3, DateUtil.d2t(bean.getDateFrom()));
        ps.setTimestamp(4, DateUtil.d2t(bean.getDateTo()));
        ps.setInt(5, bean.getState());

        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        return rs;

    }

    protected void executeUpdate(PreparedStatement ps, Object obj) throws SQLException {
        Promotion bean = (Promotion) obj;
        ps.setInt(1, bean.getDiscountType());
        ps.setString(2, bean.getName());
        ps.setTimestamp(3, DateUtil.d2t(bean.getDateFrom()));
        ps.setTimestamp(4, DateUtil.d2t(bean.getDateTo()));
        ps.setInt(5, bean.getState());

        ps.setInt(6, bean.getId());
        ps.execute();

    }

    protected void executeDelete(PreparedStatement ps, int id) throws SQLException {
        ps.setInt(1, id);
        ps.execute();

    }

    protected int setModel(ResultSet rs, Object obj) throws SQLException {
        Promotion bean = (Promotion) obj;
        if (rs.next()) {
            int id = rs.getInt(1);
            bean.setId(id);

            return id;
        }
        return 0;
    }

    protected Object setModelFromGet(ResultSet rs) throws SQLException {
        Promotion bean = new Promotion();
        if (rs.next()) {

            String name = rs.getString("name");
            Date dateFrom = DateUtil.t2d(rs.getTimestamp("dateFrom"));
            Date dateTo = DateUtil.t2d(rs.getTimestamp("dateTo"));
            int state = rs.getInt("state");
            int discountType = rs.getInt("discountType");

            bean.setDiscountType(discountType);
            bean.setName(name);
            bean.setDateFrom(dateFrom);
            bean.setDateTo(dateTo);
            bean.setState(state);
            bean.setDiscountTypeDesc(bean.getDiscountTypeDescription());

            bean.setId(rs.getInt("PromotionId"));
            return bean;
        }
        return null;
    }

    public List<Promotion> list() {
        return list(0, Short.MAX_VALUE);
    }

    public List<Promotion> list(int start, int count) {
        List<Promotion> beans = new ArrayList<>();

        String sql = "select * from promotion order by promotionId desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, start);
            ps.setInt(2, count);

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    Promotion bean = new Promotion();

                    int promotionId = rs.getInt("promotionId");
                    String name = rs.getString("name");
                    Date dateFrom = DateUtil.t2d(rs.getTimestamp("dateFrom"));
                    Date dateTo = DateUtil.t2d(rs.getTimestamp("dateTo"));
                    int state = rs.getInt("state");
                    int discountType = rs.getInt("discountType");

                    bean.setDiscountType(discountType);
                    bean.setName(name);
                    bean.setDateFrom(dateFrom);
                    bean.setDateTo(dateTo);
                    bean.setState(state);
                    bean.setDiscountTypeDesc(bean.getDiscountTypeDescription());

                    bean.setId(promotionId);
                    beans.add(bean);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return beans;
    }

}
