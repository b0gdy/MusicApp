package com.example.musicapp.repository;

import com.example.musicapp.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {

    Optional<Song> findByName(String name);

    @Query(value = "SELECT s FROM Song s INNER JOIN Artist a ON s.artistId = a.id WHERE a.name = :artistName")
    Optional<List<Song>> filterByArtistName(String artistName);
    
    @Modifying
    @Query(value = "DELETE FROM Song s WHERE s.name = :name")
    void deleteByName(String name);

    @Modifying
    @Query(value = "UPDATE Song s SET s.name = :name2 WHERE s.name = :name1")
    void update(String name1, String name2);

}
