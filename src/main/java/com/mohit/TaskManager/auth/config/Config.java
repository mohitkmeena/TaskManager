package com.mohit.TaskManager.auth.config;

import com.mohit.TaskManager.auth.service.UserDetailsImpl;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Config {
    @Autowired private UserDetailsImpl userDetails;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder(12);
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authp=new DaoAuthenticationProvider();
        authp.setPasswordEncoder(passwordEncoder());
        authp.setUserDetailsService(userDetails);
        return  authp;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }
}
