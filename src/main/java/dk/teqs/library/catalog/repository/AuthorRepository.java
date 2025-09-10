package dk.teqs.library.catalog.repository;

import dk.teqs.library.catalog.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByNameContaining(String name);

}
