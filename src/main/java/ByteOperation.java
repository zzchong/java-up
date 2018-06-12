/**
 * Created by pc on 2017/7/10.
 * 位运算
 * &(与) 两个操作数中位都为1，结果才为1，否则结果为0
 * |(或) 两个位只要有一个为1，那么结果就是1，否则就为0
 * ~(非) 如果位为0，结果是1，如果位为1，结果是0
 * ^(异或) 两个操作数的位中，相同则结果为0，不同则结果为1
 */
public class ByteOperation {

    public static void main(String[] args)
    {
        try {
            String ss = throwsTest();
            System.out.println("ss="+ss);
        }catch (Exception e){
            System.out.println("sss="+e.getMessage());
        }
        int a=2;
        System.out.println("a 非的结果是："+(~a));

        System.out.println(Integer.parseInt("0001111",2) & 15);
        System.out.println(Integer.parseInt("0011111",2) & 15);
    }

    private static  String throwsTest(){
        try{
            System.out.println("dddd");
            int i = 1/0;
            return  "ddd";
        }
        catch(Exception e){
            throw new RuntimeException("ddddd");
        }
        finally{
            System.out.println("aaaaa");
        }
    }

}
