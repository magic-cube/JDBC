package jdbc;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Clob;
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
 * ��������ʽ����,д����
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
			
//			ps=conn.prepareStatement("insert into t_user(username,myinfo) values(?,?)");
//			ps.setString(1, "haochuan");
//			ps.setClob(2, new FileReader(new File("c:/my/hc.txt")));//���ı��ļ�������ֱ�����뵽���ݿ���
//			
//			ps.executeUpdate();

			
			//��
			ps=conn.prepareStatement("select * from t_user where id=?");
			ps.setObject(1, 22019);
			rs=ps.executeQuery();
			while(rs.next()){
				Clob c=rs.getClob("myinfo");
				Reader r=c.getCharacterStream();
				int temp=0;
				while((temp=r.read())!=-1){
					System.out.print((char)temp);
				}
			}
			
			
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
