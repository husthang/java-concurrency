package md1;

/**
 * Created by liuhang on 2017/6/28.
 */
public class Synchronize {
    public static void main(String[] args) {
        CallMe target = new CallMe();
        Caller caller1 = new Caller("hello 1 ", target);
        Caller caller2 = new Caller("hello 2 ", target);
        Caller caller3 = new Caller("hello 3 ", target);
        try {
            caller1.t.join();
            caller2.t.join();
            caller3.t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();

        }


    }
}

class Caller implements Runnable {
    private String msg;
    public Thread t;
    private CallMe target;

    public void run() {
        target.call(msg);
    }

    public Caller(String msg, CallMe target) {
        this.msg = msg;
        this.target = target;
        t = new Thread(this);
        t.start();
    }
}

class CallMe {
   synchronized void call(String msg) {
        System.out.print("[" + msg);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("]");
    }
}