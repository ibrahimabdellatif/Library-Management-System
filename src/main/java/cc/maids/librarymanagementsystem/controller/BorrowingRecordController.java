package cc.maids.librarymanagementsystem.controller;

import cc.maids.librarymanagementsystem.DTO.BorrowingRecordDTOPost;
import cc.maids.librarymanagementsystem.model.BorrowingRecord;
import cc.maids.librarymanagementsystem.service.BorrowingRecordService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Validated
public class BorrowingRecordController {

    private final BorrowingRecordService borrowingRecordService;

    public BorrowingRecordController(BorrowingRecordService borrowingRecordService) {
        this.borrowingRecordService = borrowingRecordService;
    }


    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<String> addBorrowingRecord(@PathVariable int bookId, @PathVariable int patronId, @Valid @RequestBody BorrowingRecordDTOPost borrowingRecordDTOPost) {
        ModelMapper modelMapper = new ModelMapper();
        BorrowingRecord borrowingRecord = modelMapper.map(borrowingRecordDTOPost, BorrowingRecord.class);
        try {
            borrowingRecordService.addBorrowingRecord(bookId, patronId, borrowingRecord);
            return ResponseEntity.noContent().build();

        } catch (DataIntegrityViolationException ex) {
            System.out.println(ex.fillInStackTrace().toString());
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("Invalid book ID: The book does not exist.");
        }
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<String> updateBorrowingRecord(@PathVariable int bookId, @PathVariable int patronId, @Valid @RequestBody BorrowingRecordDTOPost borrowingRecordDTOPost) {
        BorrowingRecord borrowingRecord = borrowingRecordService.mapToBorrowingRecord(borrowingRecordDTOPost);

        try {
            borrowingRecordService.updateBorrowingRecord(bookId, patronId, borrowingRecord);
            return ResponseEntity.noContent().build();
        } catch (DataIntegrityViolationException ex) {
            System.out.println(ex.fillInStackTrace().toString());
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("Invalid book ID or patron ID.");
        }
    }
}

