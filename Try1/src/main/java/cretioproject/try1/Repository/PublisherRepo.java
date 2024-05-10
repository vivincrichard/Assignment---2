package cretioproject.try1.Repository;

import cretioproject.try1.Entity.PublisherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PublisherRepo extends JpaRepository<PublisherEntity,Long> {
    @Override
    Optional<PublisherEntity> findById(Long aLong);

}
