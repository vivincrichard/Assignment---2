package cretioproject.try1.DTOs;

import cretioproject.try1.Entity.BookEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AuthorDTO {

    private String name;

    private String biography;

    private List<Long> booksIsbn;
}
