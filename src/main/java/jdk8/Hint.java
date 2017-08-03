package jdk8;

import java.lang.annotation.Repeatable;

/**
 * Created by pc on 2017/8/3.
 */
@Repeatable(Hints.class)
public @interface Hint {
    String value();
}
