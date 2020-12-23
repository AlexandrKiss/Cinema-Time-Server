package com.herokuapp.cinematime.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//@Entity
@Data @AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonManagedReference
    private Session session;
    @OneToOne
    private Seat seat;

    @ManyToOne
    @JsonManagedReference
    private Order order;
}
