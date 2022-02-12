package com.epam.film.rating.dao;

import com.epam.film.rating.dao.exception.DAOException;
import com.epam.film.rating.entity.ReviewDTO;
import com.epam.film.rating.entity.review.Review;
import com.epam.film.rating.entity.review.ReviewApproval;

import java.sql.SQLException;
import java.util.List;

public interface ReviewDAO {
    List<Review> getReviewById(int filmId, int userId) throws DAOException;

    List<ReviewDTO> getReviewsByFilmId(int filmId) throws DAOException;

//    ReviewApproval getReviewApprovalById(int userId, int reviewId) throws DAOException;

    int getLikesAmountById(int reviewId) throws DAOException;

//    boolean updateReviewApprovalLike(boolean isLiked, int userId, int reviewId) throws DAOException;

    boolean updateLikesAmountById(int likesAmount, int reviewId) throws DAOException;

    int getDislikesAmountById(int reviewId) throws DAOException;

    boolean updateDislikesAmountById(int dislikesAmount, int reviewId) throws DAOException;

//    boolean updateReviewApprovalDislike(boolean isDisliked, int userId, int reviewId) throws DAOException;

//    boolean addReviewApproval (int userId, int reviewId, boolean isLiked, boolean isDisliked) throws DAOException;

    boolean addReview(Review review, int filmId) throws DAOException;

    Review getReviewByReviewId(int reviewId) throws DAOException;

    boolean updateReview(int reviewId, String review) throws DAOException;

    boolean deleteReview (int id) throws DAOException;

    int getReviewAmount(String year, String age_rating, String film_type, String []genres, String filmId, String userId) throws DAOException;
}
