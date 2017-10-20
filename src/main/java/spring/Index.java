package spring;

/**
 * Created by pc on 2017/10/19.
 * 基于spring 的事件驱动模型，即 发布-订阅模式
 * 定义：对象间一种一队多的依赖关系，使得每当一个对象的状态改变，则所有依赖于该对象都会得到通知并被自动更新
 * jdk内部直接提供了观察者模式的抽象:
 * 目标：java.util.Observable
 * 观察者:java.util.Observer
 * spring的驱动模型：
 *  事件ApplicationEvent:继承自jdk的EventObject,JDK要求所有事件将继承塔，并通过source得到事件源
 *  发布事件:ApplicationEventPublisher 以及 ApplicationEventMulticaster
 *  监听器:ApplicationListener 继承自EventListener,JDK要求所有监听器继承它
 *
 */
public class Index {
}
