package cc.maids.librarymanagementsystem.model;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "book")
@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String author;
    private int publicationYear;
    private String ISBN;


}
