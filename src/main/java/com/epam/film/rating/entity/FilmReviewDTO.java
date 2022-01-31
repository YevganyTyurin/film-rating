package com.epam.film.rating.entity;

import java.util.Objects;

public class FilmReviewDTO {
    private int reviewId;
    private String review;
    private String filmName;
    private int userId;
//    private boolean isBanned;

    public FilmReviewDTO() {}

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

//    public boolean getIsBanned() {
//        return isBanned;
//    }
//
//    public void setBanned(boolean banned) {
//        isBanned = banned;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FilmReviewDTO)) return false;
        FilmReviewDTO that = (FilmReviewDTO) o;
        return getReviewId() == that.getReviewId() && getReview().equals(that.getReview()) && getFilmName().equals(that.getFilmName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReviewId(), getReview(), getFilmName());
    }

    @Override
    public String toString() {
        return "FilmReviewDTO{" +
                "reviewId=" + reviewId +
                ", review='" + review + '\'' +
                ", filmName='" + filmName + '\'' +
                '}';
    }
}
