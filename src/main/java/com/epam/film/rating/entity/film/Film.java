package com.epam.film.rating.entity.film;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Film implements Serializable {
    private int id;
    private int productionYear;
    private String name;
    private String description;
    private double filmRating;
    private int reviewAmount;
    private String type;
    private String ageRating;
    private List<String> genre;
    private List<String> countryOfOrigin;
    private List<String> poster;
    private List<String> trailer;

    public Film() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getFilmRating() {
        return filmRating;
    }

    public void setFilmRating(double filmRating) {
        this.filmRating = filmRating;
    }

    public int getReviewAmount() {
        return reviewAmount;
    }

    public void setReviewAmount(int reviewAmount) {
        this.reviewAmount = reviewAmount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(String ageRating) {
        this.ageRating = ageRating;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    public List<String> getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(List<String> countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public List<String> getPoster() {
        return poster;
    }

    public void setPoster(List<String> poster) {
        this.poster = poster;
    }

    public List<String> getTrailer() {
        return trailer;
    }

    public void setTrailer(List<String> trailer) {
        this.trailer = trailer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Film)) return false;
        Film film = (Film) o;
        return getId() == film.getId() && getProductionYear() == film.getProductionYear() && Double.compare(film.getFilmRating(), getFilmRating()) == 0 && getReviewAmount() == film.getReviewAmount() && getName().equals(film.getName()) && getDescription().equals(film.getDescription()) && getType().equals(film.getType()) && getAgeRating().equals(film.getAgeRating()) && getGenre().equals(film.getGenre()) && getCountryOfOrigin().equals(film.getCountryOfOrigin()) && getPoster().equals(film.getPoster()) && getTrailer().equals(film.getTrailer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProductionYear(), getName(), getDescription(), getFilmRating(), getReviewAmount(), getType(), getAgeRating(), getGenre(), getCountryOfOrigin(), getPoster(), getTrailer());
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", productionYear=" + productionYear +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", filmRating=" + filmRating +
                ", reviewAmount=" + reviewAmount +
                ", type='" + type + '\'' +
                ", ageRating='" + ageRating + '\'' +
                ", genre=" + genre +
                ", countryOfOrigin=" + countryOfOrigin +
                ", poster=" + poster +
                ", trailer=" + trailer +
                '}';
    }
}