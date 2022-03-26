package ch.so.agi.oereb.cts;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ch.so.agi.oereb.cts.check.CheckFactory;
import ch.so.agi.oereb.cts.model.CheckVars;
import ch.so.agi.oereb.cts.model.CheckVarsBuilder;
import ch.so.agi.oereb.cts.model.Parameter;
import ch.so.agi.oereb.cts.probe.GetEGRID;
import ch.so.agi.oereb.cts.probe.IProbe;
import ch.so.agi.oereb.cts.probe.OerebRequest;
import ch.so.agi.oereb.cts.probe.ProbeFactory;
import ch.so.geo.schema.agi.oereb_cts.Suite;
import ch.so.geo.schema.agi.oereb_cts.XmlSuite;
import ch.so.geo.schema.agi.oereb_cts.XmlCheck;
import ch.so.geo.schema.agi.oereb_cts.XmlProbe;

public class App {

    public static void main(String[] args) throws IOException, InterruptedException, JAXBException {
        
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
                      .addParameter(new Parameter("statusCode", "201"))
                      .build();
              checksVars.add(checkVars);
          }
          {
              var checkVars = new CheckVarsBuilder("ch.so.agi.oereb.cts.check.HttpStatusMatch")
                      .addParameter(new Parameter("statusCode", "204"))
                      .build();
              checksVars.add(checkVars);
          }

          OerebRequest probe = new OerebRequest(baseUrl + "getegrid/xml/?EN=" + eastNorthCoord, checksVars);
          probe.setDescription("fubar");
          probe.run();
          
          String probeResultXml = xmlMapper.writeValueAsString(probe.getProbeResult());
          System.out.println(probeResultXml);
          for (Result result : probe.getProbeResult().getResults()) {
              String xml = xmlMapper.writeValueAsString(result);
              System.out.println(xml);

          }
          
//          System.out.println(probe.getProbeResult().getResults().size());
//          System.out.println(probe.getProbeResult().getMessage());
      }
      
      

      IProbe probe = ProbeFactory.getProbe("ch.so.agi.oereb.cts.probe.GetEGRID");
      //probe.set
        
//        JAXBContext jaxbContext = JAXBContext.newInstance(Suite.class);
//        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//        jaxbUnmarshaller.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
//
//        File suiteFile = new File("src/test/data/suite.xml");
//        XmlSuite suite = (XmlSuite) jaxbUnmarshaller.unmarshal(suiteFile);
//         
//        System.out.println(suite.getProbes().get(0).getChecks().size());
//
//        XmlProbe xmlProbe = suite.getProbes().get(0);
//        IProbe probe = ProbeFactory.getProbe(xmlProbe.getClassName());
//        System.out.println(probe.getClass());
        
        // mmmh, irgendwie muss ich die Parameter bei den Probes abhandeln .
        // oder notfalls direkte Url? Dann ist aber resource für nix.
        
        
        
       // XmlCheck xmlCheck = xmlProbe.getChecks().get(0);
        
        
        //CheckFactory.getCheck(xmlCheck.getClassName());

        
        
//        String baseUrl = "https://prozessor-oereb.ur.ch/oereb/";
//        String eastNorthCoord = "2690481.2,1195464.8";
//        String gnssCoord = "46.90413,8.62621";

//        GetEgrid getEgrid = new GetEgrid(baseUrl + "getegrid/xml/?EN=" + eastNorthCoord);
        //GetEgrid getEgrid = new GetEgrid("https://", checksVars);
//        getEgrid.run();
        //getEgrid.validate();
        
        
        System.out.println("Hallo Welt");
    }
}
