package cretioproject.try1.Repository;

import cretioproject.try1.Entity.BorrowRecordsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRecordRepo extends JpaRepository<BorrowRecordsEntity,Long> {

}
