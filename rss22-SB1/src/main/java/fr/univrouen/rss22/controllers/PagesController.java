package fr.univrouen.rss22.controllers;

import fr.univrouen.rss22.entities.Item;
import fr.univrouen.rss22.repository.ItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PagesController {
    ItemRepository itemRepository;

    @GetMapping("/accueil")
    public String getAccueil(Model model) {
        return "accueil";
    }
    @GetMapping("/items")
    public String getItems(Model model) {
        /*List<Item> articles = itemRepository.findAll();

        model.addAttribute("articles", articles);
        model.addAttribute("article", new Item());*/
        return "items";
    }
    @GetMapping("/documentation")
    public String getDoc(Model model) {
        return "documentation";
    }
}
