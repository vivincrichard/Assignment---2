package cretioproject.try1.Controller;


import cretioproject.try1.DTOs.BorrowingRecordDTO;
import cretioproject.try1.Entity.BorrowRecordsEntity;
import cretioproject.try1.Service.BorrowRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/borrow-records")
public class BorrowRecordsController {

    private final BorrowRecordsService borrowRecordsService;

    @Autowired
    public BorrowRecordsController(BorrowRecordsService borrowRecordsService) {
        this.borrowRecordsService = borrowRecordsService;
    }

    @GetMapping
    public ResponseEntity<List<BorrowRecordsEntity>> getAllBorrowRecords() {
        List<BorrowRecordsEntity> records = borrowRecordsService.getAll();
        return ResponseEntity.ok(records);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowRecordsEntity> getBorrowRecordById(@PathVariable Long id) {
        try {
            BorrowRecordsEntity record = borrowRecordsService.getSingle(id);
            return ResponseEntity.ok(record);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<BorrowRecordsEntity> createBorrowRecord(@RequestBody BorrowingRecordDTO borrowingRecordDTO) {
        try {
            BorrowRecordsEntity record = borrowRecordsService.create(borrowingRecordDTO);
            return ResponseEntity.status(201).body(record);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BorrowRecordsEntity> updateBorrowRecord(@PathVariable Long id, @RequestBody BorrowingRecordDTO borrowingRecordDTO) {
        try {
            BorrowRecordsEntity updatedRecord = borrowRecordsService.update(id, borrowingRecordDTO);
            return ResponseEntity.ok(updatedRecord);
        } catch (NoSuchElementException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrowRecord(@PathVariable Long id) {
        try {
            borrowRecordsService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}