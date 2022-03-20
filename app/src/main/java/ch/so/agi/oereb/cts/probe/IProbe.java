package ch.so.agi.oereb.cts.probe;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface IProbe {    
//    public Map<String, String> getRequestHeaders();
    
//    public String getRequestMethod();
    
//    public String getRequestTemplate();
    
    public String getName();
    
    public String setName(String name);
    
    public String getDescription();
    
    public String setDescription(String description);
}
