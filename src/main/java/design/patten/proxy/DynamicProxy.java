package design.patten.proxy;

import java.lang.reflect.*;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pc on 2018/3/1.
 * 动态代理类
 * @author zzc
 */
public class DynamicProxy<T> {

    private final static List<String> JOIN_POINT = Arrays.asList("one","two");

    public static <T> T newProxyInstance(ClassLoader loader, Class<?> [] interfaces, InvocationHandler handler,String joinPoint){

        /**
         * 前置通知  切入点  aop中是通过元数据定义的
         */
        if (JOIN_POINT.contains(joinPoint)){
            (new BeforeAdvice()).exec();
        }
        return (T) Proxy.newProxyInstance(loader,interfaces,handler);
    }
}
