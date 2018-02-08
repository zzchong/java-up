package design.patten.factory;

import design.patten.singleton.SingletonPattern;

import java.lang.reflect.Constructor;

/**
 * 单例类工厂
 *  工厂类生产单例对象（通过反射的方式）
 *  扩展：
 *      在一个项目中可以产生一个单例构造器，所有的单例类都由这个构造器来生成，只要输入一个类型就可以获得唯一的实例
 * Created by pc on 2018/2/8.
 * @author zz_chong
 */
public class SingletonFactory {

    private static Singleton singleton;

    static {
        try {

            Class c1 = Class.forName(SingletonPattern.class.getName());
            //获得无参构造
            Constructor constructor = c1.getDeclaredConstructor();

            //设置无参构造是可访问的
            constructor.setAccessible(true);
            singleton = (Singleton)constructor.newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Singleton getSingleton() {
        return singleton;
    }
}
