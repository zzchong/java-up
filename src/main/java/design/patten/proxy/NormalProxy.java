package design.patten.proxy;

/**
 * Created by pc on 2018/3/1.
 * 普通代理类
 * @author  zzc
 */
public class NormalProxy extends AbstractProxy {

    private ISubject subjectInterface = null;

    public NormalProxy() {
        try {
            this.subjectInterface = new NormalSubject(this);
            super.setSubject(this.subjectInterface);
        }catch (Exception e){
            System.out.println("创建失败");
        }
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
