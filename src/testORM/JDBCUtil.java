package testORM;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtil {
	/*
	 * 这样，以后如果是库要改动，或者是用户名，密码要改动，只需要改资源文件中的内容即可
	 * 当然，也可以在这个工具类中提供关于oracle的连接之类的，然后在资源文件中也添加相应的东西即可
	 * 在写配置文件的时候可以使用MyEclipse提供的可视化工具来完成，可以避免很多错误
	 */
	static Properties pros=null; //可以帮助读取和处理资源文件
	
	static{//只有在加载JDBCUtil这个类时才会初始化
		pros=new Properties();
		try {
			pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static Connection getMysqlConn(){
		/*
		 * 建立与MySQL的连接，并返回这个连接
		 */
		Connection conn=null;
		try {
			Class.forName(pros.getProperty("mysqlDriver"));
			conn=DriverManager.getConnection(pros.getProperty("mysqlURL"),pros.getProperty("mysqlUser"),pros.getProperty("mysqlPwd"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
		return conn;
	}
	
	public static Connection getOracleConn(){
		/*
		 * 建立与Oracle的连接，并返回这个连接
		 * 资源文件还未配置。。。
		 */
		Connection conn=null;
		try {
			Class.forName(pros.getProperty("OracleDriver"));
			conn=DriverManager.getConnection(pros.getProperty("OracleURL"),pros.getProperty("OracleUser"),pros.getProperty("OraclePwd"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
		return conn;
	}
	
	public static void close(ResultSet rs,Statement ps,Connection conn){
		/*
		 * 用于关闭常用的三个连接
		 */
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(ps!=null){
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//重载，只关闭Statement和Connection
	public static void close(Statement ps,Connection conn){

		if(ps!=null){
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//只关闭connection
	public static void close(Connection conn){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
