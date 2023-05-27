package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class UserRequestDto {
    @NotEmpty(message = "Name may not be empty")
    @Size(min = 3, max = 40, message = "Name must be between 3 and 40 characters long")
    private String name;

    @NotEmpty(message = "Surname may not be empty")
    @Size(min = 2, max = 40, message = "Surname must be between 2 and 40 characters long")
    private String surname;

    @NotEmpty(message = "Email may not be empty")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "The email is not valid")
    private String mail;

    @NotEmpty(message = "Phone may not be empty")
    @Pattern(regexp = "^\\+?[0-9]{12}$", message = "The phone is not valid")
    private String phone;

    @NotNull(message = "Birthdate may not be empty")
    @Past(message = "The date must be before the current one")
    private LocalDate birthdate;

    @NotEmpty(message = "DniNumber may not be empty")
    @Pattern(regexp = "^[0-9]{1,20}$", message = "The dniNumber is not valid")
    private String dniNumber;

    @NotEmpty(message = "Password may not be empty")
    private String password;

    @JsonIgnore
    private Long idRole;
}
