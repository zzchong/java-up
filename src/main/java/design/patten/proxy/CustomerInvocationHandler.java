package design.patten.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by pc on 2018/3/1.
 * 动态代理类的handle实现：对真实方法的调用，也就是说被代理接口的所有方法都是通过此来进行调用的
 * 实现 InvocationHandler :JDK提供的动态代理接口
 * @author zzc
 */
public class CustomerInvocationHandler implements InvocationHandler{

    /**
     * 被代理对象
     */
    private Object target = null;

    /**
     * 通过构造函数传递一个对象
     * @param target 被代理对象
     */
    public CustomerInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(this.target,args);
    }
}
