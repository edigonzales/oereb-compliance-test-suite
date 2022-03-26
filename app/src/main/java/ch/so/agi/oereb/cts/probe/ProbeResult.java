package ch.so.agi.oereb.cts.probe;

import ch.so.agi.oereb.cts.Result;

public class ProbeResult extends Result {
    private Probe probe;
    
    public ProbeResult(Probe oerebRequest) {
        this.probe = oerebRequest;
        this.className = oerebRequest.getClass().getCanonicalName();
    }
}
