import java.util.concurrent.CopyOnWriteArrayList;

public class Reader implements Runnable {
    private CopyOnWriteArrayList<Integer> listOfNumbers;

    public Reader(CopyOnWriteArrayList<Integer> listOfNumbers) {
        this.listOfNumbers = listOfNumbers;
    }

    public void run() {
        try {
            while (true) {
                Thread.sleep(500);
                listOfNumbers.forEach(number -> System.out.println(number));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}