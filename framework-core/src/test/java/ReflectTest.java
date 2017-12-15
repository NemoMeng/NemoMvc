/* 
 * All rights Reserved, Designed By 微迈科技
 * 2017/12/15 15:01
 */

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Nemo on 2017/12/15.
 */
public class ReflectTest {

    public static void main(String args[]) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method test = Test.class.getMethod("test", String.class);
        Test t = new Test();
        Object obj[] = new Object[1];
        obj[0] = null;
        test.invoke(t,obj);
    }

    static class Test{
        public void test(String arg){
            System.out.println(arg);
        }
    }

}
