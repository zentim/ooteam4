package main.java.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.java.model.bean.Product;
import main.java.model.bean.Segment;
import main.java.model.bean.Subscription;
import main.java.model.bean.User;
import main.java.model.util.DBUtil;
import main.java.model.util.DateUtil;
import main.java.pattern.template.DAOTemplate;

/**
 * 
 * Template Method Pattern - ConcreteTemplate
 *
 */
public class SubscriptionDAO extends DAOTemplate {
    final String TABLE_NAME = "subscription";

    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select count(*) from subscription";

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
            sql = "insert into " + TABLE_NAME + " values(DEFAULT, ?, ?)";
            break;
        case 2:
            sql = "update subscription set userId= ?, productId=? where subscriptionId = ?";
            break;
        case 3:
            sql = "delete from subscription where userId = ? AND productId = ?";
            break;
        case 4:
            sql = "delete from subscription where userId = ? AND productId = ?";
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
    protected Object setModelFromGet(ResultSet rs) throws Exception {
        Subscription bean = new Subscription();
        if (rs.next()) {
            int userId = rs.getInt("userId");
            int productId = rs.getInt("productId");
            User user = null;
            user = (User) new UserDAO().get(userId);
            Product product = (Product) new ProductDAO().get(productId);

            bean.setUser(user);
            bean.setProduct(product);
            bean.setId(rs.getInt("subscriptionId"));

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
        Subscription bean = (Subscription) obj;

        ps.setInt(1, bean.getUser().getId());
        ps.setInt(2, bean.getProduct().getId());

        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        return rs;

    }

    protected void executeUpdate(PreparedStatement ps, Object obj) throws SQLException {
        Subscription bean = (Subscription) obj;
        ps.setInt(1, bean.getUser().getId());
        ps.setInt(2, bean.getProduct().getId());
        ps.execute();

    }

    protected void executeDelete(PreparedStatement ps, int id) throws SQLException {

    }

    protected int setModel(ResultSet rs, Object obj) throws SQLException {
        Subscription bean = (Subscription) obj;
        if (rs.next()) {
            int id = rs.getInt(1);
            bean.setId(id);

            return id;
        }
        return 0;
    }

    public void delete(int uid, int pid) {
        String sql = "delete from subscription where userId = ? AND productId = ?";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, uid);
            ps.setInt(2, pid);
            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    

    public List<Subscription> list(int id) throws Exception {
        List<Subscription> beans = new ArrayList<>();
        Subscription bean = null;
        String sql = "select * from subscription where userId = ?";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    bean = new Subscription();

                    int userId = rs.getInt("userId");
                    int productId = rs.getInt("productId");
                    User user = null;
                    try {
                        user = (User) new UserDAO().get(userId);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    Product product = (Product) new ProductDAO().get(productId);

                    bean.setUser(user);
                    bean.setProduct(product);
                    bean.setId(id);
                    beans.add(bean);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return beans;
    }

    public List<User> getUsers(int pid) {
        List<User> beans = new ArrayList<>();
        User bean = null;
        String sql = "select * from subscription S, user U " + "where S.userId = U.userId AND productId = ?";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, pid);

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    bean = new User();

                    int userId = rs.getInt("userId");
                    String email = rs.getString("email");

                    bean.setId(userId);
                    bean.setEmail(email);
                    beans.add(bean);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return beans;
    }

    public boolean check(int pid, int uid) {
        String sql = "select * from subscription where productId = ? AND userId = ?";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, pid);
            ps.setInt(2, uid);

            try (ResultSet rs = ps.executeQuery();) {
                return rs.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Subscription> list() throws Exception {
        return list(0, Short.MAX_VALUE);
    }

    public List<Subscription> list(int start, int count) throws Exception {
        List<Subscription> beans = new ArrayList<>();

        String sql = "select * from subscription order by subscriptionId desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, start);
            ps.setInt(2, count);

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    Subscription bean = new Subscription();
                    int userId = rs.getInt("userId");
                    int productId = rs.getInt("productId");
                    User user = null;
                    user = (User) new UserDAO().get(userId);

                    Product product = (Product) new ProductDAO().get(productId);

                    bean.setUser(user);
                    bean.setProduct(product);
                    beans.add(bean);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return beans;
    }

}
