package cc.maids.librarymanagementsystem.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "borrowing_record")
@Data
public class BorrowingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private int recordId;
    @Column(name = "borrow_date")
    private LocalDate borrowDate;
    @Column(name = "return_date")
    private LocalDate returnDate;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "patron_id")
    private Patron patron;


}
