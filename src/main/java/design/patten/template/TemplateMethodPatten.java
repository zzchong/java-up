package design.patten.template;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 模板方法模式
 * 定义：定义一个操作中的算法框架，而将一些步骤延迟到子类中，使得子类可以不改变一个算法的结果即可重定义该算法的某些特定步骤
 * 角色：
 *      ·抽象模板（AbstractTemplate 基本方法和模板方法）
 *          ·基本方法：也叫基本操作，由子类实现的方法，在模板方法中被调用
 *          ·模板方法：可以有一个或多个，一般是具体的方法，也就是一个算法结构框架，实现对基本方法的调度，完成固定的逻辑
 *      ·具体模板（Template和TemplateOther）  对抽象模板的实现
 * 优点：
 *      ·封装不变部分，扩展可变部分
 *      ·提取公共部分，便于维护
 *      ·行为由父类控制，子类实现，便于扩展
 * 使用场景：
 *      ·多个子类有公有的方法，且逻辑基本相同
 *      ·重要，复杂的算法，可以把核心算法设计为模板方法，周边的相关细节功能则由各个子类实现
 *      ·重构时，把相同的代码抽取到父类，然后通过钩子函数约束其行为
 *
 * 钩子方法： 参考模板类中的 hook()方法，外界条件的改变来控制模板方法的执行
 *
 * *注意：为防止恶意操作，一般模板方法都加上final关键字，不允许被重写
 *
 * @see AbstractTemplate 抽象模板
 * @see Template 具体模板
 * @see TemplateOther 具体模板
 * Created by pc on 2018/2/8.
 * @author zz_chong
 */
public class TemplateMethodPatten {

    private static final String TRUE = "1";

    public static void main(String[] args) throws IOException {
      Template template = new Template();
      TemplateOther otherTemplate = new TemplateOther();
      /*
        通过外界条件改变来控制模板方法的执行
       */
      System.out.println("otherTemplate 是否执行doAnything? 0-不需要 1-需要");
      String type = (new BufferedReader(new InputStreamReader(System.in))).readLine();
      if(TRUE.equals(type)){
          otherTemplate.setDoAnythingFlag(true);
      }
      template.templateMethod();
      otherTemplate.templateMethod();
    }
}
