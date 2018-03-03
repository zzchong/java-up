package design.patten.decorator;

/**
 * Created by pc on 2018/3/3.
 * 被装饰对象
 * @author zzc
 */
public class Component extends AbstractComponent{
    @Override
    public void operation() {
        System.out.println("原始操作....");
    }
}
