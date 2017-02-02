package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 娴嬭瘯Statement鎺ュ彛鐨勭敤娉曪紝鎵цSQL璇彞锛屼互鍙奡QL娉ㄥ叆闂
 * @author Administrator
 *
 */
public class Demo02 {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try {
			//鍔犺浇椹卞姩绫�
			Class.forName("com.mysql.jdbc.Driver");
			//寤虹珛杩炴帴(杩炴帴瀵硅薄鍐呴儴鍏跺疄鍖呭惈浜哠ocket瀵硅薄锛屾槸涓�涓繙绋嬬殑杩炴帴銆傛瘮杈冭�楁椂锛佽繖鏄疌onnection瀵硅薄绠＄悊鐨勪竴涓鐐癸紒)
			//鐪熸寮�鍙戜腑锛屼负浜嗘彁楂樻晥鐜囷紝閮戒細浣跨敤杩炴帴姹犳潵绠＄悊杩炴帴瀵硅薄锛�
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","123456");
			
			stmt = conn.createStatement();
//			String name = "璧靛叚";
//			String sql = "insert into t_user (username,pwd,regTime) values ('"+name+"',66666,now())";
//			stmt.execute(sql);
			
			//娴嬭瘯SQL娉ㄥ叆
			String id = "5 or 1=1 ";
			String sql = "delete from t_user where id="+id;
			stmt.execute(sql);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(stmt!=null){
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
