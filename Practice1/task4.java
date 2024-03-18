import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class task4 {
    private static final ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();
    private static final AtomicInteger maxQueueLength = new AtomicInteger(0);
    private static final Object mrtLock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread patientGenerator = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    Thread.sleep(300 + (int) (Math.random() * 300));
                    queue.add(1);
                    maxQueueLength.updateAndGet(x -> Math.max(x, queue.size()));
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread therapist = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    if (!queue.isEmpty()) {
                        queue.poll();
                        Thread.sleep((long) (Math.random() * 1000));
                        synchronized (mrtLock) {
                            Thread.sleep((long) (Math.random() * 1000));
                        }
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        patientGenerator.start();
        therapist.start();

        Thread.sleep(10000);

        patientGenerator.interrupt();
        therapist.interrupt();

        patientGenerator.join();
        therapist.join();

        System.out.println("Максимальная длина очереди: " + maxQueueLength.get());
    }
}

