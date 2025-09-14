package com.devon.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devon.server.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    

}
