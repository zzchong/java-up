package jdk8;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    /**
     * A java.util.Stream represents a sequence of elements on which one or more operations can be performed.
     * Stream operations are either intermediate or terminal.
     * While terminal operations return a result of a certain type,
     * intermediate operations return the stream itself
     * so you can chain multiple method calls in a row.
     * Streams are created on a source,
     * e.g. a java.util.Collection like lists or sets (maps are not supported).
     * Stream operations can either be executed sequentially or parallely.
     */
    @Test
    public void StreamsTest(){

        /**
         *  How streams work
         */
        List<String> list = Arrays.asList("a","b","c");
        list
                .stream()
                .filter(s -> s.equals("a"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);

        list
                .stream()
                .findFirst()
                .ifPresent(System.out::println);

        /**
         * we don't have to create collections in order to work with streams as we see in the next code sample
         * just use Stream.of() to create a stream from a bunch of object references.
         */
        Stream.of("a","b","c")
                .findFirst()
                .ifPresent(System.out::println);

        /**
         * Besides regular object streams Java 8 ships with special kinds of streams for working with the primitive data types int, long and double.
         * As you might have guessed it's IntStream, LongStream and DoubleStream.
         *
         * intermediate operations will only be executed when a terminal operation is present
         *
         * 执行顺序 one by one
         */

        IntStream
                .range(1,10)
                .forEach(System.out::println);

        IntStream
                .of(1,2,3)
                .map(operand -> 2*operand+1)
                .filter(n->n>4)
                .average()
                .ifPresent(System.out::println);

        Stream
                .of("a1","a2","a3")
                .map(s -> s.substring(1))
                .mapToInt(Integer::parseInt)
                .max()
                .ifPresent(System.out::println);


        /**
         * The operation anyMatch returns true as soon as the predicate applies to the given input element.
         * This is true for the second element passed "A2".
         * Due to the vertical execution of the stream chain,
         * map has only to be executed twice in this case.
         * So instead of mapping all elements of the stream, map will be called as few as possible.
         */
        Stream
                .of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .anyMatch(s -> {
                    System.out.println("anyMatch: " + s);
                    return s.startsWith("A");
                });

        /**
         * sorted排序 后可能会reduce操作次数
         */

        Stream
                .of("d2", "a2", "b1", "b3", "c")
                .filter(s -> s.startsWith("a"))
                .sorted(String::compareTo)
                .map(String::toUpperCase)
                .forEach(System.out::println);

        /**
         * Java 8 streams cannot be reused. As soon as you call any terminal operation the stream is closed:
         * To overcome this limitation we have to to create a new stream chain for every terminal operation we want to execute,
         * e.g. we could create a stream supplier to construct a new stream with all intermediate operations already set up:
         */
        Supplier<Stream<String>> streamSupplier = ()->Stream.of("d2","a2","b1","b3","c").filter(s -> s.startsWith("a"));
        streamSupplier.get().anyMatch(s -> true);
        streamSupplier.get().noneMatch(s->true);

        /**
         *  Collect
         *      Collect is an extremely useful terminal operation to transform the elements of the stream into a different kind of result, e.g. a List, Set or Map
         *      Collect accepts a Collector which consists of four different operations: a supplier, an accumulator, a combiner and a finisher
         */

        PersonFactory<Person> personFactory = Person::new;
        List<Person> persons = Arrays.asList(
                personFactory.create("a","a1"),
                personFactory.create("b","b1"),
                personFactory.create("c","c1"),
                personFactory.create("d","d1")
        );

        List<Person> collect =
                persons
                  .stream()
                  .filter(p->p.getFirstName().startsWith("a"))
                  .collect(Collectors.toList());
        logger.info(collect.toString());

        /**
         * The next example groups all persons by firstName:
         */
        Map<String,List<Person>> personsByAge = persons
                .stream()
                .collect(Collectors.groupingBy(Person::getFirstName));

        logger.info(personsByAge.toString());

        /**
         *  Joining
         *  the join collector accepts a delimiter as well as an optional prefix and suffix.
         */
        String phrase = persons
                .stream()
                .filter(person -> person.getLastName().contains("1"))
                .map(Person::getFirstName)
                .collect(Collectors.joining("  and  ","In Germany "," are of sb"));
        logger.info(phrase);

        /**
         *  List to Map
         * @param key Function
         * @param value Function
         * @param  merger Function
         */
        Map<String,String> map = persons
                .stream()
                .collect(Collectors.toMap(Person::getFirstName, Person::getLastName, (value1,value2)->value1+";"+value1));
        logger.info(map.toString());

        /**
         * collector: a supplier, an accumulator, a combiner and a finisher
         * Collector主要定义了容器的类型，添加元素的方法，容器合并的方法还有输出的结果
         * characteristics是定义容器的三个属性，包括是否有明确的finisher，是否需要同步，是否有序
         * https://my.oschina.net/joshuashaw/blog/487322
         */

        Collector<Person,StringJoiner,String> personStringJoinerStringCollector =
                Collector.of(
                        ()->new StringJoiner("|"),
                        ((stringJoiner, person) -> stringJoiner.add(person.getFirstName())),
                        (StringJoiner::merge),
                        StringJoiner::toString
                );

        String names = persons
                .stream()
                .collect(personStringJoinerStringCollector);
        logger.info(names);

        /**
         * FlatMap transforms each element of the stream into a stream of other objects. So each object will be transformed into zero, one or multiple other objects backed by streams
         */
        List<Foo> foos = new ArrayList<>();
        IntStream
                .range(1,4)
                .forEach(i->foos.add(new Foo("Foo"+i)));

        foos
                .forEach(
                        foo -> IntStream
                        .range(1,4)
                        .forEach(i->foo.bars.add(foo.new Bar("Bar"+i+" <- "+foo.name)))
                );

        foos
                .stream()
                .flatMap(foo -> foo.bars.stream())
                .forEach(v->logger.info(v.name));

        /**
         *  the above code example can be simplified into a single pipeline of stream operations
         *
         *  FlatMap is also available for the Optional class introduced in Java 8. Optionals flatMap operation returns an optional object of another type. So it can be utilized to prevent nasty null checks.
         *  用于Optional 可以避免null异常
         */
        IntStream
                .range(1,4)
                .mapToObj(i->new Foo("Foo"+i))
                .peek(
                        foo ->
                                IntStream
                                        .range(1,4)
                                        .forEach(i->foo.bars.add(foo.new Bar("Bar"+i+" <- "+foo.name)))
                )
                .flatMap(foo -> foo.bars.stream())
                .forEach(bar -> logger.info(bar.name));

        /**
         * Reduce
         * This terminal operation performs a reduction on the elements of the stream with the given function. The result is an Optional holding the reduced value
         * The reduction operation combines all elements of the stream into a single result.
         * Java 8 supports three different kind of reduce methods.
         * http://blog.csdn.net/io_field/article/details/54971679
         * The first one reduces a stream of elements to exactly one element of the stream
         */
        persons
                .stream()
                .reduce((person, person2) -> person.getFirstName().equals(person2.getFirstName())?person:person2)
                .ifPresent(System.out::println);

        /**
         * The second reduce method accepts both an identity value and a BinaryOperator accumulato
         */


        /**
         *  Parallel streams use a common ForkJoinPool available via the static ForkJoinPool.commonPool() method.
         */
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        logger.info("thread-pool is "+commonPool.getParallelism());

        /**
         * It seems that sort is executed sequentially on the main thread only. Actually, sort on a parallel stream uses the new Java 8 method Arrays.parallelSort() under the hood
         */
        Arrays.asList("a1", "a2", "b1", "c2", "c1")
                .parallelStream()
                .filter(s -> {
                    logger.info(s+"--filter---"+Thread.currentThread().getName());
                    return true;
                })
                .map(s -> {
                    logger.info(s+"------map--"+Thread.currentThread().getName());
                    return  s.toUpperCase();
                })
                .sorted(
                        (s1,s2)->{
                            logger.info("s1="+s1+"-------s2="+s2+"------sorted--"+Thread.currentThread().getName());
                                    return s1.compareTo(s2);
                        }

                )
                .forEach(
                        s ->  logger.info(s+"------forEach--"+Thread.currentThread().getName())
                );
        /**
         * In summary, it can be stated that parallel streams can bring be a nice performance boost to streams with a large amount of input elements.
         * But keep in mind that some parallel stream operations like reduce and collect need additional computations (combine operations) which isn't needed when executed sequentially.
         */
    }

    /**
     * As already mentioned maps do not directly support streams.
     * There's no stream() method available on the Map interface itself,
     * however you can create specialized streams upon the keys, values or entries of a map via map.keySet().stream(), map.values().stream() and map.entrySet().stream().
     */
    @Test
    public void MapsTest(){

        Map<Integer,String> map = new HashMap<>();
        for (int i=0;i<10;i++){
            map.putIfAbsent(i,"val"+i);
        }
        map.forEach((integer, s) -> logger.info(integer+","+s));

        map.computeIfPresent(3,(integer, s) ->{ logger.info(integer+","+s);return integer+s;});
        map.get(3);

        map.computeIfAbsent(3,integer -> "bam");
        logger.info(map.get(3));

        map.remove(3,"val3");
        logger.info(map.get(3));

        map.remove(3,"3val3");
        logger.info(map.get(3));

        map.computeIfAbsent(3,integer -> "bam");
        logger.info(map.get(3));

        map.computeIfPresent(3,(integer, s) -> null);
        logger.info(map.get(3));

        logger.info(map.getOrDefault(50,"defaultValue"));

        /**
         * Merge either put the key/value into the map if no entry for the key exists, or the merging function will be called to change the existing value.
         */
        map.computeIfAbsent(3,integer -> "bam");
        map.merge(3,"san", String::concat);
        logger.info(map.get(3));


    }


    @Test
    public void DateAPITest(){

        /**
         * Clock provides access to the current date and time
         * Clocks are aware of a timezone and may be used instead of System.currentTimeMillis() to retrieve the current time in milliseconds since Unix EPOCH
         * Instants can be used to create legacy java.util.Date objects
         */

        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();

        System.out.println(millis);

        Instant instant = clock.instant();
        Date legacyDate = Date.from(instant);

        System.out.println(legacyDate);

        /**
         * Timezones  Timezones are represented by a ZoneId
         * Timezones define the offsets which are important to convert between instants and local dates and times.
         */

        /**
         * prints all available timezone ids
         */
        System.out.println(ZoneId.getAvailableZoneIds());

        ZoneId zoneId1 = ZoneId.of("Asia/Aden");
        ZoneId zoneId2 = ZoneId.of("America/Marigot");
        System.out.println(zoneId1.getRules());
        System.out.println(zoneId2.getRules());

        /**
         * LocalTime ocalTime represents a time without a timezone e.g. 10pm or 17:30:15
         */
        LocalTime now1 = LocalTime.now(zoneId1);
        LocalTime now2 = LocalTime.now(zoneId2);

        System.out.println(now1.isBefore(now2));

        long hoursBetween = ChronoUnit.HOURS.between(now1,now2);
        long minutesBetween  = ChronoUnit.MINUTES.between(now1,now2);

        System.out.println(hoursBetween);
        System.out.println(minutesBetween);

        LocalTime late = LocalTime.of(23,59,59);
        System.out.println(late);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.GERMAN);

        LocalTime leetTime = LocalTime.parse("13:37",dateTimeFormatter);
        System.out.println(leetTime);

        /**
         * LocalDate LocalDate represents a distinct date, e.g. 2014-03-11
         */

        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plus(1,ChronoUnit.DAYS);
        LocalDate yestoday = tomorrow.minusDays(2);

        System.out.println(today);
        System.out.println(tomorrow);
        System.out.println(yestoday);

        LocalDate independenceDay = LocalDate.of(2017,Month.AUGUST,3);
        DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
        System.out.println(dayOfWeek);

        /**
         * Parsing a LocalDate from a string is just as simple as parsing a LocalTime:
         */
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(Locale.GERMAN);
        LocalDate xmas = LocalDate.parse("24.12.2017",dateTimeFormatter1);
        System.out.println(xmas);

        /**
         * LocalDateTime LocalDateTime represents a date-time. It combines date and time as seen in the above sections into one instance
         */

        LocalDateTime sylvester = LocalDateTime.of(2017,Month.AUGUST,3,16,1,25);
        DayOfWeek dayOfWeek1 = sylvester.getDayOfWeek();
        System.out.println(dayOfWeek1);

        Month month = sylvester.getMonth();
        System.out.println(month);

        long minuteOfDay = sylvester.getLong(ChronoField.MINUTE_OF_DAY);
        System.out.println(minuteOfDay);

        Instant instant1 = sylvester.atZone(ZoneId.systemDefault()).toInstant();
        Date legacyDate1 = Date.from(instant1);
        System.out.println(legacyDate1);

        /**
         * Formatting date-times works just like formatting dates or times. Instead of using pre-defined formats we can create formatters from custom patterns.
         */

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd hh:mm:ss");


//        LocalDateTime parsed =LocalDateTime.parse("2017-8-3 11:00:00",formatter);
//        String str = formatter.format(parsed);
        System.out.println(LocalDateTime.now().format(formatter));
    }

    @Test
    public void annotationsTest(){

        Hint hint = HintTest.class.getAnnotation(Hint.class);
        System.out.println(hint);

        Hints hints = HintTest.class.getAnnotation(Hints.class);
        System.out.println(hints.value().length);
    }


}
