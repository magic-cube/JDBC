package JDBC2.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;

/**
 * JDBC性能的优化,连接池的实现
 * 在每次close连接的时候,不是真正的关闭连接,而是将其放入连接池中
 * @author hc
 *
 */
public class MyDataSource {
	private static String url="jdbc:mysql://localhost:3306/jdbc";
	private static String user="root";
	private static String password="hc433911";
	
	//由于这个集合需要频繁的增加和删除,因而选用LinkedList
	private LinkedList<Connection> connectionsPool=new LinkedList<Connection>();
	
	/*
	 * 可以去规定最小连接数与最大连接数,此处默认最小连接数为10
	 * 每次添加时加到尾部,拿的时候从首部拿(先入先出)
	 */
	public MyDataSource() {
		for(int i=0;i<10;i++){
			this.connectionsPool.addLast(this.creatConnection());
		}
	}
	
	//拿的时候从首部拿
	public Connection getConnection(){
		return connectionsPool.removeFirst();
	}
	
	
	/*
	 * 创建连接
	 */
	private Connection creatConnection(){
		Connection conn=null;
		try {
			conn=DriverManager.getConnection(url,user,password);
		} catch (Exception e) {
			throw new ExceptionInInitializerError();
		}
		return conn;
	}
	
	//释放的时候,将连接放回连接池
	public void free(Connection conn){
		this.connectionsPool.addFirst(conn);
	}
	
}












