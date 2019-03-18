package util;
/**
 * 
 */

import java.sql.*;

import dao.dataset;


/**
 * MySQL Database connection 
 * @author RollingZ
 *
 */
public class DBConnect {
	public static final String url="jdbc:mysql://localhost/Crowdsourcing?useUnicode=true&characterEncoding=utf8";
	
	public static final String name="com.mysql.jdbc.Driver";
	public static final String user="root";
	public static final String password="root";

	public static Connection conn=null;
	public static PreparedStatement pst=null;
	
	public DBConnect() {
		try {
			Class.forName(name);
			conn=DriverManager.getConnection(url,user,password);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void connect() {
		try {
			Class.forName(name);
			conn=DriverManager.getConnection(url,user,password);
			System.out.println("connect database "+url);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void close() {
		try {
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		DBConnect.connect();
		dataset.createDataset(2, 2);

	}

}