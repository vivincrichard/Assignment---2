package cretioproject.try1.Controller;

import cretioproject.try1.DTOs.AuthorDTO;
import cretioproject.try1.DTOs.BookDTO;
import cretioproject.try1.Entity.AuthorEntity;
import cretioproject.try1.Entity.BookEntity;
import cretioproject.try1.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<AuthorEntity>> getAllAuthors() {
        List<AuthorEntity> authors = authorService.getAll();
        return ResponseEntity.ok(authors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorEntity> getAuthorById(@PathVariable long id) {
        try {
            AuthorEntity author = authorService.getSingle(id);
            return ResponseEntity.ok(author);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<AuthorEntity> createAuthor(@RequestBody AuthorDTO authorDTO) {
        try {
            AuthorEntity author = authorService.create(authorDTO);
            return ResponseEntity.status(201).body(author);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorEntity> updateAuthor(@PathVariable long id, @RequestBody AuthorDTO authorDTO) {
        try {
            AuthorEntity updatedAuthor = authorService.update(id, authorDTO);
            return ResponseEntity.ok(updatedAuthor);
        } catch (NoSuchElementException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable long id) {
        try {
            authorService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
