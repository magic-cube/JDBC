package JDBC2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC工具类（单例）
 * 不允许继承，限制别人构造实例
 * @author hc
 *
 */
public final class JdbcUtilsSing {
	
	private static String url="jdbc:mysql://localhost:3306/jdbc";
	private static String user="root";
	private static String password="hc433911";
	
	private JdbcUtilsSing() {
		
	}
	//预初始化
//	private static JdbcUtilsSing instance=new JdbcUtilsSing();
	private static JdbcUtilsSing instance=null;
	
	
	/*
	 * 也可以将整个方法锁住，但每次调用这个方法都需同步，效率较低
	 * 通过以下方法，可以将加锁的范围缩小,这样只有第一次会加锁,以后都不会加锁
	 */
	public static JdbcUtilsSing getInstance(){
		//延时加载，但需要解决并发问题
		if(instance==null){
			synchronized (JdbcUtilsSing.class) {
				//双重检查机制
				if(instance==null){
					instance=new JdbcUtilsSing();
				}
			}
		}
		return instance;
	}
	
	//注册驱动，只执行一次
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url, user, password);
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
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}







