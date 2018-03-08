package design.patten.adapter;

/**
 * Created by pc on 2018/3/8.
 * 目标角色实现类
 * @author zzc
 */
public class Target implements ITarget{

    @Override
    public void request() {
        System.out.println("原有的已经运行的接口方法...");
    }
}
