package ch.so.agi.oereb.cts.probe;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ch.so.agi.oereb.cts.model.CheckVars;
import ch.so.agi.oereb.cts.model.CheckVarsBuilder;
import ch.so.agi.oereb.cts.model.Parameter;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProbeTest  {    
    // TODO
    // Momentan nur zum Ausführen von Code...
    @Test 
    public void foo() throws IOException, InterruptedException {
        String baseUrl = "https://prozessor-oereb.ur.ch/oereb/";
        String eastNorthCoord = "2690481.2,1195464.8";
        String gnssCoord = "46.90413,8.62621";
        
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        xmlMapper.registerModule(new JavaTimeModule());

        {
            List<CheckVars> checksVars = new ArrayList<>();
            {
                var checkVars = new CheckVarsBuilder("ch.so.agi.oereb.cts.check.HttpStatusMatch")
                        .addParameter(new Parameter("statusCode", "200")).build();
                checksVars.add(checkVars);
            }
            {
                var checkVars = new CheckVarsBuilder("ch.so.agi.oereb.cts.check.SchemaValidation")
                        .addParameter(new Parameter("xsd", "oereb_v2_0/Extract.xsd")).build();
                checksVars.add(checkVars);
            }

            Probe probe = new Probe(baseUrl + "getegrid/xml/?EN=" + eastNorthCoord, checksVars);
            probe.setDescription("GetEGRID-Request mit EN-Koordinaten ohne Geometrie.");
            probe.run();

            String probeResultXml = xmlMapper.writeValueAsString(probe.getProbeResult());
            System.out.println(probeResultXml);
        }
                
        {
            List<CheckVars> checksVars = new ArrayList<>();
            {
                var checkVars = new CheckVarsBuilder("ch.so.agi.oereb.cts.check.HttpStatusMatch")
                        .addParameter(new Parameter("statusCode", "200")).build();
                checksVars.add(checkVars);
            }
            {
                var checkVars = new CheckVarsBuilder("ch.so.agi.oereb.cts.check.SchemaValidation")
                        .addParameter(new Parameter("xsd", "oereb_v2_0/Extract.xsd")).build();
                checksVars.add(checkVars);
            }
            
            //Probe probe = new Probe(baseUrl + "getegrid/xml/?GEOMETRY=true&EN=" + eastNorthCoord, checksVars);
            Probe probe = new Probe("https://oereb-dev.geo.bl.ch/getegrid/xml/?GEOMETRY=true&EN=2611238.707917088,1254456.3241349652", checksVars);
            //Probe probe = new Probe("https://geo-t.so.ch/api/oereb/getegrid/xml/?EN=2600573,1215488", checksVars);
            probe.setDescription("GetEGRID-Request mit EN-Koordinaten mit Geometrie.");
            probe.run();

            String probeResultXml = xmlMapper.writeValueAsString(probe.getProbeResult());
            System.out.println(probeResultXml);
        }


    }
}
