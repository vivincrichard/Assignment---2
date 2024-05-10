package cretioproject.try1.Controller;

import cretioproject.try1.DTOs.BookDTO;
import cretioproject.try1.Entity.BookEntity;
import cretioproject.try1.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookEntity>> getAllBooks() {
        List<BookEntity> books = bookService.getAll();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<BookEntity> getBookByIsbn(@PathVariable long isbn) {
        try {
            BookEntity book = bookService.getByIsbn(isbn);
            return ResponseEntity.ok(book);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<BookEntity> createBook(@RequestBody BookDTO bookDTO) {
        try {
            BookEntity book = bookService.create(bookDTO);
            return ResponseEntity.status(201).body(book);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<BookEntity> updateBook(@PathVariable long isbn, @RequestBody BookDTO bookDTO) {
        try {
            BookEntity updatedBook = bookService.update(isbn, bookDTO);
            return ResponseEntity.ok(updatedBook);
        } catch (NoSuchElementException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> deleteBook(@PathVariable long isbn) {
        try {
            bookService.delete(isbn);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}