package design.patten.command;

/**
 * Created by pc on 2018/3/2.
 * 具体接收者1
 * @author zzc
 */
public class ReceiverOne extends AbstractReceiver {

    @Override
    public void doSomething() {
        System.out.println("接收者1执行业务逻辑......");
    }

    @Override
    public void rollBack() {
        System.out.println("接收者1执行业务回滚......");
    }
}
