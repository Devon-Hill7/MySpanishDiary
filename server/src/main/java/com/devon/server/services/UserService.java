package com.devon.server.services;
import org.springframework.stereotype.Service;

import com.devon.server.entities.Users;
import com.devon.server.repositories.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;



@Service
public class UserService implements UserDetailsService{

    private final UserRepository userRepository;

    public UserService(UserRepository repo) {
        userRepository = repo;
    }


    public Users getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void addUser(Users user) {
        userRepository.save(user);
    }  

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Users userFromDB = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return userFromDB;
    }  
}
