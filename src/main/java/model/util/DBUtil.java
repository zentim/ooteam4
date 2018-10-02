package main.java.model.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    // For mysql with XAMPP
    public static String DBMS = "mysql";
    static String ip = "127.0.0.1";
    static int port = 3306;
    static String database = "ooteam4";
    static String encoding = "UTF-8";
    static String loginName = "root";
    static String password = "";

	// For mysql with docker
	// public static String DBMS = "mysql";
 //    static String ip = "192.168.99.100";
 //    static int port = 3306;
 //    static String database = "ooteam4";
 //    static String encoding = "UTF-8";
 //    static String loginName = "root";
 //    static String password = "root";

	// For heroku postgresql
//	public static String DBMS = "postgresql";
//    static String ip = "ec2-174-129-18-98.compute-1.amazonaws.com";
//    static int port = 5432;
//    static String database = "dcri0hkb72pplu";
//    static String encoding = "UTF-8";
//    static String loginName = "iuaafgeuvfadox";
//    static String password = "c649d081154e6b7ff215132c812938ee76131e4994fedfc9644ede854739411b";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
//        	Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s", ip, port, database, encoding);
//        String url = String.format("jdbc:postgresql://%s:%d/%s?characterEncoding=%s", ip, port, database, encoding);
        return DriverManager.getConnection(url, loginName, password);
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(getConnection());

    }

}
