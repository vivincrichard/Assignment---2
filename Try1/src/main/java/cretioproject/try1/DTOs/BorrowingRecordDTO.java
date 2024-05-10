package cretioproject.try1.DTOs;

import cretioproject.try1.Entity.BookEntity;
import lombok.Getter;
import lombok.Setter;

import javax.xml.crypto.Data;
import java.util.Date;

@Getter
@Setter
public class BorrowingRecordDTO {

    private String student;

    private long bookIsbn;

    private Date borrowingDate;
    private Date returnDate;
}
