package cretioproject.try1.Repository;

import cretioproject.try1.Entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepo extends JpaRepository<AuthorEntity,Long> {
    Optional<AuthorEntity> findById(long id);
}
