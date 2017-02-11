package reflect;

import java.lang.reflect.Constructor;

import JDBC2.domain.User;

/**
 * 反射
 * @author hc
 *
 */
public class ReflectDemo01 {
	public static void main (String[] args)throws Exception {
		User user=(User)creat(User.class);
		System.out.println(user);
	}
	//传入class对象,构造一个对象
	static Object creat(Class clazz) throws Exception{
		//获取构造器
		Constructor con=clazz.getConstructor(String.class);
		//构建对象
		Object obj=con.newInstance("test name");
		return obj;
	}
}
