package design.patten.factory;

import design.patten.singleton.SingletonPattern;

import java.lang.reflect.Constructor;

/**
 * Created by pc on 2017/10/9.
 * 工厂模式
 * 定义：一个用于创建对象的接口，让子类决定实例化哪一个类。
 * 使用：工厂方法模式通常有四种角色，如下
 *          ·抽象产品类(AbstractProduct)  定义要被创建的对象的共性
 *          ·具体产品类(Product)  继承自抽象产品类，定义要被创建的对象
 *          ·抽象工厂类(AbstractFactory)
 *          ·具体工厂类(Factory)
 * 优点：
 *      ·封装性好，代码结构清晰，降低模块间的耦合。
 *      ·扩展性好
 *      ·屏蔽产品类（如JDBC连接数据库，从MySQL切换到Oracle时，只需切换一下驱动名称）
 *      ·解耦框架
 * 扩展：
 *      ·简单工厂模式（静态工厂模式）  参见 SimpleFactory
 *      ·多工厂模式
 * @author zz_chong
 */
public class FactoryPattern {

    public static void main(String[] args){
        AbstractProduct product = null;
        /*
            通用工厂模式创建对象的过程
         */
        AbstractFactory factory = new Factory();
        product = factory.createProduct(Product.class);

        /*
            简单工厂模式创建对象的过程
         */
        product = SimpleFactory.createProduct(Product.class);
        /*
            继续业务处理
         */
    }
    /**
     *     工厂类生产单例对象
     */
    private static SingletonPattern singletonPattern;

    static {
        try {

            Class c1 = Class.forName(SingletonPattern.class.getName());
            //获得无参构造
            Constructor constructor = c1.getDeclaredConstructor();

            //设置无参构造是可访问的
            constructor.setAccessible(true);
            singletonPattern = (SingletonPattern)constructor.newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static SingletonPattern getSingletonPattern() {
        return singletonPattern;
    }
}
