package JDBC2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import JDBC2.datasource.MyDataSource;

/**
 * JDBC工具类
 * 不允许继承，限制别人构造实例
 * @author hc
 *
 */
public final class JdbcUtils {
	
	private static String url="jdbc:mysql://localhost:3306/jdbc";
	private static String user="root";
	private static String password="hc433911";
	private static MyDataSource myDataSource=null;
	private JdbcUtils() {
		
	}
	
	//注册驱动，只执行一次
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			myDataSource=new MyDataSource();
		} catch (ClassNotFoundException e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static Connection getConnection() throws SQLException{
		return myDataSource.getConnection();
	}
	
	//释放资源
	public static void free(ResultSet rs,Statement st,Connection conn){
		try{
			if(rs!=null){
				rs.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				if(st!=null){
					st.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if(conn!=null){
						//conn.close();
						//释放连接时,将连接放回连接池
						myDataSource.free(conn);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}







