package design.patten.adapter;

/**
 * Created by pc on 2018/3/8.
 * 类适配器角色(继承源角色，实现目标角色)
 * @author zzc继承源角色，实现目标角色
 */
public class Adapter extends Source implements ITarget{

    @Override
    public void request() {
        super.doSomething();
    }
}
