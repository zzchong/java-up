package design.patten.mediator;

/**
 * Created by pc on 2018/3/2.
 * 抽象中介者角色
 * @author zzc
 */
public abstract class AbstractMediator {

    /**
     * 定义同事类
     */
    protected ColleagueOne colleagueOne;

    protected ColleagueTwo colleagueTwo;

    public ColleagueOne getColleagueOne() {
        return colleagueOne;
    }

    public void setColleagueOne(ColleagueOne colleagueOne) {
        this.colleagueOne = colleagueOne;
    }

    public ColleagueTwo getColleagueTwo() {
        return colleagueTwo;
    }

    public void setColleagueTwo(ColleagueTwo colleagueTwo) {
        this.colleagueTwo = colleagueTwo;
    }

    /**
     * 中介者模式的业务逻辑
     * @param str 调用者描述
     */
    public abstract void doSomething(String str);
}
