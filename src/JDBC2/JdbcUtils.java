package JDBC2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import JDBC2.datasource.MyDataSource;

/**
 * JDBC������
 * ������̳У����Ʊ��˹���ʵ��
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
	
	//ע��������ִֻ��һ��
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
	
	//�ͷ���Դ
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
						//�ͷ�����ʱ,�����ӷŻ����ӳ�
						myDataSource.free(conn);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}







