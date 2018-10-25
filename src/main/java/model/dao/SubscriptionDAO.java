package main.java.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.java.model.bean.Product;
import main.java.model.bean.Subscription;
import main.java.model.bean.User;
import main.java.model.util.DBUtil;

public class SubscriptionDAO {

	public int getTotal() {
		int total = 0;
		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

			String sql = "select count(*) from subscription";

			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return total;
	}

	public void add(int pid,int uid) {
		String sql = "insert into subscription values(DEFAULT,"+ uid +","+ pid+")";
		try (Connection c = DBUtil.getConnection();Statement s = c.createStatement();) {
			s.execute(sql);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Subscription bean) {

		String sql = "update subscription set userId= ?, productId=? where subscriptionId = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, bean.getUser().getId());
			ps.setInt(2, bean.getProduct().getId());
			ps.setInt(3, bean.getId());

			ps.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void delete(int uid,int pid) {

		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

			String sql = "delete from subscription where userId = " + uid +" AND productId =" + pid;

			s.execute(sql);

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public Subscription get(int id) {
		Subscription bean = null;

		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

			String sql = "select * from subscription where productId = " + id;

			ResultSet rs = s.executeQuery(sql);

			if (rs.next()) {
				bean = new Subscription();

				int userId = rs.getInt("userId");
				int productId = rs.getInt("productId");
				User user = new UserDAO().get(userId);
				Product product = new ProductDAO().get(productId);

				bean.setUser(user);
				bean.setProduct(product);
				bean.setId(id);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return bean;
	}
	
	public List<Subscription> list(int id) {
		List<Subscription> beans = new ArrayList<Subscription>();
		Subscription bean = null;

		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

			String sql = "select * from subscription where userId = " + id;

			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				bean = new Subscription();

				int userId = rs.getInt("userId");
				int productId = rs.getInt("productId");
				User user = new UserDAO().get(userId);
				Product product = new ProductDAO().get(productId);

				bean.setUser(user);
				bean.setProduct(product);
				bean.setId(id);
				beans.add(bean);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return beans;
	}
	
	public List<User> getUsers(int pid) {
		List<User> beans = new ArrayList<User>();
		User bean = null;

		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

			String sql = "select * from subscription S, user U where S.userId = U.userId AND productId = " + pid;

			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				bean = new User();

				int userId = rs.getInt("userId");
				String email = rs.getString("email");
				
				bean.setId(userId);
				bean.setEmail(email);
				beans.add(bean);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return beans;
	}
	
	
	
	public boolean check(int pid,int uid) {
		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

			String sql = "select * from subscription where productId = " + pid + " AND userId = " +uid;
			
			ResultSet rs = s.executeQuery(sql);

			if(rs.next()) {
				return true;
			}else {
				
				return false;
				
			
			}
			

		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}

	public List<Subscription> list() {
		return list(0, Short.MAX_VALUE);
	}

	public List<Subscription> list(int start, int count) {
		List<Subscription> beans = new ArrayList<Subscription>();

		String sql = "select * from subscription order by subscriptionId desc limit ?,? ";

		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, start);
			ps.setInt(2, count);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Subscription bean = new Subscription();
				int userId = rs.getInt("userId");
				int productId = rs.getInt("productId");
				User user = new UserDAO().get(userId);
				Product product = new ProductDAO().get(productId);

				bean.setUser(user);
				bean.setProduct(product);
				beans.add(bean);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return beans;
	}

}
