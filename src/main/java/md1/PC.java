package md1;

/**
 * Created by liuhang on 2017/6/28.
 */
public class PC {
    public static void main(String[] args) {
        Q q = new Q();
        Producer producer = new Producer(q);
        Consumer consumer = new Consumer(q);
        System.out.println("press control-d to stop");
    }
}

//生产者
class Producer implements Runnable {
    Q q;

    public Producer(Q q) {
        this.q = q;
        new Thread(this, "Producer").start();
    }

    public void run() {
        int i = 0;
        while (true) {
            q.put(i++);
        }
    }
}

//消费者
class Consumer implements Runnable {
    Q q;

    public void run() {
        int i = 0;
        while (true) {
            q.get();
        }
    }

    public Consumer(Q q) {
        this.q = q;
        new Thread(this, "Consumer").start();
    }
}

class Q {
    int n;
    boolean flag = false;

    synchronized int get() {
        if (!flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Get: " + n);
        flag = false;
        notify();
        return n;
    }

    synchronized void put(int n) {
        if (flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.n = n;
        System.out.println("Put: " + n);
        flag = true;
        notify();
    }


}
