import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Cyclist implements Runnable {

    private final String name;
    private final CyclicBarrier cyclicBarrier1, cyclicBarrier2, cyclicBarrier3;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public Cyclist(String name, CyclicBarrier cyclicBarrier1, CyclicBarrier cyclicBarrier2, CyclicBarrier cyclicBarrier3) {
        this.name = name;
        this.cyclicBarrier1 = cyclicBarrier1;
        this.cyclicBarrier2 = cyclicBarrier2;
        this.cyclicBarrier3 = cyclicBarrier3;
    }

    @Override
    public void run() {
        try {
            leavingHome();
        } catch (InterruptedException e) {
            System.out.printf("%s ha sido interrumpido mientras salía de su casa\n", name);
            return;
        }
        try {
            cyclicBarrier1.await();
        } catch (InterruptedException e) {
            System.out.printf("%s ha sido interrumpido mientras esperaba a sus amigos en la gasolinera\n", name);
            return;
        } catch (BrokenBarrierException e) {
            System.out.printf("%s no espera más a sus amigos en la gasolinera porque alguno no va a venir\n", name);
        }
        try {
            meetingSale();
        } catch (InterruptedException e) {
            System.out.printf("%s ha sido interrumpido mientras iba a la venta\n", name);
            return;
        }
        try {
            cyclicBarrier2.await();
        } catch (InterruptedException e) {
            System.out.printf("%s ha sido interrumpido mientras esperaba a sus amigos en la venta", name);
            return;
        } catch (BrokenBarrierException e) {
            System.out.printf("%s no espera más a sus amigos en la venta porque alguno no va a venir\n", name);
        }
        try {
            backToGasStation();
        } catch (InterruptedException e) {
            System.out.printf("%s ha sido interrumpido mientras volvía a la gasolinera\n", name);
            return;
        }
        try {
            cyclicBarrier3.await();
        } catch (InterruptedException e) {
            System.out.printf("%s ha sido interrumpido mientras esperaba a sus amigos en la gasolinera", name);
            return;
        } catch (BrokenBarrierException e) {
            System.out.printf("%s no espera más a sus amigos en la gasolinera porque alguno no va a venir\n", name);
        }
        try {
            backHome();
        } catch (InterruptedException e) {
            System.out.printf("%s ha sido interrumpido mientras volvía a su casa desde la gasolinera\n", name);
            return;
        }

    }

    private void leavingHome() throws InterruptedException {
        System.out.printf("%s - %s sale de su casa\n",
                LocalTime.now().format(dateTimeFormatter), name);
        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(3) + 1);
        System.out.printf("%s - %s ha llegado a la gasolinera\n",
                LocalTime.now().format(dateTimeFormatter), name);
    }

    private void meetingSale() throws InterruptedException {
        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10) + 5);
        System.out.printf("%s - %s ha llegado a la venta\n",
                LocalTime.now().format(dateTimeFormatter), name);
    }

    private void backToGasStation() throws InterruptedException {
        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10) + 5);
        System.out.printf("%s - %s ha llegado de vuelta a la gasolinera\n",
                LocalTime.now().format(dateTimeFormatter), name);
    }

    private void backHome() throws InterruptedException {
        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(3) + 1);
        System.out.printf("%s - %s ya está en casa\n",
                LocalTime.now().format(dateTimeFormatter), name);
    }


}
