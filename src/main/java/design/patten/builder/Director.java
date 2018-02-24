package design.patten.builder;

import java.util.ArrayList;

/**
 * Created by pc on 2018/2/23.
 * 导演类
 *      ·导演类起到了封装的作用，避免调用者会深入到建造者内部的实现类
 * @author zzc
 */
public class Director {

    /**
     * 已知的顺序
     */
    private ArrayList<String> list = new ArrayList<>();

    /**
     * 第一类产品建造者
     */
    private ProductOneBuilder builderProductOne = new ProductOneBuilder();

    /**
     * 第二类产品建造者
     */
    private ProductTwoBuilder builderProductTwo = new ProductTwoBuilder();

    /**
     * 构建不同的产品
     * 构建第一类产品，先做第一件事 后做第二件事
     */
    public ProductOne getAProductOne(){
        list.clear();
        /**
         * 设置不同的零件，产生不同的产品
         */
        list.add("ONE");
        list.add("TWO");

        builderProductOne.setPart(list);

        return  builderProductOne.builderProduct();
    }

    /**
     * 构建不同的产品
     * 构建第一类产品，先做第二件事 后做第一件事
     */
    public ProductOne getBProductOne(){
        list.clear();
        list.add("TWO");
        list.add("ONE");
        builderProductOne.setPart(list);
        return  builderProductOne.builderProduct();
    }

    /**
     * 构建不同的产品
     * 构建第二类产品，只做第一件事
     */
    public ProductTwo getAProductTwo(){
        list.clear();
        list.add("ONE");
        builderProductTwo.setPart(list);
        return  builderProductTwo.builderProduct();
    }

    /**
     * 构建不同的产品
     * 构建第二类产品，只做第二件事
     */
    public ProductTwo getBProductTwo(){
        list.clear();
        list.add("TWO");
        builderProductTwo.setPart(list);
        return  builderProductTwo.builderProduct();
    }

    /**
     *
     *   等等等还可以有很多方法 ，按照不同的顺序来建造产品
     */
}
