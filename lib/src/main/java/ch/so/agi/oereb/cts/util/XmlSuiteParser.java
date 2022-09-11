package ch.so.agi.oereb.cts.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import ch.so.agi.oereb.cts.model.CheckVars;
import ch.so.agi.oereb.cts.model.Parameter;
import ch.so.agi.oereb.cts.model.ProbeConfig;
import ch.so.geo.schema.agi.oereb_cts.Suite;

public class XmlSuiteParser {
    public static List<ProbeConfig> parse(String suiteFile) throws JAXBException, FileNotFoundException {
        var context = JAXBContext.newInstance(Suite.class);
        var suite = (Suite) context.createUnmarshaller().unmarshal(new FileReader(suiteFile));
        
        String serviceEndpoint = suite.getServiceEndpoint();

        var probesConfig = new ArrayList<ProbeConfig>();

        for (var probe : suite.getProbes()) {
            List<CheckVars> checksVars = new ArrayList<>();
            for (var check : probe.getChecks()) {
                String checkClass = check.getClassName();
                var parameters = new HashMap<String, Parameter>();
                for (var param : check.getParameters()) {
                    var checkParam = new Parameter(param.getName(), param.getValue());
                    parameters.put(checkParam.name(), checkParam);
                }
                var checkVars = new CheckVars(checkClass, parameters);
                checksVars.add(checkVars);
            }
            
            var requestParams = new HashMap<String,String>();
            for (var param : probe.getRequestParameters()) {
                requestParams.put(param.getName(), param.getValue());
            }
            
            var probeConfig = new ProbeConfig(serviceEndpoint, probe.getDescription(), 
                    probe.getRequestTemplate(), requestParams, checksVars);
            probesConfig.add(probeConfig);
        }
        return probesConfig;
    }
}
