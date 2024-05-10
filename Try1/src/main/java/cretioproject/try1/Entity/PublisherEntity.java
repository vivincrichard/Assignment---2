package cretioproject.try1.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

//@Entity
//@Setter
//@Getter
//public class PublisherEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    private String name;
//
//    private String address;
//
//    private String contactInformation;
//
//    @OneToMany(mappedBy = "publisher")
//    @JsonBackReference // Indicates parent-child relationship in serialization
//    private List<BookEntity> booksId; // This list holds the associated books
//}


@Entity
public class PublisherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String address;

    private String contactInformation;

    @OneToMany(mappedBy = "publisher")
    @JsonBackReference
    private List<BookEntity> booksID;

    // Getter for booksIsbn
//    public List<Long> getBooksIsbn() {
//        return booksID.stream().map(BookEntity::getIsbn).toList();
//    }
    public List<Long> getBooksIsbn() {
        List<Long> list = new ArrayList<>();
        for (BookEntity book : booksID) {
            list.add(book.getIsbn());
        }
        return list;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public List<BookEntity> getBooks() {
        return booksID;
    }

    public void setBooks(List<BookEntity> books) {
        this.booksID = books;
    }
}