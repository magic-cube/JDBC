package JDBC2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Base {
	public static void main(String[] args) throws SQLException {
		test();
	}
	
	public static void test() throws SQLException{
		//1.ע������
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		
		//2.��������
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "hc433911");
		
		//3.�������
		Statement st = conn.createStatement();
		
		//4.ִ�����
		ResultSet rs = st.executeQuery("select * from user where id=1");
		
		//5.������
		while(rs.next()){
			System.out.println(rs.getObject(1)+"\t"+rs.getObject(2)+"\t"+rs.getObject(3));
		}
		
		//6.�ͷ���Դ
		rs.close();
		st.close();
		conn.close();
	}
	
}































