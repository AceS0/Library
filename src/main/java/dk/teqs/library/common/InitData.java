package dk.teqs.library.common;

import com.zaxxer.hikari.HikariDataSource;
import dk.teqs.library.catalog.model.*;
import dk.teqs.library.catalog.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

@Component
public class InitData implements CommandLineRunner {
    private final WorkRepository workRepository;
    private final EditionRepository editionRepository;
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;
    private final SubjectRepository subjectRepository;

    public InitData(WorkRepository workRepository, EditionRepository editionRepository, PublisherRepository publisherRepository, AuthorRepository authorRepository, SubjectRepository subjectRepository) {
        this.workRepository = workRepository;
        this.editionRepository = editionRepository;
        this.publisherRepository = publisherRepository;
        this.authorRepository = authorRepository;
        this.subjectRepository = subjectRepository;
    }

    public void run(String... args) throws Exception {
        Work work1 = new Work("Java tutorial", WorkType.BOOK,"This book is a detailed explanation of basic java");
        Work work2 = new Work("C++ tutorial", WorkType.BOOK,"This book is a detailed explanation of basic c++");
        Work work3 = new Work("Python tutorial", WorkType.BOOK,"This book is a detailed explanation of basic Python");

        Edition edition1 = new Edition("1st Edition",2003, Format.PAPERBACK);
        Edition edition2 = new Edition("2nd Edition",2007, Format.HARDCOVER);
        Edition edition3 = new Edition("3rd Edition",2012, Format.EBOOK);


        work1.addEdition(edition1);
        work2.addEdition(edition2);
        work3.addEdition(edition3);

        Publisher publisher1 = new Publisher("Bro Code","Boston, MA","contact@BroCode.com");
        Publisher publisher2 = new Publisher("W3Schools","New York","contact@W3Schools.com");
        Publisher publisher3 = new Publisher("Osman Butt","Albertslund","info@OsmanButt.com");

        edition1.setPublisher(publisher1);
        edition2.setPublisher(publisher2);
        edition3.setPublisher(publisher3);

        publisherRepository.saveAll(List.of(publisher1, publisher2, publisher3));


        Author author1 = new Author("Abdulcelil Sekerci");
        Author author2 = new Author("ABSE");

        authorRepository.saveAll(List.of(author1,author2));

        work1.addAuthor(author1);
        work2.addAuthor(author2);
        work3.addAuthor(author1);

        Subject subject1 = new Subject("Java");
        Subject subject2 = new Subject("C++");
        Subject subject3 = new Subject("Python");

        subjectRepository.saveAll(List.of(subject1,subject2,subject3));

        work1.addSubject(subject1);
        work2.addSubject(subject2);
        work3.addSubject(subject3);

        workRepository.saveAll(List.of(work1,work2,work3));
        editionRepository.saveAll(List.of(edition1, edition2, edition3));
    }
}
