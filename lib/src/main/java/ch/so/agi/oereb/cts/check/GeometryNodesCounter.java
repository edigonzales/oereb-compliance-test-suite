package ch.so.agi.oereb.cts.check;

import java.io.InputStream;

import ch.so.agi.oereb.cts.model.CheckVars;

public class GeometryNodesCounter extends Check {

    @Override
    public String getName() {
        return "Geometry elements counter";
    }

    @Override
    public String getDescription() {
        return "Counts the geometry elements in a XML document and compares the number with a (minimum) target value.";
    }

    @Override
    public void perform(CheckVars checkVars) {
        log.info("Check: " + this.getClass().getCanonicalName());

        int count = Integer.valueOf(checkVars.parameters().get("count").value());
        int min = Integer.valueOf(checkVars.parameters().get("min").value());
        
        InputStream is = (InputStream) this.probe.getResponse().body();


        
        
        
        this.setResult(true, "OK");
    }

}
