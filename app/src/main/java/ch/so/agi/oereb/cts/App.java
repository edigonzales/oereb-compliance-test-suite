package ch.so.agi.oereb.cts;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import ch.so.agi.oereb.cts.check.CheckFactory;
import ch.so.agi.oereb.cts.probe.GetEGRID;
import ch.so.agi.oereb.cts.probe.IProbe;
import ch.so.agi.oereb.cts.probe.ProbeFactory;
import ch.so.geo.schema.agi.oereb_cts.Suite;
import ch.so.geo.schema.agi.oereb_cts.XmlSuite;
import ch.so.geo.schema.agi.oereb_cts.XmlCheck;
import ch.so.geo.schema.agi.oereb_cts.XmlProbe;

public class App {

    public static void main(String[] args) throws IOException, InterruptedException, JAXBException {
        
        JAXBContext jaxbContext = JAXBContext.newInstance(Suite.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        jaxbUnmarshaller.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());

        File suiteFile = new File("src/test/data/suite.xml");
        XmlSuite suite = (XmlSuite) jaxbUnmarshaller.unmarshal(suiteFile);
         
        System.out.println(suite.getProbes().get(0).getChecks().size());

        XmlProbe xmlProbe = suite.getProbes().get(0);
        IProbe probe = ProbeFactory.getProbe(xmlProbe.getClassName());
        System.out.println(probe.getClass());
        
        // mmmh, irgendwie muss ich die Parameter bei den Probes abhandeln .
        // oder notfalls direkte Url? Dann ist aber resource für nix.
        
        
        
        XmlCheck xmlCheck = xmlProbe.getChecks().get(0);
        
        
        CheckFactory.getCheck(xmlCheck.getClassName());

        
        
//        String baseUrl = "https://prozessor-oereb.ur.ch/oereb/";
//        String eastNorthCoord = "2690481.2,1195464.8";
//        String gnssCoord = "46.90413,8.62621";

//        GetEgrid getEgrid = new GetEgrid(baseUrl + "getegrid/xml/?en=" + eastNorthCoord);
        //GetEgrid getEgrid = new GetEgrid("https://", checksVars);
//        getEgrid.run();
        //getEgrid.validate();
        
        
        System.out.println("Hallo Welt");
    }
}
