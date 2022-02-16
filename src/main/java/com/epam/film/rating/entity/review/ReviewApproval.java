package com.epam.film.rating.entity.review;

import java.io.Serializable;
import java.util.Objects;

public class ReviewApproval implements Serializable {
    private boolean isLiked;
    private boolean isDisliked;

    public ReviewApproval() {}

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        this.isLiked = liked;
    }

    public boolean isDisliked() {
        return isDisliked;
    }

    public void setDisliked(boolean disliked) {
        isDisliked = disliked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReviewApproval)) return false;
        ReviewApproval that = (ReviewApproval) o;
        return isLiked() == that.isLiked() && isDisliked() == that.isDisliked();
    }

    @Override
    public int hashCode() {
        return Objects.hash(isLiked(), isDisliked());
    }

    @Override
    public String toString() {
        return "ReviewApproval{" +
                "idLiked=" + isLiked +
                ", isDisliked=" + isDisliked +
                '}';
    }
}
