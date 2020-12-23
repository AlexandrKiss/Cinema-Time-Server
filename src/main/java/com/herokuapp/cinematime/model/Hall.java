package com.herokuapp.cinematime.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @JsonBackReference
    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "hall")
    private List<Seat> seats;

//    @JsonIgnore
//    @ToString.Exclude
//    @JsonBackReference
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hall")
//    private Set<Session> sessions;
}
