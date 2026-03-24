package com.learning.springboot_projects.movieBuzz.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(
        name = "ticket",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"movie_id", "seat_no"})
        }
)
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    // Foreign key reference to Movie
    @NotNull
    @Column(name = "movie_id")
    private int movieId;

    // Foreign key reference to User
    @NotNull
    @Column(name = "user_id")
    private int userId;

    @NotNull
    @Column(name = "seat_id")
    private int seatId;

    @NotNull
    @Min(1)
    @Column(name = "price")
    private int price;

//    // Seat pattern Y-XXX (A-Z and three digits)
//    @NotBlank
//    @Pattern(regexp = "^[A-Z]-[0-9]{3}$", message = "Seat number must match pattern Y-XXX (e.g., A-123)")
//    @Column(name = "seat_no", length = 5)
//    private String seatNo;


    @NotNull
    @Min(1)
    @Column(name = "duration")
    private int duration;

    @NotNull
    @Column(name = "start_time")
    private LocalTime startTime;

    @NotNull
    @FutureOrPresent(message = "Movie date cannot be in the past")
    @Column(name = "movie_date")
    private LocalDate movieDate;

    // ---------------- Constructors ----------------
    public Ticket() {
        // No-arg constructor required by JPA
    }

    public Ticket(int movieId, int userId, int price, String seatNo,
                  String bookingStatus, String type, int duration,
                  LocalTime startTime, LocalDate movieDate) {
        this.movieId = movieId;
        this.userId = userId;
        this.price = price;
//        this.seatNo = seatNo;
        this.duration = duration;
        this.startTime = startTime;
        this.movieDate = movieDate;
    }

    // ---------------- Getters & Setters ----------------
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getMovieId() { return movieId; }
    public void setMovieId(int movieId) { this.movieId = movieId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getSeatId() { return seatId; }
    public void setSeatId(int seatId) { this.seatId = seatId; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

//    public String getSeatNo() { return seatNo; }
//    public void setSeatNo(String seatNo) { this.seatNo = seatNo; }


    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }

    public LocalDate getMovieDate() { return movieDate; }
    public void setMovieDate(LocalDate movieDate) { this.movieDate = movieDate; }

    // ---------------- ToString ----------------
    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", movieId=" + movieId +
                ", userId=" + userId +
                ", seatId=" + seatId +
                ", price=" + price +
                ", duration=" + duration +
                ", startTime=" + startTime +
                ", movieDate=" + movieDate +
                '}';
    }
}
