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
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_addresses",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "address_id", referencedColumnName = "id")}
    )
//    @OneToOne(targetEntity=Address.class, mappedBy="user",cascade=CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Address address;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_songs",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "song_id", referencedColumnName = "id")}
    )
    private List<Song> songs = new ArrayList<>();

    @OneToMany(targetEntity=UserSong.class, mappedBy="user",cascade=CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<UserSong> userSongs = new ArrayList<>();

}
