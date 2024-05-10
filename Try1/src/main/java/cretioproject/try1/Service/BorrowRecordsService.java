package cretioproject.try1.Service;


import cretioproject.try1.DTOs.BorrowingRecordDTO;
import cretioproject.try1.Entity.BookEntity;
import cretioproject.try1.Entity.BorrowRecordsEntity;
import cretioproject.try1.Entity.StudentEntity;
import cretioproject.try1.Repository.BookRepo;
import cretioproject.try1.Repository.BorrowRecordRepo;
import cretioproject.try1.Repository.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BorrowRecordsService {

    private BorrowRecordRepo borrowRecordsRepo;
    private StudentRepo studentRepo;
    private BookRepo bookRepo;

    public BorrowRecordsService(BorrowRecordRepo borrowRecordsRepo, StudentRepo studentRepo, BookRepo bookRepo) {
        this.borrowRecordsRepo = borrowRecordsRepo;
        this.studentRepo = studentRepo;
        this.bookRepo = bookRepo;
    }

    public List<BorrowRecordsEntity> getAll() {
        return borrowRecordsRepo.findAll();
    }

    public BorrowRecordsEntity getSingle(Long id) {
        Optional<BorrowRecordsEntity> recordOpt = borrowRecordsRepo.findById(id);
        if (recordOpt.isPresent()) {
            return recordOpt.get();
        } else {
            throw new NoSuchElementException("Borrow record with ID " + id + " not found.");
        }
    }

    public void delete(Long id) {
        if (!borrowRecordsRepo.existsById(id)) {
            throw new NoSuchElementException("Borrow record with ID " + id + " not found.");
        }
        borrowRecordsRepo.deleteById(id);
    }

    public BorrowRecordsEntity create(BorrowingRecordDTO borrowingRecordDTO) {
        BorrowRecordsEntity record = new BorrowRecordsEntity();

        StudentEntity student = studentRepo.findByName(borrowingRecordDTO.getStudent());
        if (student == null) {
            throw new IllegalArgumentException("Student not found.");
        }

        BookEntity book = bookRepo.findByIsbn(borrowingRecordDTO.getBookIsbn());
        if (book == null) {
            throw new IllegalArgumentException("Book with ISBN " + borrowingRecordDTO.getBookIsbn() + " not found.");
        }

        record.setStudent(student);
        record.setBook(book);
        record.setBorrowingDate(borrowingRecordDTO.getBorrowingDate());
        record.setReturnDate(borrowingRecordDTO.getReturnDate());

        return borrowRecordsRepo.save(record);
    }

    public BorrowRecordsEntity update(Long id, BorrowingRecordDTO borrowingRecordDTO) {
        BorrowRecordsEntity record = getSingle(id);

        StudentEntity student = studentRepo.findByName(borrowingRecordDTO.getStudent());
        if (student == null) {
            throw new IllegalArgumentException("Student not found.");
        }

        BookEntity book = bookRepo.findByIsbn(borrowingRecordDTO.getBookIsbn());
        if (book == null) {
            throw new IllegalArgumentException("Book with ISBN " + borrowingRecordDTO.getBookIsbn() + " not found.");
        }

        record.setStudent(student);
        record.setBook(book);
        record.setBorrowingDate(borrowingRecordDTO.getBorrowingDate());
        record.setReturnDate(borrowingRecordDTO.getReturnDate());

        return borrowRecordsRepo.save(record);
    }
}