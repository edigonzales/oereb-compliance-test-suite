package ch.so.agi.oereb.cts.check;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import ch.so.agi.oereb.cts.model.CheckVars;

public class SchemaValidation extends Check {
    private String xsdFile;

    @Override
    public String getName() {
        return "XSD schema validation";
    }

    @Override
    public String getDescription() {
        return "Validates a HTTP response against a XSD file";
    }

    @Override
    public void perform(CheckVars checkVars) {
        log.info("Check: " + this.getClass().getCanonicalName());
        
        xsdFile = checkVars.parameters().get("xsd").value();
        
        InputStream is = (InputStream) this.probe.getResponse().body();
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            dbf.setNamespaceAware(true);
            Document doc = dbf.newDocumentBuilder().parse(is);

            Schema schema = schemaFactory.newSchema(getClass().getClassLoader().getResource(xsdFile));
            Validator validator = schema.newValidator();
                        
            final List<SAXParseException> exceptions = new LinkedList<SAXParseException>();
            validator.setErrorHandler(new ErrorHandler() {
                @Override
                public void warning(SAXParseException exception) throws SAXException {
                    exceptions.add(exception);
                }

                @Override
                public void fatalError(SAXParseException exception) throws SAXException {
                    exceptions.add(exception);
                }

                @Override
                public void error(SAXParseException exception) throws SAXException {
                    exceptions.add(exception);
                }
            });

            validator.validate(new DOMSource(doc));
            
            if (exceptions.size() == 0) {
                this.setResult(true, "OK");
                return;
            }

            String errorMessage = new String();
            for(SAXParseException exception : exceptions) {
                errorMessage += exception.getMessage() + "\n";
            }
            this.setResult(false, errorMessage);
            
        } catch (SAXException | ParserConfigurationException | IOException e) {
            this.setResult(false, e.getMessage());
            return;
        }
    }
}
