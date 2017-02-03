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
 * ����CLOB���ı������ʹ��
 */
public class Demo10 {
	
	public static void main(String[] args) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","hc433911");
			
			ps=conn.prepareStatement("select * from t_user where regTime>? and regTime<?");
			
			
		}catch(Exception e){
			
			e.printStackTrace();
		}finally{
			/*
			 * �������ر�
			 * �󿪵��ȹر�
			 * һ��Ҫ������try-catch�ֿ�д���Է���һ�������쳣��������Ҳ���ܹر�
			 */

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
