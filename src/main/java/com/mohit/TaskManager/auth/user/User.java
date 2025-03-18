package com.mohit.TaskManager.auth.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "user")
@Getter
@Setter
@RequiredArgsConstructor
@Builder
public class User {
    private String username;
    @Id
    @Email(message = "please enter a valid email")
    private String email;
    @Size(min = 6 ,message = "password should be greater than 6")
    private String password;
}
