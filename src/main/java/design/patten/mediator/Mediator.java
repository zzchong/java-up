package design.patten.mediator;

/**
 * Created by pc on 2018/3/2.
 * 具体中介者
 * @author zzc
 */
public class Mediator extends AbstractMediator {

    @Override
    public void doSomething(String str) {

        System.out.println(str+"调用中介者的业务逻辑....");
    }
}
