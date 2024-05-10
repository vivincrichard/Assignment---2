package cretioproject.try1.DTOs;

import cretioproject.try1.Entity.BookEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class PublisherDTO {

    private String name;

    private String address;

    private String contactInformation;

    private List<BookDTO> books;

    private List<Long> booksIsbn;
}
