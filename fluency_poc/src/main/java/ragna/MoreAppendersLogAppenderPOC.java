package ragna;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;

/**
 * Created by ragnarokkrr on 1/8/18.
 */
public class MoreAppendersLogAppenderPOC {
    private static final Logger LOG = LoggerFactory.getLogger(MoreAppendersLogAppenderPOC.class);

    public static void main(String[] args) {
        String appender = System.getenv("FLUENTD_APPENDER");

        System.out.println ("BEGIN: " + appender);


        pause (2000);


        IntStream.range (1,10000).forEach (i -> {
            LOG.info("Test Info message - '" + appender + "' A: " + i);
        });


        pause (10000);


        System.out.println ("OK");
    }

    private static void pause(int i2) {
        try {
            Thread.sleep (i2);
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }
    }

}
