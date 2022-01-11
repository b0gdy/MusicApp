package com.example.musicapp.repository;

import com.example.musicapp.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Modifying
    @Query(value = "DELETE FROM Address a WHERE a.id = :id")
    void deleteById(Integer id);

    @Modifying
    @Query(value = "UPDATE Address a SET a.name = :name WHERE a.id = :id")
    void update(Integer id, String name);
    
}
