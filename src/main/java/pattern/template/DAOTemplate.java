package main.java.pattern.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.model.util.DBUtil;

/**
 * 
 * Template Pattern - template
 *
 */
public abstract class DAOTemplate {

    protected final static int TYPE_CREATE = 1;
    protected final static int TYPE_UPDATE = 2;
    protected final static int TYPE_DELETE = 3;
    protected final static int TYPE_CONDITION = 4;

    public final int add(Object obj) throws Exception {
        Connection c = getConnection();
        String sql = this.getMainSql(TYPE_CREATE);
        PreparedStatement ps = this.getPreparedStatement(c, sql);
        ResultSet rs = this.executeAdd(ps, obj);
        int result = setModel(rs, obj);
        c.close();
        return result;
    }

    public final void update(Object obj) throws Exception {
        Connection c = getConnection();
        String sql = this.getMainSql(TYPE_UPDATE);
        PreparedStatement ps = this.getPreparedStatement(c, sql);
        this.executeUpdate(ps, obj);
        c.close();

    }

    public final void delete(int id) throws Exception {
        Connection c = getConnection();
        String sql = this.getMainSql(TYPE_DELETE);
        PreparedStatement ps = this.getPreparedStatement(c, sql);
        this.executeDelete(ps, id);
        c.close();

    }

    public final Object get(int id) throws Exception {
        Connection c = getConnection();
        String sql = this.getMainSql(TYPE_CONDITION);
        PreparedStatement ps = this.getPreparedStatement(c, sql);
        ResultSet rs = this.executeGetById(ps, id);
        Object result = setModelFromGet(rs);
        c.close();
        return result;

    }

    public final PreparedStatement getPreparedStatement(Connection c, String sql) throws SQLException {
        PreparedStatement ps = c.prepareStatement(sql);
        return ps;
    }

    private Connection getConnection() throws Exception {
        return DBUtil.getConnection();
    }

    private void disConnect() throws Exception {

    }

    protected abstract String getMainSql(int type);

    public ResultSet executeGetById(PreparedStatement ps, int id) throws SQLException {
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        return rs;
    };

    protected Object setModelFromGet(ResultSet rs) throws Exception {
        return null;
    }

    protected abstract ResultSet executeAdd(PreparedStatement ps, Object obj) throws SQLException;

    protected abstract void executeUpdate(PreparedStatement ps, Object obj) throws SQLException;

    protected abstract void executeDelete(PreparedStatement ps, int id) throws SQLException;

    protected abstract int setModel(ResultSet rs, Object obj) throws SQLException;

}
