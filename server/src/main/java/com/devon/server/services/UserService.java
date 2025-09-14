package com.devon.server.services;
import org.springframework.stereotype.Service;

import com.devon.server.entities.User;
import com.devon.server.repositories.UserRepository;;


@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository repo) {
        userRepository = repo;
    }


    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void addUser(User user) {
        userRepository.save(user);
    }  

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
