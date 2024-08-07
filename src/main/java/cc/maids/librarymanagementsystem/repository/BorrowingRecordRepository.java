package cc.maids.librarymanagementsystem.repository;

import cc.maids.librarymanagementsystem.model.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Integer> {

    BorrowingRecord findBorrowingRecordByBookIdAndAndPatronId(int bookId,int patronId);
}
