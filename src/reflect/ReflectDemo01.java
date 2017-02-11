package reflect;

import java.lang.reflect.Constructor;

import JDBC2.domain.User;

/**
 * ����
 * @author hc
 *
 */
public class ReflectDemo01 {
	public static void main (String[] args)throws Exception {
		User user=(User)creat(User.class);
		System.out.println(user);
	}
	//����class����,����һ������
	static Object creat(Class clazz) throws Exception{
		//��ȡ������
		Constructor con=clazz.getConstructor(String.class);
		//��������
		Object obj=con.newInstance("test name");
		return obj;
	}
}
