package com.example.musicapp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "songs")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "artist_id")
    private Integer artistId;

    @ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="artist_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Artist artist;

    @ManyToMany(targetEntity = User.class, mappedBy = "songs")
    private List<User> users = new ArrayList<>();

    @OneToMany(targetEntity=UserSong.class, mappedBy="song", cascade=CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<UserSong> userSongs = new ArrayList<>();

}
