package design.patten.strategy;

/**
 * Created by pc on 2018/3/3.
 * 具体策略角色2
 * @author zzc
 */
public class StrategyTwo implements IStrategy {

    @Override
    public void doSomething() {
        System.out.println("策略二的算法...");
    }
}
