package cretioproject.try1.Service;

import cretioproject.try1.DTOs.AuthorDTO;
import cretioproject.try1.Entity.AuthorEntity;
import cretioproject.try1.Entity.BookEntity;
import cretioproject.try1.Repository.AuthorRepo;
import cretioproject.try1.Repository.BookRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepo authorRepo;
    private final BookRepo bookRepo;

    public AuthorService(AuthorRepo authorRepo, BookRepo bookRepo) {
        this.authorRepo = authorRepo;
        this.bookRepo = bookRepo;
    }

    public List<AuthorEntity> getAll() {
        return authorRepo.findAll();
    }

    public AuthorEntity getSingle(long id) {
        Optional<AuthorEntity> authorOpt = authorRepo.findById(id);
        if (authorOpt.isPresent()) {
            return authorOpt.get();
        } else {
            throw new NoSuchElementException("Author with ID " + id + " not found.");
        }
    }

    public void delete(long id) {
        if (!authorRepo.existsById(id)) {
            throw new NoSuchElementException("Author with ID " + id + " not found.");
        }
        authorRepo.deleteById(id);
    }

    public AuthorEntity create(AuthorDTO authorDTO) {
        AuthorEntity author = new AuthorEntity();
        author.setName(authorDTO.getName());
        author.setBiography(authorDTO.getBiography());

        List<BookEntity> bookList = new ArrayList<>();
        if (authorDTO.getBooksIsbn() != null) {
            for (Long bookIsbn : authorDTO.getBooksIsbn()) {
                BookEntity book = bookRepo.findByIsbn(bookIsbn);
                if (book != null) {
                    bookList.add(book);
                } else {
                    throw new IllegalArgumentException("Book with ISBN " + bookIsbn + " not found.");
                }
            }
        }

        author.setBooks(bookList);
        return authorRepo.save(author);
    }

    public AuthorEntity update(long id, AuthorDTO authorDTO) {
        AuthorEntity author = getSingle(id);

        author.setName(authorDTO.getName());
        author.setBiography(authorDTO.getBiography());

        List<BookEntity> bookList = new ArrayList<>();
        if (authorDTO.getBooksIsbn() != null) {
            for (Long bookIsbn : authorDTO.getBooksIsbn()) {
                BookEntity book = bookRepo.findByIsbn(bookIsbn);
                if (book != null) {
                    bookList.add(book);
                } else {
                    throw new IllegalArgumentException("Book with ISBN " + bookIsbn + " not found.");
                }
            }
        }

        author.setBooks(bookList);
        return authorRepo.save(author);
    }
}
