package com.epam.film.rating.service;

import com.epam.film.rating.entity.review.Review;
import com.epam.film.rating.service.exception.ServiceException;

import java.util.List;

public interface ReviewService {

    boolean addReview(Review review, int filmId) throws ServiceException;

    int getLikesAmountById(int reviewId) throws ServiceException;

    boolean updateLikesAmountById(int likesAmount, int reviewId) throws ServiceException;

    List<Review> getReviewById(int filmId, int userId) throws ServiceException;

    Review getReviewByReviewId(int reviewId) throws ServiceException;

    int getDislikesAmountById(int reviewId) throws ServiceException;

    boolean updateDislikesAmountById(int dislikesAmount, int reviewId) throws ServiceException;

    boolean updateReview(int reviewId, String review) throws ServiceException;

    boolean deleteReview (int id) throws ServiceException;

    int getReviewAmount(String year, String age_rating, String film_type, String []genres, String filmId, String userId) throws ServiceException;
}
