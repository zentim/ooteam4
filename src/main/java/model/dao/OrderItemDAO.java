package main.java.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.java.model.bean.Order;
import main.java.model.bean.OrderItem;
import main.java.model.bean.Product;
import main.java.model.bean.Segment;
import main.java.model.bean.User;
import main.java.model.util.DBUtil;
import main.java.model.util.DateUtil;
import main.java.pattern.template.DAOTemplate;

/**
 * 
 * Command Pattern - Receiver (update method and delete method)
 * Template Method Pattern - ConcreteTemplate
 * 
 */
public class OrderItemDAO extends DAOTemplate {
    String TABLE_NAME = "order_item";

    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select count(*) from order_item";

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
            sql = "insert into " + TABLE_NAME + " values(DEFAULT,?, ?, ?, ?, ?, ?, ?)";
            break;
        case 2:
            sql = "update " + TABLE_NAME
                    + " set orderId= ?, userId=?, productId=?, quantity=?, state=?, originalPrice=?, promotionalPrice=?  where orderItemId = ?";

            break;
        case 3:
            sql = "delete from " + TABLE_NAME + " where orderItemId = ?";
            break;
        case 4:
            sql = "select * from " + TABLE_NAME + " where orderItemId = ?";
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
        OrderItem bean = new OrderItem();
        if (rs.next()) {
            int orderId = rs.getInt("orderId");
            int userId = rs.getInt("userId");
            int productId = rs.getInt("productId");
            int quantity = rs.getInt("quantity");
            int state = rs.getInt("state");
            float originalPrice = rs.getFloat("originalPrice");
            float promotionalPrice = rs.getFloat("promotionalPrice");
            Product product = (Product) new ProductDAO().get(productId);
            User user = (User) new UserDAO().get(userId);

            if (-1 != orderId) {
                Order order = (Order) new OrderDAO().get(orderId);
                bean.setOrder(order);
            }
            bean.setProduct(product);
            bean.setUser(user);
            bean.setQuantity(quantity);
            bean.setState(state);
            bean.setOriginalPrice(originalPrice);
            bean.setPromotionalPrice(promotionalPrice);
            bean.setId(rs.getInt("orderItemId"));

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
        OrderItem bean = (OrderItem) obj;
        if (null == bean.getOrder()) {
            ps.setInt(1, -1);
        } else {
            ps.setInt(1, bean.getOrder().getId());
        }
        ps.setInt(2, bean.getUser().getId());
        ps.setInt(3, bean.getProduct().getId());
        ps.setInt(4, bean.getQuantity());
        ps.setInt(5, bean.getState());
        ps.setFloat(6, bean.getOriginalPrice());
        ps.setFloat(7, bean.getPromotionalPrice());

        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        return rs;

    }

    protected void executeUpdate(PreparedStatement ps, Object obj) throws SQLException {
        OrderItem bean = (OrderItem) obj;
        if (null == bean.getOrder()) {
            ps.setInt(1, -1);
        } else {
            ps.setInt(1, bean.getOrder().getId());
        }
        ps.setInt(2, bean.getUser().getId());
        ps.setInt(3, bean.getProduct().getId());
        ps.setInt(4, bean.getQuantity());
        ps.setInt(5, bean.getState());
        ps.setFloat(6, bean.getOriginalPrice());
        ps.setFloat(7, bean.getPromotionalPrice());
        ps.setInt(8, bean.getId());
        ps.execute();

    }

    protected void executeDelete(PreparedStatement ps, int id) throws SQLException {
        ps.setInt(1, id);
        ps.execute();

    }

    protected int setModel(ResultSet rs, Object obj) throws SQLException {
        OrderItem bean = (OrderItem) obj;
        if (rs.next()) {
            int id = rs.getInt(1);
            bean.setId(id);

            return id;
        }
        return 0;
    }

    

    public List<OrderItem> listCartByUser(int userId) throws Exception {
        return listCartByUser(userId, 0, Short.MAX_VALUE);
    }

    public List<OrderItem> listCartByUser(int userId, int start, int count) throws Exception {
        List<OrderItem> beans = new ArrayList<>();

        String sql = "select * from order_item " + "where state = 1 and userId = ? and orderId = -1 "
                + "order by orderItemId desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, userId);
            ps.setInt(2, start);
            ps.setInt(3, count);

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    OrderItem bean = new OrderItem();
                    int id = rs.getInt(1);

                    int productId = rs.getInt("productId");
                    int quantity = rs.getInt("quantity");
                    int state = rs.getInt("state");
                    float originalPrice = rs.getInt("originalPrice");
                    float promotionalPrice = rs.getInt("promotionalPrice");

                    Product product = (Product) new ProductDAO().get(productId);
                    User user = (User) new UserDAO().get(userId);

                    if (product.getInventory() < quantity) {
                        quantity = product.getInventory();

                        bean.setProduct(product);
                        bean.setUser(user);
                        bean.setQuantity(quantity);
                        bean.setState(state);
                        bean.setOriginalPrice(originalPrice);
                        bean.setPromotionalPrice(promotionalPrice);

                        bean.setId(id);
                        try {
                            new OrderItemDAO().update(bean);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    } else {
                        bean.setProduct(product);
                        bean.setUser(user);
                        bean.setQuantity(quantity);
                        bean.setState(state);
                        bean.setOriginalPrice(originalPrice);
                        bean.setPromotionalPrice(promotionalPrice);

                        bean.setId(id);
                    }

                    beans.add(bean);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return beans;
    }

    public List<OrderItem> listByOrder(int orderId) throws Exception {
        return listByOrder(orderId, 0, Short.MAX_VALUE);
    }

    public List<OrderItem> listByOrder(int orderId, int start, int count) throws Exception {
        List<OrderItem> beans = new ArrayList<>();

        String sql = "select * from order_item " + "where orderId = ? " + "order by orderItemId desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, orderId);
            ps.setInt(2, start);
            ps.setInt(3, count);

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    OrderItem bean = new OrderItem();
                    int id = rs.getInt(1);

                    int userId = rs.getInt("userId");
                    int productId = rs.getInt("productId");
                    int quantity = rs.getInt("quantity");
                    int state = rs.getInt("state");
                    float originalPrice = rs.getInt("originalPrice");
                    float promotionalPrice = rs.getInt("promotionalPrice");

                    Product product = (Product) new ProductDAO().get(productId);
                    User user = null;
                    try {
                        user = (User) new UserDAO().get(userId);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    if (-1 != orderId) {
                        Order order = (Order) new OrderDAO().get(orderId);
                        bean.setOrder(order);
                    }
                    bean.setProduct(product);
                    bean.setUser(user);
                    bean.setQuantity(quantity);
                    bean.setState(state);
                    bean.setOriginalPrice(originalPrice);
                    bean.setPromotionalPrice(promotionalPrice);

                    bean.setId(id);
                    beans.add(bean);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return beans;
    }

    public void fill(List<Order> os) throws Exception {
        for (Order o : os) {
            List<OrderItem> ois = listByOrder(o.getId());
            float total = 0;
            int totalQuantity = 0;
            for (OrderItem oi : ois) {
                total += oi.getQuantity() * oi.getProduct().getPrice();
                totalQuantity += oi.getQuantity();
            }
            o.setTotal(total);
            o.setOrderItems(ois);
            o.setTotalQuantity(totalQuantity);
        }

    }

    public void fill(Order o) throws Exception {
        List<OrderItem> ois = listByOrder(o.getId());
        float total = 0;
        for (OrderItem oi : ois) {
            total += oi.getQuantity() * oi.getProduct().getPrice();
        }
        o.setTotal(total);
        o.setOrderItems(ois);
    }

    public List<OrderItem> listByProduct(int productId) throws Exception {
        return listByProduct(productId, 0, Short.MAX_VALUE);
    }

    public List<OrderItem> listByProduct(int productId, int start, int count) throws Exception {
        List<OrderItem> beans = new ArrayList<>();

        String sql = "select * from order_item " + "where productId = ? " + "order by orderItemId desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, productId);
            ps.setInt(2, start);
            ps.setInt(3, count);

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    OrderItem bean = new OrderItem();
                    int id = rs.getInt(1);

                    int userId = rs.getInt("userId");
                    int orderId = rs.getInt("orderId");
                    int quantity = rs.getInt("quantity");
                    int state = rs.getInt("state");
                    float originalPrice = rs.getInt("originalPrice");
                    float promotionalPrice = rs.getInt("promotionalPrice");

                    Product product = (Product) new ProductDAO().get(productId);
                    User user = null;
                    try {
                        user = (User) new UserDAO().get(userId);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    if (-1 != orderId) {
                        Order order = (Order) new OrderDAO().get(orderId);
                        bean.setOrder(order);
                    }
                    bean.setProduct(product);
                    bean.setUser(user);
                    bean.setQuantity(quantity);
                    bean.setState(state);
                    bean.setOriginalPrice(originalPrice);
                    bean.setPromotionalPrice(promotionalPrice);

                    bean.setId(id);
                    beans.add(bean);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return beans;
    }

    public int getSaleCount(int productId) {

        int total = 0;
        String sql = "select sum(quantity) from order_item where productId = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, productId);

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    total = rs.getInt(1);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return total;
    }

}
