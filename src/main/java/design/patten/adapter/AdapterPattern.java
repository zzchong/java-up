package design.patten.adapter;

/**
 * Created by pc on 2018/3/7.
 * 适配器模式：
 *      定义：将一个类的接口变换成客户端所期待的另一个接口，从而使原本因接口不匹配而无法再一起工作的两个类能够在一起工作
 *      角色：
 *          ·目标角色（ITarget）：期望接口，即转换后的接口。是一个已经正式运行的接口。
 *          ·源角色（Source）：转换前的接口
 *          ·适配器角色（类适配器 Adapter,对象适配器 ObjectAdapter）：将源角色转换为目标角色的接口(继承源角色，实现目标角色)
 *      优点：
 *          ·让两个没有关系的类一起运行
 *          ·增加了类的透明性
 *          ·提高了类的复用性。源角色即可在原有的系统中使用，也可在目标角色中使用
 *          ·灵活性非常好，随时可以删除这个适配器，而不影响原有的系统
 *       应用：
 *          ·修改有个已经投入生产的接口时，可以考虑适配者模式
 *       注意：
 *          ·类适配器是类间继承，对象适配器是对象的合成关系
 *          ·适配器是一种补偿模式，用来解决接口不相容的问题。
 * @author zzc
 */
public class AdapterPattern {

    public static void main(String[] args){

        /**
         * 原有的业务逻辑
         */
        ITarget target = new Target();
        target.request();

        /**
         * 类适配后的业务逻辑
         */
        ITarget adapter = new Adapter();
        adapter.request();

        /**
         * 对象适配后的业务逻辑
         */

        ITarget objectAdapter = new ObjectAdapter(new Source());
        objectAdapter.request();
    }
}
