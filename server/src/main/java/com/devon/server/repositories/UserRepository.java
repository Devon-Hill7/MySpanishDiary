package com.devon.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devon.server.entities.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    

}
