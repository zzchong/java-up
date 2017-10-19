package designPatten.factory;

import designPatten.singleton.SingletonPattern;

import java.lang.reflect.Constructor;

/**
 * Created by pc on 2017/10/9.
 * 工厂模式
 * 定义一个用于创建对象的接口，让子类决定实例化哪一个类。
 *
 * 以下例子是 简单工厂模式/静态工厂模式
 * 不易扩展
 * 2 所有产品类放在一个工厂类会显得结构不太清晰，所以可以为每一个产品定义一个工厂类
 * 3 延迟初始化 通过map变量产生一个缓存
 */
public class FactoryPattern {

    public static  <T extends Product> T createProduct(Class<T> c){
            Product product = null;
            try {
                product = (Product) Class.forName(c.getName()).newInstance();
            }catch (Exception e){
                e.printStackTrace();
            }

            return (T)product;
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
