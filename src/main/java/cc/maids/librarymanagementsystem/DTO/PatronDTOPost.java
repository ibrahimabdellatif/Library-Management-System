package cc.maids.librarymanagementsystem.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PatronDTOPost {

    @NotNull
    private int id;

    @NotBlank(message = "Name cannot be null")
    @Size(min = 2 , max = 255 , message = "Name must be between 2 and 255 characters")
    private String name;


    @NotBlank(message = "Name cannot be null")
    @Email(message = "You must add a valid email")
    private String email;


    @NotBlank(message = "You must add a phone number")
    @Size(min = 3 , max = 15 , message = "phone should be between 3 and 15 digits")
    private String phone;
}
