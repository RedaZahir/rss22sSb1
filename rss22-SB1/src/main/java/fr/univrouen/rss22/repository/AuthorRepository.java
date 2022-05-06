package fr.univrouen.rss22.repository;

import fr.univrouen.rss22.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
