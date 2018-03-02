package design.patten.command;

/**
 * Created by pc on 2018/3/2.
 * 命令模式
 *      定义：讲一个请求封装成一个对象，从而让你使用不同的请求把客户端参数化，对请求排队或者记录请求日志，可以提供命令的撤销和恢复功能
 *      角色：
 *          ·接收者角色：执行业务逻辑的角色
 *          ·命令角色：声明需要执行的命令
 *          ·调用者角色：接收命令并执行。
 *      优点：
 *          ·类间解耦：调用者角色和接收者角色没有任何依赖关系，只需调用Command抽象类的exec方法就可以，不需要了解是哪个接受者执行的
 *          ·可扩展性：Command的子类非常容易扩展
 *          ·命令模式结合其他模式更优秀：结合责任链模式，实现命令族解析任务；结合模板方法模式，可以减少Command子类的胖胀问题
 *       应用：你认为有命令的顶峰
 * @author zzc
 */
public class CommandPattern {

    public static void main(String[] args){

        /**
         * 调用者
         */
        Invoker invoker = new Invoker();

        /**
         * 执行命令（默认接收者1）
         */
        invoker.setCommand(new CommandOne()).action();

        /**
         * 执行命令（默认接收者2）
         */
        invoker.setCommand(new CommandTwo()).action();

    }
}
