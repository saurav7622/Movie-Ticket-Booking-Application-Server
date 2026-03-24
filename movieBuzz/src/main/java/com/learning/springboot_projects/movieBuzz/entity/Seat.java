package com.learning.springboot_projects.movieBuzz.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "seat")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank
    @Pattern(regexp = "^[A-Z]-[0-9]{3}$", message = "Seat number must match pattern Y-XXX (e.g., A-123)")
    @Column(name = "seat_no", length = 5)
    private String seatNo;

    @NotNull
    @Pattern(regexp = "CLASSIC|PRIME", message = "Type must be CLASSIC or PRIME")
    @Column(name = "type")
    private String type;

    //constuctors
    public Seat(){

    }

    public Seat(String seatNo) {
        this.seatNo = seatNo;
        this.type = type;
    }

    //setters and getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    //toString method


    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", seatNo='" + seatNo +
                ", type='" + type + '\'' +
                '}';
    }
}
