package jdk8;

/**
 * Created by pc on 2017/8/1.
 *  Method and Constructor References
 */
@FunctionalInterface
public interface PersonFactory<P extends Person> {

    P create(String firstName,String lastName);
}
