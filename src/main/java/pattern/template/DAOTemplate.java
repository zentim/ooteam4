package main.java.pattern.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.model.util.DBUtil;

/**
 * 
 * Template Method Pattern - template
 *
 */
public abstract class DAOTemplate {

    protected final static int TYPE_CREATE = 1;
    protected final static int TYPE_UPDATE = 2;
    protected final static int TYPE_DELETE = 3;
    protected final static int TYPE_CONDITION = 4;

    /**
     * 
     * template methods
     * 
     */
    public final int add(Object obj) throws Exception {
        /* primitive method */
        Connection c = getConnection();
        String sql = this.getMainSql(TYPE_CREATE);
        
        /* hook method */
        PreparedStatement ps = this.getPreparedStatement(c, sql);
        
        /* primitive method */
        ResultSet rs = this.executeAdd(ps, obj);
        int result = setModel(rs, obj);
        disConnect(c);
        
        return result;
    }

    public final void update(Object obj) throws Exception {
        /* primitive method */
        Connection c = getConnection();
        String sql = this.getMainSql(TYPE_UPDATE);
        
        /* hook method */
        PreparedStatement ps = this.getPreparedStatement(c, sql);
        
        /* primitive method */
        this.executeUpdate(ps, obj);
        disConnect(c);
    }

    public final void delete(int id) throws Exception {
        /* primitive method */
        Connection c = getConnection();
        String sql = this.getMainSql(TYPE_DELETE);
        
        /* hook method */
        PreparedStatement ps = this.getPreparedStatement(c, sql);
        
        /* primitive method */
        this.executeDelete(ps, id);
        disConnect(c);
    }

    public final Object get(int id) throws Exception {
        /* primitive method */
        Connection c = getConnection();
        String sql = this.getMainSql(TYPE_CONDITION);
        
        /* hook method */
        PreparedStatement ps = this.getPreparedStatement(c, sql);
        ResultSet rs = this.executeGetById(ps, id);
        Object result = setModelFromGet(rs);
        
        /* primitive method */
        disConnect(c);
        
        return result;
    }

    
    
    /**
     * 
     * primitive methods
     * 
     */
    private Connection getConnection() throws Exception {
        return DBUtil.getConnection();
    }

    protected abstract String getMainSql(int type);

    
    
    /**
     * 
     * hook methods
     * 
     */
    public final PreparedStatement getPreparedStatement(Connection c, String sql) throws SQLException {
        PreparedStatement ps = c.prepareStatement(sql);
        return ps;
    }
    
    public ResultSet executeGetById(PreparedStatement ps, int id) throws SQLException {
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        return rs;
    };
    
    protected Object setModelFromGet(ResultSet rs) throws Exception {
        return null;
    }
    
    
    
    /**
     * 
     * primitive methods
     * 
     */
    protected abstract ResultSet executeAdd(PreparedStatement ps, Object obj) throws SQLException;

    protected abstract void executeUpdate(PreparedStatement ps, Object obj) throws SQLException;

    protected abstract void executeDelete(PreparedStatement ps, int id) throws SQLException;

    protected abstract int setModel(ResultSet rs, Object obj) throws SQLException;
    
    
    private void disConnect(Connection c) throws Exception {
        c.close();
    }

}
