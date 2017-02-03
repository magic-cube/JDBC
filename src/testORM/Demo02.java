package testORM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用Object[] 来封装一条数据
 * 使用List<Object[]> 来封装多条数据
 * @author hc
 *
 */
public class Demo02 {
	public static void main(String[] args) {
		Connection conn=JDBCUtil.getMysqlConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		/*
		 * 使用map存储一条记录，
		 * 当然，如果想存储多条记录，也可以用List将Map封装起来
		 */
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			ps=conn.prepareStatement("select empname,age,salary from emp where id=?");
			ps.setInt(1, 1);
			
			rs=ps.executeQuery();
			
			while(rs.next()){
				System.out.println(rs.getString("empname")+"--"+rs.getString("age")+"--"+rs.getString("salary"));
				map.put("empname", rs.getString("empname"));
				map.put("age", rs.getString("age"));
				map.put("salary", rs.getString("salary"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.close(rs, ps, conn);
		}
		/*
		 * 即使是在方法调用完毕之后，也可以再次使用这个信息
		 * 遍历map
		 */
		for(String key:map.keySet()){
			System.out.print(key+"-"+map.get(key)+"\t");
		}
	}
}
