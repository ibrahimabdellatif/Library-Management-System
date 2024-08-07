package cc.maids.librarymanagementsystem.service;

import cc.maids.librarymanagementsystem.DTO.BorrowingRecordDTOPost;
import cc.maids.librarymanagementsystem.model.BorrowingRecord;
import cc.maids.librarymanagementsystem.repository.BookRepository;
import cc.maids.librarymanagementsystem.repository.BorrowingRecordRepository;
import cc.maids.librarymanagementsystem.repository.PatronRepository;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class BorrowingRecordService {

    private final BorrowingRecordRepository borrowingRecordRepository;
    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;

    public BorrowingRecordService(BorrowingRecordRepository borrowingRecordRepository, BookRepository bookRepository, PatronRepository patronRepository) {
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
    }
    @Transactional
    public void addBorrowingRecord(int bookId, int patronId, BorrowingRecord borrowingRecord) {
        boolean bookExists = bookRepository.existsById(bookId);
        boolean patronExists = patronRepository.existsById(patronId);

        if (bookExists && patronExists) {
            borrowingRecord.setBook(bookRepository.findById(bookId).isPresent() ? bookRepository.findById(bookId).get() : null);
            borrowingRecord.setPatron(patronRepository.findById(patronId).isPresent() ? patronRepository.findById(patronId).get() : null);
            borrowingRecord.setBorrowDate(LocalDate.now());
            borrowingRecordRepository.save(borrowingRecord);
        } else throw new DataIntegrityViolationException("Book does not exist");
    }
    @Transactional
    public void updateBorrowingRecord(int bookId, int patronId, BorrowingRecord updatedBorrowingRecord) {

        BorrowingRecord borrowingRecord = borrowingRecordRepository.findBorrowingRecordByBookIdAndAndPatronId(bookId , patronId);

        if (borrowingRecord != null) {
           borrowingRecord.setReturnDate(updatedBorrowingRecord.getReturnDate() != null ? updatedBorrowingRecord.getReturnDate() : LocalDate.now());
            borrowingRecordRepository.save(borrowingRecord);
        } else throw new DataIntegrityViolationException("Record does not exist");

    }

    public BorrowingRecord mapToBorrowingRecord(BorrowingRecordDTOPost borrowingRecordDTOPost) {

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(borrowingRecordDTOPost, BorrowingRecord.class);
    }
}
