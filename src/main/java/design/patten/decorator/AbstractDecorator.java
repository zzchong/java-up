package design.patten.decorator;

/**
 * Created by pc on 2018/3/3.
 * 装饰角色对象抽象
 * 如果只有一个装饰类，则可以直接实现具体的装饰角色，而不用在使用装饰角色的抽象
 * @author zzc
 */
public abstract class AbstractDecorator extends AbstractComponent{

    /**
     * 被装饰的对象
     */
    private AbstractComponent component;

    public AbstractDecorator(AbstractComponent component) {
        this.component = component;
    }

    @Override
    public void operation() {
        this.component.operation();
    }
}
