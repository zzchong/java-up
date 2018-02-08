package design.patten.template;

/**
 * 具体模板类
 * Created by pc on 2018/2/8.
 * @author zz_chong
 */
public class TemplateOther extends AbstractTemplate{

    /**
     默认为false，不执行doAnything()
     */
    private boolean doAnythingFlag = false;

    @Override
    void doSomething() {
        System.out.println("其他模板的算法");
    }

    @Override
    void doAnything() {
        System.out.println("其他模板的其他算法");
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
