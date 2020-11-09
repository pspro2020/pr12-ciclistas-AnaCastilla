import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
public class OpenedBarrierAction2ndStage implements Runnable {

    private final DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern("HH:mm:ss", Locale.getDefault());

    @Override
    public void run() {
        System.out.printf("%s - De vuelta a casa (ejecutado en %s)\n",
                LocalTime.now().format(dateTimeFormatter), Thread.currentThread().getName());
    }
}
