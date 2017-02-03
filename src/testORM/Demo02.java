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
 * ʹ��Map ����װһ������
 * @author hc
 *
 */
public class Demo02 {
	public static void main(String[] args) {
		Connection conn=JDBCUtil.getMysqlConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		/*
		 * ʹ��map�洢һ����¼��
		 * ��Ȼ�������洢������¼��Ҳ������List��Map��װ����
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
		 * ��ʹ���ڷ����������֮��Ҳ�����ٴ�ʹ�������Ϣ
		 * ����map
		 */
		for(String key:map.keySet()){
			System.out.print(key+"-"+map.get(key)+"\t");
		}
	}
}
