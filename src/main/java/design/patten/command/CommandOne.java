package design.patten.command;

/**
 * Created by pc on 2018/3/2.
 * 具体命令1
 * @author zzc
 */
public class CommandOne extends AbstractCommand {

    /**
     * 默认接收者
     */
    public CommandOne() {
        super(new ReceiverOne());
    }

    /**
     * 设置新接收者
     */
    public CommandOne(AbstractReceiver receiver) {
       super(receiver);
    }


    @Override
    public void exec() {
        super.receiver.doSomething();
    }
}
