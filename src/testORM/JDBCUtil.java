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
	 * �������Ժ�����ǿ�Ҫ�Ķ����������û���������Ҫ�Ķ���ֻ��Ҫ����Դ�ļ��е����ݼ���
	 * ��Ȼ��Ҳ������������������ṩ����oracle������֮��ģ�Ȼ������Դ�ļ���Ҳ�����Ӧ�Ķ�������
	 * ��д�����ļ���ʱ�����ʹ��MyEclipse�ṩ�Ŀ��ӻ���������ɣ����Ա���ܶ����
	 */
	static Properties pros=null; //���԰�����ȡ�ʹ�����Դ�ļ�
	
	static{//ֻ���ڼ���JDBCUtil�����ʱ�Ż��ʼ��
		pros=new Properties();
		try {
			pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static Connection getMysqlConn(){
		/*
		 * ������MySQL�����ӣ��������������
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
		 * ������Oracle�����ӣ��������������
		 * ��Դ�ļ���δ���á�����
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
		 * ���ڹرճ��õ���������
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
	//���أ�ֻ�ر�Statement��Connection
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
	//ֻ�ر�connection
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
