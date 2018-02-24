package design.patten.builder;

/**
 * Created by pc on 2018/2/23.
 * 具体产品类一（一般使用模板方法模式）
 * @author zzc
 */
public class ProductOne extends AbstractProduct{

    @Override
    void doSomething1() {
        System.out.println("第一类产品做了事件1");
    }

    @Override
    void doSomething2() {
        System.out.println("第一类产品做了事件2");
    }

    /**
     * 业务
     */
    public void doSomething(){

    }

}
