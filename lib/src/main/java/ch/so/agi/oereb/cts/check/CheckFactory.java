package ch.so.agi.oereb.cts.check;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckFactory {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public static Check getCheck(String probeCheck) {
        if (probeCheck == null) {
            return null;
//        } else if (probeCheck.equalsIgnoreCase("ch.so.agi.healthcheck.check.HttpStatusNoError")) {
//            return new ch.so.agi.healthcheck.check.HttpStatusNoError();
        } else if (probeCheck.equalsIgnoreCase("ch.so.agi.oereb.cts.check.HttpStatusMatch")) {
            return new ch.so.agi.oereb.cts.check.HttpStatusMatch();
        } else if (probeCheck.equalsIgnoreCase("ch.so.agi.oereb.cts.check.SchemaValidation")) {
            return new ch.so.agi.oereb.cts.check.SchemaValidation();
        } else if (probeCheck.equalsIgnoreCase("ch.so.agi.oereb.cts.check.GeometryNodesCounter")) {
            return new ch.so.agi.oereb.cts.check.GeometryNodesCounter();
        }
        
        return null;
    }
    
}
