package com.epam.film.rating.service;

import com.epam.film.rating.dao.exception.DAOException;
import com.epam.film.rating.entity.ReviewDTO;
import com.epam.film.rating.entity.film.Film;
import com.epam.film.rating.entity.review.Review;
import com.epam.film.rating.entity.review.ReviewApproval;
import com.epam.film.rating.entity.user.User;
import com.epam.film.rating.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public interface Service {

    List<ReviewDTO> getReviewsByFilmId(int filmId) throws ServiceException;

//    List<Film> getFilmsByParameters(String year, String age_rating, String film_type, String genres[], int startFromRecordNumber) throws ServiceException;

//    int getLikesAmountById(int reviewId) throws ServiceException;

//    ReviewApproval getReviewApprovalById(int userId, int reviewId) throws ServiceException;

//    Film getFilmById(int id) throws ServiceException;

//    boolean updateReviewApprovalLike(boolean isLiked, int userId, int reviewId) throws ServiceException;

//    boolean updateLikesAmountById(int likesAmount, int reviewId) throws ServiceException;

//    boolean addReviewApproval (int userId, int reviewId, boolean isLiked, boolean isDisliked) throws ServiceException;

//    int getDislikesAmountById(int reviewId) throws ServiceException;

//    boolean updateReviewApprovalDislike(boolean isDisliked, int userId, int reviewId) throws ServiceException;

//    boolean updateDislikesAmountById(int dislikesAmount, int reviewId) throws ServiceException;

//    boolean addReview(Review review, int filmId) throws ServiceException;

//    User login(String login, String password) throws ServiceException;

//    int getFilmAmount(String year, String age_rating, String film_type, String genres[]) throws ServiceException;

//    Review getReviewByReviewId(int reviewId) throws ServiceException;
}
