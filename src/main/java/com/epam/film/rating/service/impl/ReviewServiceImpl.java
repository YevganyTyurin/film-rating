package com.epam.film.rating.service.impl;

import com.epam.film.rating.dao.DAOFactory;
import com.epam.film.rating.dao.ReviewDAO;
import com.epam.film.rating.dao.exception.DAOException;
import com.epam.film.rating.entity.review.Review;
import com.epam.film.rating.service.ReviewService;
import com.epam.film.rating.service.exception.ServiceException;

import java.util.List;

public class ReviewServiceImpl implements ReviewService {
    @Override
    public boolean addReview(Review review, int filmId) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        ReviewDAO reviewDAO = factory.getReviewDAO();
        try {
            return reviewDAO.addReview(review, filmId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getLikesAmountById(int reviewId) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        ReviewDAO reviewDAO = factory.getReviewDAO();
        try {
            return reviewDAO.getLikesAmountById(reviewId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateLikesAmountById(int likesAmount, int reviewId) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        ReviewDAO reviewDAO = factory.getReviewDAO();
        try {
            return reviewDAO.updateLikesAmountById(likesAmount, reviewId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Review> getReviewById(int filmId, int userId) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        ReviewDAO reviewDAO = factory.getReviewDAO();
        try {
            return reviewDAO.getReviewById(filmId, userId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Review getReviewByReviewId(int reviewId) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        ReviewDAO reviewDAO = factory.getReviewDAO();
        try {
            return reviewDAO.getReviewByReviewId(reviewId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getDislikesAmountById(int reviewId) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        ReviewDAO reviewDAO = factory.getReviewDAO();
        try {
            return reviewDAO.getDislikesAmountById(reviewId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateDislikesAmountById(int dislikesAmount, int reviewId) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        ReviewDAO reviewDAO = factory.getReviewDAO();
        try {
            return reviewDAO.updateDislikesAmountById(dislikesAmount, reviewId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
