package design.patten.strategy;

import org.springframework.util.StringUtils;

/**
 * Created by pc on 2018/3/3.
 * 封装角色
 * @author zzc
 */
public class StrategyContext {

    private IStrategy strategy = null;
    private String className = STRATEGY_ONE;
    public static String STRATEGY_ONE = StrategyOne.class.getName();
    public static String STRATEGY_TWO = StrategyTwo.class.getName();

    /**
     * 不结合工厂模式,注入具体的策略
     */
    public StrategyContext(IStrategy strategy) {
        this.strategy = strategy;
    }
    /**
     * 不结合工厂模式
     */
    public void doAnything(){
        this.strategy.doSomething();
    }


    /**
     * 结合工厂模式，传入策略名称即可
     */
    public StrategyContext(String className) {
        this.className = className;
    }

    /**
     * 结合工厂模式 执行算法
     */
    public void strategy(){
        if(StringUtils.isEmpty(className)){
            className = StrategyContext.STRATEGY_ONE;
        }
        this.strategy = StrategyFactory.createStrategy(className);
        this.strategy.doSomething();
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
