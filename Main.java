import java.util.Date;

/**
 * Created by thoma on 12-Apr-17.
 */
public class Main {
    public static void main(String[] args) {
        Buffer<Date> buffer = new BoundedBuffer<>();

        Thread producer = new Thread(new Producer(buffer));
        Thread consumer = new Thread(new Consumer(buffer));

        producer.start();
        consumer.start();
    }
}
