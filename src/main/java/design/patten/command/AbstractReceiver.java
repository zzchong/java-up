package design.patten.command;

/**
 * Created by pc on 2018/3/2.
 * 接收者角色抽象
 * @author zzc
 */
public abstract  class AbstractReceiver {

    /**
     * 每个接收者都必须完成的业务逻辑
     */
    public abstract void doSomething();

    /**
     * 回滚
     */
    public abstract void rollBack();
}
