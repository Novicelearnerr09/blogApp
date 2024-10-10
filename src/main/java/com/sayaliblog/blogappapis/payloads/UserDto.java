package com.sayaliblog.blogappapis.payloads;

import jakarta.validation.constraints.*;
//import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private  Integer id;
    @NotEmpty
    @Size(min = 4, message="Username must be min of 4 characters")

    private  String name;
    @Email(message = "Email address is not valid !!")
    private  String email;
    @NotEmpty
    @Size(min=3,max=10,message = "password be minof 3 chars and max of 10 chars")
    @Pattern(regexp="^[0-9]{10}$",message="Invalid Password")
    private  String password;
    @NotEmpty
    private  String about;
}
