package ch.so.agi.oereb.cts.check;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import ch.so.agi.oereb.cts.model.CheckVars;

public class HttpStatusMatch extends Check {
    private String statusCode;

    @Override
    public void perform(CheckVars checkVars) {
        log.info("Check: " + this.getClass().getCanonicalName());
                
        statusCode = checkVars.parameters().get("statusCode").value();

        int serverStatusCode = this.probe.getResponse().statusCode();
        
        if (serverStatusCode == Integer.valueOf(statusCode)) {
            this.setResult(true, "OK");
            return;
        }
        
        this.setResult(false, "HTTP status " + String.valueOf(serverStatusCode) + " does not match expected status " + statusCode);
    }

    @Override
    public String getName() {
        return "HTTP status match.";
    }

    @Override
    public String getDescription() {
        return "Response must match specific HTTP status";
    }
}
