package cretioproject.try1.Service;


import cretioproject.try1.DTOs.StudentDTO;
import cretioproject.try1.Entity.BorrowRecordsEntity;
import cretioproject.try1.Entity.StudentEntity;
import cretioproject.try1.Repository.BorrowRecordRepo;
import cretioproject.try1.Repository.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepo studentRepo;
    private BorrowRecordRepo borrowRecordsRepo;

    public StudentService(StudentRepo studentRepo, BorrowRecordRepo borrowRecordsRepo) {
        this.studentRepo = studentRepo;
        this.borrowRecordsRepo = borrowRecordsRepo;
    }

    public List<StudentEntity> getAll() {
        return studentRepo.findAll();
    }

    public StudentEntity getSingle(Long id) {
        Optional<StudentEntity> studentOpt = studentRepo.findById(id);
        if (studentOpt.isPresent()) {
            return studentOpt.get();
        } else {
            throw new NoSuchElementException("Student with ID " + id + " not found.");
        }
    }

    public void delete(Long id) {
        if (!studentRepo.existsById(id)) {
            throw new NoSuchElementException("Student with ID " + id + " not found.");
        }
        studentRepo.deleteById(id);
    }

    public StudentEntity create(StudentDTO studentDTO) {
        StudentEntity student = new StudentEntity();
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setAddress(studentDTO.getAddress());

        List<BorrowRecordsEntity> borrowingRecords = new ArrayList<>();
        if (studentDTO.getBorrowingRecordsId() != null) {
            for (Long borrowId : studentDTO.getBorrowingRecordsId()) {
                BorrowRecordsEntity record = borrowRecordsRepo.findById(borrowId).orElse(null);
                if (record != null) {
                    borrowingRecords.add(record);
                } else {
                    throw new IllegalArgumentException("Borrow record with ID " + borrowId + " not found.");
                }
            }
        }

        student.setBorrowingRecords(borrowingRecords);
        return studentRepo.save(student);
    }

    public StudentEntity update(Long id, StudentDTO studentDTO) {
        StudentEntity student = getSingle(id);

        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setAddress(studentDTO.getAddress());

        List<BorrowRecordsEntity> borrowingRecords = new ArrayList<>();
        if (studentDTO.getBorrowingRecordsId() != null) {
            for (Long borrowId : studentDTO.getBorrowingRecordsId()) {
                BorrowRecordsEntity record = borrowRecordsRepo.findById(borrowId).orElse(null);
                if (record != null) {
                    borrowingRecords.add(record);
                } else {
                    throw new IllegalArgumentException("Borrow record with ID " + borrowId + " not found.");
                }
            }
        }

        student.setBorrowingRecords(borrowingRecords);
        return studentRepo.save(student);
    }
}