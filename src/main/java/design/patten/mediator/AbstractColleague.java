package design.patten.mediator;

/**
 * Created by pc on 2018/3/2.
 * 同事抽象类
 * @author zzc
 */
public abstract class AbstractColleague {

    protected AbstractMediator mediator;

    public AbstractColleague(AbstractMediator mediator) {
        this.mediator = mediator;
    }
}
