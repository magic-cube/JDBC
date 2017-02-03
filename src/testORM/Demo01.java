package testORM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * ʹ��Object[] ����װһ������
 * ʹ��List<Object[]> ����װ��������
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
			 * ʹ��Object�����װ��һ����¼
			 * ������װ������¼������ʹ��List����object�����ٴη�װ
			 */
			//----------------------------------------------------
			/*
			 * ��List�����ٴη�װ
			 */
			list=new ArrayList<Object[]>();
			while(rs.next()){
				Object []obj=new Object[3];
				System.out.println(rs.getString("empname")+"--"+rs.getString("age")+"--"+rs.getString("salary"));
				//�ֱ�洢��Ա�����������нˮ
				obj[0]=rs.getObject(1);
				obj[1]=rs.getObject(2);
				obj[2]=rs.getObject(3);
				//ÿѭ��һ�Σ���List�����һ��Object���󣬼�һ����¼
				list.add(obj);
			}
			
		
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.close(rs, ps, conn);
		}
		//��ʹ���ڷ����������֮��Ҳ�����ٴ�ʹ�������Ϣ
		System.out.println(list.get(0)[0]);
	}
}
