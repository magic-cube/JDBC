package JDBC2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * �ɹ�����������ҳ
 * @author hc
 *
 */
public class ScrollRS {
	public static void main(String[] args) throws SQLException {
		read2();
	}
	/*
	 * �Լ�ʵ�ֵķ�ҳ,�Ὣ�������ݼ��ص��ڴ���
	 * Ч�ʲ���,�����������ʱ��,����������ڴ����
	 * MySQL֧�ַ�ҳ,�������Ҫ���ַ�ʽ
	 */
	static void read() throws SQLException{
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			//��������
			conn=JdbcUtils.getConnection();
			//�������
			st=conn.createStatement();
			//ִ�����
			rs=st.executeQuery("select id,name,birthday,money from user");
			
			/*
			 * ��λ��ָ��������,������ʵ�ַ�ҳ
			 */
			rs.absolute(2);
			int i=0;
			
			//������
			while(rs.next()&&i<4){
				i++;
				System.out.println(rs.getObject("id")+"\t"
						+rs.getObject("name")+"\t"
						+rs.getObject("birthday")+"\t"
						+rs.getObject("money"));
			}
			System.out.println("----------");
		}finally{
			JdbcUtils.free(rs, st, conn);
		}
	}
	
	/*
	 * MySQL֧�ַ�ҳ
	 */
	static void read2() throws SQLException{
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			//��������
			conn=JdbcUtils.getConnection();
			//�������
			st=conn.createStatement();
			//ִ�����
			rs=st.executeQuery("select id,name,birthday,money from user limit 2,4");
			//������
			while(rs.next()){
				System.out.println(rs.getObject("id")+"\t"
						+rs.getObject("name")+"\t"
						+rs.getObject("birthday")+"\t"
						+rs.getObject("money"));
			}
		}finally{
			JdbcUtils.free(rs, st, conn);
		}
	}
	
}
