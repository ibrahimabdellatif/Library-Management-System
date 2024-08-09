package cc.maids.librarymanagementsystem.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "patron")
@Data
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String phone;
    private String email;
}
