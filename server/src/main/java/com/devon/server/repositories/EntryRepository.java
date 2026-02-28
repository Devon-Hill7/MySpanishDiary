package com.devon.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devon.server.entities.Entries;
import java.util.List;

@Repository
public interface EntryRepository extends JpaRepository<Entries, Long> {

    List<Entries> findAllEntriesByUserId(int userId);
}
