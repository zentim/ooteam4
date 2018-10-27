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

public class PromotionDAO {
	public static final int noDiscount = 0;
	public static final int productSet = 1;
    public static final int eachGroupOfN = 2;
    public static final int broughtMoreThanInLastYear = 3;
    public static final int buyXGetYFree = 4;

	public int getTotal() {
		int total = 0;
		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

			String sql = "select count(*) from promotion";

			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return total;
	}

	public int add(Promotion bean) {
		String sql = "insert into promotion values(DEFAULT,?, ?, ?, ?, ?)";
		try (Connection c = DBUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
			ps.setInt(1, bean.getDiscountType());
			ps.setString(2, bean.getName());
			ps.setTimestamp(3, DateUtil.d2t(bean.getDateFrom()));
			ps.setTimestamp(4, DateUtil.d2t(bean.getDateTo()));
			ps.setInt(5, bean.getState());

			int affectedRows = ps.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Creating failed, no rows affected.");
			}

			try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					int id = generatedKeys.getInt(1);
					bean.setId(id);

					return id;
				} else {
					throw new SQLException("Createing failed, no ID obtained.");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	public void update(Promotion bean) {
		String sql = "update promotion "
		        + "set discountType=?, name=?, dateFrom=?, dateTo=?, state=? "
		        + "where promotionId = ?";
		
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, bean.getDiscountType());
			ps.setString(2, bean.getName());
			ps.setTimestamp(3, DateUtil.d2t(bean.getDateFrom()));
			ps.setTimestamp(4, DateUtil.d2t(bean.getDateTo()));
			ps.setInt(5, bean.getState());

			ps.setInt(6, bean.getId());

			ps.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void delete(int id) {
		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

			String sql = "delete from promotion where promotionId = " + id;

			s.execute(sql);

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public Promotion get(int id) {
		Promotion bean = null;

		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

			String sql = "select * from promotion where promotionId = " + id;

			ResultSet rs = s.executeQuery(sql);

			if (rs.next()) {
				bean = new Promotion();
				
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

				bean.setId(id);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return bean;
	}

	public List<Promotion> list() {
		return list(0, Short.MAX_VALUE);
	}

	public List<Promotion> list(int start, int count) {
		List<Promotion> beans = new ArrayList<Promotion>();

		String sql = "select * from promotion order by promotionId desc limit ?,? ";

		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, start);
			ps.setInt(2, count);

			ResultSet rs = ps.executeQuery();

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
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return beans;
	}

}
