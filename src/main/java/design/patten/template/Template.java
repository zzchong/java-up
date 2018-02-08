package design.patten.template;

/**
 * 具体模板类
 * Created by pc on 2018/2/8.
 * @author zz_chong
 */
public class Template extends AbstractTemplate{

    /**
        默认为true，可以执行doAnything()
     */
    private boolean doAnythingFlag = true;

    @Override
    void doSomething() {
        System.out.println("模板一的算法");
    }

    @Override
    void doAnything() {
        System.out.println("模板一的其他算法");
    }

    @Override
    boolean hook() {
        return this.doAnythingFlag;
    }

    public boolean isDoAnythingFlag() {
        return doAnythingFlag;
    }

    public void setDoAnythingFlag(boolean doAnythingFlag) {
        this.doAnythingFlag = doAnythingFlag;
    }
}
