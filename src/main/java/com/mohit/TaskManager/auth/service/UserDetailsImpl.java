package com.mohit.TaskManager.auth.service;

import com.mohit.TaskManager.auth.user.User;
import com.mohit.TaskManager.auth.userrepository.UserRepository;
import com.mohit.TaskManager.exceptions.UserNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsImpl implements UserDetailsService {
   @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findById(username).orElseThrow(()->new UserNotFound("user not found "+ username));
        return new CustomUserDetails(user);
    }
}
