package testORM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 使用JavaBean封装数据
 * @author hc
 *
 */
public class TestJavaBean {
	
	public static void main(String[] args) {
		Connection conn=JDBCUtil.getMysqlConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		//Emp对象
		Emp emp=null;
		try{
			ps=conn.prepareStatement("select empname,age,salary from emp where id=?");
			ps.setObject(1, 1);
			rs=ps.executeQuery();
			/*
			 * 此处只使用javabean封装了一条数据，而如果要封装多条数据
			 * 可以讲JavaBean对象放进list或别的容器中 
			 */
			while(rs.next()){
				/*
				 * 也可以使用set方法去修改
				 * 此处的getString（1）代表的是结果集中的第一列的数，是select后得到的，而不是原表的第一列
				 * 此处1代表empname，2代表age，3代表salary，与select时的顺序相同
				 */
				emp=new Emp(rs.getString("empname"),rs.getDouble("salary"),rs.getInt("age"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtil.close(rs, ps, conn);
		}
		//使用时非常方便，见名知意
		System.out.println(emp.getEmpname()+"-"+emp.getSalary()+"-"+emp.getAge());
		
	}
}
