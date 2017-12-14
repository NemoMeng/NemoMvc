/* 
 * All rights Reserved, Designed By 微迈科技
 * 2017/11/24 19:09
 */
package com.nemo.framework.core.utils;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.Type;
import jdk.internal.org.objectweb.asm.Label;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * 框架中参数操作相关工具
 * Created by Nemo on 2017/11/24.
 */
public class NemoFrameworkParameterUtils {

    /**
     * 得到request中方法需要的对应参数
     * @param request
     * @param response
     * @param method
     * @return
     */
    public static Object[] getParameters(HttpServletRequest request, HttpServletResponse response, Object clss, Method method){

        String parameterNames[] = getMethodParameterNamesByAsm4(clss.getClass(),method);
        if(parameterNames == null || parameterNames.length<=0){
            return null;
        }

        Object params[] = new Object[parameterNames.length];
        for(int i=0;i<parameterNames.length;i++){
            String param = request.getParameter(parameterNames[i]);
            params[i] = param;
        }

        return params;
    }

    /**
     * 获取指定类指定方法的参数名
     *
     * @param clazz 要获取参数名的方法所属的类
     * @param method 要获取参数名的方法
     * @return 按参数顺序排列的参数名列表，如果没有参数，则返回null
     */
    private static String[] getMethodParameterNamesByAsm4(Class<?> clazz, final Method method) {
        final Class<?>[] parameterTypes = method.getParameterTypes();
        if (parameterTypes == null || parameterTypes.length == 0) {
            return null;
        }
        final Type[] types = new Type[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            types[i] = Type.getType(parameterTypes[i]);
        }
        final String[] parameterNames = new String[parameterTypes.length];

        String className = clazz.getName();
        int lastDotIndex = className.lastIndexOf(".");
        className = className.substring(lastDotIndex + 1) + ".class";
        InputStream is = clazz.getResourceAsStream(className);
        try {
            ClassReader classReader = new ClassReader(is);
            classReader.accept(new ClassVisitor(Opcodes.ASM4) {
                @Override
                public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                    // 只处理指定的方法
                    Type[] argumentTypes = Type.getArgumentTypes(desc);
                    if (!method.getName().equals(name) || !Arrays.equals(argumentTypes, types)) {
                        return null;
                    }
                    return new MethodVisitor(Opcodes.ASM4) {
                        @Override
                        public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
                            // 静态方法第一个参数就是方法的参数，如果是实例方法，第一个参数是this
                            if(index<=parameterNames.length) {
                                if (Modifier.isStatic(method.getModifiers())) {
                                    parameterNames[index] = name;
                                } else if (index > 0) {
                                    parameterNames[index - 1] = name;
                                }
                            }
                        }
                    };

                }
            }, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parameterNames;
    }

}
