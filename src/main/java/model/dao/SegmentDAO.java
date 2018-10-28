package main.java.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.java.model.bean.Segment;
import main.java.model.util.DBUtil;

public class SegmentDAO {

	public int getTotal() {
		int total = 0;
		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

			String sql = "select count(*) from segment";

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

	public int add(Segment bean) {
		String sql = "insert into segment values(DEFAULT,?)";
		try (Connection c = DBUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
			ps.setString(1, bean.getName());

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

	public void update(Segment bean) {

		String sql = "update segment set name= ? where segmentId = ?";
		try (Connection c = DBUtil.getConnection(); 
		        PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setString(1, bean.getName());
			ps.setInt(2, bean.getId());

			ps.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void delete(int id) {
	    String sql = "delete from segment where segmentId = ?";
	    
	    try (Connection c = DBUtil.getConnection(); 
                PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, id);
			ps.execute(sql);

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public Segment get(int id) {
		Segment bean = null;
		String sql = "SELECT * FROM segment WHERE segmentId = ?";

		try (Connection c = DBUtil.getConnection(); 
                PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, id);

			try (ResultSet rs = ps.executeQuery();) {
			    if (rs.next()) {
	                bean = new Segment();
	                String name = rs.getString(2);
	                bean.setName(name);
	                bean.setId(id);
	            }
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return bean;
	}

	public List<Segment> list() {
		return list(0, Short.MAX_VALUE);
	}

	public List<Segment> list(int start, int count) {
		List<Segment> beans = new ArrayList<>();

		String sql = "select * from segment order by segmentId desc limit ?,? ";

		try (Connection c = DBUtil.getConnection(); 
		        PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, start);
			ps.setInt(2, count);

			try (ResultSet rs = ps.executeQuery();) {
			    while (rs.next()) {
	                Segment bean = new Segment();
	                int id = rs.getInt(1);
	                String name = rs.getString(2);
	                bean.setId(id);
	                bean.setName(name);
	                beans.add(bean);
	            }
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return beans;
	}

}
