package JDBC2.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;

/**
 * JDBC���ܵ��Ż�,���ӳص�ʵ��
 * ��ÿ��close���ӵ�ʱ��,���������Ĺر�����,���ǽ���������ӳ���
 * @author hc
 *
 */
public class MyDataSource {
	private static String url="jdbc:mysql://localhost:3306/jdbc";
	private static String user="root";
	private static String password="hc433911";
	
	//�������������ҪƵ�������Ӻ�ɾ��,���ѡ��LinkedList
	private LinkedList<Connection> connectionsPool=new LinkedList<Connection>();
	
	/*
	 * ����ȥ�涨��С�����������������,�˴�Ĭ����С������Ϊ10
	 * ÿ�����ʱ�ӵ�β��,�õ�ʱ����ײ���(�����ȳ�)
	 */
	public MyDataSource() {
		for(int i=0;i<10;i++){
			this.connectionsPool.addLast(this.creatConnection());
		}
	}
	
	//�õ�ʱ����ײ���
	public Connection getConnection(){
		return connectionsPool.removeFirst();
	}
	
	
	/*
	 * ��������
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
	
	//�ͷŵ�ʱ��,�����ӷŻ����ӳ�
	public void free(Connection conn){
		this.connectionsPool.addFirst(conn);
	}
	
}












