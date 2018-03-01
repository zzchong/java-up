package design.patten.proxy;

/**
 * Created by pc on 2018/3/1.
 * 前置通知
 * @author zzc
 */
public class BeforeAdvice implements IAdvice {
    @Override
    public void exec() {
        System.out.println("前置通知....");
    }
}
