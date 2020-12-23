package com.herokuapp.cinematime.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int num;
    private int row;

    @ToString.Exclude
    @JsonBackReference
    @ManyToOne
    private Hall hall;

    @ManyToOne
    private Type type;

    public Seat(int num, int row, Hall hall, Type type) {
        this.num = num;
        this.row = row;
        this.hall = hall;
        this.type = type;
    }

    public Seat() {

    }
}
