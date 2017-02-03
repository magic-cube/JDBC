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
 * ʹ��List<Map<String,Object>>����װ��������
 * @author hc
 *
 */
public class Demo03 {
	public static void main(String[] args) {
		Connection conn=JDBCUtil.getMysqlConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		/*
		 * ʹ��map�洢һ����¼��
		 * ��Ȼ�������洢������¼��Ҳ������List��Map��װ����
		 */
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		Map<String,Object> map=null;
		try {
			ps=conn.prepareStatement("select empname,age,salary from emp where id>=?");
			ps.setInt(1, 1);
			
			rs=ps.executeQuery();
			
			while(rs.next()){
				//ÿ��ѭ����newһ��Map����
				map=new HashMap<String,Object>();
				System.out.println(rs.getString("empname")+"--"+rs.getString("age")+"--"+rs.getString("salary"));
				map.put("empname", rs.getString("empname"));
				map.put("age", rs.getString("age"));
				map.put("salary", rs.getString("salary"));
				
				//��map����List��
				list.add(map);
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
		System.out.println(list.size());
		for(int i=0;i<list.size();i++){
			for(String key:list.get(i).keySet()){
				System.out.print(key+"-"+list.get(i).get(key)+"\t");
			}
			System.out.println();
		}
	}
}
