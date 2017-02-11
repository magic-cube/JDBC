package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * 单例工厂
 */
public class DaoFactory {
	/*
	 * private static UserDao userDao=null 一定要放在前面，不然会赋值完后又被赋为空
	 * 先执行private static DaoFactory instance=new DaoFactory()静态初始化
	 * 初始化时，会调用到DaoFactory的构造器，完成对userDao的赋值
	 * 如果把private static UserDao userDao=null;放在后面，会在赋值完后，又被赋为空
	 * 出现空指针异常
	 */
	private static UserDao userDao=null;
	
	private static DaoFactory instance=new DaoFactory();
	/**
	 * 在构造器中完成对资源文件的初始化
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
			//初始化错误的异常,比较严重
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
