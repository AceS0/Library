package dk.teqs.library.catalog.repository;

import dk.teqs.library.catalog.model.Work;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkRepository extends JpaRepository<Work, Long> {
    List<Work> findByTitleContaining(String title);
}
