package com.kenpxrk.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {
    @NotEmpty()
    @Size(min = 4, max = 25, message = "Username must be between 4 and 25 symbols")
    private String username;

    @Email(message = "Invalid email address")
    @NotEmpty
    private String email;

    @NotNull
    @Min(18)
    @Max(110)
    private Integer age;
}
