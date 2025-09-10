package dk.teqs.library.catalog.controller;

import dk.teqs.library.catalog.dto.WorkDto;
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
    public ResponseEntity<List<WorkDto>> getAllWorks() {
        List<WorkDto> works = workService.getAllWorks();
        return ResponseEntity.ok(works);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkDto> getWorkById(@PathVariable Long id){
        try {
           return ResponseEntity.status(HttpStatus.ACCEPTED).body(workService.getWorkById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping()
    public ResponseEntity<WorkDto> addWork(@RequestBody WorkDto workDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(workService.createWork(workDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkDto> updateWork(@PathVariable Long id, @RequestBody WorkDto workDto){
        workService.updateWork(id,workDto);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWork(@PathVariable Long id){
        workService.deleteWork(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<WorkDto>> searchWork(@RequestParam(required = false) String title){
        List<WorkDto> works = workService.searchWorks(title);
        return ResponseEntity.ok(works);
    }
}
