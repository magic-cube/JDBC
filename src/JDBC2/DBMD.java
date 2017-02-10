package JDBC2;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.jdbc.DatabaseMetaData;

/**
 * 数据库的元数据
 * 对于我们一般写程序来说没多大用处,
 * 但对于很多框架来说,例如Hibernate等,会经常使用,
 * 他需要兼容很多数据库,因而需要知道数据库的源信息
 * 像事务啊,隔离级别等
 * @author hc
 *
 */
public class DBMD {
	public static void main(String[] args) throws SQLException {
		Connection conn=JdbcUtils.getConnection();
		DatabaseMetaData dbmd=(DatabaseMetaData) conn.getMetaData();
		
		/*
		 * 数据库的名字
		 */
		System.out.println("db name:"+dbmd.getDatabaseProductName());
		/*
		 * 是否支持事务
		 */
		System.out.println("tx:"+dbmd.supportsTransactions());
	}
}
