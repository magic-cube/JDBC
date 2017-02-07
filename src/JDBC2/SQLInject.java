package JDBC2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Statement的SQL注入问题
 * @author hc
 *
 */
public class SQLInject {
	public static void main(String[] args) throws SQLException {
		read("'or 1 or'");
	}
	
	static void read(String name) throws SQLException{
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			//建立连接
			conn=JdbcUtils.getConnection();
			//创建语句
			st=conn.createStatement();
			//执行语句
			String sql="select id,name,birthday,money from user where name='"+name+"'";
			System.out.println("sql:"+sql);
			rs=st.executeQuery(sql);
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













