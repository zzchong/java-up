package designPatten.singleton;

/**
 * Created by pc on 2017/10/9.
 * 单例模式
 * 定义：确保某一个类只有一个实例，而且自行实例化并向整个系统提供这个实例
 * 使用：饿汉式单例和懒汉式单例，建议使用饿汉式单例
 * 应用：在Spring中，每个Bean默认就是单例，这样Spring容器可以管理这些Bean的生命周期。
 * @author zz_chong
 */
public class SingletonPattern {

    /**
     * （1）饿汉式单例
     */
    private static SingletonPattern singletonPattern = new SingletonPattern();

    /**
     * 限制产生多个对象
     * 通过使用private的构造函数确保了在一个应用中只产生一个实例，并且是自行实例化的。
     */
    private SingletonPattern(){

    }

    public static SingletonPattern getSingleton(){
        return singletonPattern;
    }

    /**
     * 类中其他方法，尽量使用static
     */
    public static void doSomething(){

    }
    /**
     * （2）懒汉式单例
     */
    private static SingletonPattern singletonPattern1 = null;


    /**
     * 在多线程高并发下会有线程安全问题  有可能会有多个实例生成 所以要加上synchronized
     * @return SingletonPattern
     */
    public synchronized static SingletonPattern getSingleton1(){
        if(singletonPattern1 ==null){
            return  new SingletonPattern();
        }
        return singletonPattern1;
    }
}
