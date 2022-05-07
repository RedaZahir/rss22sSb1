package fr.univrouen.rss22.services;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParserFactory;
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
    public boolean valid(String rss22) throws SAXException,IOException {
        try{
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(false);
            factory.setNamespaceAware(true);
            SchemaFactory schemaFactory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            Resource resource = new DefaultResourceLoader().getResource("classpath:item.xsd");

            factory.setSchema(schemaFactory.newSchema(
                    new Source[] {new StreamSource(resource.getInputStream())}));

            javax.xml.validation.Validator validator = factory.getSchema().newValidator();

            validator.validate(new StreamSource(new StringReader(rss22)));
        }  catch (SAXException exp) {
            throw  exp;
        }  catch (IOException exp) {
            throw  exp;
        }

        return true;
    }
    /*public boolean valid(File file, String xmlString){
        Source xml=new StreamSource(new StringReader(xmlString));
        SchemaFactory schemaFactory=SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            System.out.println(xmlString);
            Schema schema=schemaFactory.newSchema(file);
            Validator validator=schema.newValidator();
            validator.validate(xml);
            System.out.println("IS VALID");
        } catch (IOException | SAXException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }*/
}
