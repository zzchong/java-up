package design.patten.mediator;

/**
 * Created by pc on 2018/3/2.
 * 具体同事类1
 * @author zzc
 */
public class ColleagueOne extends AbstractColleague{

    /**
     * 通过构造函数注入 中介者
     * @param mediator
     */
    public ColleagueOne(AbstractMediator mediator) {
        super(mediator);
    }

    /**
     * 同事类自有方法
     */
    public void sefMethod(){
        System.out.println("同事类1自有方法执行...");
    }

    /**
     * 同事类依赖方法
     */
    public void depMethod(){

        //处理自己的业务逻辑
        System.out.println("同事类1依赖方法执行...");

        //自己处理不了的委托给中介
        super.mediator.doSomething("ColleagueOne");
    }
}
