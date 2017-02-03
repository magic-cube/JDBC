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
 * 测试CLOB大文本对象的使用
 * 以流的形式操作,写，读
 */
public class Demo10 {
	
	public static void main(String[] args) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","hc433911");
			
//			ps=conn.prepareStatement("insert into t_user(username,myinfo) values(?,?)");
//			ps.setString(1, "haochuan");
//			ps.setClob(2, new FileReader(new File("c:/my/hc.txt")));//将文本文件的内容直接输入到数据库中
//			
//			ps.executeUpdate();

			
			//读
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
			 * 最后，依序关闭
			 * 后开的先关闭
			 * 一定要将三个try-catch分开写，以防当一个发生异常，其他的也不能关闭
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
