package design.patten.command;

/**
 * Created by pc on 2018/3/2.
 * 调用者
 * @author zzc
 */
public class Invoker {

    /**
     * 命令
     */
    private AbstractCommand command;

    /**
     * 接收命令
     */
    public Invoker setCommand(AbstractCommand command) {
        this.command = command;
        return  this;
    }

    /**
     * 执行命令
     */
    public void action(){

        command.exec();
    }
}
