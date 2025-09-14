package com.devon.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devon.server.entities.Entry;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {

}
