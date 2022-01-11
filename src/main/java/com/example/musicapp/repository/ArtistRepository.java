package com.example.musicapp.repository;

import com.example.musicapp.domain.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {

    Optional<Artist> findByName(String name);

    @Modifying
    @Query(value = "DELETE FROM Artist a WHERE a.name = :name")
    void deleteByName(String name);

    @Modifying
    @Query(value = "UPDATE Artist a SET a.name = :name2 WHERE a.name = :name1")
    void update(String name1, String name2);
    
}
