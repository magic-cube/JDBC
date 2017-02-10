package JDBC2;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 参数的元数据信息
 * 针对PreparedStatement,
 * @author hc
 *
 */
public class ParameterMetaTest {
	public static void main(String[] args) throws SQLException {
		read("select * from user where name=? and birthday<? and money >?",null);
	}
	
	static void read(String sql,Object[] params) throws SQLException{
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=JdbcUtils.getConnection();
			ps=conn.prepareStatement(sql);
			ParameterMetaData pmd=ps.getParameterMetaData();
			int count=pmd.getParameterCount();
			for(int i=1;i<=count;i++){
				System.out.println(pmd.getParameterClassName(i));
				System.out.println(pmd.getParameterType(i));
				System.out.println(pmd.getParameterTypeName(i));
			}
			
			System.out.println("sql:"+sql);
			
//			rs=ps.executeQuery();
//			while(rs.next()){
//				System.out.println(rs.getObject("id")+"\t"
//						+rs.getObject("name")+"\t"
//						+rs.getObject("birthday")+"\t"
//						+rs.getObject("money"));
//			}
		}finally{
			JdbcUtils.free(rs, ps, conn);
		}
	}
}
