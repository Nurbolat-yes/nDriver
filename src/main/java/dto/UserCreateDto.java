package dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class UserCreateDto {

    @Valid

    @NotNull(message = "Username can not be null")
    @NotEmpty(message = "Username can not be empty")
    @Size(min = 3, max = 128,message = "Username should be between 3 and 128")
    private String username;

    @NotNull(message = "Password can not be null")
    @NotEmpty(message = "Password can not be empty")
    @Size(min = 8, message = "Password must contain minimum 8 symbol")
    private String password;
}
