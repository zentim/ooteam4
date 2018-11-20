package main.java.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.java.model.bean.Category;
import main.java.model.bean.Product;
import main.java.model.bean.Segment;
import main.java.model.util.DBUtil;

public class CategoryDAO {

	public int getTotal() {
		int total = 0;
		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

			String sql = "select count(*) from category";

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

	public int add(Category bean) {
		String sql = "insert into category values(DEFAULT,?, ?)";
		try (Connection c = DBUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
			ps.setString(1, bean.getName());
			ps.setInt(2, bean.getSegment().getId());

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

	public void update(Category bean) {

		String sql = "update category set name= ? where categoryId = ?";
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

	    String sql = "delete from category where categoryId = ?";
	    try (Connection c = DBUtil.getConnection(); 
	            PreparedStatement ps = c.prepareStatement(sql);) {

	        ps.setInt(1, id);
			ps.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public Category get(int id) {
		Category bean = null;

		String sql = "select * from category where categoryId = ?";
		try (Connection c = DBUtil.getConnection(); 
		        PreparedStatement ps = c.prepareStatement(sql);) {
		    
		    ps.setInt(1, id);
		    
			try (ResultSet rs = ps.executeQuery();) {
			    if (rs.next()) {
	                bean = new Category();
	                String name = rs.getString("name");
	                int segmentId = rs.getInt("segmentId");
	                
	                Segment segment = new SegmentDAO().get(segmentId);
	                
	                bean.setName(name);
	                bean.setSegment(segment);
	                bean.setId(id);
	            }
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return bean;
	}

	public List<Category> list() {
		return list(0, Short.MAX_VALUE);
	}

	public List<Category> list(int start, int count) {
		List<Category> beans = new ArrayList<>();

		String sql = "select * from category order by categoryId desc limit ?,? ";

		try (Connection c = DBUtil.getConnection(); 
		        PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, start);
			ps.setInt(2, count);

			try(ResultSet rs = ps.executeQuery();){
			    while (rs.next()) {
	                Category bean = new Category();
	                int id = rs.getInt(1);
	                String name = rs.getString("name");
	                int segmentId = rs.getInt("segmentId");
	                
	                Segment segment = new SegmentDAO().get(segmentId);
	                
	                bean.setName(name);
	                bean.setSegment(segment);
	                bean.setId(id);
	                beans.add(bean);
	            }
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return beans;
	}
	
	public List<Category> list(int segmentId) {
        return list(segmentId, 0, Short.MAX_VALUE);
    }

    public List<Category> list(int segmentId, int start, int count) {
        List<Category> beans = new ArrayList<>();

        String sql = "select * from category "
                + "where segmentId = ? "
                + "order by categoryId desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); 
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, segmentId);
            ps.setInt(2, start);
            ps.setInt(3, count);

            try(ResultSet rs = ps.executeQuery();){
                while (rs.next()) {
                    Category bean = new Category();
                    int id = rs.getInt(1);
                    String name = rs.getString("name");
                    
                    Segment segment = new SegmentDAO().get(segmentId);
                    
                    bean.setName(name);
                    bean.setSegment(segment);
                    bean.setId(id);
                    beans.add(bean);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return beans;
    }
    
    public void fill(List<Segment> ss) {
        System.out.println("\n=== Show Segment ===");
        for (Segment s : ss) {
            fill(s);
        }
    }

    public void fill(Segment s) {
        List<Category> cs = this.list(s.getId());
        s.setCategorys(cs);
        
        // Use Composite Pattern
        for (Category c : cs) {
            s.add(c);
        }

        s.operation();
    }

}
