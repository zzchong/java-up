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
 *      ·多工厂模式 每个产品类对应一个工厂类（这样会给可扩展性和可维护性带来一定的影响，一般是再增加一个协调类，封装子工厂类，对高层模块提供统一的访问接口）
 *      ·替代单例模式  单例类 参见Singleton   单例类工厂 参见SingletonFactory
 *      ·延迟加载的工厂类  参见 LazyFactory
 * 建议：工厂模式在项目中使用的非常频繁，与其他模式混合（模板模式，单例模式，原型模式等）使用威力更大
 * @author zz_chong
 * @see AbstractProduct 抽象产品类
 * @see Product 具体产品类
 * @see AbstractFactory 抽象工厂类
 * @see Factory 具体工厂类
 * @see SimpleFactory 简单工厂模式
 * @see SingletonFactory 替代单例模式
 * @see LazyFactory 延迟加载的工厂类
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
            创建单例类对象的过程
         */
        Singleton singleton = SingletonFactory.getSingleton();

        /*
            延迟加载创建对象的过程
         */
        product = LazyFactory.createProduct(Product.class.getName());


        /*
            继续业务处理
         */

    }

}
