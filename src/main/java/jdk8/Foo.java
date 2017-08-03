package jdk8;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2017/8/3.
 */
public class Foo {

    String name;
    List<Bar> bars = new ArrayList<>();

    public Foo(String name) {
        this.name = name;
    }

    class  Bar {
        String name;

        public Bar(String name) {
            this.name = name;
        }
    }
}
