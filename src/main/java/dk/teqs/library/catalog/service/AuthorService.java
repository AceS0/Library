package dk.teqs.library.catalog.service;

import dk.teqs.library.catalog.dto.AuthorDto;
import dk.teqs.library.catalog.dto.DtoMapper;
import dk.teqs.library.catalog.dto.AuthorDto;
import dk.teqs.library.catalog.model.Author;
import dk.teqs.library.catalog.model.Author;
import dk.teqs.library.catalog.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public AuthorDto createAuthor(AuthorDto authorDto){
        Author author = DtoMapper.toEntity(authorDto);
        author.setId(null);
        return DtoMapper.toDto(authorRepository.save(author));
    }

    public List<AuthorDto> getAllAuthors(){
        List<Author> authors = authorRepository.findAll();
        List<AuthorDto> authorDtos = new ArrayList<>();
        for (var author : authors){
            authorDtos.add(DtoMapper.toDto(author));
        }
        return authorDtos;
    }

    public AuthorDto getAuthorById(Long id){
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()){
            return DtoMapper.toDto(author.get());
        }
        throw new RuntimeException("Author not found with the id: " + id);
    }

    public AuthorDto updateAuthor(Long id, AuthorDto authorDto){
        Optional<Author> existingAuthor = authorRepository.findById(id);
        if (existingAuthor.isPresent()){
            Author author = DtoMapper.toEntity(authorDto);
            Author updatedAuthor = existingAuthor.get();
            updatedAuthor.setName(author.getName());
            updatedAuthor.setWork(author.getWorks());
            return DtoMapper.toDto(authorRepository.save(updatedAuthor));
        } else{
            throw new RuntimeException("Author not found with the id: " + id);
        }
    }

    public void deleteAuthor(Long id){
        if (authorRepository.existsById(id)){
            authorRepository.deleteById(id);
        } else {
            throw new RuntimeException("Author not found with the id: " + id);
        }
    }

    public List<AuthorDto> searchAuthors(String name){
        List<Author> authors = authorRepository.findByNameContaining(name);
        List<AuthorDto> authorDtos = new ArrayList<>();
        for (var author : authors){
            authorDtos.add(DtoMapper.toDto(author));
        }
        return authorDtos;
    }
}
