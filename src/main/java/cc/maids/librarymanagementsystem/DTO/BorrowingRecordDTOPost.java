package cc.maids.librarymanagementsystem.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BorrowingRecordDTOPost {

    @NotNull
    private int recordID;

    private LocalDate borrowDate;

    //    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Return date must be in the format yyyy-mm-dd")
    private LocalDate returnDate;


}
