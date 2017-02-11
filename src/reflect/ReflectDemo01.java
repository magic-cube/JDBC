package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import JDBC2.domain.User;

/**
 * ����
 * @author hc
 *
 */
public class ReflectDemo01 {
	public static void main (String[] args)throws Exception {
//		User user=(User)creat(User.class);
//		System.out.println(user);
		
		invoke1(new User());
		
	}
	//����class����,����һ������
	static Object creat(Class clazz) throws Exception{
		//�������в���ʱ,��ȡ����������������
		Constructor con=clazz.getConstructor(String.class);
		//��������
		Object obj=con.newInstance("test name");
		return obj;
	}
	static void invoke1(Object obj){
		//��ȡ����,����˽�е�,���ط�������
		Method[] ms=obj.getClass().getDeclaredMethods();
		//��ȡ��������ķ���,�����ܻ��˽�з���
		ms=obj.getClass().getMethods();
		for(Method m:ms){
			System.out.println(m.getName());
		}
		
		
	}
	
	
}
