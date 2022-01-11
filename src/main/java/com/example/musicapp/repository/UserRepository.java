package com.example.musicapp.repository;

import com.example.musicapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByName(String name);

    @Modifying
    @Query(value = "DELETE FROM User u WHERE u.name = :name")
    void deleteByName(String name);

    @Modifying
    @Query(value = "UPDATE User u SET u.name = :name2 WHERE u.name = :name1")
    void update(String name1, String name2);

}
