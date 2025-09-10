package dk.teqs.library.catalog.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "authors")
    @JsonBackReference
    private Set<Work> works = new HashSet<>();

    private String name;

    public Author() {}

    public Author(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Work> getWorks(){
        return works;
    }

    public void setWork(Set<Work> works){
        this.works = works;
    }

    public void addWork(Work work){
        if (work == null) return;
        if (this.works.add(work)) {
            work.addAuthor(this);
        }
    }

    public void removeWork(Work work){
        if (work == null) return;
        if (this.works.remove(work)){
            work.removeAuthor(this);
        }
    }

}
