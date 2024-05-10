package cretioproject.try1.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter

public class BorrowRecordsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference // Indicates this is the "child" side, breaking the loop
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "isbn")
    private BookEntity book;

    private Date borrowingDate;

    private Date returnDate;
}
