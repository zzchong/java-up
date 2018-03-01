package design.patten.prototype;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2018/3/1.
 * 原型实例
 * @author zzc
 */
public class Prototype implements Cloneable {

    private String memo = "new 出来的对象";


    private ArrayList<String> list = new ArrayList<>();

    public void print(){
        System.out.println("这是"+getMemo());
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }



    @Override
    protected Object clone() throws CloneNotSupportedException {

        Prototype prototype = null;
        try {
            /**
             *  clone方法是浅拷贝
             *  对象内部的数组和引用对象等不会拷贝
             *  不会拷贝的两个条件：（1）类成员变量 （2）可变的引用对象
             */
            prototype = (Prototype)super.clone();
            prototype.setMemo("clone 出来的对象");

            /**
             * 深拷贝
             */
            prototype.list = (ArrayList<String>) this.list.clone();
        }catch (Exception e){
            e.printStackTrace();
        }
        return prototype;
    }
}
