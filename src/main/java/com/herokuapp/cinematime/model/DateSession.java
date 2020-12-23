package com.herokuapp.cinematime.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class DateSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date;
    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, mappedBy = "dateSession")
    @Fetch(FetchMode.SUBSELECT)
    private List<Session> sessions;

    @ToString.Exclude
    @JsonBackReference
    @ManyToOne
    private Movie movie;

    public DateSession(String date, Movie movie) {
        this.date = date;
        this.movie = movie;
    }

    public DateSession() {

    }
}
