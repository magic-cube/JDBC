package JDBC2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OtherApi {
	public static void main(String[] args) throws SQLException {
		creat();
	}
	static void creat() throws SQLException{
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//��������
			conn=JdbcUtils.getConnection();
			//�������
			String sql="insert into user(name,birthday,money) values('name2','1987-01-02',400)";
			//��ȡ����
			ps=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			//ִ�����
			int i=ps.executeUpdate();
			//��ȡ����,����һ��ResultSet,�������ܲ�ֹһ��,��������
			rs=ps.getGeneratedKeys();
			int id=0;
			while(rs.next()){
				id=rs.getInt(1);
			}
			
			System.out.println("i="+i);
			
		}finally{
			JdbcUtils.free(rs, ps, conn);
		}
	}
}
