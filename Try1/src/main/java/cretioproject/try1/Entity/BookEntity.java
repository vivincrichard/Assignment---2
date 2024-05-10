package cretioproject.try1.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
//
//@Entity
//@Getter
//@Setter
//public class BookEntity {
//
//    @Id
//    private long isbn;
//
//    private String title;
//
//    private Date publicationDate;
//
//    private String genre;
//
//    @ManyToMany
//    @JoinTable(
//            name = "book_author",
//            joinColumns = @JoinColumn(name = "isbn"),
//            inverseJoinColumns = @JoinColumn(name = "author_id")
//    )
//    @JsonManagedReference
//    private List<AuthorEntity> authors;
//
//    @ManyToOne
//    @JoinColumn(name = "publisher_id")
//    @JsonBackReference // Prevents recursive serialization with `PublisherEntity`
//    private PublisherEntity publisher;
//}


@Entity
public class BookEntity {
    @Id
    private long isbn;

    private String title;

    private Date publicationDate;

    private String genre;

    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "isbn"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    @JsonManagedReference
    private List<AuthorEntity> authors;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    @JsonBackReference
    private PublisherEntity publisher;

    // Getter for publisherId
    public Long getPublisherId() {
        return (publisher != null) ? publisher.getId() : null;
    }

    // Other getters and setters
    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<AuthorEntity> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorEntity> authors) {
        this.authors = authors;
    }

    public PublisherEntity getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherEntity publisher) {
        this.publisher = publisher;
    }
}
