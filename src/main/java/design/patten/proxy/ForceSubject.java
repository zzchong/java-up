package design.patten.proxy;

/**
 * Created by pc on 2018/3/1.
 * 强制代理的具体主题角色
 * ·有自己的代理类
 * ·如果没有的话，无法获取该主题角色的业务实现
 * @author zzc
 */
public class ForceSubject implements ISubject {

    private IProxy proxyInterface = null;

    @Override
    public void request() {
        if(isProxy()) {
            System.out.println("抽象主题的实现^_^");
        }else {
            System.out.println("请使用指定的代理访问....");
        }
    }

    /**
     * 获取自己的代理类
     * @return IProxy
     */
    public IProxy getProxy(){
        this.proxyInterface = new ForceProxy(this);
        return this.proxyInterface;
    }

    /**
     * 是否被代理
     * @return true or false
     */
    boolean isProxy(){
        return  proxyInterface !=null;
    }
}
