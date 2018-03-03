package design.patten.decorator;

/**
 * Created by pc on 2018/3/3.
 * 装饰模式：
 *      定义：动态的给一个对象添加一些额外的职责。就增加功能来说，装饰模式相比生成子类更为灵活
 *      角色：
 *          ·抽象构件：原始构件（被装饰对象）的抽象
 *          ·具体构件：被装饰对象
 *          ·装饰角色抽象：装饰类的抽象
 *          ·具体装饰角色：具体的装饰类
 *      优点：
 *          ·装饰类和被装饰类可以独立发展，而不会相互耦合
 *          ·装饰模式是继承关系的一个替代方案
 *          ·装饰模式可以动态的扩展一个实现类的功能
 *      应用：
 *          ·需要扩展一个类的功能，或给一个类增加附加功能
 *          ·需要动态的给一个对象增加功能，这些功能可以在动态的撤销
 *          ·需要为一批的兄弟类进行改装或加装功能
 *      注意：减少装饰类的数量
 * @author zzc
 */
public class DecoratorPattern {

    public static void main(String [] args){

        /**
         * 原始对象
         */
        AbstractComponent component = new Component();

        /**
         * 经过修饰
         */
        component = new Decorator(component);

        component.operation();

    }
}
