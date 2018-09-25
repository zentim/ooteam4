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
  
            String sql = "select count(*) from Order_";
  
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return total;
    }
  
    public void add(Order bean) {
 
        String sql = "insert into Order_ values(null,?,?,?,?,?,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setString(1, bean.getOrderCode());
            ps.setString(2, bean.getAddress());
            ps.setString(3, bean.getReceiver());
            ps.setString(4, bean.getPhone());
            ps.setTimestamp(5,  DateUtil.d2t(bean.getCreateDate()));
            ps.setTimestamp(6,  DateUtil.d2t(bean.getPayDate()));
            ps.setInt(7, bean.getUser().getId());
            ps.setString(8, bean.getStatus());
 
            ps.execute();
  
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                bean.setId(id);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
    }
  
    public void update(Order bean) {
 
        String sql = "update Order_ set address= ?, receiver=?, phone=?, createDate = ? , payDate =? , orderCode =?, uid=?, status=? where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setString(1, bean.getAddress());
            ps.setString(2, bean.getReceiver());
            ps.setString(3, bean.getPhone());
            ps.setTimestamp(4, DateUtil.d2t(bean.getCreateDate()));
            ps.setTimestamp(5, DateUtil.d2t(bean.getPayDate()));
            ps.setString(6, bean.getOrderCode());
            ps.setInt(7, bean.getUser().getId());
            ps.setString(8, bean.getStatus());
            ps.setInt(9, bean.getId());
            ps.execute();
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
  
    }
  
    public void delete(int id) {
  
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "delete from Order_ where id = " + id;
  
            s.execute(sql);
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
    }
  
    public Order get(int id) {
        Order bean = new Order();
  
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "select * from Order_ where id = " + id;
  
            ResultSet rs = s.executeQuery(sql);
  
            if (rs.next()) {
                String orderCode =rs.getString("orderCode");
                String address = rs.getString("address");
                String receiver = rs.getString("receiver");
                String phone = rs.getString("phone");
                String status = rs.getString("status");
                int uid =rs.getInt("uid");
                Date createDate = DateUtil.t2d( rs.getTimestamp("createDate"));
                Date payDate = DateUtil.t2d( rs.getTimestamp("payDate"));
                 
                bean.setOrderCode(orderCode);
                bean.setAddress(address);
                bean.setReceiver(receiver);
                bean.setPhone(phone);
                bean.setCreateDate(createDate);
                bean.setPayDate(payDate);
                User user = new UserDAO().get(uid);
                bean.setUser(user);
                bean.setStatus(status);
                 
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
  
        String sql = "select * from Order_ order by id desc limit ?,? ";
  
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setInt(1, start);
            ps.setInt(2, count);
  
            ResultSet rs = ps.executeQuery();
  
            while (rs.next()) {
                Order bean = new Order();
                String orderCode =rs.getString("orderCode");
                String address = rs.getString("address");
                String receiver = rs.getString("receiver");
                String phone = rs.getString("phone");
                String status = rs.getString("status");
                Date createDate = DateUtil.t2d( rs.getTimestamp("createDate"));
                Date payDate = DateUtil.t2d( rs.getTimestamp("payDate"));
                int uid =rs.getInt("uid");                
                 
                int id = rs.getInt("id");
                bean.setId(id);
                bean.setOrderCode(orderCode);
                bean.setAddress(address);
                bean.setReceiver(receiver);
                bean.setPhone(phone);
                bean.setCreateDate(createDate);
                bean.setPayDate(payDate);
                User user = new UserDAO().get(uid);
                bean.setUser(user);
                bean.setStatus(status);
                beans.add(bean);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return beans;
    }
     
    public List<Order> list(int uid,String excludedStatus) {
        return list(uid,excludedStatus,0, Short.MAX_VALUE);
    }
      
    public List<Order> list(int uid, String excludedStatus, int start, int count) {
        List<Order> beans = new ArrayList<Order>();
         
        String sql = "select * from Order_ where uid = ? and status != ? order by id desc limit ?,? ";
         
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
             
            ps.setInt(1, uid);
            ps.setString(2, excludedStatus);
            ps.setInt(3, start);
            ps.setInt(4, count);
             
            ResultSet rs = ps.executeQuery();
             
            while (rs.next()) {
                Order bean = new Order();
                String orderCode =rs.getString("orderCode");
                String address = rs.getString("address");
                String receiver = rs.getString("receiver");
                String phone = rs.getString("phone");
                String status = rs.getString("status");
                Date createDate = DateUtil.t2d( rs.getTimestamp("createDate"));
                Date payDate = DateUtil.t2d( rs.getTimestamp("payDate"));
                
                int id = rs.getInt("id");
                bean.setId(id);
                bean.setOrderCode(orderCode);
                bean.setAddress(address);
                bean.setReceiver(receiver);
                bean.setPhone(phone);
                bean.setCreateDate(createDate);
                bean.setPayDate(payDate);
                User user = new UserDAO().get(uid);
                bean.setStatus(status);
                bean.setUser(user);
                beans.add(bean);
            }
        } catch (SQLException e) {
             
            e.printStackTrace();
        }
        return beans;
    }
  
}