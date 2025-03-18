package com.mohit.TaskManager.auth.controller;

import com.mohit.TaskManager.auth.dtos.LoginRequest;
import com.mohit.TaskManager.auth.dtos.SignInRequest;
import com.mohit.TaskManager.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
 @Autowired private AuthService authService;
 @PostMapping("/signup")
 public  String signUp(@RequestBody SignInRequest signInRequest){
     boolean isSignedUp=authService.isAlreadyAUser(signInRequest.getEmail());
     if(isSignedUp){
         return "Username already registered";
     }
     authService.signup(signInRequest);
     return "Sign up successful";
 }
    @PostMapping("/login")
    public  String login(@RequestBody LoginRequest loginRequest){

        return authService.login(loginRequest);
    }
    @GetMapping("/ping")
    public ResponseEntity<String> ping(){
        System.out.println("in ping ");
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        if(auth!=null&&auth.isAuthenticated()){
            return ResponseEntity.ok(auth.getName());
        }
        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("unauthorized");
    }

}
