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
 * 使用List<Map<String,Object>>来封装多条数据
 * @author hc
 *
 */
public class Demo03 {
	public static void main(String[] args) {
		Connection conn=JDBCUtil.getMysqlConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		/*
		 * 使用map存储一条记录，
		 * 当然，如果想存储多条记录，也可以用List将Map封装起来
		 */
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		Map<String,Object> map=null;
		try {
			ps=conn.prepareStatement("select empname,age,salary from emp where id>=?");
			ps.setInt(1, 1);
			
			rs=ps.executeQuery();
			
			while(rs.next()){
				//每次循环，new一个Map出来
				map=new HashMap<String,Object>();
				System.out.println(rs.getString("empname")+"--"+rs.getString("age")+"--"+rs.getString("salary"));
				map.put("empname", rs.getString("empname"));
				map.put("age", rs.getString("age"));
				map.put("salary", rs.getString("salary"));
				
				//将map放入List中
				list.add(map);
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
		System.out.println(list.size());
		for(int i=0;i<list.size();i++){
			for(String key:list.get(i).keySet()){
				System.out.print(key+"-"+list.get(i).get(key)+"\t");
			}
			System.out.println();
		}
	}
}
