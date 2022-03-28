package ch.so.agi.oereb.cts.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckVarsBuilder {
    private final String checkClass;
    
    private Map<String, Parameter> parameters = new HashMap<>();
    
    public CheckVarsBuilder(String checkClass) {
        this.checkClass = checkClass;
    }
    
    public CheckVarsBuilder addParameter(Parameter parameter) {
        parameters.put(parameter.name(), parameter);
        return this;
    }
    
    public CheckVars build() {
        return new CheckVars(checkClass, parameters);
    }
}
