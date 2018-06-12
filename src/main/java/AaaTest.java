/**
 * Created by pc on 2018/4/3.
 */
public class AaaTest {

    public static void main(String[] args) throws InterruptedException {
        for (int i=0;i<100000;i++) {
            Object obj = new Object();
            Ttt ttt = new Ttt(obj);
            ttt.start();
            synchronized (obj) {
                System.out.println("等待-----"+i);
                obj.wait();
            }
            System.out.println("wait先执行，程序通过-----"+i);
        }
    }
    static class Ttt extends Thread{
        Object obj;
        Ttt(Object obj){
            this.obj=obj;
        }
        public void run() {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized(obj){
                obj.notify();
            }
        }
    }
}
