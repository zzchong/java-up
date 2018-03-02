package design.patten.mediator;


/**
 * Created by pc on 2018/3/2
 * 中介者模式
 *      定义：用一个中介对象封装一系列的对象交互，中介者使各对象不需要显示地相互作用，从而使其耦合松散，而且可以独立的改变他们之间的交互。
 *      角色：
 *          ·抽象中介者角色：定义统一接口，用于各同事角色之间的通信。
 *          ·具体中介者角色：实现接口，协调各同事角色行为
 *          ·同事角色
 *      优点：
 *          ·减少类间的依赖，同事类只依赖中介者，减少了依赖，同时也降低了类间的耦合
 *      缺点：中介者可能会膨胀的很大，而且逻辑复杂
 *      应用：
 *          ·中介者模式适用于多个对象之间紧密耦合的情况——在类图中出现了蜘蛛网状结构
 *          ·MVC框架：C就是一个中介者，把M和V隔开，协调M和V协同工作
 *      注意：同事类要使用构造函数注入中介者，因为同事类必须得有中介者；中介者用getter/setter方式注入同事类，因为中介者可以只有部分同事类
 * @author zzc.
 */
public class MediatorPattern {


    public static void main(String[] args) throws CloneNotSupportedException {

            //中介者
            AbstractMediator mediator = new Mediator();
            //同事类one
            ColleagueOne colleagueOne = new ColleagueOne(mediator);
            //同事类two
            ColleagueTwo colleagueTwo = new ColleagueTwo(mediator);
            //同事类one的自发方法
            colleagueOne.sefMethod();
            //同事类one的依赖方法
            colleagueOne.depMethod();
            //同事类two的自发方法
            colleagueTwo.sefMethod();
            //同事类two的依赖方法
            colleagueTwo.depMethod();
    }
}
