package cretioproject.try1.Controller;

import cretioproject.try1.DTOs.PublisherDTO;
import cretioproject.try1.Entity.PublisherEntity;
import cretioproject.try1.Service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    private final PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping
    public ResponseEntity<List<PublisherEntity>> getAllPublishers() {
        List<PublisherEntity> publishers = publisherService.getAll();
        return ResponseEntity.ok(publishers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherEntity> getPublisherById(@PathVariable long id) {
        try {
            PublisherEntity publisher = publisherService.getSingle(id);
            return ResponseEntity.ok(publisher);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PublisherEntity> createPublisher(@RequestBody PublisherDTO publisherDTO) {
        try {
            PublisherEntity publisher = publisherService.create(publisherDTO);
            return ResponseEntity.status(201).body(publisher);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublisherEntity> updatePublisher(@PathVariable long id, @RequestBody PublisherDTO publisherDTO) {
        try {
            PublisherEntity updatedPublisher = publisherService.update(id, publisherDTO);
            return ResponseEntity.ok(updatedPublisher);
        } catch (NoSuchElementException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable long id) {
        try {
            publisherService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}