package dk.teqs.library.catalog.dto;

import dk.teqs.library.catalog.model.*;

import java.util.*;

public class DtoMapper {
    public static WorkDto toDto(Work work){
        List<EditionDto> editions = new ArrayList<>();
        Set<AuthorDto> authors = new HashSet<>();
        List<SubjectDto> subjects = new ArrayList<>();



        for (var line : work.getEditions()) {
            editions.add(toDto(line));
        }

        for (var line : work.getAuthors()) {
            authors.add(toDto(line));
        }

        for (var line : work.getSubjects()) {
            subjects.add(toDto(line));
        }

        return new WorkDto(work.getId(), work.getTitle(), work.getWorkType().name(), work.getDetails(), authors, editions, subjects);
    }

    public static EditionDto toDto(Edition edition){
        return new EditionDto(edition.getId(),edition.getEditionNumber(), edition.getYear(), edition.getFormat().name(), toDto(edition.getPublisher()));
    }

    public static PublisherDto toDto(Publisher publisher){
        return new PublisherDto(publisher.getId(), publisher.getName(), publisher.getAddress(), publisher.getContactInfo())
;    }

    public static AuthorDto toDto(Author author){
        return new AuthorDto(author.getId(), author.getName());
    }

    public static SubjectDto toDto(Subject subject){
        return new SubjectDto(subject.getId(), subject.getName());
    }

    public static Work toEntity(WorkDto workDto) {
        Work work = new Work();
        work.setTitle(workDto.title());
        work.setWorkType(WorkType.valueOf(workDto.workType()));
        work.setDetails(workDto.details());

        for (var firstLineDto : workDto.authors()){
            var line = toEntity(firstLineDto);
            work.addAuthor(line);
        }

        for (var secondLineDto : workDto.subjects()){
            var line = toEntity(secondLineDto);
            work.addSubject(line);
        }

        for (var lineDto : workDto.editions()){
            var line = toEntity(lineDto);
            work.addEdition(line);
        }

        return work;
    }

    public static Edition toEntity(EditionDto editionDto){
        Edition edition = new Edition();
        edition.setEditionNumber(editionDto.editionNumber());
        edition.setYear(editionDto.year());
        edition.setFormat(Format.valueOf(editionDto.fromat()));
        edition.setPublisher(toEntity(editionDto.publisher()));
        return edition;
    }

    public static Publisher toEntity(PublisherDto publisherDto){
        Publisher publisher = new Publisher();
        publisher.setName(publisherDto.name());
        publisher.setAddress(publisherDto.address());
        publisher.setContactInfo(publisherDto.contactInfo());
        return publisher;
    }

    public static Author toEntity(AuthorDto authorDto){
        Author author = new Author();
        author.setName(authorDto.name());
        return author;
    }

    public static Subject toEntity(SubjectDto subjectDto){
        Subject subject = new Subject();
        subject.setName(subjectDto.name());
        return subject;
    }
}
