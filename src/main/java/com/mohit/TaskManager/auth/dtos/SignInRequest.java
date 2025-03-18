package com.mohit.TaskManager.auth.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Builder
@Getter
@Setter
public class SignInRequest {
    private String username;
    private String email;
    private String password;
}
