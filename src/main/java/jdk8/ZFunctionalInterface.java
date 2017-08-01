package jdk8;

/**
 * Created by pc on 2017/8/1.
 * 有 FunctionalInterface 注释 则可以用lambda expressions ,而且只能有一个abstract方法 可以有一个default 方法
 */
@FunctionalInterface
interface ZFunctionalInterface {

    void  doSomething();

    default String defaultSomething(){
        return  "this is a default thing";
    }
}
