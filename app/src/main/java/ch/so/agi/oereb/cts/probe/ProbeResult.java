package ch.so.agi.oereb.cts.probe;

import ch.so.agi.oereb.cts.Result;

public class ProbeResult extends Result {
    private OerebRequest probe;
    
    public ProbeResult(OerebRequest oerebRequest) {
        this.probe = oerebRequest;
        this.className = oerebRequest.getClass().getCanonicalName();
    }
}
