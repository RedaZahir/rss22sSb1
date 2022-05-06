package fr.univrouen.rss22.repository;

import fr.univrouen.rss22.entities.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content, Long> {
}
