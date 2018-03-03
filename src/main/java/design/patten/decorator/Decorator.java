package design.patten.decorator;

/**
 * Created by pc on 2018/3/3.
 * 具体装饰类角色对象
 * @author zzc
 */
public class Decorator extends AbstractDecorator  {
    public Decorator(AbstractComponent component) {
        super(component);
    }

    /**
     * 修饰操作
     */
    private void method(){
        System.out.println("修饰操作...");
    }

    /**
     * 原始方法 和 修饰方法的执行顺序是固定的，可以通过重载实现不同的执行顺序
     */
    @Override
    public void operation() {
        this.method();
        super.operation();
    }
}
