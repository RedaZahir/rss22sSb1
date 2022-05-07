package fr.univrouen.rss22.controllers;

import fr.univrouen.rss22.entities.Item;
import fr.univrouen.rss22.repository.*;
import fr.univrouen.rss22.services.AService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.StringReader;
import java.util.List;

@RestController
@RequestMapping
public class ItemController {
    private static final File file=new File("src/main/resources/xsd/item.xsd");
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private AService aService;


    @GetMapping(value = "/rss/resume/xml",produces = MediaType.APPLICATION_XML_VALUE)
    public List<Item> getXMLItems(){
        return itemRepository.findAll();
    }

    @GetMapping(value = "/rss/resume/xml/{guid}",produces = MediaType.APPLICATION_XML_VALUE)
    public Item getXMLItemsByGuid(@PathVariable("guid") String guid){
        return itemRepository.findByGuid(guid);
    }

    @PostMapping(value = "/rss/insert")
    public ResponseEntity<Item> addXMLItem(@RequestBody String flux) throws Exception{

        String out=flux.replaceAll("rss:","");

        StringBuilder xml = new StringBuilder("");
        xml.append("<rss:feed lang=\"ar-AR\" xmlns:rss=\"http://univrouen.fr/rss22\">\n" +
                "  <title>string</title>\n" +
                "  <pubDate>2008-09-09T03:49:45</pubDate>\n" +
                "  <copyright>string</copyright>\n" +
                "  <!--1 or more repetitions:-->\n" +
                "  <link rel=\"self\" type=\"string\" href=\"string\"/>");
        xml.append(flux);
        xml.append("</rss:feed>");

        String rss = xml.toString();

        if(aService.valid(rss)){
            JAXBContext jaxbContext=JAXBContext.newInstance(Item.class);
            Unmarshaller unmarshaller=jaxbContext.createUnmarshaller();

            StreamSource streamSource=new StreamSource(new StringReader(out));
            JAXBElement<Item> jaxbElement=unmarshaller.unmarshal(streamSource,Item.class);

            Item item=(Item) jaxbElement.getValue();
            System.out.println(item);

            imageRepository.save(item.getImage());
            categoryRepository.save(item.getCategory());
            contentRepository.save(item.getContent());
            authorRepository.save(item.getAuthor());
            return new ResponseEntity<>(itemRepository.save(item), HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity("status:error",HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable("guid")String guid){
        System.out.println(guid);
        itemRepository.deleteByGuid(guid);
        return new  ResponseEntity<>(HttpStatus.OK);
    }
}