package ch.so.agi.oereb.cts.check;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import ch.so.agi.healthcheck.model.CheckVarsDTO;
import ch.so.agi.oereb.cts.probe.Probe;

public abstract class Check implements ICheck {
    final Logger log = LoggerFactory.getLogger(Check.class);

    protected Probe probe;
    
//    private CheckResult result;
    
    public Check() {
//        result = new CheckResult(this);
//        result.start();
    }
    
    public void setProbe(Probe probe) {
        this.probe = probe;
    }
    
    public void setResult(boolean success, String message) {
//        result.setMessage(message);
//        result.setSuccess(success);
//        result.stop();
    }
    
//    public CheckResult getResult() {
//        return this.result;
//    } 
    
    //public abstract void perform(CheckVarsDTO checkVars) throws IOException;
    public abstract void perform() throws IOException;
}
