package ragna;

import org.komamitsu.fluency.Fluency;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FluencyPoc {
    public static void main(String[] args) throws IOException, InterruptedException {

        Fluency.Config config = new Fluency.Config ()
                .setFileBackupDir ("/tmp/fluencyapi")
                .setWaitUntilBufferFlushed (5)
                .setWaitUntilFlusherTerminated (10);


        // localhost 24224
        Fluency fluency = Fluency.defaultFluency (config);

        String tag = "test.tag.fluency";


        //IntStream.range(1,10).forEach (i -> {
            Map<String, Object> event = new HashMap<> ();

            event.put("name", "komamitsu");
            event.put("age", 42);
            event.put ("rate", 3.14);

            // OK
            try {
                fluency.emit (tag, event);
            } catch (IOException e) {
                e.printStackTrace ();
            }
        //});

        // ERROR
        //EventTime eventTime = EventTime.fromEpochMilli (System.currentTimeMillis ());
        //fluency.emit (tag, eventTime, event);

        // ERROR
        //long now = System.currentTimeMillis ();
        //EventTime eventTime = EventTime.fromEpoch ((int) now /1000, 999999999);
        //fluency.emit (tag, eventTime, event);


        fluency.flush ();


        try {
            Thread.sleep (3000);
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }

    }
}
