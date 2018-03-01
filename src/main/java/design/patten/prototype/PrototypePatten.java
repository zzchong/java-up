package design.patten.prototype;

/**
 * Created by pc on 2018/3/1
 * 原型模式
 *      定义：用原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象（重新clone）
 *      优点：
 *          ·性能优良：是在内存二进制流的拷贝，比直接new性能要好，特别是在一个循环体内产生大量的对象时。
 *          ·逃避构造函数约束（是优点也是缺点）：直接在内存中拷贝，构造函数是不会执行的
 *       应用：
 *          ·资源优化场景：类初始化需要消化非常多的资源，比如数据，硬件资源等。
 *          ·性能和安全要求的场景：通过new产生一个对象需要非常繁琐的数据准备和访问权限，则可以使用原型模式
 *          ·一个对象有多个修改者的场景
 *      注意：
 *          ·原型实例需要实现Cloneable接口，并重写clone()方法
 *          ·原型模式一般与工厂模式一起使用
 *          ·clone方法的拷贝是浅拷贝，对象内部的数组和引用对象等不会拷贝
 *          ·深拷贝的时候，需要将内部的数组合引用对象在拷贝一次
 *          ·建议深拷贝和浅拷贝分开实现
 *          ·对象的clone和关键字final是有冲突的，所有要是用clone方法，类成员变量上不要增加final关键字
 * @author zzc.
 */
public class PrototypePatten {

    public static void main(String[] args) throws CloneNotSupportedException {

        /**
         * 原型模式
         */
        Prototype prototype = new Prototype();
        prototype.print();

        Prototype prototypeClone = (Prototype) prototype.clone();
        prototypeClone.print();
    }
}
