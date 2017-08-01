package jdk8;

/**
 * Created by pc on 2017/8/1.
 */
public class ValueAbsentException extends Throwable {

    public ValueAbsentException() {
        super();
    }

    public ValueAbsentException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "no value present in the Optional instance";
    }
}
