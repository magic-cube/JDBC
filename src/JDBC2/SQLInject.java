package JDBC2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Statement��SQLע������
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
			//��������
			conn=JdbcUtils.getConnection();
			//�������
			st=conn.createStatement();
			//ִ�����
			String sql="select id,name,birthday,money from user where name='"+name+"'";
			System.out.println("sql:"+sql);
			rs=st.executeQuery(sql);
			//������
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













