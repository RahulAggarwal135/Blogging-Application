package com.demo.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {


    private int id;
    @NotEmpty
    @Size(min =4, message = "User must be of 4 chars !!!")
    private String name;
    @Email(message = "invalid email")
    private String email;
    @NotNull
    @Size(min=5,max = 10,message = "must be greater than 5 and less than 10")
    private String password;
    @NotNull
    private String about;

}
