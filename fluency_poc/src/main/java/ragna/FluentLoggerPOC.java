package ragna;

import org.fluentd.logger.FluentLogger;

import java.util.HashMap;
import java.util.Map;

public class FluentLoggerPOC {
    private static FluentLogger LOG = FluentLogger.getLogger ("test.fluentlogger");

    public static void main(String[] args) {


        Map<String, Object> data = new HashMap<> ();
        data.put("from", "userA");
        data.put("to", "userB");
        LOG.log("follow", data);


        System.out.println ("OK");

    }
}
