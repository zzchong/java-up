package design.patten.strategy;

/**
 * Created by pc on 2018/2/7.
 *
 * 策略模式和简单工厂模式混用，会有更好的效果
 * @author zz_chong
 */
public class StrategyFactory{

    public static IStrategy createStrategy(String name) {
        IStrategy strategy = null;
        try {
            strategy = (IStrategy)Class.forName(name).newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        return strategy;
    }
}
