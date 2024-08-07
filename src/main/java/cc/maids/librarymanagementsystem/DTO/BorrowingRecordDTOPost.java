package cc.maids.librarymanagementsystem.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
public class BorrowingRecordDTOPost {

    @NotNull
    private int recordID;

    @NotNull(message = "Must add patron id")
    private int PatronID;

    private LocalDate borrowDate;

    //    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Return date must be in the format yyyy-mm-dd")
    private LocalDate returnDate;


}
