package cretioproject.try1.Service;


import cretioproject.try1.DTOs.BookDTO;
import cretioproject.try1.Entity.AuthorEntity;
import cretioproject.try1.Entity.BookEntity;
import cretioproject.try1.Entity.PublisherEntity;
import cretioproject.try1.Repository.AuthorRepo;
import cretioproject.try1.Repository.BookRepo;
import cretioproject.try1.Repository.PublisherRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookService {

    private final BookRepo bookRepo;
    private final AuthorRepo authorRepo;
    private final PublisherRepo publisherRepo;

    public BookService(BookRepo bookRepo, AuthorRepo authorRepo, PublisherRepo publisherRepo) {
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
        this.publisherRepo = publisherRepo;
    }

    public List<BookEntity> getAll() {
        return bookRepo.findAll();
    }

    public BookEntity getByIsbn(long isbn) {
        BookEntity book = bookRepo.findByIsbn(isbn);
        if (book == null) {
            throw new NoSuchElementException("Book with ISBN " + isbn + " not found.");
        }
        return book;
    }

    public void delete(long isbn) {
        bookRepo.deleteByIsbn(isbn);
    }

    public BookEntity create(BookDTO bookDTO) {
        BookEntity book = new BookEntity();
        book.setIsbn(bookDTO.getIsbn());
        book.setTitle(bookDTO.getTitle());
        book.setPublicationDate(bookDTO.getPublicationDate());
        book.setGenre(bookDTO.getGenre());

        List<AuthorEntity> authors = new ArrayList<>();
        if (bookDTO.getAuthorsId() != null) {
            for (Long authorId : bookDTO.getAuthorsId()) {
                AuthorEntity author = authorRepo.findById(authorId).orElse(null);
                if (author != null) {
                    authors.add(author);
                } else {
                    throw new IllegalArgumentException("Author with ID " + authorId + " not found.");
                }
            }
        }

        book.setAuthors(authors);

        Long publisherId = bookDTO.getPublisherID();
        PublisherEntity publisher = publisherRepo.findById(publisherId).orElse(null);

        if (publisher == null) {
            throw new IllegalArgumentException("Publisher with ID " + publisherId + " not found.");
        }

        book.setPublisher(publisher);

        return bookRepo.save(book);
    }


//public BookEntity create(BookDTO bookDTO) {
//    BookEntity book = new BookEntity();
//    book.setIsbn(bookDTO.getIsbn());
//    book.setTitle(bookDTO.getTitle());
//    book.setPublicationDate(bookDTO.getPublicationDate());
//    book.setGenre(bookDTO.getGenre());
//
//    List<AuthorEntity> authors = new ArrayList<>();
//    if (bookDTO.getAuthorsId() != null) {
//        for (Long authorId : bookDTO.getAuthorsId()) {
//            AuthorEntity author = authorRepo.findById(authorId).orElse(null);
//            if (author != null) {
//                authors.add(author);
//            } else {
//                throw new IllegalArgumentException("Author with ID " + authorId + " not found.");
//            }
//        }
//    }
//
//    book.setAuthors(authors);
//
//    PublisherEntity publisher = publisherRepo.findById(bookDTO.getPublisherID()).orElse(null);
//    if (publisher == null) {
//        throw new IllegalArgumentException("Publisher with ID " + bookDTO.getPublisherID() + " not found.");
//    }
//
//    book.setPublisher(publisher); // Ensure this line is present
//
//    return bookRepo.save(book);
//}

    public BookEntity update(long isbn, BookDTO bookDTO) {
        BookEntity book = bookRepo.findByIsbn(isbn);
        if (book == null) {
            throw new NoSuchElementException("Book with ISBN " + isbn + " not found.");
        }

        book.setTitle(bookDTO.getTitle());
        book.setPublicationDate(bookDTO.getPublicationDate());
        book.setGenre(bookDTO.getGenre());

        List<AuthorEntity> authors = new ArrayList<>();
        if (bookDTO.getAuthorsId() != null) {
            for (Long authorId : bookDTO.getAuthorsId()) {
                AuthorEntity author = authorRepo.findById(authorId).orElse(null);
                if (author != null) {
                    authors.add(author);
                } else {
                    throw new IllegalArgumentException("Author with ID " + authorId + " not found.");
                }
            }
        }
        book.setAuthors(authors);

        Long publisherId = bookDTO.getPublisherID();
        PublisherEntity publisher = publisherRepo.findById(publisherId).orElse(null);

        if (publisher == null) {
            throw new IllegalArgumentException("Publisher with ID " + publisherId + " not found.");
        }

        book.setPublisher(publisher);

        return bookRepo.save(book);
    }
}

