package com.mohit.TaskManager.auth.service;

import com.mohit.TaskManager.auth.dtos.LoginRequest;
import com.mohit.TaskManager.auth.dtos.SignInRequest;
import com.mohit.TaskManager.auth.user.User;
import com.mohit.TaskManager.auth.userrepository.UserRepository;
import org.hibernate.engine.jdbc.spi.JdbcWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
   @Autowired
   private UserRepository userRepository;
   @Autowired private AuthenticationManager authenticationManager;
   @Autowired private JwtService jwtService;
   @Autowired
   private PasswordEncoder passwordEncoder;


   public boolean isAlreadyAUser(String email){
       return userRepository.existsById(email);
   }
    public void signup(SignInRequest signInRequest) {
        signInRequest.setPassword(passwordEncoder.encode(signInRequest.getPassword()));
        User user= User.builder()
                .username(signInRequest.getUsername())
                .password(signInRequest.getPassword())
                .email(signInRequest.getEmail())
                .build();
        userRepository.save(user);
    }
    public String login(LoginRequest loginRequest){

        try{
            Authentication auth=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));

            String  token =jwtService.generateToken(auth.getName());

            return token;
        }
        catch (BadCredentialsException ex){
            System.out.println("message " +ex.getMessage());
            return "invalid credentials";
        }
    }

}
