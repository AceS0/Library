package dk.teqs.library.catalog.controller;

import dk.teqs.library.catalog.dto.AuthorDto;
import dk.teqs.library.catalog.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
    
    @GetMapping
    public ResponseEntity<List<AuthorDto>> getAllAuthors() {
        List<AuthorDto> authors = authorService.getAllAuthors();
        return ResponseEntity.ok(authors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(authorService.getAuthorById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping()
    public ResponseEntity<AuthorDto> addAuthor(@RequestBody AuthorDto authorDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.createAuthor(authorDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable Long id, @RequestBody AuthorDto authorDto){
        authorService.updateAuthor(id,authorDto);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id){
        authorService.deleteAuthor(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<AuthorDto>> searchAuthor(@RequestParam(required = false) String title){
        List<AuthorDto> authors = authorService.searchAuthors(title);
        return ResponseEntity.ok(authors);
    }
}
