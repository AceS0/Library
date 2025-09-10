package dk.teqs.library.catalog.repository;

import dk.teqs.library.catalog.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findByNameContaining(String name);

}
