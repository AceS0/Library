package dk.teqs.library.catalog.dto;

import dk.teqs.library.catalog.model.Author;

import java.util.List;
import java.util.Set;

public record WorkDto(Long id, String title, String workType, String details, Set<AuthorDto> authors, List<EditionDto> editions, List<SubjectDto> subjects) {
}
