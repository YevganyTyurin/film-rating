package com.epam.film.rating.entity.review;

import java.io.Serializable;
import java.util.Objects;

public class Review implements Serializable {
    private int id;
    private String review;
    private int mark;
    private int likesAmount;
    private int dislikesAmount;
    private int userId;

    public Review() {}

    public Review(int id, String review, int mark, int likesAmount,
                  int dislikesAmount) {
        this.id = id;
        this.review = review;
        this.mark = mark;
        this.likesAmount = likesAmount;
        this.dislikesAmount = dislikesAmount;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getLikesAmount() {
        return likesAmount;
    }

    public void setLikesAmount(int likesAmount) {
        this.likesAmount = likesAmount;
    }

    public int getDislikesAmount() {
        return dislikesAmount;
    }

    public void setDislikesAmount(int dislikesAmount) {
        this.dislikesAmount = dislikesAmount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review)) return false;
        Review review1 = (Review) o;
        return getId() == review1.getId() && getMark() == review1.getMark() && getLikesAmount() == review1.getLikesAmount() && getDislikesAmount() == review1.getDislikesAmount() && getUserId() == review1.getUserId() && Objects.equals(getReview(), review1.getReview());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getReview(), getMark(), getLikesAmount(), getDislikesAmount(), getUserId());
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", review='" + review + '\'' +
                ", mark=" + mark +
                ", likesAmount=" + likesAmount +
                ", dislikesAmount=" + dislikesAmount +
                ", userId=" + userId +
                '}';
    }
}

