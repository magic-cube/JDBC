package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * 批处理
 */
public class Bath {
	public static void main(String[] args) {
		Connection conn=null;
		Statement stmt=null;
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","hc433911");
			stmt=conn.createStatement();
			conn.setAutoCommit(false);//设置为手动提交
			
			for(int i=0;i<1000;i++){
				stmt.addBatch("insert into t_user(username,pwd,regTime) values ('hc"+i+"',433911,now())");
			}
			//执行
			stmt.executeBatch();
			//提交
			conn.commit();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(stmt!=null){
				try {
					stmt.close();
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
