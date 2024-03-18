public class task1 {
    public static void main(String[] args) {

        MyRunnable instance1 = new MyRunnable();
        MyRunnable instance2 = new MyRunnable();
        MyRunnable instance3 = new MyRunnable();

        Thread thread1 = new Thread(instance1);
        Thread thread2 = new Thread(instance2);
        Thread thread3 = new Thread(instance3);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}