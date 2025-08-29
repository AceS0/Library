package dk.teqs.library.catalog.controller;

import dk.teqs.library.catalog.model.Work;
import dk.teqs.library.catalog.service.WorkService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/works")
public class WorkController {
    private final WorkService workService;


    public WorkController(WorkService workService){
        this.workService = workService;
    }

    @GetMapping
    public ResponseEntity<List<Work>> getAllWorks() {
        List<Work> works = workService.getAllWorks();
        return ResponseEntity.ok(works);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Work> getWorkById(@PathVariable Long id){
        Work work = workService.getWorkById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(work);
    }

    @PostMapping()
    public ResponseEntity<Work> addWork(@RequestBody Work work){
        Work newWork = workService.createWork(work);
        return ResponseEntity.status(HttpStatus.CREATED).body(newWork);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Work> updateWork(@PathVariable Long id, @RequestBody Work work){
        Work newWork = workService.updateWork(id,work);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWork(@PathVariable Long id){
        workService.deleteWork(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Work>> searchWork(@RequestParam(required = false) String title){
        List<Work> works = workService.searchWorks(title);
        return ResponseEntity.ok(works);
    }
}
