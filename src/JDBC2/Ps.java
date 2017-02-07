package JDBC2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * PreparedStatement
 * �ṩ��Ԥ���������SQLע������
 * @author hc
 *
 */
public class Ps {
	public static void main(String[] args) throws SQLException {
		read("����");
	}
	static void read(String name) throws SQLException{
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//��������
			conn=JdbcUtils.getConnection();
			//�������
			String sql="select id,name,birthday,money from user where name=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, name);
			
			//ִ�����
			System.out.println("sql:"+sql);
			rs=ps.executeQuery();
			//������
			while(rs.next()){
				System.out.println(rs.getObject("id")+"\t"
						+rs.getObject("name")+"\t"
						+rs.getObject("birthday")+"\t"
						+rs.getObject("money"));
			}
		}finally{
			JdbcUtils.free(rs, ps, conn);
		}
	}
}
