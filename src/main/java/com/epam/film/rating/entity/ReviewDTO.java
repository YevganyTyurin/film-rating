package com.epam.film.rating.entity;

import java.io.Serializable;
import java.util.Objects;

public class ReviewDTO implements Serializable {
    private int id;
    private String review;
    private int mark;
    private int likesAmount;
    private int dislikesAmount;
    private String nickname;
    private double rating;
    private String avatarImage;
    private String status;

    public ReviewDTO() {}

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(String avatarImage) {
        this.avatarImage = avatarImage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReviewDTO)) return false;
        ReviewDTO reviewDTO = (ReviewDTO) o;
        return getId() == reviewDTO.getId() && getMark() == reviewDTO.getMark() && getLikesAmount() == reviewDTO.getLikesAmount() && getDislikesAmount() == reviewDTO.getDislikesAmount() && Double.compare(reviewDTO.getRating(), getRating()) == 0 && getReview().equals(reviewDTO.getReview()) && getNickname().equals(reviewDTO.getNickname()) && getAvatarImage().equals(reviewDTO.getAvatarImage()) && getStatus().equals(reviewDTO.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getReview(), getMark(), getLikesAmount(), getDislikesAmount(), getNickname(), getRating(), getAvatarImage(), getStatus());
    }

    @Override
    public String toString() {
        return "ReviewDTO{" +
                "id=" + id +
                ", review='" + review + '\'' +
                ", mark=" + mark +
                ", likesAmount=" + likesAmount +
                ", dislikesAmount=" + dislikesAmount +
                ", nickname='" + nickname + '\'' +
                ", rating=" + rating +
                ", avatarImage='" + avatarImage + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
