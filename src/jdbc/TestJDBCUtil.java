package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 测试使用JDBCUtil工具类以简化开发
 * 以后正式工作中，基本都是使用已经封装好的工具，而不用自己去写相对底层的连接
 * @author hc
 *
 */
public class TestJDBCUtil {
	public static void main(String[] args) {
		//建立连接
		Connection conn=JDBCUtil.getMysqlConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		//-------------------------------------------------------------------
		/*
		 * 中间为变化的逻辑，上下基本为写好的模板，根据连接数据库的不同会有所变动
		 * 如果闲着没事，也可以做一个工具类，分别对增删查改进行封装。。。
		 */
		try {
			ps=conn.prepareStatement("select * from t_user where id=?");
			ps.setObject(1, 22020);;
			rs=ps.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString("username"));
			}
		//-------------------------------------------------------------------
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//关闭连接
			JDBCUtil.close(rs, ps, conn);
		}
		
	}
}
