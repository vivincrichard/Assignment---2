package cretioproject.try1.Service;

import cretioproject.try1.DTOs.AuthorDTO;
import cretioproject.try1.DTOs.PublisherDTO;
import cretioproject.try1.Entity.BookEntity;
import cretioproject.try1.Entity.PublisherEntity;
import cretioproject.try1.Repository.BookRepo;
import cretioproject.try1.Repository.PublisherRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PublisherService {

    private final PublisherRepo publisherRepo;
    private final BookRepo bookRepo;

    public PublisherService(PublisherRepo publisherRepo, BookRepo bookRepo) {
        this.publisherRepo = publisherRepo;
        this.bookRepo = bookRepo;
    }

    public List<PublisherEntity> getAll() {
        return publisherRepo.findAll();
    }

    public PublisherEntity getSingle(long id) {
        Optional<PublisherEntity> publisherOpt = publisherRepo.findById(id);
        if (publisherOpt.isPresent()) {
            return publisherOpt.get();
        } else {
            throw new NoSuchElementException("Publisher with ID " + id + " not found.");
        }
    }

    public void delete(long id) {
        if (!publisherRepo.existsById(id)) {
            throw new NoSuchElementException("Publisher with ID " + id + " not found.");
        }
        publisherRepo.deleteById(id);
    }

    public PublisherEntity create(PublisherDTO publisherDTO) {
        PublisherEntity publisher = new PublisherEntity();
        publisher.setName(publisherDTO.getName());
        publisher.setAddress(publisherDTO.getAddress());
        publisher.setContactInformation(publisherDTO.getContactInformation());

        List<BookEntity> bookList = new ArrayList<>();
        if (publisherDTO.getBooksIsbn() != null) {
            for (Long bookIsbn : publisherDTO.getBooksIsbn()) {
                BookEntity book = bookRepo.findByIsbn(bookIsbn);
                if (book != null) {
                    bookList.add(book);
                } else {
                    throw new IllegalArgumentException("Book with ISBN " + bookIsbn + " not found.");
                }
            }
        }

        publisher.setBooks(bookList);

        return publisherRepo.save(publisher);
    }

    public PublisherEntity update(long id, PublisherDTO publisherDTO) {
        PublisherEntity publisher = getSingle(id);

        publisher.setName(publisherDTO.getName());
        publisher.setAddress(publisherDTO.getAddress());
        publisher.setContactInformation(publisherDTO.getContactInformation());

        List<BookEntity> bookList = new ArrayList<>();
        if (publisherDTO.getBooksIsbn() != null) {
            for (Long bookIsbn : publisherDTO.getBooksIsbn()) {
                BookEntity book = bookRepo.findByIsbn(bookIsbn);
                if (book != null) {
                    bookList.add(book);
                } else {
                    throw new IllegalArgumentException("Book with ISBN " + bookIsbn + " not found.");
                }
            }
        }

        publisher.setBooks(bookList);
        return publisherRepo.save(publisher);
    }
}









