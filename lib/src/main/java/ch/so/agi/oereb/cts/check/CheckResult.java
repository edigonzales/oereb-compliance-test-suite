package ch.so.agi.oereb.cts.check;

import ch.so.agi.oereb.cts.probe.Result;

public class CheckResult extends Result {
        
    public CheckResult(String checkClass) {
        this.className = checkClass;
    }    
}
