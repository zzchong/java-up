package design.patten.proxy;


import java.io.IOException;

/**
 * Created by pc on 2018/3/1.
 * 代理模式（委托模式）：
 *      定义：为其他对象提供一种代理以控制对这个对象的访问
 *      角色：
 *          ·抽象主题角色（ISubject）：可以是抽象类也可以是接口
 *          ·具体主题角色（被代理角色 Subject,NormalSubject）：业务逻辑的具体执行者
 *          ·代理主题角色（代理类 Proxy,NormalProxy）：他负责对真实角色的应用，在真实主题角色处理问前后做预处理和善后工作
 *      优点：
 *          ·职责清晰：真实角色实现实际业务逻辑，通过代理完成一件事务
 *          ·高扩展：具体主题角色随时都会变化，只要它实现了接口，代理类完全可以不做任何修改
 *      应用：spring aop（动态代理）
 *      扩展：
 *          ·普通代理（NormalProxy）：只能访问代理角色，而不能访问真实角色。调用者只知代理而不知真实的角色是谁，屏蔽了真实角色的变更对调用模块的影响，易于扩展。
 *          ·强制代理（强制代理角色  ForceSubject ，强制代理类 ForceProxy）：从真实角色中查到代理角色，不允许直接访问真实角色，代理的管理由真实角色自己完成。
 *          ·动态代理（动态代理类 DynamicProxy，动态代理类的具体实现 SubjectDynamicProxy，自定义Handler CustomerInvocationHandler）：在实现阶段不用关心代理谁，而在运行阶段才指定代理哪一个对象
 *      注意：（1）代理的目的是在目标对象方法的基础上作增强，这种增强本质上是对目标对象的方法进行拦截和过滤。
 *            （2）要实现动态代理，被代理类必须实现一个接口。如果不实现接口，可以通过CGLIB来实现动态代理
 *      应用：spring aop ,aspectJ等
 *
 * @author zzc
 *
 * 通用代理
 * @see ISubject
 * @see Subject
 * @see IProxy
 * @see AbstractProxy
 * @see Proxy
 *
 * 普通代理
 * @see NormalSubject
 * @see NormalProxy
 *
 * 强制代理
 * @see ForceSubject
 * @see ForceProxy
 *
 * 动态代理
 * @see DynamicProxy
 * @see SubjectDynamicProxy
 * @see CustomerInvocationHandler
 * @see IAdvice
 * @see BeforeAdvice
 */
public class ProxyPattern {

    public static void main(String[] args) throws IOException {

        /**
         * 通用代理类
         */
        System.out.println("----------------------------通用代理类 场景调用----------------------");
        IProxy commonIProxy = new Proxy(new Subject());

        commonIProxy.request();

        System.out.println("------------end----------");
        System.out.println("----------------------------普通代理类 场景调用----------------------");
        /**
         * 普通代理类
         */
        IProxy proxyInterface = new NormalProxy();

        proxyInterface.request();
        System.out.println("------------end----------");
        /**
         * 强制代理类
         *  （1）不允许直接访问真实角色
         */
        System.out.println("----------------------------强制代理类 场景调用----------------------");
        System.out.println("---------------------------（1）不允许直接访问真实角色----------------------");
        ForceSubject forceSubject = new ForceSubject();
        forceSubject.request();

        /**
         * 强制代理类
         * （2）也不允许设置其他代理类来访问真实角色
         */
        System.out.println("---------------------------（2）也不允许设置其他代理类来访问真实角色----------------------");
        ForceProxy proxy = new ForceProxy(forceSubject);
        proxy.request();

        /**
         * 强制代理类
         *  （3）使用角色的代理类访问
         */
        System.out.println("---------------------------（3）使用角色的代理类正确访问----------------------");
        IProxy forceProxy = forceSubject.getProxy();
        forceProxy.request();
        System.out.println("---------------------------end----------------------");
        System.out.println("---------------------------动态代理----------------------");
        /**
         * 动态代理
         */
        ISubject subjectInterface = new Subject();
        ISubject dynamicProxy = SubjectDynamicProxy.newProxyInstance(subjectInterface,"one");
        dynamicProxy.request();

    }
}
