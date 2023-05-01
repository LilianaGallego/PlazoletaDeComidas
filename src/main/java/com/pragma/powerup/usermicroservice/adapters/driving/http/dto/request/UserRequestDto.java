package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@Getter
public class UserRequestDto {
    @NotEmpty(message = "Name may not be empty")
    @Size(min = 3, max = 40, message = "Name must be between 3 and 40 characters long")
    private String name;

    @NotEmpty(message = "Surname may not be empty")
    @Size(min = 2, max = 40, message = "Surname must be between 2 and 40 characters long")
    private String surname;

    @NotEmpty(message = "Email may not be empty")
    @Email(message = "The email is not valid")
    private String mail;

    @NotEmpty(message = "Phone may not be empty")
    @Pattern(regexp = "^\\+?[0-9]{1,12}$", message = "The phone is not valid")
    private String phone;

    @NotEmpty(message = "Birthdate may not be empty")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date birthdate;

    @NotEmpty(message = "DniNumber may not be empty")
    @Pattern(regexp = "^[0-9]{1,10}$", message = "The dniNumber is not valid")
    private String dniNumber;
    @NotEmpty(message = "Password may not be empty")
    private String password;
    @NotEmpty(message = "IdRole may not be empty")
    @Pattern(regexp = "^[0-9]{1,4}$", message = "The idRole is not valid")
    private Long idRole;
}
