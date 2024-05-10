package cretioproject.try1.DTOs;

import cretioproject.try1.Entity.BorrowRecordsEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class StudentDTO {

    private String name;

    private String email;

    private String address;

    private List<Long> borrowingRecordsId;
}
