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
			//建立连接
			conn=JdbcUtils.getConnection();
			//创建语句
			String sql="insert into user(name,birthday,money) values('name2','1987-01-02',400)";
			//获取主键
			ps=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			//执行语句
			int i=ps.executeUpdate();
			//获取主键,返回一个ResultSet,主键可能不止一个,联合主键
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
