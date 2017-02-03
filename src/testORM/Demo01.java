package testORM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用Object[] 来封装一条数据
 * 使用List<Object[]> 来封装多条数据
 * @author hc
 *
 */
public class Demo01 {
	public static void main(String[] args) {
		Connection conn=JDBCUtil.getMysqlConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Object[]> list=null;
		try {
			ps=conn.prepareStatement("select empname,age,salary from emp where id=?");
			ps.setInt(1, 1);
			
			rs=ps.executeQuery();
			/*
			 * 使用Object数组封装了一条记录
			 * 如果想封装多条记录，可以使用List，对object数组再次封装
			 */
			//----------------------------------------------------
			/*
			 * 对List进行再次封装
			 */
			list=new ArrayList<Object[]>();
			while(rs.next()){
				Object []obj=new Object[3];
				System.out.println(rs.getString("empname")+"--"+rs.getString("age")+"--"+rs.getString("salary"));
				//分别存储雇员姓名，年龄和薪水
				obj[0]=rs.getObject(1);
				obj[1]=rs.getObject(2);
				obj[2]=rs.getObject(3);
				//每循环一次，向List中添加一个Object对象，即一条记录
				list.add(obj);
			}
			
		
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.close(rs, ps, conn);
		}
		//即使是在方法调用完毕之后，也可以再次使用这个信息
		System.out.println(list.get(0)[0]);
	}
}
