package old;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadExample {

    public static void main(String[] args) throws InterruptedException {
//        Runnable task = () -> {
//            try {
//                System.out.println("SLEEPING: " + Thread.currentThread().getName());
//                TimeUnit.SECONDS.sleep(10);
//                System.out.println("WOKE UP: " + Thread.currentThread().getName());
//            } catch (InterruptedException e) {
//                System.out.println("Interrupted");
//            }
//        };
//        Thread thread = new Thread(task);
//        System.out.println("WAIT JOIN: " + Thread.currentThread().getName());
//        thread.start();
//        thread.join();
//        System.out.println("Finished");

//        Object lock = new Object();
//
//        Runnable task = () -> {
//            synchronized (lock) {
//                System.out.println("Thread: " + Thread.currentThread().getName());
//            }
//        };
//
//        Thread th1 = new Thread(task);
//        th1.start();
//        synchronized (lock) {
//            for (int i = 0; i < 8; i++) {
//                Thread.currentThread().sleep(1000);
//                System.out.print("  " + i + " ");
//                System.out.println(Thread.currentThread().getState());
//            }
//            System.out.println(" ...");
//        }

//        Object lock = new Object();
//        // task будет ждать, пока его не оповестят через lock
//        Runnable task = () -> {
//            synchronized (lock) {
//                try {
//                    System.out.println("Thread: " + Thread.currentThread().getName() + " awaits!");
//                    lock.wait();
//                    System.out.println("Thread: " + Thread.currentThread().getName() + " returns!");
//                } catch (InterruptedException e) {
//                    System.out.println("interrupted");
//                }
//            }
//            // После оповещения нас мы будем ждать, пока сможем взять лок
//            System.out.println("thread");
//        };
//        Thread taskThread = new Thread(task);
//        taskThread.start();
//        // Ждём и после этого забираем себе лок, оповещаем и отдаём лок
//        Thread.sleep(3000);
//        System.out.println("main");
//        synchronized (lock) {
//            lock.notify();
//            System.out.println("Thread: " + Thread.currentThread().getName() + " notified!");
//        }
//
//        Semaphore semaphore = new Semaphore(0);
//        try {
//            semaphore.acquire();
//        } catch (InterruptedException e) {
//            // Просим разрешение и ждём, пока не получим его
//            e.printStackTrace();
//        }
//        System.out.println("Hello, World!");

//        Runnable task = () -> {
//            //Запаркуем текущий поток
//            System.err.println("Will be Parked");
//            LockSupport.park();
//            // Как только нас распаркуют - начнём действовать
//            System.err.println("Unparked");
//        };
//        Thread th = new Thread(task);
//        th.start();
//        Thread.currentThread().sleep(2000);
//        System.err.println("Thread state: " + th.getState());
//
//        LockSupport.unpark(th);
//        Thread.currentThread().sleep(2000);

        Lock lock = new ReentrantLock();
        Runnable task = () -> {
            lock.lock();
            System.out.println("Thread");
            lock.unlock();
        };
        lock.lock();

        Thread th = new Thread(task);
        th.start();
        System.out.println("main");
        Thread.currentThread().sleep(2000);
        lock.unlock();
    }

}
