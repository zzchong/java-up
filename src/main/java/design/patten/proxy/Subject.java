package design.patten.proxy;

/**
 * Created by pc on 2018/3/1.
 * 主题
 * @author zzc
 */
public class Subject implements ISubject {

    @Override
    public void request() {
        System.out.println("抽象主题的实现^_^");
    }
}
