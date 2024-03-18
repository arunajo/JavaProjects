public class MyRunnable implements Runnable {
    public void run() {
        System.out.println("ID потока: " + Thread.currentThread().getId());
    }
}