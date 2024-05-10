package cretioproject.try1.Repository;

import cretioproject.try1.Entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<StudentEntity,Long> {
    StudentEntity findByName(String name);
}
