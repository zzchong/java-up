package design.patten.command;

/**
 * Created by pc on 2018/3/2.
 * 具体命令2
 * @author zzc
 */
public class CommandTwo extends AbstractCommand {

    /**
     * 默认接收者
     */
    public CommandTwo() {
        super(new ReceiverTwo());
    }


    /**
     * 设置新接收者
     */
    public CommandTwo(AbstractReceiver receiver) {
        super(receiver);
    }

    @Override
    public void exec() {
        super.receiver.doSomething();
    }
}
