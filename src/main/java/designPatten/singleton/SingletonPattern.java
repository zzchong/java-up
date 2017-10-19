package designPatten.singleton;

/**
 * Created by pc on 2017/10/9.
 * 单例模式
 * 某个类在内存中只有一个实例
 */
public class SingletonPattern {

    //1.饿汉式单例
    private static SingletonPattern singletonPattern = new SingletonPattern();

    //保证不能通过正常渠道新建一个对象
    private SingletonPattern(){

    }

    public static SingletonPattern getSingleton(){
        return singletonPattern;
    }

    //2 懒汉式单例
    private static SingletonPattern singletonPattern1 = null;

    // 在多线程高并发下会有线程安全问题  有可能会有多个实例生成 所以要加上synchronized
    public synchronized static SingletonPattern getSingleton1(){
        if(singletonPattern ==null){
            return  new SingletonPattern();
        }
        return singletonPattern1;
    }
}
