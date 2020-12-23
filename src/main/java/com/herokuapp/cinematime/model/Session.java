package com.herokuapp.cinematime.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String time;

    @ManyToOne
    private Hall hall;
//    private Long hall;

    @ToString.Exclude
    @JsonBackReference
    @ManyToOne
    private DateSession dateSession;

//    @OneToOne
//    private Movie movie;
//    @OneToMany
//    private List<Ticket> tickets;

    public Session(String time, Hall hall, DateSession dateSession) {
        this.time = time;
        this.hall = hall;
        this.dateSession = dateSession;
    }

    public Session() {

    }
}
