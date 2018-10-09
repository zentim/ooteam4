package main.java.model.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.java.model.bean.User;
import main.java.model.util.DBUtil;



public class UserDAO {

    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select count(*) from user";

            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return total;
    }

    public int add(User bean) {

        String sql = "insert into user values(DEFAULT ,? ,?)";
        try (
            Connection c = DBUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {

            ps.setString(1, bean.getEmail());
            ps.setString(2, bean.getPassword());

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

        return 0;
    }

    public void update(User bean) {

        String sql = "update user set email = ?, password = ? where userId = ? ";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

        	ps.setString(1, bean.getEmail());
            ps.setString(2, bean.getPassword());
            
            ps.setInt(3, bean.getId());

            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    public void delete(int id) {

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "delete from user where userId = " + id;

            s.execute(sql);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public User get(int id) {
        User bean = null;

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select * from user where userId = " + id;

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                bean = new User();
                
                String email = rs.getString("email");
                String password = rs.getString("password");
                
                bean.setEmail(email);
                bean.setPassword(password);
                
                bean.setId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return bean;
    }

    public List<User> list() {
        return list(0, Short.MAX_VALUE);
    }

    public List<User> list(int start, int count) {
        List<User> beans = new ArrayList<User>();

        String sql = "select * from user order by userId desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, start);
            ps.setInt(2, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User bean = new User();
                int id = rs.getInt(1);

                String email = rs.getString("email");
                String password = rs.getString("password");
                
                bean.setEmail(email);
                bean.setPassword(password);

                bean.setId(id);
                beans.add(bean);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return beans;
    }

    public boolean isExist(String email) {
        User user = get(email);
        return user != null;

    }

    public User get(String email) {
        User bean = null;

        String sql = "select * from user where email = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                bean = new User();
                int id = rs.getInt("userId");
                bean.setEmail(email);
                String password = rs.getString("password");
                bean.setPassword(password);
                bean.setId(id);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return bean;
    }
    
    public User get(String email, String password) {
        User bean = null;

        String sql = "select * from user where email = ? and password = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                bean = new User();
                int id = rs.getInt("userId");
                bean.setEmail(email);
                bean.setPassword(password);
                bean.setId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return bean;
    }

}
