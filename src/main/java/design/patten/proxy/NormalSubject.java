package design.patten.proxy;

/**
 * Created by pc on 2018/3/1.
 * 普通被代理类
 * @author zzc
 */
public class NormalSubject implements ISubject {

    /**
     * 限制创建该角色的条件——必须由代理类创建
     */
    public NormalSubject(IProxy proxy) throws Exception {
        if(proxy==null){
            throw new Exception("没有代理类，不能创建该角色");
        }
    }

    @Override
    public void request() {
        System.out.println("普通被代理类的业务逻辑");
    }
}
