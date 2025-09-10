package dk.teqs.library.catalog.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Enumerated(EnumType.STRING)
    private WorkType workType;
    private String details;

    @ManyToMany
    @JsonManagedReference
    private Set<Author> authors = new HashSet<>();

    @ManyToMany
    @JsonManagedReference
    private List<Subject> subjects = new ArrayList<>();

    @OneToMany(mappedBy = "work", cascade = CascadeType.PERSIST)
    private List<Edition> editions = new ArrayList<>();

    public Work(String title, WorkType workType, String details) {
        this.title = title;
        this.workType = workType;
        this.details = details;
    }

    public Work(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public WorkType getWorkType() {
        return workType;
    }

    public void setWorkType(WorkType workType) {
        this.workType = workType;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Set<Author> getAuthors(){
        return authors;
    }

    public void addAuthor(Author author){
        authors.add(author);
    }

    public void removeAuthor(Author author){
        authors.remove(author);
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    public void removeSubject(Subject subject){
        subjects.remove(subject);
    }

    public void clearSubjects(){
        for (Subject subject : new ArrayList<>(subjects)){
            removeSubject(subject);
        }
    }

    public List<Edition> getEditions() {
        return editions;
    }

    public void addEdition(Edition edition){
        editions.add(edition);
        edition.setWork(this);
    }

    public void removeEdition(Edition edition){
        editions.remove(edition);
        edition.setWork(null);
    }

    public void clearEditions(){
        for (Edition edition : new ArrayList<>(editions)){
            removeEdition(edition);
        }
    }
}
