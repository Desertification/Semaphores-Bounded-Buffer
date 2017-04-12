import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by thoma on 12-Apr-17.
 */
public class Consumer implements Runnable {
    private Buffer<Date> buffer;

    public Consumer(Buffer<Date> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            SleepUtilities.nap();
            Date date = buffer.remove();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            System.out.println(MessageFormat.format("Consumer consumed: {0}", dateFormat.format(date)));
        }
    }
}
