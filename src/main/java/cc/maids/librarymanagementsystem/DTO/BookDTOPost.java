package cc.maids.librarymanagementsystem.DTO;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class BookDTOPost {

    @NotNull(message = "Id is required")
    private int id;
    @NotBlank(message = "Title is required")
    @Size(min = 1 , max = 255 , message = "Title must be between 1 and 255 characters")
    private String title;
    @NotBlank(message = "Author is required")
    @Size(min = 1 , max = 255 , message = "Author must be between 1 and 255 characters")
    private String author;

    @NotNull(message = "Publication Year is required")
    @Min(1000)
    @Max(2050)
    private int publicationYear;

    @Size(min = 10 , max = 13 , message = "ISBN must be between 10 and 13 digits")
    private String isbn;

}
