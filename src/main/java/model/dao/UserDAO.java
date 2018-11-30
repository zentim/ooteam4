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
import main.java.pattern.template.DAOTemplate;

/**
 * 
 * Template Pattern - ConcreteTemplate
 *
 */
public class UserDAO extends DAOTemplate {
    String TABLE_NAME = "user";

    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select count(*) from user";

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
            sql = "insert into " + TABLE_NAME + " values(DEFAULT ,? ,?)";
            break;
        case 2:
            sql = "update user set email = ?, password = ? where userId = ? ";
            break;
        case 3:
            sql = "delete from " + TABLE_NAME + "  where userId = ?";
            break;
        case 4:
            sql = "select * from " + TABLE_NAME + " where userId = ?";
            break;
        default:
            break;
        }
        return sql;

    }

    protected ResultSet executeAdd(PreparedStatement ps, Object obj) throws SQLException {
        User bean = (User) obj;
        ps.setString(1, bean.getEmail());
        ps.setString(2, bean.getPassword());

        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        return rs;

    }

    protected void executeUpdate(PreparedStatement ps, Object obj) throws SQLException {
        User bean = (User) obj;

        ps.setString(1, bean.getEmail());
        ps.setString(2, bean.getPassword());
        ps.setInt(3, bean.getId());

        ps.execute();

    }

    protected void executeDelete(PreparedStatement ps, int id) throws SQLException {
        ps.setInt(1, id);
        ps.execute();

    }

    protected int setModel(ResultSet rs, Object obj) throws SQLException {
        User bean = (User) obj;
        if (rs.next()) {
            int id = rs.getInt(1);
            bean.setId(id);

            return id;
        }
        return 0;
    }

    protected Object setModelFromGet(ResultSet rs) throws Exception {
        User bean = new User();
        while (rs.next()) {
            bean.setEmail(rs.getString("email"));
            bean.setPassword(rs.getString("password"));
            bean.setId(rs.getInt("userId"));
            return bean;
        }
        return null;
    }

    public List<User> list() throws CloneNotSupportedException {
        return list(0, Short.MAX_VALUE);
    }

    public List<User> list(int start, int count) throws CloneNotSupportedException {
        List<User> beans = new ArrayList<>();

        String sql = "select * from user order by userId desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, start);
            ps.setInt(2, count);

            try (ResultSet rs = ps.executeQuery();) {
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

            try (ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    bean = new User();
                    int id = rs.getInt("userId");
                    bean.setEmail(email);
                    String password = rs.getString("password");
                    bean.setPassword(password);
                    bean.setId(id);
                }
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
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    bean = new User();
                    int id = rs.getInt("userId");
                    bean.setEmail(email);
                    bean.setPassword(password);
                    bean.setId(id);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bean;
    }

}
