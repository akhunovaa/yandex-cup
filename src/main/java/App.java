import java.util.concurrent.atomic.AtomicInteger;

public class App {
//    public static volatile boolean flag = false;
//
//    public static void main(String[] args) throws InterruptedException {
//        Runnable whileFlagFalse = () -> {
//            while (!flag) {
//                System.out.print("-");
//            }
//            System.out.println("");
//            System.out.println("Flag is now TRUE");
//        };
//
//        new Thread(whileFlagFalse).start();
//        Thread.sleep(1000);
//        flag = true;
//    }

    public static int value = 0;
    public static AtomicInteger atomic = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            for (int i = 0; i < 10000; i++) {
                value++;
                atomic.incrementAndGet();
            }
        };
        for (int i = 0; i < 3; i++) {
            new Thread(task).start();
        }
        Thread.sleep(300);
        System.out.println(value);
        System.out.println(atomic.get());
    }
}
