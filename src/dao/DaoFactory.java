package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * ��������
 */
public class DaoFactory {
	/*
	 * private static UserDao userDao=null һ��Ҫ����ǰ�棬��Ȼ�ḳֵ����ֱ���Ϊ��
	 * ��ִ��private static DaoFactory instance=new DaoFactory()��̬��ʼ��
	 * ��ʼ��ʱ������õ�DaoFactory�Ĺ���������ɶ�userDao�ĸ�ֵ
	 * �����private static UserDao userDao=null;���ں��棬���ڸ�ֵ����ֱ���Ϊ��
	 * ���ֿ�ָ���쳣
	 */
	private static UserDao userDao=null;
	
	private static DaoFactory instance=new DaoFactory();
	/**
	 * �ڹ���������ɶ���Դ�ļ��ĳ�ʼ��
	 */
	private DaoFactory(){
		try {
			Properties prop=new Properties();
			InputStream inStream = new FileInputStream(new File("src/daoconfig.properties"));
			prop.load(inStream);
			String userDaoClass=prop.getProperty("userDaoClass");
			Class clazz=Class.forName(userDaoClass);
			userDao=(UserDao)clazz.newInstance();
		} catch (Exception e) {
			//��ʼ��������쳣,�Ƚ�����
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static DaoFactory getInstance(){
		return instance;
	}
	
	public UserDao getUserDao(){
		return userDao;
	}
}
