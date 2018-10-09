package main.java.model.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.java.model.bean.Order;
import main.java.model.bean.User;
import main.java.model.util.DBUtil;
import main.java.model.util.DateUtil;


public class OrderDAO {
    public static final String waitPay = "waitPay";
    public static final String waitDelivery = "waitDelivery";
    public static final String waitConfirm = "waitConfirm";
    public static final String waitReview = "waitReview";
    public static final String finish = "finish";
    public static final String delete = "delete";

    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select count(*) from order_";

            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return total;
    }

    public int add(Order bean) {

        String sql = "insert into order_ values(DEFAULT,?,?,?,?,?,?,?)";
        try (
        		Connection c = DBUtil.getConnection();
        		PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
        	ps.setInt(1, bean.getUser().getId());
        	ps.setTimestamp(2, DateUtil.d2t(bean.getDateOrdered()));
        	ps.setTimestamp(3, DateUtil.d2t(bean.getDatePaid()));
        	ps.setString(4, bean.getState());
        	ps.setFloat(5, bean.getTotal());
        	ps.setInt(6, bean.getDeliverMethod());
        	ps.setString(7, bean.getAddress());

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

    public void update(Order bean) {

        String sql = "update order_ set userId= ?, dateOrdered=?, datePaid=?, state = ? , total =? , deliverMethod =?, address=? where orderId = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

        	ps.setInt(1, bean.getUser().getId());
        	ps.setTimestamp(2, DateUtil.d2t(bean.getDateOrdered()));
        	ps.setTimestamp(3, DateUtil.d2t(bean.getDatePaid()));
        	ps.setString(4, bean.getState());
        	ps.setFloat(5, bean.getTotal());
        	ps.setInt(6, bean.getDeliverMethod());
        	ps.setString(7, bean.getAddress());
        	
            ps.setInt(8, bean.getId());
            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    public void delete(int id) {

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "delete from order_ where orderId = " + id;

            s.execute(sql);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public Order get(int id) {
        Order bean = new Order();

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select * from order_ where orderId = " + id;

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
            	int userId = rs.getInt("userId");
            	Date dateOrdered = DateUtil.t2d( rs.getTimestamp("dateOrdered"));
            	Date datePaid = DateUtil.t2d( rs.getTimestamp("datePaid"));
            	String state = rs.getString("state");
            	float total = rs.getFloat("total");
            	int deliverMethod = rs.getInt("deliverMethod");
            	String address = rs.getString("address");

                User user = new UserDAO().get(userId);
            	
                bean.setUser(user);
                bean.setDateOrdered(dateOrdered);
                bean.setDatePaid(datePaid);
                bean.setState(state);
                bean.setTotal(total);
                bean.setDeliverMethod(deliverMethod);
                bean.setAddress(address);
                
                bean.setId(id);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return bean;
    }

    public List<Order> list() {
        return list(0, Short.MAX_VALUE);
    }

    public List<Order> list(int start, int count) {
        List<Order> beans = new ArrayList<Order>();

        String sql = "select * from order_ order by orderId desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, start);
            ps.setInt(2, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order bean = new Order();
                
                int orderId = rs.getInt("orderId");
                int userId = rs.getInt("userId");
            	Date dateOrdered = DateUtil.t2d( rs.getTimestamp("dateOrdered"));
            	Date datePaid = DateUtil.t2d( rs.getTimestamp("datePaid"));
            	String state = rs.getString("state");
            	float total = rs.getFloat("total");
            	int deliverMethod = rs.getInt("deliverMethod");
            	String address = rs.getString("address");

                User user = new UserDAO().get(userId);
            	
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
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return beans;
    }

    public List<Order> list(int userId,String excludedStatus) {
        return list(userId,excludedStatus,0, Short.MAX_VALUE);
    }

    public List<Order> list(int userId, String excludedStatus, int start, int count) {
        List<Order> beans = new ArrayList<Order>();

        String sql = "select * from order_ where userId = ? and state != ? order by orderId desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, userId);
            ps.setString(2, excludedStatus);
            ps.setInt(3, start);
            ps.setInt(4, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            	Order bean = new Order();
                
                int orderId = rs.getInt("orderId");
            	Date dateOrdered = DateUtil.t2d( rs.getTimestamp("dateOrdered"));
            	Date datePaid = DateUtil.t2d( rs.getTimestamp("datePaid"));
            	String state = rs.getString("state");
            	float total = rs.getFloat("total");
            	int deliverMethod = rs.getInt("deliverMethod");
            	String address = rs.getString("address");

                User user = new UserDAO().get(userId);
            	
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
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return beans;
    }

}
