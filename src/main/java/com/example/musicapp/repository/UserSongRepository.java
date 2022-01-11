package com.example.musicapp.repository;

import com.example.musicapp.domain.UserSong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserSongRepository extends JpaRepository<UserSong, Integer> {

    @Query(value = "SELECT us FROM UserSong us WHERE us.id = :id")
    Optional<UserSong> getByIdMy(Integer id);
//    UserSong findByIdMy(Integer id);

    @Query(value = "SELECT us FROM UserSong us INNER JOIN User u ON us.userId = u.id WHERE u.name = :userName")
    Optional<List<UserSong>> filterByUserName(String userName);

    @Query(value = "SELECT us FROM UserSong us INNER JOIN Song s ON us.songId = s.id WHERE s.name = :songName")
    Optional<List<UserSong>> filterBySongName(String songName);

    @Modifying
    @Query(value = "DELETE FROM UserSong us WHERE us.id = :id")
    void deleteById(Integer id);

}
