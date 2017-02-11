package reflect;

import java.lang.annotation.Annotation;
/**
 * 反射获取注解(Annotation)
 */
public class AnnotationDemo {
	public static void main(String[] args) {
		
	}
	static void anno(Class clazz) throws Exception{
		Annotation [] as=clazz.getAnnotations();
	}
}
