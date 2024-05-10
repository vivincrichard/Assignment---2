package cretioproject.try1.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
//@Getter
//@Setter
//
//public class AuthorEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    private String name;
//
//    private String biography;
//
//    @ManyToMany(mappedBy = "authors")
//    @JsonBackReference // This breaks the circular reference in JSON serialization
//    private List<BookEntity> books;
//
//}



public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String biography;

    @ManyToMany(mappedBy = "authors")
    @JsonBackReference
    private List<BookEntity> books;

    // Getter for bookIsbn
    public List<Long> getBooksIsbn() {
        return books.stream().map(BookEntity::getIsbn).toList();
    }

    // Other getters and setters
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

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public List<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(List<BookEntity> books) {
        this.books = books;
    }
}