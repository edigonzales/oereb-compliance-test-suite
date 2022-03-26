package ch.so.agi.oereb.cts.check;

import ch.so.agi.oereb.cts.Result;

public class CheckResult extends Result {
    private Check check;
        
    public CheckResult(Check check) {
        this.check = check;
        this.className = check.getClass().getCanonicalName();
    }    
}
