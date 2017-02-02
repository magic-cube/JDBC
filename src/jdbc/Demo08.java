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
 * 娴嬭瘯鏃堕棿澶勭悊(java.sql.Date,Time,Timestamp),鍙栧嚭鎸囧畾鏃堕棿娈电殑鏁版嵁
 * @author 楂樻穱 www.sxt.cn
 *
 */
public class Demo08 {
	
	/**
	 * 灏嗗瓧绗︿覆浠ｈ〃鐨勬棩鏈熻浆涓簂ong鏁板瓧(鏍煎紡锛歽yyy-MM-dd hh:mm:ss)
	 * @param dateStr
	 * @return
	 */
	public static  long  str2Date(String dateStr){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			return format.parse(dateStr).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//鍔犺浇椹卞姩绫�
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","123456");
			
//			ps = conn.prepareStatement("select * from t_user where regTime>? and regTime<?");
//			java.sql.Date start = new java.sql.Date(str2Date("2015-4-10 10:23:45"));
//			java.sql.Date end = new java.sql.Date(str2Date("2015-4-13 10:23:45"));
//			ps.setObject(1, start);
//			ps.setObject(2, end);
			
			ps = conn.prepareStatement("select * from t_user where lastLoginTime>? and lastLoginTime<?  order by lastLoginTime ");
			Timestamp start = new Timestamp(str2Date("2015-4-18 8:10:20"));
			Timestamp end = new Timestamp(str2Date("2015-4-18  9:9:10"));
			ps.setObject(1, start);
			ps.setObject(2, end);
			rs = ps.executeQuery();
			while(rs.next()){
				System.out.println(rs.getInt("id")+"--"+rs.getString("username")+"--"+rs.getTimestamp("lastLoginTime"));
			}
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(ps!=null){
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
