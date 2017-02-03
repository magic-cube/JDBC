package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ����ʹ��JDBCUtil�������Լ򻯿���
 * �Ժ���ʽ�����У���������ʹ���Ѿ���װ�õĹ��ߣ��������Լ�ȥд��Եײ������
 * @author hc
 *
 */
public class TestJDBCUtil {
	public static void main(String[] args) {
		//��������
		Connection conn=JDBCUtil.getMysqlConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		//-------------------------------------------------------------------
		/*
		 * �м�Ϊ�仯���߼������»���Ϊд�õ�ģ�壬�����������ݿ�Ĳ�ͬ�������䶯
		 * �������û�£�Ҳ������һ�������࣬�ֱ����ɾ��Ľ��з�װ������
		 */
		try {
			ps=conn.prepareStatement("select * from t_user where id=?");
			ps.setObject(1, 22020);;
			rs=ps.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString("username"));
			}
		//-------------------------------------------------------------------
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//�ر�����
			JDBCUtil.close(rs, ps, conn);
		}
		
	}
}
