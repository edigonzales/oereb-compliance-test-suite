package ch.so.agi.oereb.cts.probe;

public class ProbeResult extends Result {
    private Probe probe;
    
    public ProbeResult(Probe oerebRequest) {
        this.probe = oerebRequest;
        this.className = oerebRequest.getClass().getCanonicalName();
    }
}
