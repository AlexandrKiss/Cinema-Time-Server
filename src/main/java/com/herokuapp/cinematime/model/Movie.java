package com.herokuapp.cinematime.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.jsoup.nodes.Element;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String genre;
    private Integer duration;
    private String img;
    @Lob
    private String description;
    private String trailer;
    @Transient
    private Element data;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "movie")
    @Fetch(FetchMode.SUBSELECT)
    private List<DateSession> dateSessions;

    public Movie(String name, String genre, int duration, String img, String description, String trailer) {
        this.name = name;
        this.genre = genre;
        this.duration = duration;
        this.img = img;
        this.description = description;
        this.trailer = trailer;
    }

    public Movie() {

    }
}
