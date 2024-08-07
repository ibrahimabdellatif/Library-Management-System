package cc.maids.librarymanagementsystem.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PatronDTOPut {


    @Size(min = 2 , max = 255 , message = "Name must be between 2 and 255 characters")
    private String name;

    @Email(message = "You must add a valid email")
    private String email;

    @Size(min = 3 , max = 15 , message = "phone should be between 3 and 15 digits")
    private String phone;
}
