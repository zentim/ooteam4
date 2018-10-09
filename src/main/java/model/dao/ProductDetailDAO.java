package main.java.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.java.model.bean.Product;
import main.java.model.bean.ProductDetail;
import main.java.model.util.DBUtil;

public class ProductDetailDAO {

	public int getTotal() {
		int total = 0;
		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

			String sql = "select count(*) from product_detail";

			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return total;
	}

	public int add(ProductDetail bean) {
		String sql = "insert into product_detail values(DEFAULT,?, ?, ?)";
		try (
				Connection c = DBUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		) {
			ps.setInt(1, bean.getProduct().getId());
			ps.setString(2, bean.getField());
			ps.setString(3, bean.getContent());

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

	public void update(ProductDetail bean) {

		String sql = "update product_detail set productId = ?, field = ?, content = ? where productDetailId = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, bean.getProduct().getId());
			ps.setString(2, bean.getField());
			ps.setString(3, bean.getContent());
			
			ps.setInt(4,  bean.getId());

			ps.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void delete(int id) {

		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

			String sql = "delete from product_detail where productDetailId = " + id;

			s.execute(sql);

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public ProductDetail get(int id) {
		ProductDetail bean = null;

		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

			String sql = "select * from product_detail where productDetailId = " + id;

			ResultSet rs = s.executeQuery(sql);

			if (rs.next()) {
				bean = new ProductDetail();
				
				int productId = rs.getInt("productId");
				String field = rs.getString("field");
				String content = rs.getString("content");
				
				Product product = new ProductDAO().get(productId);
				
				bean.setProduct(product);
				bean.setField(field);
				bean.setContent(content);
				
				bean.setId(id);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return bean;
	}

	public List<ProductDetail> list() {
		return list(0, Short.MAX_VALUE);
	}

	public List<ProductDetail> list(int start, int count) {
		List<ProductDetail> beans = new ArrayList<ProductDetail>();

		String sql = "select * from product_detail order by productDetailId desc limit ?,? ";

		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, start);
			ps.setInt(2, count);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ProductDetail bean = new ProductDetail();
				
				int productDetailId = rs.getInt("productDetailId");
				int productId = rs.getInt("productId");
				String field = rs.getString("field");
				String content = rs.getString("content");
				
				Product product = new ProductDAO().get(productId);
				
				bean.setProduct(product);
				bean.setField(field);
				bean.setContent(content);
				
				bean.setId(productDetailId);
				
				beans.add(bean);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return beans;
	}

}
