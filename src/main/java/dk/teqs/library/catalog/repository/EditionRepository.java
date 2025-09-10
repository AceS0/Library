package dk.teqs.library.catalog.repository;

import dk.teqs.library.catalog.model.Edition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditionRepository extends JpaRepository<Edition, Long> {
}
