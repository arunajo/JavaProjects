import java.util.concurrent.CopyOnWriteArrayList;

public class Writer implements Runnable {
    private CopyOnWriteArrayList<Integer> listOfNumbers;

    public Writer(CopyOnWriteArrayList<Integer> listOfNumbers) {
        this.listOfNumbers = listOfNumbers;
    }

    public void run() {
        try {
            int numberToAdd = 1;
            while (true) {
                Thread.sleep(1000);
                listOfNumbers.add(numberToAdd++);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
