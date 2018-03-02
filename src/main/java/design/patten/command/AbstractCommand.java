package design.patten.command;

/**
 * Created by pc on 2018/3/2.
 * 抽象命令
 * @author zzc
 */
public abstract class AbstractCommand {

    /**
     * 子类全局共享变量
     */
    protected final AbstractReceiver receiver;

    /**
     * 实现类必须设置一个接收者
     * @param receiver 接收者
     */
    public AbstractCommand(AbstractReceiver receiver) {
        this.receiver = receiver;
    }

    /**
     * 每个命令类都必须有一个执行命令的方法
     */
    public abstract void exec();
}
