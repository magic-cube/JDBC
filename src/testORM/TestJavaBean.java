package testORM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * ʹ��JavaBean��װ����
 * @author hc
 *
 */
public class TestJavaBean {
	
	public static void main(String[] args) {
		Connection conn=JDBCUtil.getMysqlConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		//Emp����
		Emp emp=null;
		try{
			ps=conn.prepareStatement("select empname,age,salary from emp where id=?");
			ps.setObject(1, 1);
			rs=ps.executeQuery();
			/*
			 * �˴�ֻʹ��javabean��װ��һ�����ݣ������Ҫ��װ��������
			 * ���Խ�JavaBean����Ž�list���������� 
			 */
			while(rs.next()){
				/*
				 * Ҳ����ʹ��set����ȥ�޸�
				 * �˴���getString��1��������ǽ�����еĵ�һ�е�������select��õ��ģ�������ԭ��ĵ�һ��
				 * �˴�1����empname��2����age��3����salary����selectʱ��˳����ͬ
				 */
				emp=new Emp(rs.getString("empname"),rs.getDouble("salary"),rs.getInt("age"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtil.close(rs, ps, conn);
		}
		//ʹ��ʱ�ǳ����㣬����֪��
		System.out.println(emp.getEmpname()+"-"+emp.getSalary()+"-"+emp.getAge());
		
	}
}
