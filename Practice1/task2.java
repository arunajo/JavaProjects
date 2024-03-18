import java.util.concurrent.CopyOnWriteArrayList;
public class task2 {

    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> sharedList = new CopyOnWriteArrayList<>();

        Thread readerThread = new Thread(new Reader(sharedList));
        Thread writerThread = new Thread(new Writer(sharedList));

        readerThread.start();
        writerThread.start();
    }
}
