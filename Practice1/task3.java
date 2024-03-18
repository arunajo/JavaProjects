import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class task3 implements Runnable {
    private static final AtomicInteger totalYoung = new AtomicInteger(0);
    private static final AtomicInteger totalElderly = new AtomicInteger(0);
    private static final AtomicInteger totalBusinessmen = new AtomicInteger(0);
    private static final AtomicInteger leftYoung = new AtomicInteger(0);
    private static final AtomicInteger leftElderly = new AtomicInteger(0);
    private static final AtomicInteger leftBusinessmen = new AtomicInteger(0);
    private final int category; // 1 - молодые, 2 - пожилые, 3 - бизнесмены
    private final AtomicBoolean[] windows;

    public task3(int category, AtomicBoolean[] windows) {
        this.category = category;
        this.windows = windows;
        incrementTotal(category);
    }

    private static void incrementTotal(int category) {
        if (category == 1) totalYoung.incrementAndGet();
        else if (category == 2) totalElderly.incrementAndGet();
        else if (category == 3) totalBusinessmen.incrementAndGet();
    }

    private static void incrementLeft(int category) {
        if (category == 1) leftYoung.incrementAndGet();
        else if (category == 2) leftElderly.incrementAndGet();
        else if (category == 3) leftBusinessmen.incrementAndGet();
    }

    @Override
    public void run() {
        int windowIndex = (int) (Math.random() * 3);
        try {
            if (windows[windowIndex].compareAndSet(false, true)) {
                if (windowIndex == 0 || windowIndex == category) {
                    Thread.sleep((long) (Math.random() * 1000));
                } else {
                    incrementLeft(category);
                }
                windows[windowIndex].set(false);
            } else {
                incrementLeft(category);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicBoolean[] windows = new AtomicBoolean[]{new AtomicBoolean(false), new AtomicBoolean(false), new AtomicBoolean(false)};
        Thread[] citizens = new Thread[100];

        for (int i = 0; i < citizens.length; i++) {
            int category = (int) (Math.random() * 3) + 1;
            citizens[i] = new Thread(new task3(category, windows));
            citizens[i].start();
        }

        for (Thread citizen : citizens) {
            citizen.join();
        }

        System.out.println("Процент ушедших молодых: " + (100.0 * leftYoung.get() / totalYoung.get()) + "%");
        System.out.println("Процент ушедших пожилых: " + (100.0 * leftElderly.get() / totalElderly.get()) + "%");
        System.out.println("Процент ушедших бизнесменов: " + (100.0 * leftBusinessmen.get() / totalBusinessmen.get()) + "%");
    }
}