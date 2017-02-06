package JDBC2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC�����ࣨ������
 * ������̳У����Ʊ��˹���ʵ��
 * @author hc
 *
 */
public final class JdbcUtilsSing {
	
	private static String url="jdbc:mysql://localhost:3306/jdbc";
	private static String user="root";
	private static String password="hc433911";
	
	private JdbcUtilsSing() {
		
	}
	//Ԥ��ʼ��
//	private static JdbcUtilsSing instance=new JdbcUtilsSing();
	private static JdbcUtilsSing instance=null;
	
	
	/*
	 * Ҳ���Խ�����������ס����ÿ�ε��������������ͬ����Ч�ʽϵ�
	 * ͨ�����·��������Խ������ķ�Χ��С,����ֻ�е�һ�λ����,�Ժ󶼲������
	 */
	public static JdbcUtilsSing getInstance(){
		//��ʱ���أ�����Ҫ�����������
		if(instance==null){
			synchronized (JdbcUtilsSing.class) {
				//˫�ؼ�����
				if(instance==null){
					instance=new JdbcUtilsSing();
				}
			}
		}
		return instance;
	}
	
	//ע��������ִֻ��һ��
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
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}







