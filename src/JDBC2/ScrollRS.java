package JDBC2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 可滚动结果集与分页
 * @author hc
 *
 */
public class ScrollRS {
	public static void main(String[] args) throws SQLException {
		read2();
	}
	/*
	 * 自己实现的分页,会将所有数据加载到内存中
	 * 效率不高,在数据量大的时候,更可能造成内存溢出
	 * MySQL支持分页,因而不需要这种方式
	 */
	static void read() throws SQLException{
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			//建立连接
			conn=JdbcUtils.getConnection();
			//创建语句
			st=conn.createStatement();
			//执行语句
			rs=st.executeQuery("select id,name,birthday,money from user");
			
			/*
			 * 定位到指定的行数,可用以实现分页
			 */
			rs.absolute(2);
			int i=0;
			
			//处理结果
			while(rs.next()&&i<4){
				i++;
				System.out.println(rs.getObject("id")+"\t"
						+rs.getObject("name")+"\t"
						+rs.getObject("birthday")+"\t"
						+rs.getObject("money"));
			}
			System.out.println("----------");
		}finally{
			JdbcUtils.free(rs, st, conn);
		}
	}
	
	/*
	 * MySQL支持分页
	 */
	static void read2() throws SQLException{
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			//建立连接
			conn=JdbcUtils.getConnection();
			//创建语句
			st=conn.createStatement();
			//执行语句
			rs=st.executeQuery("select id,name,birthday,money from user limit 2,4");
			//处理结果
			while(rs.next()){
				System.out.println(rs.getObject("id")+"\t"
						+rs.getObject("name")+"\t"
						+rs.getObject("birthday")+"\t"
						+rs.getObject("money"));
			}
		}finally{
			JdbcUtils.free(rs, st, conn);
		}
	}
	
}
