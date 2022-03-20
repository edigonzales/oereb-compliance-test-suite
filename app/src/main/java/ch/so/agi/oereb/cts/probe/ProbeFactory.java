package ch.so.agi.oereb.cts.probe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProbeFactory {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public static Probe getProbe(String probeType) {
        if (probeType == null) {
            return null;
        } else if (probeType.equalsIgnoreCase("ch.so.agi.oereb.cts.probe.GetEGRID")) {
            return new ch.so.agi.oereb.cts.probe.GetEGRID();
        }
//        } else if (probeType.equalsIgnoreCase("ch.so.agi.healthcheck.probe.WmsGetCaps")) {
//            return new ch.so.agi.healthcheck.probe.WmsGetCaps();
//        } else if (probeType.equalsIgnoreCase("ch.so.agi.healthcheck.probe.WmsGetMap")) {
//            return new ch.so.agi.healthcheck.probe.WmsGetMap();
//        } else if (probeType.equalsIgnoreCase("ch.so.agi.healthcheck.probe.WmsGetMapAll")) {
//            return new ch.so.agi.healthcheck.probe.WmsGetMapAll();
//        } else if (probeType.equalsIgnoreCase("ch.so.agi.healthcheck.probe.OerebV1GetEgridByCoord")) {
//            return new ch.so.agi.healthcheck.probe.OerebV1GetEgridByCoord();
//        } else if (probeType.equalsIgnoreCase("ch.so.agi.healthcheck.probe.HttpGetQuery")) {
//            return new ch.so.agi.healthcheck.probe.HttpGetQuery();
//        } else if (probeType.equalsIgnoreCase("ch.so.agi.healthcheck.probe.HttpGetPath")) {
//            return new ch.so.agi.healthcheck.probe.HttpGetPath();
//        } else if (probeType.equalsIgnoreCase("ch.so.agi.healthcheck.probe.HttpGet")) {
//            return new ch.so.agi.healthcheck.probe.HttpGet();
//        } else if (probeType.equalsIgnoreCase("ch.so.agi.healthcheck.probe.IliRepoGet")) {
//            return new ch.so.agi.healthcheck.probe.IliRepoGet();
//        } 
        return null;
    }
    
}
