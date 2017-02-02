package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 娴嬭瘯璺熸暟鎹簱寤虹珛杩炴帴
 * @author Administrator
 *
 */
public class Demo01 {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			//鍔犺浇椹卞姩绫�
			Class.forName("com.mysql.jdbc.Driver");
			long start = System.currentTimeMillis();
			//寤虹珛杩炴帴(杩炴帴瀵硅薄鍐呴儴鍏跺疄鍖呭惈浜哠ocket瀵硅薄锛屾槸涓�涓繙绋嬬殑杩炴帴銆傛瘮杈冭�楁椂锛佽繖鏄疌onnection瀵硅薄绠＄悊鐨勪竴涓鐐癸紒)
			//鐪熸寮�鍙戜腑锛屼负浜嗘彁楂樻晥鐜囷紝閮戒細浣跨敤杩炴帴姹犳潵绠＄悊杩炴帴瀵硅薄锛�
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","123456s");
			long end = System.currentTimeMillis();
			System.out.println(conn);
			System.out.println("寤虹珛杩炴帴锛岃�楁椂锛�"+(end-start)+"ms姣");
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
