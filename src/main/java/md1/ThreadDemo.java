package md1;

/**
 * Created by liuhang on 2017/6/28.
 */
public class ThreadDemo {
    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        System.out.println("current thread :" + thread);
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println(i);
                Thread.sleep(500);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread t1 = new Thread1();
        t1.start();
        Thread t2 = new Thread(new Thread2());
        t2.start();
    }
}

class Thread1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println("new 1 " + i);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Thread2 implements Runnable {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("new 2 " + i);
        }
    }
}