/* 
 * All rights Reserved, Designed By 农金圈
 * 2017/12/15 14:17
 */

import com.nemo.framework.common.annotation.ReqBody;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * Created by Nemo on 2017/12/15.
 */
public class AnnotationTest {

    public static void main(String args[]){
        AnnotationTest t = new AnnotationTest();
        t.test("");
        Method[] methods = AnnotationTest.class.getDeclaredMethods();
        for(Method method : methods){
            Parameter[] parameters = method.getParameters();
            for(Parameter parameter : parameters){
                Annotation[] annotations = parameter.getAnnotations();
                for(Annotation annotation : annotations){
                    System.out.println(annotation.annotationType());
                }
            }
        }
    }

    public void test(@ReqBody String name){

    }

}
