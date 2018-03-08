package design.patten.adapter;

/**
 * Created by pc on 2018/3/8.
 * 对象适配器（实现目标接口。然后通过关联源角色的实现类来完成适配）
 * @author zzc
 */
public class ObjectAdapter implements ITarget {

    private Source source = null;

    public ObjectAdapter(Source source) {
        this.source = source;
    }

    @Override
    public void request() {
        this.source.doSomething();
    }
}
