package fr.univrouen.rss22.services;

import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

@Service
public class AService {
    public boolean valid(File file, String xmlString){
        Source xml=new StreamSource(new StringReader(xmlString));
        SchemaFactory schemaFactory=SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema=schemaFactory.newSchema(file);
            Validator validator=schema.newValidator();
            validator.validate(xml);
            System.out.println("IS VALID");
        } catch (IOException | SAXException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
