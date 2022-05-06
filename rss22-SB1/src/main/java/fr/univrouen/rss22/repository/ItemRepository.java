package fr.univrouen.rss22.repository;

import fr.univrouen.rss22.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByGuid(String guid);
    void deleteByGuid(String guid);
}
