package cretioproject.try1.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter

public class StudentEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        private String name;

        private String email;

        private String address;

        @OneToMany(mappedBy = "student")
        @JsonManagedReference // Indicates that this is the "parent" side of the relationship
        private List<BorrowRecordsEntity> borrowingRecords;

}
