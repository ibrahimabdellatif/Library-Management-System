package cc.maids.librarymanagementsystem.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookDTOPut {

    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    private String title;

    @Size(min = 1, max = 255, message = "Author must be between 1 and 255 characters")
    private String author;

    @NotNull(message = "Year must be not null")
    private int publicationYear;

    @Size(min = 10, max = 13, message = "ISBN must be between 10 and 13 digits")
    private String isbn;
}
