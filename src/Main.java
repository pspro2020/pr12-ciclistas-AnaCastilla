import java.util.concurrent.CyclicBarrier;

public class Main {

    private static final int NUMBER_OF_FRIENDS = 10;

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier1 = new CyclicBarrier(NUMBER_OF_FRIENDS, new OpenedBarrierAction1stStage());
        CyclicBarrier cyclicBarrier2 = new CyclicBarrier(NUMBER_OF_FRIENDS, new OpenedBarrierAction2ndStage());
        CyclicBarrier cyclicBarrier3 = new CyclicBarrier(NUMBER_OF_FRIENDS, new OpenedBarrierAction3rdStage());

        for (int i = 0; i < NUMBER_OF_FRIENDS; i++) {
            new Thread(new Cyclist("Ciclista #" + (i+1), cyclicBarrier1, cyclicBarrier2, cyclicBarrier3), "Ciclista #" + (i+1)).start();
        }
    }
}
