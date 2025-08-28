package dk.teqs.library.catalog.controller;

import dk.teqs.library.catalog.model.Work;
import dk.teqs.library.catalog.service.WorkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
