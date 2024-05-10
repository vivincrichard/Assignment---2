package cretioproject.try1.DTOs;

import cretioproject.try1.Entity.AuthorEntity;
import cretioproject.try1.Entity.PublisherEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class BookDTO {
    private long isbn;

    private String title;

    private Date publicationDate;

    private String genre;

    private List<Long> authorsId;

    private Long publisherID;
}
