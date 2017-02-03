package jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.sql.Blob;
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
 * ����BLOB �����ƴ�����ʹ��
 * 
 */
public class Demo11 {
	
	public static void main(String[] args) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		OutputStream os=null;
		try{
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","hc433911");
			
//			ps=conn.prepareStatement("insert into t_user(username,headImg) values(?,?)");
//			ps.setString(1, "haochuan");
//			ps.setBlob(2, new FileInputStream("C:/my/hc.jpg"));
//			
//			ps.executeUpdate();

			
			//��
			ps=conn.prepareStatement("select * from t_user where id=?");
			ps.setObject(1, 22020);
			rs=ps.executeQuery();
			while(rs.next()){
				Blob b=rs.getBlob("headImg");
				InputStream is=b.getBinaryStream();
				os=new FileOutputStream("c:/my/a.jpg");
				int temp=0;
				while((temp=is.read())!=-1){
					os.write(temp);
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
