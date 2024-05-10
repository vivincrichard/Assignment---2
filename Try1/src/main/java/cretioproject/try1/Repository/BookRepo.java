package cretioproject.try1.Repository;

import cretioproject.try1.Entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<BookEntity,Long> {
    BookEntity findByIsbn(long isbn);

    void deleteByIsbn(long isbn);
}
