package design.patten.proxy;

import java.lang.reflect.*;

/**
 * Created by pc on 2018/3/1.
 * 动态代理类的具体实现
 * @author zzc
 */
public class SubjectDynamicProxy extends DynamicProxy {

    public static <T> T newProxyInstance(ISubject subjectInterface,String joinPoint){

        /**
         * 获取ClassLoader
         */
        ClassLoader loader = subjectInterface.getClass().getClassLoader();

        /**
         * 获取接口所有数组
         */
        Class<?> [] interfaces = subjectInterface.getClass().getInterfaces();

        /**
         * 获取handler
         */
        InvocationHandler handler = new CustomerInvocationHandler(subjectInterface);

        return newProxyInstance(loader,interfaces,handler,joinPoint);
    }
}
