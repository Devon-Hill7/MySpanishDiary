package com.devon.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devon.server.entities.Entries;

@Repository
public interface EntryRepository extends JpaRepository<Entries, Long> {

}
