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
import main.java.model.bean.User;
import main.java.model.util.DBUtil;

public class OrderItemDAO {

    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select count(*) from order_item";

            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return total;
    }

    public int add(OrderItem bean) {

        String sql = "insert into order_item values(DEFAULT,?, ?, ?, ?, ?, ?, ?)";
        try (Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

            // When an order_item created, it has not order info (orderId)
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

    public void update(OrderItem bean) {

        String sql = "update order_item set orderId= ?, userId=?, productId=?, quantity=?, state=?, originalPrice=?, promotionalPrice=?  where orderItemId = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            // When an order_item created, it has not order info (orderId)
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

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    public void delete(int id) {

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "delete from order_item where orderItemId = " + id;

            s.execute(sql);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public OrderItem get(int id) {
        OrderItem bean = new OrderItem();

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select * from order_item where orderItemId = " + id;

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                int orderId = rs.getInt("orderId");
                int userId = rs.getInt("userId");
                int productId = rs.getInt("productId");
                int quantity = rs.getInt("quantity");
                int state = rs.getInt("state");
                float originalPrice = rs.getFloat("originalPrice");
                float promotionalPrice = rs.getFloat("promotionalPrice");
                Product product = new ProductDAO().get(productId);
                User user = new UserDAO().get(userId);

                if (-1 != orderId) {
                    Order order = new OrderDAO().get(orderId);
                    bean.setOrder(order);
                }
                bean.setProduct(product);
                bean.setUser(user);
                bean.setQuantity(quantity);
                bean.setState(state);
                bean.setOriginalPrice(originalPrice);
                bean.setPromotionalPrice(promotionalPrice);

                bean.setId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bean;
    }

    public List<OrderItem> listCartByUser(int userId) {
        return listCartByUser(userId, 0, Short.MAX_VALUE);
    }

    public List<OrderItem> listCartByUser(int userId, int start, int count) {
        List<OrderItem> beans = new ArrayList<OrderItem>();

        String sql = "select * from order_item where state = 1 and userId = ? and orderId = -1 order by orderItemId desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, userId);
            ps.setInt(2, start);
            ps.setInt(3, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderItem bean = new OrderItem();
                int id = rs.getInt(1);

                int orderId = rs.getInt("orderId");
                int productId = rs.getInt("productId");
                int quantity = rs.getInt("quantity");
                int state = rs.getInt("state");
                float originalPrice = rs.getInt("originalPrice");
                float promotionalPrice = rs.getInt("promotionalPrice");

                Product product = new ProductDAO().get(productId);
                User user = new UserDAO().get(userId);

                if (-1 != orderId) {
                    Order order = new OrderDAO().get(orderId);
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
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return beans;
    }

    public List<OrderItem> listByOrder(int orderId) {
        return listByOrder(orderId, 0, Short.MAX_VALUE);
    }

    public List<OrderItem> listByOrder(int orderId, int start, int count) {
        List<OrderItem> beans = new ArrayList<OrderItem>();

        String sql = "select * from order_item where orderId = ? order by orderItemId desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, orderId);
            ps.setInt(2, start);
            ps.setInt(3, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderItem bean = new OrderItem();
                int id = rs.getInt(1);

                int userId = rs.getInt("userId");
                int productId = rs.getInt("productId");
                int quantity = rs.getInt("quantity");
                int state = rs.getInt("state");
                float originalPrice = rs.getInt("originalPrice");
                float promotionalPrice = rs.getInt("promotionalPrice");

                Product product = new ProductDAO().get(productId);
                User user = new UserDAO().get(userId);

                if (-1 != orderId) {
                    Order order = new OrderDAO().get(orderId);
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
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return beans;
    }

    public void fill(List<Order> os) {
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

    public void fill(Order o) {
        List<OrderItem> ois = listByOrder(o.getId());
        float total = 0;
        for (OrderItem oi : ois) {
            total += oi.getQuantity() * oi.getProduct().getPrice();
        }
        o.setTotal(total);
        o.setOrderItems(ois);
    }

    public List<OrderItem> listByProduct(int productId) {
        return listByProduct(productId, 0, Short.MAX_VALUE);
    }

    public List<OrderItem> listByProduct(int productId, int start, int count) {
        List<OrderItem> beans = new ArrayList<OrderItem>();

        String sql = "select * from order_item where productId = ? order by orderItemId desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, productId);
            ps.setInt(2, start);
            ps.setInt(3, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderItem bean = new OrderItem();
                int id = rs.getInt(1);

                int userId = rs.getInt("userId");
                int orderId = rs.getInt("orderId");
                int quantity = rs.getInt("quantity");
                int state = rs.getInt("state");
                float originalPrice = rs.getInt("originalPrice");
                float promotionalPrice = rs.getInt("promotionalPrice");

                Product product = new ProductDAO().get(productId);
                User user = new UserDAO().get(userId);

                if (-1 != orderId) {
                    Order order = new OrderDAO().get(orderId);
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
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return beans;
    }

    public int getSaleCount(int productId) {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select sum(quantity) from order_item where productId = " + productId;

            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return total;
    }

}
