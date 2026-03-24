package com.learning.springboot_projects.movieBuzz.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "Movie name cannot be blank")
    @Size(max = 45, message = "Movie name cannot exceed 45 characters")
    @Column(name = "name", length = 45)
    private String name;

    @NotBlank(message = "Genre cannot be blank")
    @Size(max = 45, message = "Genre cannot exceed 45 characters")
    @Column(name = "genre", length = 45)
    private String genre;

    @NotNull(message = "Rating is required")
    @Min(value = 0, message = "Rating cannot be less than 0")
    @Max(value = 10, message = "Rating cannot be greater than 10")
    @Column(name = "rating")
    private Integer rating;

    @NotNull(message = "Money collection is required")
    @PositiveOrZero(message = "Money collection cannot be negative")
    @Column(name = "money_collection_INR")
    private Long moneyCollectionInr;

    @NotBlank(message = "Timings cannot be blank")
    @Size(max = 100, message = "Timings cannot exceed 100 characters")
    @Column(name = "timings", length = 100)
    private String timings;

    // ---------------- Constructors ----------------
    public Movie() {
        // No-arg constructor for JPA
    }

    public Movie(String name, String genre, Integer rating,
                 Long moneyCollectionInr, String timings) {
        this.name = name;
        this.genre = genre;
        this.rating = rating;
        this.moneyCollectionInr = moneyCollectionInr;
        this.timings = timings;
    }

    // ---------------- Getters & Setters ----------------
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }

    public Long getMoneyCollectionInr() { return moneyCollectionInr; }
    public void setMoneyCollectionInr(Long moneyCollectionInr) { this.moneyCollectionInr = moneyCollectionInr; }

    public String getTimings() { return timings; }
    public void setTimings(String timings) { this.timings = timings; }

    // ---------------- ToString ----------------
    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", rating=" + rating +
                ", moneyCollectionInr=" + moneyCollectionInr +
                ", timings='" + timings + '\'' +
                '}';
    }
}
