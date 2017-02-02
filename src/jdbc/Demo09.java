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
 * 测试时间的处理
 * 取出指定时间段的内容
 */
public class Demo09 {
	/*
	 * 将字符串转换为long类型的数(格式为：yyyy-mm-dd hh:mm:ss)
	 */
	public static long str2Date(String strDate){
		
		DateFormat format=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");	//定义的格式
		
		try {
			return format.parse(strDate).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			//出现异常返回-1
			return -1;
		}
	}
	
	public static void main(String[] args) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			//加载驱动
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
				//拿第四列的数据
				System.out.println(rs.getInt("id")+"--"+rs.getString("username")+"--"+rs.getDate("regTime"));
			}
			
			
			
		}catch(Exception e){
			try {
				//遇到异常，回滚事务
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			/*
			 * 最后，依序关闭
			 * 后开的先关闭
			 * 一定要将三个try-catch分开写，以防当一个发生异常，其他的也不能关闭
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
