import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class OpenedBarrierAction1stStage implements Runnable {

    private final DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern("HH:mm:ss", Locale.getDefault());

    @Override
    public void run() {
        System.out.printf("%s - Comienza la etapa (ejecutado en %s)\n",
                LocalTime.now().format(dateTimeFormatter), Thread.currentThread().getName());
    }
}
