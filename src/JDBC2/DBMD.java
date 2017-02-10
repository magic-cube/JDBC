package JDBC2;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.jdbc.DatabaseMetaData;

/**
 * ���ݿ��Ԫ����
 * ��������һ��д������˵û����ô�,
 * �����ںܶ�����˵,����Hibernate��,�ᾭ��ʹ��,
 * ����Ҫ���ݺܶ����ݿ�,�����Ҫ֪�����ݿ��Դ��Ϣ
 * ������,���뼶���
 * @author hc
 *
 */
public class DBMD {
	public static void main(String[] args) throws SQLException {
		Connection conn=JdbcUtils.getConnection();
		DatabaseMetaData dbmd=(DatabaseMetaData) conn.getMetaData();
		
		/*
		 * ���ݿ������
		 */
		System.out.println("db name:"+dbmd.getDatabaseProductName());
		/*
		 * �Ƿ�֧������
		 */
		System.out.println("tx:"+dbmd.supportsTransactions());
	}
}
