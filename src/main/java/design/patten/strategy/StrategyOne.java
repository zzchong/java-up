package design.patten.strategy;

/**
 * Created by pc on 2018/3/3.
 * 具体策略角色1
 * @author zzc
 */
public class StrategyOne implements IStrategy {

    @Override
    public void doSomething() {
        System.out.println("策略一的算法...");
    }
}
