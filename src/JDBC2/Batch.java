package JDBC2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Batch {
	public static void main(String[] args) throws SQLException {
		for(int i=0;i<1000;i++){
			creat();
		}
	}
	
	static void creatBatch() throws SQLException{
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//建立连接
			conn=JdbcUtils.getConnection();
			//创建语句
			String sql="insert into user(name,birthday,money) values(?,?,?)";
			//获取主键
			ps=conn.prepareStatement(sql);
			
			//批处理
			for(int i=0;i<1000;i++){
				ps.setObject(1, "name4");
				ps.setObject(2, new Date(System.currentTimeMillis()));
				ps.setObject(3, 100f);
				
				ps.addBatch();
			}
			//执行语句
			ps.executeBatch();
			
		}finally{
			JdbcUtils.free(rs, ps, conn);
		}
	}

	
	static void creat() throws SQLException{
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//建立连接
			conn=JdbcUtils.getConnection();
			//创建语句
			String sql="insert into user(name,birthday,money) values(?,?,?)";
			//获取主键
			ps=conn.prepareStatement(sql);
			ps.setObject(1, "name4");
			ps.setObject(2, new Date(System.currentTimeMillis()));
			ps.setObject(3, 100f);
			
			//执行语句
			int i=ps.executeUpdate();

			
			System.out.println("i="+i);
			
		}finally{
			JdbcUtils.free(rs, ps, conn);
		}
	}
}
