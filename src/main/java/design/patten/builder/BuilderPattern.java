package design.patten.builder;

/**
 * Created by pc on 2018/2/23.
 * 建造者模式（生成器模式）
 *
 * 定义：将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示
 * 角色：
 *      ·产品类（AbstractProduct,ProductOne,ProductTwo）：通常是实现的模板方法模式
 *      ·抽象建造者（AbstractBuilder）：规范产品的构建，一般由子类实现
 *      ·具体建造者（ProductOneBuilder,ProductTwoBuilder）：返回一个组建好的对象
 *      ·导演类（Director）：负责安排已有的模块顺序，然后由相应的建造者去建造
 * 优点：
 *      ·封装性：调用者不必知道产品内部的组成细节
 *      ·建造者独立，容易扩展
 * 使用场景：
 *      ·相同的方法，不同的执行顺序，产生不同的事件结果
 *      ·多个部件或零件，都可以装配到一个对象中，但是运行结果又不相同
 *      ·产品类非常复杂，或者产品类中的调用顺序不同产生了不同的效能
 * 注意事项：
 *      建造者模式关注的是零件类型和装配工艺（顺序），这是它与工厂方法模式最大不同的地方
 *
 * @see AbstractProduct
 * @see ProductOne
 * @see ProductTwo
 * @see AbstractBuilder
 * @see ProductOneBuilder
 * @see ProductTwoBuilder
 * @see Director
 * @author zzc
 */
public class BuilderPattern {

    public static void main(String[] args){

        Director director = new Director();
        System.out.println("-----------------建造第一类产品，先做第一件事后做第二件事-------------------");
        ProductOne productOneA = director.getAProductOne();

        productOneA.start();

        System.out.println("-----------------建造第一类产品，先做第二件事后做第一件事-------------------");
        ProductOne productOneB = director.getBProductOne();

        productOneB.start();

        System.out.println("-----------------建造第二类产品，只做第一件事-------------------");
        ProductTwo productTwoA = director.getAProductTwo();

        productTwoA.start();

        System.out.println("-----------------建造第二类产品，只做第二件事-------------------");
        ProductTwo productTwoB = director.getBProductTwo();

        productTwoB.start();
    }
}
