package jdk8;

/**
 * Created by pc on 2017/8/1.
 * 1 接口允许设置默认方法  out of box
 */
public interface DefaultInterface {

    void  doSomething();

    default String defaultDoSomething(){
        return  "this is a default thing";
    }

}
