package reflect;

import java.lang.annotation.Annotation;
/**
 * �����ȡע��(Annotation)
 */
public class AnnotationDemo {
	public static void main(String[] args) {
		
	}
	static void anno(Class clazz) throws Exception{
		Annotation [] as=clazz.getAnnotations();
	}
}
