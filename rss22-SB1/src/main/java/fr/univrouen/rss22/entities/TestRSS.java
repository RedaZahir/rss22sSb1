package fr.univrouen.rss22.entities;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.core.io.DefaultResourceLoader;

public class TestRSS {

    public String loadFileXML()  {
        org.springframework.core.io.Resource  resource = new DefaultResourceLoader().getResource("classpath:item.xml");
        InputStream inputStream;
        try {
            inputStream = resource.getInputStream();
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            return "error " + e.getMessage();
        }

    }
}
