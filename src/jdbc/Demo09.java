package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;


/**
 * ����ʱ��Ĵ���
 * ȡ��ָ��ʱ��ε�����
 */
public class Demo09 {
	/*
	 * ���ַ���ת��Ϊlong���͵���(��ʽΪ��yyyy-mm-dd hh:mm:ss)
	 */
	public static long str2Date(String strDate){
		
		DateFormat format=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");	//����ĸ�ʽ
		
		try {
			return format.parse(strDate).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			//�����쳣����-1
			return -1;
		}
	}
	
	public static void main(String[] args) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","hc433911");
			
			ps=conn.prepareStatement("select * from t_user where regTime>? and regTime<?");
			
			java.sql.Date start=new java.sql.Date(str2Date("2017-1-1 10:23:45"));
			java.sql.Date end=new java.sql.Date(str2Date("2017-1-10 10:23:45"));
			
			ps.setObject(1, start);
			ps.setObject(2, end);
			System.out.println(rs);
			rs=ps.executeQuery();
			System.out.println(rs);
			System.out.println(rs.next());
			while(rs.next()){
				//�õ����е�����
				System.out.println(rs.getInt("id")+"--"+rs.getString("username")+"--"+rs.getDate("regTime"));
			}
			
			
			
		}catch(Exception e){
			try {
				//�����쳣���ع�����
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			/*
			 * �������ر�
			 * �󿪵��ȹر�
			 * һ��Ҫ������try-catch�ֿ�д���Է���һ�������쳣��������Ҳ���ܹر�
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
	}
}
