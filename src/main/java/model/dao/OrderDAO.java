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
import main.java.model.bean.Order;
import main.java.model.bean.User;
import main.java.model.util.DBUtil;
import main.java.model.util.DateUtil;
import main.java.pattern.template.DAOTemplate;

/**
 * 
 * Template Pattern - ConcreteTemplate
 *
 */
public class OrderDAO extends DAOTemplate {
    String TABLE_NAME = "order_";
    public static final String WAIT_PAY = "waitPay";
    public static final String WAIT_DELIVERY = "waitDelivery";
    public static final String WAIT_CONFIRM = "waitConfirm";
    public static final String WAIT_REVIEW = "waitReview";
    public static final String FINISH = "finish";
    public static final String DELETE = "delete";

    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select count(*) from order_";

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
            sql = "insert into " + TABLE_NAME + " values(DEFAULT,?,?,?,?,?,?,?)";
            break;
        case 2:
            sql = "update " + TABLE_NAME
                    + " set userId= ?, dateOrdered=?, datePaid=?, state = ? , total =? , deliverMethod =?, address=?  where orderId = ?";
            break;
        case 3:
            sql = "delete from " + TABLE_NAME + " where orderId = ?";
            break;
        case 4:
            sql = "select * from " + TABLE_NAME + " where orderId = ?";
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
        Order bean = new Order();
        if (rs.next()) {

            Date dateOrdered = DateUtil.t2d(rs.getTimestamp("dateOrdered"));
            Date datePaid = DateUtil.t2d(rs.getTimestamp("datePaid"));

            User user = null;
            try {
                user = (User) new UserDAO().get(rs.getInt("userId"));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            bean.setUser(user);
            bean.setDateOrdered(dateOrdered);
            bean.setDatePaid(datePaid);
            bean.setState(rs.getString("state"));
            bean.setTotal(rs.getFloat("total"));
            bean.setDeliverMethod(rs.getInt("deliverMethod"));
            bean.setAddress(rs.getString("address"));

            bean.setId(rs.getInt("orderId"));
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
        Order bean = (Order) obj;
        ps.setInt(1, bean.getUser().getId());
        ps.setTimestamp(2, DateUtil.d2t(bean.getDateOrdered()));
        ps.setTimestamp(3, DateUtil.d2t(bean.getDatePaid()));
        ps.setString(4, bean.getState());
        ps.setFloat(5, bean.getTotal());
        ps.setInt(6, bean.getDeliverMethod());
        ps.setString(7, bean.getAddress());

        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        return rs;
    }

    protected void executeUpdate(PreparedStatement ps, Object obj) throws SQLException {
        Order bean = (Order) obj;

        ps.setInt(1, bean.getUser().getId());
        ps.setTimestamp(2, DateUtil.d2t(bean.getDateOrdered()));
        ps.setTimestamp(3, DateUtil.d2t(bean.getDatePaid()));
        ps.setString(4, bean.getState());
        ps.setFloat(5, bean.getTotal());
        ps.setInt(6, bean.getDeliverMethod());
        ps.setString(7, bean.getAddress());
        ps.setInt(8, bean.getId());
        ps.execute();
    }

    protected void executeDelete(PreparedStatement ps, int id) throws SQLException {
        ps.setInt(1, id);
        ps.execute();
    }

    protected int setModel(ResultSet rs, Object obj) throws SQLException {
        Order bean = (Order) obj;
        if (rs.next()) {
            int id = rs.getInt(1);
            bean.setId(id);

            return id;
        }
        return 0;
    }

    

    public List<Order> list() {
        return list(0, Short.MAX_VALUE);
    }

    public List<Order> list(int start, int count) {
        List<Order> beans = new ArrayList<>();

        String sql = "select * from order_ order by orderId desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, start);
            ps.setInt(2, count);

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    Order bean = new Order();

                    int orderId = rs.getInt("orderId");
                    int userId = rs.getInt("userId");
                    Date dateOrdered = DateUtil.t2d(rs.getTimestamp("dateOrdered"));
                    Date datePaid = DateUtil.t2d(rs.getTimestamp("datePaid"));
                    String state = rs.getString("state");
                    float total = rs.getFloat("total");
                    int deliverMethod = rs.getInt("deliverMethod");
                    String address = rs.getString("address");

                    User user = null;
                    try {
                        user = (User) new UserDAO().get(userId);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    bean.setUser(user);
                    bean.setDateOrdered(dateOrdered);
                    bean.setDatePaid(datePaid);
                    bean.setState(state);
                    bean.setTotal(total);
                    bean.setDeliverMethod(deliverMethod);
                    bean.setAddress(address);

                    bean.setId(orderId);

                    beans.add(bean);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return beans;
    }

    public List<Order> list(int userId, String excludedStatus) {
        return list(userId, excludedStatus, 0, Short.MAX_VALUE);
    }

    public List<Order> list(int userId, String excludedStatus, int start, int count) {
        List<Order> beans = new ArrayList<>();

        String sql = "select * from order_ where userId = ? and state != ? order by orderId desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, userId);
            ps.setString(2, excludedStatus);
            ps.setInt(3, start);
            ps.setInt(4, count);

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    Order bean = new Order();

                    int orderId = rs.getInt("orderId");
                    Date dateOrdered = DateUtil.t2d(rs.getTimestamp("dateOrdered"));
                    Date datePaid = DateUtil.t2d(rs.getTimestamp("datePaid"));
                    String state = rs.getString("state");
                    float total = rs.getFloat("total");
                    int deliverMethod = rs.getInt("deliverMethod");
                    String address = rs.getString("address");

                    User user = null;
                    try {
                        user = (User) new UserDAO().get(userId);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    bean.setUser(user);
                    bean.setDateOrdered(dateOrdered);
                    bean.setDatePaid(datePaid);
                    bean.setState(state);
                    bean.setTotal(total);
                    bean.setDeliverMethod(deliverMethod);
                    bean.setAddress(address);

                    bean.setId(orderId);

                    beans.add(bean);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return beans;
    }

}
