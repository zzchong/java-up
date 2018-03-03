package design.patten.strategy;

/**
 * Created by pc on 2018/3/3.
 * 策略枚举
 * 浓缩了策略模式的枚举
 * @author zzc
 */
public enum StrategyEnum {

    /**
     * 策略一
     */
    STRATEGY_ONE("one"){
        @Override
        public void doSomething() {
            System.out.println("枚举策略一算法...");
        }
    },

    /**
     * 策略二
     */
    STRATEGY_TWO("two"){
        @Override
        public void doSomething() {
            System.out.println("枚举策略二算法...");
        }
    }
    ;

    private String type;

    StrategyEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public abstract void doSomething();
}
