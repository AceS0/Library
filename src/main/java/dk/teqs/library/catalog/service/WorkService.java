package dk.teqs.library.catalog.service;

import dk.teqs.library.catalog.model.Work;
import dk.teqs.library.catalog.repository.WorkRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WorkService {
    private final WorkRepository workRepository;

    public WorkService(WorkRepository workRepository){
        this.workRepository = workRepository;
    }

    public Work createWork(Work work){
        work.setId(null);
        return workRepository.save(work);
    }

    public List<Work> getAllWorks(){
        return workRepository.findAll();
    }

    public Work getWorkById(Long id){
        return workRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    public Work updateWork(Long id, Work workDetails){
        Work work = this.getWorkById(id);
        work.setTitle(workDetails.getTitle());
        work.setWorkType(workDetails.getWorkType());
        work.setDetails(workDetails.getDetails());
        work.setAuthors(workDetails.getAuthors());
        work.setSubjects(workDetails.getSubjects());
        return workRepository.save(work);
    }

    public void deleteWork(Long id){
        Work work = this.getWorkById(id);
        if (work == null) {
            throw new RuntimeException("Work not found with id: " + id);
        }
        workRepository.delete(work);
    }

    public List<Work> searchWorks(String title){
        return workRepository.findByTitleContaining(title);
    }
}
