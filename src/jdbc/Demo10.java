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
 * 测试CLOB大文本对象的使用
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
			
			ps=conn.prepareStatement("select * from t_user where regTime>? and regTime<?");
			
			
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
