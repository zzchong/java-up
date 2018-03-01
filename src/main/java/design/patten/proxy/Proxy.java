package design.patten.proxy;

/**
 * Created by pc on 2018/3/1.
 * 代理类
 * @author zzc
 */
public class Proxy extends AbstractProxy {

    /**
     * 传递被代理的对象实例
     */
    public Proxy(ISubject subject) {
        super.setSubject(subject);
    }

    /**
     * 预处理工作
     */
     @Override
     void before(){
        System.out.println("代理类预处理工作...........");
    }

    /**
     * 善后工作
     */
     @Override
     void after(){
        System.out.println("代理类善后工作......");
    }
}
