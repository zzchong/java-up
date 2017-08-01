package jdk8;

import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.logging.Logger;

/**
 * Created by pc on 2017/8/1.
 * 1 接口允许设置默认方法  out of box
 */
public class Index {

    private Logger logger = Logger.getLogger(Index.class.getName());


    @Test
    public void DefaultInterfaceTest(){

        DefaultInterface defaultInterface = ()-> logger.info("do something");

        defaultInterface.doSomething();
        logger.info(defaultInterface.defaultDoSomething());
    }


    @Test
    public void LambdaExpressionsTest(){

        List<String> names = Arrays.asList("1","2");

        //jdk7
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });

        //jdk8  one
        //自然顺序相反
        names.sort(Comparator.reverseOrder());
        logger.info(Arrays.toString(names.toArray()));

        //自然顺序
        names.sort(String::compareTo);
        logger.info(Arrays.toString(names.toArray()));
    }

    @Test
    public void FunctionalInterfaceTest(){

        ZFunctionalInterface functionalInterface = ()-> logger.info("doSomething");
        functionalInterface.doSomething();
        logger.info(functionalInterface.defaultSomething());


        //Method  References

//        ZConverter<String,Integer> converter = Integer::valueOf;
//        logger.info(String.valueOf(converter.convert("123")));

        ZConverter<String,String> converter = Something::startsWith;
        String converted = converter.convert("123");
        logger.info(converted);


       //Constructor References
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("hello","world");
        logger.info(person.toString());

    }

    /**
     * Accessing outer scope variables from lambda expressions is very similar to anonymous objects. You can access final variables from the local outer scope as well as instance fields and static variables.
     * 作用范围
     */
    @Test
    public void LambdaScopesTest(){

        /**
         * accessing local variables
         * 不用定义final类型  在 lambda expressions 中也可以获取到
         */
//        final  int num = 1;
        int num = 1;
        ZConverter<Integer,String> converter = form -> String.valueOf(form+num);

        String converted = converter.convert(2);
        logger.info(converted);

        /**
         *  accessing field and static variables
         *  Default methods cannot be accessed from within lambda expressions.
         */

    }

    /**
     * Predicate<T>接口接受一个T类型参数，返回一个boolean值。
     * Predicates are boolean-valued functions of one argument. The interface contains various default methods for composing predicates to complex logical terms (and, or, negate)
     */
    @Test
    public void PredicatesTest(){

        Predicate<String> predicate = x->x.equals("5");
        logger.info(String.valueOf(predicate.test("6")));
        logger.info(String.valueOf(predicate.negate().test("5")));

    }

    /**
     * 接受一定数量的参数 同时提供一个返回结果
     * Functions accept one argument and produce a result. Default methods can be used to chain multiple functions together (compose, andThen).
     */
    @Test
    public void FunctionsTest(){

        Function<String,Integer> toInteger = Integer::valueOf;
        Function<String,String> backToString = toInteger.andThen(String::valueOf);
        logger.info(String.valueOf(toInteger.apply("123")));
        logger.info(String.valueOf(backToString.apply("123")));

    }

    /**
     * 不提供参数,获取一种类型的返回结果
     * Suppliers produce a result of a given generic type. Unlike Functions, Suppliers don't accept arguments.
     */
    @Test
    public void SupplierTest(){
        Supplier<Person> personSupplier = Person::new;
        logger.info(personSupplier.get().toString());

    }

    /**
     * Consumer接口主要是处理参数不提供返回结果的函数式接口
     */
    @Test
    public void ConsumerTest(){

        Consumer<Person> personConsumer = person -> logger.info(person.toString());

        PersonFactory personFactory = Person::new;
        personConsumer.accept(personFactory.create("hello","world"));
    }

    /**
     * Comparators are well known from older versions of Java. Java 8 adds various default methods to the interface
     */
    @Test
    public void ComparatorsTest(){

        PersonFactory personFactory = Person::new;

        Comparator<Person> personComparator = (p1,p2)->p2.getFirstName().compareTo(p1.getFirstName());
        Comparator<Person> personComparatorObs = Comparator.comparing(Person::getFirstName);
        logger.info(String.valueOf(personComparator.compare(personFactory.create("hello","world"),personFactory.create("world","hello"))));
        logger.info(String.valueOf(personComparator.reversed().compare(personFactory.create("hello","world"),personFactory.create("world","hello"))));
        logger.info(String.valueOf(personComparatorObs.compare(personFactory.create("hello","world"),personFactory.create("world","hello"))));
    }


    /**
     * 这是一个可以为null的容器对象。如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象。
     *  Optional is a simple container for a value which may be null or non-null. Think of a method which may return a non-null result but sometimes return nothing. Instead of returning null you return an Optional in Java 8.
     */
    @Test
    public void OptionalTest(){

        /**
         * 1 of 为非null的值创建一个Optional。
         * 2 ofNullable 为指定的值创建一个Optional，如果指定的值为null，则返回一个空的Optional。
         * 3 isPresent  如果值存在返回true，否则返回false。
         * 4 get  如果Optional有值则将其返回，否则抛出NoSuchElementException
         * 5 ifPresent 如果Optional实例有值则为其调用consumer，否则不做处理
         * 6 orElse 如果有值则将其返回，否则返回指定的其它值。
         * 7 orElseGet orElseGet与orElse方法类似，区别在于得到的默认值。orElse方法将传入的字符串作为默认值，orElseGet方法可以接受Supplier接口的实现用来生成默认值
         * 8 orElseThrow ，在orElseThrow中我们可以传入一个lambda表达式或方法，如果值不存在来抛出异常
         * 9 map 如果有值，则对其执行调用mapping函数得到返回值。如果返回值不为null，则创建包含mapping返回值的Optional作为map方法返回值，否则返回空Optional。
         * 10 flatMap 如果有值，为其执行mapping函数返回Optional类型返回值，否则返回空Optional。flatMap与map（Funtion）方法类似，区别在于flatMap中的mapper返回值必须是Optional。调用结束时，flatMap不会对结果用Optional封装。
         * 11 filter filter个方法通过传入限定条件对Optional实例的值进行过滤 如果有值并且满足断言条件返回包含该值的Optional，否则返回空Optional
         */
        //1
        Optional<String> optional = Optional.of("bam");

        //2
        Optional<String> empty = Optional.ofNullable(null);

        //3
        logger.info(String.valueOf(optional.isPresent()));
        logger.info(String.valueOf(empty.isPresent()));

        //4
        logger.info(String.valueOf(optional.get()));
        try {
            logger.info(String.valueOf(empty.get()));
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }

        //5
        optional.ifPresent(value->logger.info("value is "+value+",length is "+value.length()));

        //6
        logger.info(optional.orElse("optional is null"));
        logger.info(empty.orElse("empty is null"));

        //7
        logger.info(optional.orElseGet(()->"optional is null"));
        logger.info(empty.orElseGet(()->"empty is null"));

        //8
        try {
           optional.orElseThrow(ValueAbsentException::new);
           empty.orElseThrow(ValueAbsentException::new);
        }catch (ValueAbsentException e){
            logger.info(e.getMessage());
        }

        //9
        Optional<String> mapString = optional.map(String::toUpperCase);
        logger.info(mapString.orElse("no value found"));
        logger.info(mapString.map(String::toLowerCase).orElse("no value found"));

        //10
        Optional<String> flatMapString = optional.flatMap(s -> Optional.of(s.toLowerCase()));
        logger.info(flatMapString.orElse("no value found"));

        //11
        logger.info(optional.filter(value->value.length()>6).orElse("this value is less than 6 characters"));
    }

    @Test
    public void StreamsTest(){


    }

    @Test
    public void FilterTest(){


    }

    @Test
    public void SortedTest(){


    }

    @Test
    public void MapTest(){


    }

    @Test
    public void MatchTest(){


    }
}
