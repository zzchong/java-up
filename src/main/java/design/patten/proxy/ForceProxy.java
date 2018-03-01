package design.patten.proxy;

/**
 * Created by pc on 2018/3/1.
 * 强制代理类
 * @author  zzc
 */
public class ForceProxy extends AbstractProxy {


    public ForceProxy(ISubject subjectInterface) {
       super.setSubject(subjectInterface);
    }

    @Override
    void before() {
        System.out.println("普通代理类特殊预处理....");
    }

    @Override
    void after() {
        System.out.println("普通代理类特殊善后处理....");
    }

}
