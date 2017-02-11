package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import JDBC2.domain.User;

/**
 * 反射
 * @author hc
 *
 */
public class ReflectDemo01 {
	public static void main (String[] args)throws Exception {
//		User user=(User)creat(User.class);
//		System.out.println(user);
		
		invoke1(new User());
		
	}
	//传入class对象,构造一个对象
	static Object creat(Class clazz) throws Exception{
		//构造器有参数时,获取构造器来创建对象
		Constructor con=clazz.getConstructor(String.class);
		//构建对象
		Object obj=con.newInstance("test name");
		return obj;
	}
	static void invoke1(Object obj){
		//获取方法,包括私有的,返回方法数组
		Method[] ms=obj.getClass().getDeclaredMethods();
		//获取包括父类的方法,但不能获得私有方法
		ms=obj.getClass().getMethods();
		for(Method m:ms){
			System.out.println(m.getName());
		}
		
		
	}
	
	
}
