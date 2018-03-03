package design.patten.strategy;

/**
 * Created by pc on 2018/3/3
 * 策略模式
 *      定义：定义一组算法，将每个算法都封装起来，并且使它们之间可以互换
 *      角色：
 *          ·封装角色(StrategyContext)：上下文角色，封装算法
 *          ·抽象策略角色(IStrategy)：算法组的抽象
 *          ·具体策略角色(StrategyOne,StrategyTwo)：算法的具体实现
 *      优点：
 *          ·算法可以自由切换
 *          ·避免使用多重条件判断
 *          ·扩展性良好
 *       注意：
 *          ·策略算法过多的场景下，结合工厂模式使用，效果更高（策略工厂 StrategyFactory）
 *          ·策略模式的一个特殊的应用是 策略枚举（StrategyEnum）
 *
 * @author zzc.
 */
public class StrategyPattern {

    public static void main(String[] args){

        System.out.println("-----------------------普通策略----------------------");
        /**
         * 定义策略
         */
        IStrategy strategy = new StrategyOne();
        /**
         * 封装策略
         */
        StrategyContext context = new StrategyContext(strategy);
        /**
         * 执行策略
         */
        context.doAnything();


        /**
         * 枚举策略
         */
        System.out.println("-----------------------枚举策略----------------------");
        StrategyEnum.STRATEGY_ONE.doSomething();
        StrategyEnum.STRATEGY_TWO.doSomething();

        /**
         * 结合工厂模式执行策略
         */
        System.out.println("-----------------------结合工厂模式执行策略----------------------");
        StrategyContext context1 = new StrategyContext(StrategyContext.STRATEGY_ONE);
        context1.strategy();

        System.out.println("切换策略....");
        context1.setClassName(StrategyContext.STRATEGY_TWO);
        context1.strategy();
    }
}
